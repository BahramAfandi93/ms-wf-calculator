package com.construe.waterflowcalc.service

import com.construe.waterflowcalc.dto.PipeRequest
import com.construe.waterflowcalc.dto.PipeResponse

interface PipeCalculatorService {
    fun structureShape(): String
    fun pipeFlowCalculator(): Double?
    fun addPipe(pipeRequest: PipeRequest): PipeResponse
}