package com.construe.waterflowcalc.dto;

import com.construe.waterflowcalc.model.Pipe;
import com.construe.waterflowcalc.model.User;
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
