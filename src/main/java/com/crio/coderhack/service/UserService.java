package com.crio.coderhack.service;

import com.crio.coderhack.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);


    List<User> getAllUsers();


    User updateUser(User user);


    User deleteUser(String id);
}
