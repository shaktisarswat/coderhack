package com.crio.coderhack.controller;

import com.crio.coderhack.entity.User;
import com.crio.coderhack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(LeaderBoardController.API_END_POINT)
public class LeaderBoardController {
    public static final String API_END_POINT = "/coderhack";
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allRegisteredUser = userService.getAllUsers();
        return new ResponseEntity<>(allRegisteredUser, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody @Validated User user) {
        if (user.getScore() != 0 || user.getBadges().size() != 0) {
            return new ResponseEntity<>("Not Valid Data!", HttpStatus.NOT_ACCEPTABLE);
        }
        User registeredUser = userService.registerUser(user);
        if (registeredUser != null) {
            return new ResponseEntity(registeredUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("For given Id : " + user.getUserID() + " user already Exist", HttpStatus.CONFLICT);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUserDetails(@PathVariable String userId, @RequestBody @Validated User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("For given Id : " + userId + " user not found", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        User deletedUser = userService.deleteUser(userId);
        if (deletedUser != null) {
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("For given Id : " + userId + " user not found", HttpStatus.BAD_REQUEST);
    }

}
