package com.construe.waterflowcalc.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-21T00:45:15+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.name( user.getName() );
        userResponseDto.lastname( user.getLastname() );
        userResponseDto.email( user.getEmail() );
        List<Pipe> list = user.getPipes();
        if ( list != null ) {
            userResponseDto.pipes( new ArrayList<Pipe>( list ) );
        }

        return userResponseDto.build();
    }

    @Override
    public UserRequestDto userToUserRequestDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserRequestDtoBuilder userRequestDto = UserRequestDto.builder();

        userRequestDto.name( user.getName() );
        userRequestDto.lastname( user.getLastname() );
        userRequestDto.email( user.getEmail() );
        userRequestDto.birthday( user.getBirthday() );
        userRequestDto.position( user.getPosition() );
        userRequestDto.companyName( user.getCompanyName() );
        userRequestDto.phoneNumber( user.getPhoneNumber() );

        return userRequestDto.build();
    }

    @Override
    public User userResponseDtoToUser(UserResponseDto userResponseDto) {
        if ( userResponseDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.name( userResponseDto.getName() );
        user.lastname( userResponseDto.getLastname() );
        user.email( userResponseDto.getEmail() );
        List<Pipe> list = userResponseDto.getPipes();
        if ( list != null ) {
            user.pipes( new ArrayList<Pipe>( list ) );
        }

        return user.build();
    }

    @Override
    public List<UserResponseDto> userListToUserResponseDtoList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( userList.size() );
        for ( User user : userList ) {
            list.add( userToUserResponseDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> userRequestDtoListToUserList(List<UserRequestDto> userRequestDtoList) {
        if ( userRequestDtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userRequestDtoList.size() );
        for ( UserRequestDto userRequestDto : userRequestDtoList ) {
            list.add( userRequestDtoToUser( userRequestDto ) );
        }

        return list;
    }

    @Override
    public User userRequestDtoToUser(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.name( userRequestDto.getName() );
        user.lastname( userRequestDto.getLastname() );
        user.email( userRequestDto.getEmail() );
        user.birthday( userRequestDto.getBirthday() );
        user.position( userRequestDto.getPosition() );
        user.companyName( userRequestDto.getCompanyName() );
        user.phoneNumber( userRequestDto.getPhoneNumber() );

        return user.build();
    }
}
