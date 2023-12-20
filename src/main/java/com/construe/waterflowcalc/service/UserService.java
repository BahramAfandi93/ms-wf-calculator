package com.construe.waterflowcalc.service;

import com.construe.waterflowcalc.dto.UserRequestDto;
import com.construe.waterflowcalc.dto.UserResponseDto;
import com.construe.waterflowcalc.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);

    List<UserResponseDto> findAll();

    UserResponseDto addUser(UserRequestDto userRequestDto);

    List<UserResponseDto> addUsers(List<UserRequestDto> userRequestDtoList);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    UserResponseDto findById(Long id);

    List<UserResponseDto> findByName(String name);

    List<UserResponseDto> findByLastname(String lastname);

    UserResponseDto findByEmail(String email);
    UserResponseDto findByUsername(String username);

    void deleteUserById(Long id);
}
