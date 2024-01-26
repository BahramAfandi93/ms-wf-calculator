//package com.construe.waterflowcalc.controller;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import javax.websocket.server.PathParam;
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping(value = "/user")
//@Slf4j
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping(value = "/get-all")
//    public List<UserResponseDto> findAll() {
//        return userService.findAll();
//    }
//
//    @GetMapping(value = "/get-by-id/{id}")
//    public UserResponseDto findById(@PathVariable(name = "id") Long id){
//        return userService.findById(id);
//    }
//
//    @GetMapping(value = "/get-by-name")
//    public List<UserResponseDto> findByName(@PathParam("name") String name){
//        return userService.findByName(name);
//    }
//
//    @GetMapping(value = "/get-by-last-name")
//    public List<UserResponseDto> findByLastname(@PathParam("lastname") String lastname){
//        return userService.findByLastname(lastname);
//    }
//
//    @GetMapping(value = "/get-by-email/{email}")
//    public  UserResponseDto findByEmail(@PathVariable("email") String email){
//        return userService.findByEmail(email);
//    }
//
//    @GetMapping(value = "/get-by-username/{username}")
//    public  UserResponseDto findByUsername(@PathVariable("username") String username){
//        return userService.findByUsername(username);
//    }
//
//    @PostMapping(value = "/save")
//    public UserResponseDto saveUser(@RequestBody UserRequestDto userRequestDto){
//        return userService.addUser(userRequestDto);
//    }
//
//    @PostMapping(value = "/save-all")
//    public List<UserResponseDto> saveAllUser(@RequestBody List<UserRequestDto> userRequestDtoList){
//        return userService.addUsers(userRequestDtoList);
//    }
//
//    @PutMapping(value = "/update/{id}")
//    public  UserResponseDto userResponseDto(@PathVariable long id,
//                                            @RequestBody UserRequestDto userRequestDto){
//        return userService.updateUser(id, userRequestDto);
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    void delete(Long id){
//        userService.deleteUserById(id);
//    }
//}
