package com.ankush003.MovieBase.controller;

import com.ankush003.MovieBase.mappers.Mapper;
import com.ankush003.MovieBase.model.dto.UserDto;
import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        log.info("Logging in user");
        Optional<UserEntity> user = userService.loginUser(email, password);
        return user.map(userEntity -> ResponseEntity.ok(userMapper.mapTo(userEntity))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
