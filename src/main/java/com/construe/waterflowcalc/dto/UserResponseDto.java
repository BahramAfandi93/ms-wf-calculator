package com.construe.waterflowcalc.dto;

import com.construe.waterflowcalc.entity.Pipe;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    String name;
    String lastname;
    String email;
    List<Pipe> pipes;
}
