package com.ankush003.MovieBase.service.impl;

import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.repository.UserRepository;
import com.ankush003.MovieBase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity registerUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
