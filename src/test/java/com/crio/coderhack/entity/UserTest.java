package com.crio.coderhack.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testGetUserID() {
        // Given
        String userId = "123";
        User user = new User();
        user.setUserID(userId);
        // When
        String retrievedUserId = user.getUserID();
        // Then
        assertEquals(userId, retrievedUserId);
    }

    @Test
    void testGetUsername() {
        // Given
        String username = "JohnDoe";
        User user = new User();
        user.setUsername(username);
        // When
        String retrievedUsername = user.getUsername();
        // Then
        assertEquals(username, retrievedUsername);
    }

    @Test
    void testGetScore() {
        // Given
        int score = 50;
        User user = new User();
        user.setScore(score);
        // When
        int retrievedScore = user.getScore();
        // Then
        assertEquals(score, retrievedScore);
    }

    @Test
    void testUpdateUserBadges_CodeNinja() {
        // Given
        User user = new User();
        user.setScore(30); // score between 1 and 30
        // When
        user.updateUserBadges();
        // Then
        List<String> expectedBadges = Arrays.asList("Code Ninja");
        assertEquals(expectedBadges, user.getBadges());
    }

    @Test
    void testUpdateUserBadges_CodeChamp() {
        // Given
        User user = new User();
        user.setScore(45); // score between 31 and 60

        // When
        user.updateUserBadges();

        // Then
        List<String> expectedBadges = Arrays.asList("Code Champ");
        assertEquals(expectedBadges, user.getBadges());
    }

    @Test
    void testUpdateUserBadges_CodeMaster() {
        // Given
        User user = new User();
        user.setScore(80); // score between 61 and 100
        // When
        user.updateUserBadges();
        // Then
        List<String> expectedBadges = Arrays.asList("Code Master");
        assertEquals(expectedBadges, user.getBadges());
    }

    @Test
    void testUpdateUserBadges_NoUpdate() {
        // Given
        User user = new User();
        user.setScore(0); // score 0
        // When
        user.updateUserBadges();
        // Then
        List<String> expectedBadges = Arrays.asList();
        assertEquals(expectedBadges, user.getBadges());
    }
}
