package com.construe.waterflowcalc.mapper;


import com.construe.waterflowcalc.dto.UserRequestDto;
import com.construe.waterflowcalc.dto.UserResponseDto;
import com.construe.waterflowcalc.model.User;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "Spring")
public interface UserMapper {
    UserResponseDto userToUserResponseDto(User user);
    UserRequestDto userToUserRequestDto(User user);
    User userResponseDtoToUser(UserResponseDto userResponseDto);

    List<UserResponseDto> userListToUserResponseDtoList(List<User> userList);
    List<User> userRequestDtoListToUserList(List<UserRequestDto> userRequestDtoList);

    User userRequestDtoToUser(UserRequestDto userRequestDto);
}
