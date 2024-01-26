package com.construe.waterflowcalc.controller;

import com.construe.waterflowcalc.dto.PipeRequest;
import com.construe.waterflowcalc.dto.PipeResponse;
import com.construe.waterflowcalc.service.PipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/pipe")
@Slf4j
public class PipeController {

    private final PipeService pipeService;

    @GetMapping(value = "/get-by-chainage/{location}/{project}/{chainage}")
    public PipeResponse findByChainage(@PathVariable String location,
                                       @PathVariable String project,
                                       @PathVariable String chainage) {
        return pipeService.findByLocationAndProjectNameAndChainage(location, project, chainage);
    }

    @GetMapping(value = "/get-by-id/{id}")
    public PipeResponse findById(@PathVariable(name = "id") long id) {
        return pipeService.findById(id);
    }

    @PostMapping(value = "/save/{userId}")
    public PipeResponse savePipe(@PathVariable Long userId,
                                 @RequestBody PipeRequest pipeRequest) {
        return pipeService.addPipe(userId, pipeRequest);
    }
}
