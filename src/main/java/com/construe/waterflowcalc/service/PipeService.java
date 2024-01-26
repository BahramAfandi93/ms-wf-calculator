package com.construe.waterflowcalc.service;

import com.construe.waterflowcalc.dto.PipeRequest;
import com.construe.waterflowcalc.dto.PipeResponse;

import java.util.List;

public interface PipeService {
    PipeResponse addPipe(Long userId, PipeRequest pipeRequest);
    List<PipeResponse> addPipeList(List<PipeRequest> pipeRequestDtoList);
    PipeResponse findById(Long id);

    PipeResponse findByLocationAndProjectNameAndChainage(String location, String projectName, String chainage);
}
