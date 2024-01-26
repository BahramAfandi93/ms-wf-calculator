package com.construe.waterflowcalc.service;

import com.construe.waterflowcalc.dto.PipeRequestDto;
import com.construe.waterflowcalc.dto.PipeResponseDto;
import com.construe.waterflowcalc.mapper.PipeMapper;
import com.construe.waterflowcalc.repository.PipeRepository;
import com.construe.waterflowcalc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PipeServiceImpl implements PipeService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PipeRepository pipeRepository;
    private final PipeMapper pipeMapper;

    @Override
    public PipeResponseDto addPipe(Long userId, PipeRequestDto pipeRequestDto) {
        log.info("Adding new pipe");

        Pipe pipe = pipeMapper.pipeRequestDtoToPipe(pipeRequestDto);
        User user = userService.findUserById(userId);

        String location = pipe.getLocation();
        String projectName = pipe.getProjectName();
        String chainage = pipe.getChainage();

        if (pipeRepository.findByLocationAndProjectNameAndChainage(location, projectName, chainage).isPresent()) {
            pipe = pipeRepository.findByLocationAndProjectNameAndChainage(location, projectName, chainage).get();
        } else {
            pipe = pipeFieldSetter(pipe);
            pipe.setUser(user);
            pipeRepository.save(pipe);
            user.getPipes().add(pipe);
            userRepository.save(user);
            userService.updateUser(userId, userMapper.userToUserRequestDto(user));
        }
        return pipeMapper.pipeToPipeResponseDto(pipe);
    }

    @Override
    public List<PipeResponseDto> addPipeList(List<PipeRequestDto> pipeRequestDtoList) {
        log.info("Adding new pipe list");

        return null;
    }

    @Override
    public PipeResponseDto findById(Long id) {
        log.info("Finding by id");

        return pipeMapper.pipeToPipeResponseDto(pipeRepository.findById(id).get());
    }

    @Override
    public PipeResponseDto findByLocationAndProjectNameAndChainage(
    String location, String projectName, String chainage) {
        log.info("Finding by location and project name and chainage");

        return pipeMapper.pipeToPipeResponseDto(
                pipeRepository.findByLocationAndProjectNameAndChainage(location, projectName, chainage).get());
    }

    public static double getCentralAngle(double diameter, int percent) {
        log.info("Getting central angle");

        double h;
        double r = diameter / 2;

        if (percent < 50) {
            h = diameter * percent * 0.01;
        } else {
            h = diameter - diameter * percent * 0.01;
        }

        return 2 * Math.acos((r - h) / r);
    }

    public static double getWettedPerimeter(double diameter, int percent) {
        log.info("Getting wetted perimeter");

        double wettedPerimeter;
        double angle = getCentralAngle(diameter, percent);
        if (percent < 50) {
            wettedPerimeter = diameter / 2 * angle;
        } else {
            wettedPerimeter = 2 * Math.PI * (diameter / 2) - diameter / 2 * angle;
        }
        return wettedPerimeter;
    }

    public static double getFlowArea(double diameter, int percent) {
        log.info("Getting flow area");

        double radius = diameter / 2;
        double angle = getCentralAngle(diameter, percent);
        double circularSegmentArea = (Math.pow(radius, 2) * (angle - Math.sin(angle))) / 2;


        double flowArea;
        if (percent < 50) {
            flowArea = circularSegmentArea;
        } else {
            flowArea = Math.PI * Math.pow(radius, 2) - circularSegmentArea;

        }
        return flowArea;
    }

    public double getHydraulicRadius(double diameter, int percent) {
        log.info("Getting hydraulic radius");

        double flowArea = getFlowArea(diameter, percent);
        double wettedPerimeter = getWettedPerimeter(diameter, percent);
        double hydraulicRadius = flowArea / wettedPerimeter;
        return hydraulicRadius;
    }

    public double getMinimumSlope(double diameter) {
        log.info("Getting minimum slope");

        diameter *= 1000;
        return (1 / diameter) * 100;
    }

    public double getValueN(String material) {
        log.info("Getting value N");

        switch (material) {
            case "demirbeton":
                return 0.0014;
            case "demir":
                return 0.0015;
            case "kerpic":
                return 0.0016;
            case "keramik":
                return 0.0017;
            case "asbessement":
                return 0.0018;
            case "cugun":
                return 0.0019;
            case "polad":
                return 0.015;
            case "PVX":
                return 0.0021;
            case "asfaltbeton":
                return 0.0022;
            case "torpaqkanal":
                return 0.0023;
            case "cinqilkanal":
                return 0.0024;
            default:
                return 0;
        }
    }

    public double getWaterSpeed(double diameter, int percent, String material, double slope) {
        log.info("Getting water speed");

        double R = getHydraulicRadius(diameter, percent);
        double valueN = getValueN(material);
//        double cValue = (Math.pow(R, 0.1666666666666667)) / valueN;   bu dustur islemedi asagidaki isleyir
//        System.out.println("C VALUE: "+ cValue);
//        return cValue * Math.sqrt(R * (slope/100));

        double powValue = 0.666 - 0.014 * Math.sqrt(R);
        return 71.4 * Math.pow(R, powValue) * Math.sqrt(slope / 100);
    }

    public double getFlowRate(double flowArea, double waterSpeed) {
        log.info("Getting flow rate");

        return flowArea * waterSpeed * 1000;
    }

    public double getRequiredFlowRate(double rainIntensity, double calculationArea) {
        log.info("Getting required flow rate");

        return rainIntensity * calculationArea;
    }

    public String getResult(double flowRate, double requiredFlowRate) {
        log.info("Getting result");

        if (flowRate > requiredFlowRate) {
            return "OK";
        } else {
            return "PIS";
        }
    }

    public Pipe pipeFieldSetter(Pipe addPipe) {
        log.info("Pipe field setter is working");

        addPipe.setWettedPerimeter(getWettedPerimeter(addPipe.getStructureDiameter(), addPipe.getFlowHeight()));
        addPipe.setFlowArea(
                getFlowArea(addPipe.getStructureDiameter(), addPipe.getFlowHeight()));
        addPipe.setHydraulicRadius(getHydraulicRadius(addPipe.getStructureDiameter(), addPipe.getFlowHeight()));
        addPipe.setMinAllowedSlope(getMinimumSlope(addPipe.getSlope()));
        addPipe.setRoughness(getValueN(addPipe.getMaterial()));
        addPipe.setWaterSpeed(getWaterSpeed(
                addPipe.getStructureDiameter(), addPipe.getFlowHeight(),
                addPipe.getMaterial(), addPipe.getSlope()));
        addPipe.setFlowRate(getFlowRate(addPipe.getFlowArea(), addPipe.getWaterSpeed()));
        addPipe.setRequiredFlowRate(getRequiredFlowRate(addPipe.getRainIntensity(), addPipe.getCalculationArea()));
        addPipe.setResult(getResult(addPipe.getFlowRate(), addPipe.getRequiredFlowRate()));
        return addPipe;
    }

}
