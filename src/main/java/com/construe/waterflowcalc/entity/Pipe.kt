package com.construe.waterflowcalc.entity

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
    var location: String? = null,
    var projectName: String? = null,
    var chainage: String? = null,
    var material: String? = null,
    var flowHeight: Int? = null,
    var rainIntensity: Int? = null,
    var calculationArea: Int? = null,
    var slope: Double? = null,

    @Enumerated(STRING)
    var shape: StructureShape? = null,

    var structureDiameter: Double? = null,
    var structureWidth: Double? = null,
    var structureHeight: Double? = null,

    var waterSpeed: Double? = null,
    var wettedPerimeter: Double? = null,
    var flowArea: Double? = null,
    var hydraulicRadius: Double? = null,
    var minAllowedSlope: Double? = null,
    var flowRate: Double? = null,
    var roughness: Double? = null,
    var requiredFlowRate: Double? = null,

    var pipePostDate: LocalDateTime? = null
){

}