package com.construe.waterflowcalc.dao.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "pipe")
data class Pipe(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var location: String,
    var projectName: String,
    var chainage: String,
    var material: String,
    var flowHeight: Int,
    var rainIntensity: Int,
    var calculationArea: Int,
    var slope: Double,

    @Enumerated(STRING)
    var shape: StructureShape,
    var structureDiameter: Double,
    var structureWidth: Double,
    var structureHeight: Double,
    var waterSpeed: Double,
    var wettedPerimeter: Double,
    var flowArea: Double,
    var hydraulicRadius: Double,
    var minAllowedSlope: Double,
    var roughness: Double,
    var flowRate: Double,
    var requiredFlowRate: Double,
    var pipePostDate: LocalDateTime,
    var result: String
)