package com.construe.waterflowcalc.mapper;


import com.construe.waterflowcalc.dto.PipeRequest;
import com.construe.waterflowcalc.entity.Pipe;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "Spring")
public interface PipeMapper {
    PipeResponseDto pipeToPipeResponseDto(Pipe pipe);

    List<PipeResponseDto> pipeListToPipeResponseDtoList(List<Pipe> pipeList);

    List<Pipe> pipeRequestDtoListToPipeList(List<PipeRequest> pipeRequestDtoList);

    Pipe pipeRequestDtoToPipe(PipeRequest pipeRequestDto);
}
