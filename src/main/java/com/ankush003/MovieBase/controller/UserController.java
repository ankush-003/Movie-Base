package com.ankush003.MovieBase.controller;

import com.ankush003.MovieBase.mappers.Mapper;
import com.ankush003.MovieBase.model.dto.UserDto;
import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Log
public class UserController {
    private UserService userService;
    private Mapper<UserEntity, UserDto> userMapper;

    @Autowired
    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto user) {
        log.info("Registering user" + user);
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUser = userService.registerUser(userEntity);

        return ResponseEntity.ok(userMapper.mapTo(savedUser));
        // sample json
        // {
        //     "id": 1,
        //     "username": "user",
        //     "password": "password",
        //     "email": "
        // }
    }
}
