package com.construe.waterflowcalc.service;

import com.construe.waterflowcalc.dto.PipeRequestDto;
import com.construe.waterflowcalc.dto.PipeResponseDto;
import com.construe.waterflowcalc.model.Pipe;

import java.util.List;
import java.util.Optional;

public interface PipeService {
    PipeResponseDto addPipe(Long userId, PipeRequestDto pipeRequestDto);
    List<PipeResponseDto> addPipeList(List<PipeRequestDto> pipeRequestDtoList);
    PipeResponseDto findById(Long id);

    PipeResponseDto findByLocationAndProjectNameAndChainage(String location, String projectName, String chainage);
}
