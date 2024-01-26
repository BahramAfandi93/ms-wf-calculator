package com.construe.waterflowcalc.mapper;


import com.construe.waterflowcalc.dto.PipeRequest;
import com.construe.waterflowcalc.dto.PipeResponse;
import com.construe.waterflowcalc.entity.Pipe;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "Spring")
public interface PipeMapper {
    PipeResponse pipeToPipeResponse(Pipe pipe);

    List<PipeResponse> pipeListToPipeResponseList(List<Pipe> pipeList);

    List<Pipe> pipeRequestListToPipeList(List<PipeRequest> pipeRequestList);

    Pipe pipeRequestToPipe(PipeRequest pipeRequestDto);
}
