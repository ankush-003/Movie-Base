package com.ankush003.MovieBase.service;

import com.ankush003.MovieBase.model.dto.UserDto;
import com.ankush003.MovieBase.model.entities.UserEntity;
import org.springframework.stereotype.Service;

public interface UserService {
    UserEntity registerUser(UserEntity userEntity);
}
