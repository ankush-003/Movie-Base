package com.ankush003.MovieBase.service.impl;

import com.ankush003.MovieBase.model.entities.ReviewEntity;
import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.repository.ReviewRepository;
import com.ankush003.MovieBase.repository.UserRepository;
import com.ankush003.MovieBase.service.ReviewService;
import com.ankush003.MovieBase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewService reviewService;

    @Override
    public UserEntity registerUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<ReviewEntity> findReviewsByUserId(Long userId) {
        return reviewService.findReviewsByUserId(userId);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> loginUser(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
