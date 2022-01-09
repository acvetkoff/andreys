package com.softuni.andreys.service;

import com.softuni.andreys.models.entity.User;
import com.softuni.andreys.models.service.UserServiceModel;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);
}
