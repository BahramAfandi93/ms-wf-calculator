package com.construe.waterflowcalc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String location;
    String projectName;
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

    Double waterSpeed;
    Double wettedPerimeter;
    Double flowArea;
    Double hydraulicRadius;
    Double minAllowedSlope;
    Double flowRate;
    Double roughness;
    Double requiredFlowRate;
    @CreationTimestamp
    LocalDateTime pipePostDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;
    String result;
}