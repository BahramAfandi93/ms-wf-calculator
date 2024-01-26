package com.construe.waterflowcalc.dto;

import com.construe.waterflowcalc.entity.Roughness;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PipeRequestDto {
    String location;
    String projectName;
    String chainage;
    String material;
    Integer flowHeight;
    Integer rainIntensity;
    Integer calculationArea;
    Double slope;

    Roughness shape;
    Double structureDiameter;
    Double structureWidth;
    Double structureHeight;

    Long userId;
}