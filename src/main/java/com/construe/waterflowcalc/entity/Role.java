package com.construe.waterflowcalc.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long roleId;
    String role;
}