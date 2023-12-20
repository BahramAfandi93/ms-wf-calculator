package com.construe.waterflowcalc.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {
    String name;
    String lastname;
    String email;
    LocalDate birthday;
    String position;
    String companyName;
    String phoneNumber;
}
