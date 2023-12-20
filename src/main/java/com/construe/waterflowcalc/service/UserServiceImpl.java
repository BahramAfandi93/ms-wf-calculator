package com.construe.waterflowcalc.service;

import com.construe.waterflowcalc.dto.UserRequestDto;
import com.construe.waterflowcalc.dto.UserResponseDto;
import com.construe.waterflowcalc.mapper.UserMapper;
import com.construe.waterflowcalc.model.User;
import com.construe.waterflowcalc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> findAll() {
        log.info("finding all users");
        return userMapper.userListToUserResponseDtoList(userRepository.findAll());
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        log.info("adding new user");

        User addUser = userMapper.userRequestDtoToUser(userRequestDto);
        return userMapper.userToUserResponseDto(userRepository.save(addUser));
    }

    @Override
    public List<UserResponseDto> addUsers(List<UserRequestDto> userRequestDtoList) {
        log.info("adding new users");

        List<User> addUserList = userMapper.userRequestDtoListToUserList(userRequestDtoList);
        return userMapper.userListToUserResponseDtoList(userRepository.saveAll(addUserList));
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        log.info("updating user");

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("not Found"));

        user.setName(userRequestDto.getName());
        user.setLastname(userRequestDto.getLastname());
        user.setEmail(userRequestDto.getEmail());
        user.setBirthday(userRequestDto.getBirthday());
        user.setPosition(userRequestDto.getPosition());
        user.setCompanyName(userRequestDto.getCompanyName());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());

        return userMapper.userToUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto findById(Long id) {
        log.info("finding user by id");

        return userMapper.userToUserResponseDto(userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("not Found")));
    }

    @Override
    public User findUserById(Long id) {
        log.info("find user by id");

        return userRepository.findById(id).get();
    }


    @Override
    public List<UserResponseDto> findByName(String name) {
        log.info("finding user by name");

        return userMapper.userListToUserResponseDtoList(userRepository.findByName(name));
    }

    @Override
    public List<UserResponseDto> findByLastname(String lastname) {
        log.info("finding user by lastname");

        return userMapper.userListToUserResponseDtoList(userRepository.findByLastname(lastname));
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        log.info("finding user by email");

        return userMapper.userToUserResponseDto(userRepository.findByEmail(email).get());
    }

    @Override
    public UserResponseDto findByUsername(String username) {
        log.info("finding user by username");

        return userMapper.userToUserResponseDto(userRepository.findByUsername(username));
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("delete user by id");

        userRepository.deleteById(id);
    }
}
