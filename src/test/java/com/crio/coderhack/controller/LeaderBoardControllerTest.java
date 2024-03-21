package com.crio.coderhack.controller;

import com.crio.coderhack.entity.User;
import com.crio.coderhack.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class LeaderBoardControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private LeaderBoardController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers_Success() {
        // Given
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(users);
        // When
        ResponseEntity<List<User>> response = controller.getAllUsers();
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testRegisterUser_Success() {
        // Given
        User user = new User();
        when(userService.registerUser(user)).thenReturn(user);

        // When
        ResponseEntity<?> response = controller.registerUser(user);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        // Given
        User user = new User();
        when(userService.registerUser(user)).thenReturn(null);
        // When
        ResponseEntity<?> response = controller.registerUser(user);
        // Then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("user already Exist"));
    }

    @Test
    void testUpdateUserDetails_Success() {
        // Given
        String userId = "123";
        User user = new User();
        when(userService.updateUser(user)).thenReturn(user);
        // When
        ResponseEntity<?> response = controller.updateUserDetails(userId, user);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testUpdateUserDetails_UserNotFound() {
        // Given
        String userId = "123";
        User user = new User();
        when(userService.updateUser(user)).thenReturn(null);
        // When
        ResponseEntity<?> response = controller.updateUserDetails(userId, user);
        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("user not found"));
    }

    @Test
    void testDeleteUser_Success() {
        // Given
        String userId = "123";
        User user = new User();
        when(userService.deleteUser(userId)).thenReturn(user);
        // When
        ResponseEntity<?> response = controller.deleteUser(userId);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testDeleteUser_UserNotFound() {
        // Given
        String userId = "123";
        when(userService.deleteUser(userId)).thenReturn(null);
        // When
        ResponseEntity<?> response = controller.deleteUser(userId);
        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("user not found"));
    }
}
