package com.crio.coderhack.service.implementation;

import com.crio.coderhack.entity.User;
import com.crio.coderhack.repository.UserRepository;
import com.crio.coderhack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        Optional<User> findUser = userRepository.findById(user.getUserID());
        if (!findUser.isPresent()) {
            User newUser = userRepository.save(user);
            return newUser;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        Collections.sort(allUser, (u1, u2) -> {
            if (u1.getScore() > u2.getScore()) return -1;
            else if (u1.getScore() < u2.getScore()) return 1;
            else return 0;
        });
        return allUser;
    }

    @Override
    public User updateUser(User user) {
        Optional<User> findUser = userRepository.findById(user.getUserID());
        if (findUser.isPresent()) {
            findUser.get().setScore(user.getScore());
            findUser.get().updateUserBadges();
            userRepository.save(findUser.get());
            return findUser.get();
        }
        return null;
    }

    @Override
    public User deleteUser(String id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            userRepository.delete(findUser.get());
            return findUser.get();
        }
        return null;
    }
}
