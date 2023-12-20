package com.construe.waterflowcalc.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String lastname;
    String username;
    String email;
    String password;
    LocalDate birthday;
    String position;
    String companyName;
    String phoneNumber;

    @CreationTimestamp
    LocalDateTime userRegistrationDate;

    boolean isEnabled;
    boolean credentialsNonExpired;
    boolean isAccountNonLocked;
    boolean isAccountNonExpired;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Pipe> pipes;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> role = new ArrayList<>();


}