package com.construe.waterflowcalc.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-21T00:45:15+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class PipeMapperImpl implements PipeMapper {

    @Override
    public PipeResponseDto pipeToPipeResponseDto(Pipe pipe) {
        if ( pipe == null ) {
            return null;
        }

        PipeResponseDtoBuilder pipeResponseDto = PipeResponseDto.builder();

        pipeResponseDto.chainage( pipe.getChainage() );
        pipeResponseDto.material( pipe.getMaterial() );
        pipeResponseDto.flowHeight( pipe.getFlowHeight() );
        pipeResponseDto.rainIntensity( pipe.getRainIntensity() );
        pipeResponseDto.calculationArea( pipe.getCalculationArea() );
        pipeResponseDto.slope( pipe.getSlope() );
        pipeResponseDto.shape( pipe.getShape() );
        pipeResponseDto.structureDiameter( pipe.getStructureDiameter() );
        pipeResponseDto.structureWidth( pipe.getStructureWidth() );
        pipeResponseDto.structureHeight( pipe.getStructureHeight() );
        pipeResponseDto.flowArea( pipe.getFlowArea() );
        pipeResponseDto.waterSpeed( pipe.getWaterSpeed() );
        pipeResponseDto.minAllowedSlope( pipe.getMinAllowedSlope() );
        pipeResponseDto.hydraulicRadius( pipe.getHydraulicRadius() );
        pipeResponseDto.roughness( pipe.getRoughness() );
        pipeResponseDto.wettedPerimeter( pipe.getWettedPerimeter() );
        pipeResponseDto.flowRate( pipe.getFlowRate() );
        pipeResponseDto.requiredFlowRate( pipe.getRequiredFlowRate() );
        pipeResponseDto.result( pipe.getResult() );

        return pipeResponseDto.build();
    }

    @Override
    public List<PipeResponseDto> pipeListToPipeResponseDtoList(List<Pipe> pipeList) {
        if ( pipeList == null ) {
            return null;
        }

        List<PipeResponseDto> list = new ArrayList<PipeResponseDto>( pipeList.size() );
        for ( Pipe pipe : pipeList ) {
            list.add( pipeToPipeResponseDto( pipe ) );
        }

        return list;
    }

    @Override
    public List<Pipe> pipeRequestDtoListToPipeList(List<PipeRequestDto> pipeRequestDtoList) {
        if ( pipeRequestDtoList == null ) {
            return null;
        }

        List<Pipe> list = new ArrayList<Pipe>( pipeRequestDtoList.size() );
        for ( PipeRequestDto pipeRequestDto : pipeRequestDtoList ) {
            list.add( pipeRequestDtoToPipe( pipeRequestDto ) );
        }

        return list;
    }

    @Override
    public Pipe pipeRequestDtoToPipe(PipeRequestDto pipeRequestDto) {
        if ( pipeRequestDto == null ) {
            return null;
        }

        PipeBuilder pipe = Pipe.builder();

        pipe.location( pipeRequestDto.getLocation() );
        pipe.projectName( pipeRequestDto.getProjectName() );
        pipe.chainage( pipeRequestDto.getChainage() );
        pipe.material( pipeRequestDto.getMaterial() );
        pipe.flowHeight( pipeRequestDto.getFlowHeight() );
        pipe.rainIntensity( pipeRequestDto.getRainIntensity() );
        pipe.calculationArea( pipeRequestDto.getCalculationArea() );
        pipe.slope( pipeRequestDto.getSlope() );
        pipe.shape( pipeRequestDto.getShape() );
        pipe.structureDiameter( pipeRequestDto.getStructureDiameter() );
        pipe.structureWidth( pipeRequestDto.getStructureWidth() );
        pipe.structureHeight( pipeRequestDto.getStructureHeight() );

        return pipe.build();
    }
}
