package com.construe.waterflowcalc.service.calculator

import az.pashabank.ips.integration.logger.DPLogger
import com.construe.waterflowcalc.dto.PipeRequest
import com.construe.waterflowcalc.dto.PipeResponse
import com.construe.waterflowcalc.dao.entity.Pipe
import com.construe.waterflowcalc.dao.entity.Result.FLOW_FAILED
import com.construe.waterflowcalc.dao.entity.Result.FLOW_IS_SATISFIED
import com.construe.waterflowcalc.dao.entity.Roughness
import com.construe.waterflowcalc.dao.entity.Roughness.REINFORCED_CONCRETE
import com.construe.waterflowcalc.dao.entity.StructureShape.CIRCLE_CULVERT
import com.construe.waterflowcalc.service.PipeHandler
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import org.springframework.stereotype.Service

@Service
class CircleCulvertCalculator : PipeHandler {
    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    override fun structureShape() = CIRCLE_CULVERT

    override fun pipeFlowCalculator(): Double? {
        val pipe =
        TODO("Not yet implemented")
    }

    override fun addPipe(pipeRequest: PipeRequest): PipeResponse {
        TODO("Not yet implemented")
    }

    private fun getCentralAngle(diameter: Double, percent: Int): Double {

        log.info("ActionLog.CircleCulvertCalculator.getCentralAngle -> diameter = {}, percent = {}",diameter, percent)

        val radius = diameter / 2
        val height = if (percent < 50) {
            diameter * percent * 0.01
        } else {
            diameter - diameter * percent * 0.01
        }

        return 2 * acos((radius - height) / radius)
    }

    private fun getWettedPerimeter(diameter: Double, percent: Int): Double {

        log.info("ActionLog.CircleCulvertCalculator.getWettedPerimeter -> {} , {}", diameter, percent)

        val angle = getCentralAngle(diameter, percent)
        val wettedPerimeter = if (percent < 50) {
            diameter / 2 * angle
        } else {
            2 * Math.PI * (diameter / 2) - diameter / 2 * angle
        }
        return wettedPerimeter
    }

    private fun getFlowArea(diameter: Double, percent: Int): Double {
        val angle = getCentralAngle(diameter, percent)
        val circularSegmentArea: Double = ((diameter / 2).pow(2.0) * (angle - sin(angle))) / 2
        val flowArea = if (percent < 50) {
            circularSegmentArea
        } else {
            Math.PI * (diameter / 2).pow(2.0) - circularSegmentArea
        }
        return flowArea
    }

    private fun getHydraulicRadius(diameter: Double, percent: Int): Double {

        log.info("Getting hydraulic radius")

        val flowArea = getFlowArea(diameter, percent)
        val wettedPerimeter = getWettedPerimeter(diameter, percent)
        val hydraulicRadius = flowArea / wettedPerimeter
        return hydraulicRadius
    }

    private fun getMinimumSlope(diameter: Double): Double {

        log.info("Getting minimum slope")

        return (1 / (diameter * 1000)) * 100
    }

    private fun getValueN(material: String?): Double {

        log.info("Getting value N")

        return when (material) {
            "REINFORCED_CONCRETE" -> REINFORCED_CONCRETE.roughness
            "CORRUGATED_METAL" -> 0.0015
            "BRICK" -> 0.0016
            "CERAMIC" -> 0.0017
            "ASBESTOS_CEMENT" -> 0.0018
            "cugun" -> 0.0019
            "STEEL" -> 0.015
            "PVC" -> 0.0021
            "asfaltbeton" -> 0.0022
            "torpaqkanal" -> 0.0023
            "cinqilkanal" -> 0.0024
            else -> 0.00
        }
    }

    private fun getWaterSpeed(diameter: Double, percent: Int, material: String?, slope: Double): Double {
        log.info(
            "ActionLog.CircleCulvertCalculator.getWaterSpeedGetting water speed ->  {},{},{}",
            diameter, percent, material, slope
        )

        val rad = getHydraulicRadius(diameter, percent)
        val valueN = material?.let { Roughness.valueOf(it) }

        val powValue = 0.666 - getValueN(material) * sqrt(rad)
        return 71.4 * rad.pow(powValue) * sqrt(slope / 100)
    }

    private fun getFlowRate(flowArea: Double, waterSpeed: Double): Double {
        log.info("Getting flow rate")

        return flowArea * waterSpeed * 1000
    }

    private fun getRequiredFlowRate(rainIntensity: Double, calculationArea: Double): Double {
        log.info("Getting required flow rate")

        return rainIntensity * calculationArea
    }

    private fun getResult(flowRate: Double, requiredFlowRate: Double): String {
        log.info("Getting result {}")

        return if (flowRate > requiredFlowRate) {
            FLOW_IS_SATISFIED.toString()
        } else {
            FLOW_FAILED.toString()
        }
    }

    private fun pipeFieldSetter(addPipe: Pipe): Pipe {
        log.info("Pipe field setter is working")

        val result = getResult(addPipe.flowRate, addPipe.requiredFlowRate)

        return Pipe(
            location = addPipe.location,
            projectName = addPipe.projectName,
            chainage = addPipe.chainage,
            material = addPipe.material,
            flowHeight = addPipe.flowHeight,
            rainIntensity = addPipe.rainIntensity,
            wettedPerimeter = getWettedPerimeter(addPipe.structureDiameter, addPipe.flowHeight),
            flowArea = getFlowArea(addPipe.structureDiameter, addPipe.flowHeight),
            hydraulicRadius = getHydraulicRadius(addPipe.structureDiameter, addPipe.flowHeight),
            minAllowedSlope = getMinimumSlope(addPipe.slope),
            roughness = getValueN(addPipe.material),
            waterSpeed = getWaterSpeed(
                addPipe.structureDiameter, addPipe.flowHeight,
                addPipe.material, addPipe.slope
            ),
            flowRate = getFlowRate(addPipe.flowArea, addPipe.waterSpeed),
            requiredFlowRate = getRequiredFlowRate(
                addPipe.rainIntensity.toDouble(),
                addPipe.calculationArea.toDouble()
            ),
        )
    }

}