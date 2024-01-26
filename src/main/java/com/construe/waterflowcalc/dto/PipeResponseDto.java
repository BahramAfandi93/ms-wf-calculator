package com.construe.waterflowcalc.dto;

import com.construe.waterflowcalc.entity.StructureShape;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PipeResponseDto {
    String chainage;
    String material;
    Integer flowHeight;
    Integer rainIntensity;
    Integer calculationArea;
    Double slope;

    @Enumerated(EnumType.STRING)
    StructureShape shape;
    Double structureDiameter;
    Double structureWidth;
    Double structureHeight;

    Double flowArea;
    Double waterSpeed;
    Double minAllowedSlope;
    Double hydraulicRadius;
    Double roughness;
    Double wettedPerimeter;

    Long userId;

    Double flowRate;
    Double requiredFlowRate;
    String result;
}
