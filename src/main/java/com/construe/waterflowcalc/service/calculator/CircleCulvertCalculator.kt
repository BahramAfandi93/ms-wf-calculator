package com.construe.waterflowcalc.service.calculator

import az.pashabank.ips.integration.logger.DPLogger
import com.construe.waterflowcalc.dto.PipeRequest
import com.construe.waterflowcalc.dto.PipeResponse
import com.construe.waterflowcalc.entity.Pipe
import com.construe.waterflowcalc.entity.Roughness
import com.construe.waterflowcalc.entity.Roughness.REINFORCED_CONCRETE
import com.construe.waterflowcalc.entity.StructureShape.CIRCLE_CULVERT
import com.construe.waterflowcalc.service.PipeCalculatorService
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import org.springframework.stereotype.Service

@Service
class CircleCulvertCalculator : PipeCalculatorService {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    private fun getCentralAngle(diameter: Double, percent: Int): Double {
        log.info("Getting central angle")
        val r = diameter / 2

        val h = if (percent < 50) {
            diameter * percent * 0.01
        } else {
            diameter - diameter * percent * 0.01
        }

        return 2 * acos((r - h) / r)
    }

    private fun getWettedPerimeter(diameter: Double, percent: Int): Double {
        log.info("Getting wetted perimeter")

        val wettedPerimeter: Double
        val angle = getCentralAngle(diameter, percent)
        wettedPerimeter = if (percent < 50) {
            diameter / 2 * angle
        } else {
            2 * Math.PI * (diameter / 2) - diameter / 2 * angle
        }
        return wettedPerimeter
    }

    private fun getFlowArea(diameter: Double, percent: Int): Double {
        log.info("Getting flow area")

        val radius = diameter / 2
        val angle = getCentralAngle(diameter, percent)
        val circularSegmentArea: Double = (radius.pow(2.0) * (angle - sin(angle))) / 2
        val flowArea = if (percent < 50) {
            circularSegmentArea
        } else {
            Math.PI * radius.pow(2.0) - circularSegmentArea
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
        var dia = diameter
        log.info("Getting minimum slope")

        dia *= 1000.0
        return (1 / dia) * 100
    }

    private fun getValueN(material: String?): Double {
        log.info("Getting value N")

        return when (material) {
            "REINFORCED_CONCRETE" -> REINFORCED_CONCRETE.roughness
            "demir" -> 0.0015
            "kerpic" -> 0.0016
            "keramik" -> 0.0017
            "asbessement" -> 0.0018
            "cugun" -> 0.0019
            "polad" -> 0.015
            "PVX" -> 0.0021
            "asfaltbeton" -> 0.0022
            "torpaqkanal" -> 0.0023
            "cinqilkanal" -> 0.0024
            else -> 0.00
        }
    }

    private fun getWaterSpeed(diameter: Double, percent: Int, material: String?, slope: Double): Double {
        log.info("Getting water speed")

        val rad = getHydraulicRadius(diameter, percent)
        val valueN = material?.let { Roughness.valueOf(it) }
        //  double cValue = (Math.pow(R, 0.1666666666666667)) / valueN; bu dustur islemedi asagidaki isleyir
        //  System.out.println("C VALUE: "+ cValue);
        //  return cValue * Math.sqrt(R * (slope/100));
        val powValue = 0.666 - 0.014 * sqrt(rad)
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
        log.info("Getting result")

        return if (flowRate > requiredFlowRate) {
            "OK"
        } else {
            "PIS"
        }
    }

    private fun pipeFieldSetter(addPipe: Pipe): Pipe {
        log.info("Pipe field setter is working")

        addPipe.wettedPerimeter = getWettedPerimeter(addPipe.structureDiameter!!, addPipe.flowHeight!!)
        addPipe.flowArea = getFlowArea(addPipe.structureDiameter!!, addPipe.flowHeight!!)
        addPipe.hydraulicRadius = getHydraulicRadius(addPipe.structureDiameter!!, addPipe.flowHeight!!)
        addPipe.minAllowedSlope = getMinimumSlope(addPipe.slope!!)
        addPipe.roughness = getValueN(addPipe.material)
        addPipe.waterSpeed = getWaterSpeed(
            addPipe.structureDiameter!!, addPipe.flowHeight!!,
            addPipe.material, addPipe.slope!!
        )
        addPipe.flowRate = getFlowRate(addPipe.flowArea!!, addPipe.waterSpeed!!)
        addPipe.requiredFlowRate =
            getRequiredFlowRate(addPipe.rainIntensity!!.toDouble(), addPipe.calculationArea!!.toDouble())
        addPipe.setResult(getResult(addPipe.flowRate!!, addPipe.requiredFlowRate!!))
        return addPipe
    }

    override fun structureShape(): = CIRCLE_CULVERT

    override fun pipeFlowCalculator(): Double? {
        TODO("Not yet implemented")
    }

    override fun addPipe(pipeRequest: PipeRequest): PipeResponse {
        TODO("Not yet implemented")
    }

}