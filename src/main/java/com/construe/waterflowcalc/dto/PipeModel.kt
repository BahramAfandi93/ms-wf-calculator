package com.construe.waterflowcalc.dto

import com.construe.waterflowcalc.dao.entity.Roughness

data class PipeRequest(
    var project: Project,
    var chainage: String? = null,
    var material: String? = null,
    var flowHeight: Int? = null,
    var rainIntensity: Int? = null,
    var calculationArea: Int? = null,
    var slope: Double? = null,

    var shape: Roughness? = null,
    var structureDiameter: Double? = null,
    var structureWidth: Double? = null,
    var structureHeight: Double? = null,

    var userId: Long? = null
)

data class PipeResponse(
    var chainage: String? = null,
    var material: String? = null,
    var flowHeight: Int? = null,
    var rainIntensity: Int? = null,
    var calculationArea: Int? = null,
    var slope: Double? = null,
    var shape: Roughness? = null,
    var structureDiameter: Double? = null,
    var structureWidth: Double? = null,
    var structureHeight: Double? = null,
    var flowArea: Double? = null,
    var waterSpeed: Double? = null,
    var minAllowedSlope: Double? = null,
    var hydraulicRadius: Double? = null,
    var roughness: Double? = null,
    var wettedPerimeter: Double? = null,
    var userId: Long? = null,
    var flowRate: Double? = null,
    var requiredFlowRate: Double? = null,
    var result: String? = null
)

data class Project(
    var location: String? = null,
    var projectName: String? = null,
)