package com.softuni.andreys.service.impl;

import com.softuni.andreys.models.entity.User;
import com.softuni.andreys.models.service.UserServiceModel;
import com.softuni.andreys.repository.UserRepository;
import com.softuni.andreys.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        User user = this.modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        User user = this.userRepository
                        .findByUsernameAndPassword(username, password)
                        .orElse(null);

        return user == null ? null : this.modelMapper.map(user, UserServiceModel.class);
    }
}
