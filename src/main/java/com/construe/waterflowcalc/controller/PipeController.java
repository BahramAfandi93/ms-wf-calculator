package com.construe.waterflowcalc.controller;

import com.construe.waterflowcalc.dto.PipeRequestDto;
import com.construe.waterflowcalc.dto.PipeResponseDto;
import com.construe.waterflowcalc.service.PipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/pipe")
@Slf4j
public class PipeController {

    private final PipeService pipeService;

    @GetMapping(value = "/get-by-chainage/{location}/{project}/{chainage}")
    public PipeResponseDto findByChainage(@PathVariable String location,
                                          @PathVariable String project,
                                          @PathVariable String chainage) {
        return pipeService.findByLocationAndProjectNameAndChainage(location, project, chainage);
    }

    @GetMapping(value = "/get-by-id/{id}")
    public PipeResponseDto findById(@PathVariable(name = "id") long id) {
        return pipeService.findById(id);
    }

    @PostMapping(value = "/save/{userId}")
    public PipeResponseDto savePipe(@PathVariable Long userId,
                                    @RequestBody PipeRequestDto pipeRequestDto) {
        return pipeService.addPipe(userId, pipeRequestDto);
    }
}
