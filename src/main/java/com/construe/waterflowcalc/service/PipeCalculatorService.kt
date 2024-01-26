package com.construe.waterflowcalc.service

interface PipeCalculatorService {
    fun structureShape(): String
    fun pipeFlowCalculator(): Double?{
        return null
    }
}