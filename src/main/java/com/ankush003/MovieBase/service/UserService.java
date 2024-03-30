package com.ankush003.MovieBase.service;

import com.ankush003.MovieBase.model.dto.UserDto;
import com.ankush003.MovieBase.model.entities.ReviewEntity;
import com.ankush003.MovieBase.model.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity registerUser(UserEntity userEntity);
    Optional<UserEntity> getUserById(Long id);

    List<ReviewEntity> findReviewsByUserId(Long userId);

    void deleteUser(Long id);

    UserEntity updateUser(UserEntity userEntity);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> loginUser(String email, String password);
}
