package com.crio.coderhack.service;

import com.crio.coderhack.entity.User;
import com.crio.coderhack.repository.UserRepository;
import com.crio.coderhack.service.implementation.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplementationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser_UserNotExists_Success() {
        // Mocking userRepository.findById() to return an empty Optional
        when(userRepository.findById("userId")).thenReturn(Optional.empty());

        // Mocking userRepository.save() to return the saved user
        User user = new User();
        user.setUserID("userId");
        when(userRepository.save(user)).thenReturn(user);

        // Calling the method under test
        User registeredUser = userService.registerUser(user);

        // Verifying that userRepository.findById() was called with the correct argument
        verify(userRepository).findById("userId");

        // Verifying that userRepository.save() was called with the correct argument
        verify(userRepository).save(user);

        // Verifying that the returned user is the same as the user passed to userRepository.save()
        assertEquals(user, registeredUser);
    }

    @Test
    public void testGetAllUsers_Success() {
        // Mocking userRepository.findAll() to return a list of users
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        // Calling the method under test
        List<User> allUsers = userService.getAllUsers();

        // Verifying that userRepository.findAll() was called
        verify(userRepository).findAll();

        // Verifying that the returned list is the same as the list returned by userRepository.findAll()
        assertEquals(userList, allUsers);
    }

    @Test
    public void testUpdateUser_UserExists_Success() {
        // Mocking userRepository.findById() to return an Optional containing a user
        User existingUser = new User();
        existingUser.setUserID("userId");
        when(userRepository.findById("userId")).thenReturn(Optional.of(existingUser));

        // Mocking userRepository.save() to return the saved user
//        User updatedUser = new User();
//        updatedUser.setUserID("userId");
//        updatedUser.setScore(100); // Updating score
//        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        // Calling the method under test
        User returnedUser = userService.updateUser(existingUser);

        // Verifying that userRepository.findById() was called with the correct argument
        verify(userRepository).findById("userId");

        // Verifying that userRepository.save() was called with the correct argument
        verify(userRepository).save(existingUser);

        // Verifying that the returned user is the same as the updated user
        assertEquals(existingUser, returnedUser);
    }

    @Test
    public void testDeleteUser_UserExists_Success() {
        // Mocking userRepository.findById() to return an Optional containing a user
        User existingUser = new User();
        existingUser.setUserID("userId");
        when(userRepository.findById("userId")).thenReturn(Optional.of(existingUser));

        // Calling the method under test
        User deletedUser = userService.deleteUser("userId");

        // Verifying that userRepository.findById() was called with the correct argument
        verify(userRepository).findById("userId");

        // Verifying that userRepository.delete() was called with the correct argument
        verify(userRepository).delete(existingUser);

        // Verifying that the returned user is the same as the existing user
        assertEquals(existingUser, deletedUser);
    }
}
