package com.crio.coderhack.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Document(value = "users")
public class User {

    @Id
    @NotEmpty
    String userID;
    @NotBlank
    String username;

    @Min(value = 0)
    @Max(value = 100)
    int score;
    List<String> badges = new ArrayList<>();

    public String getUserID() {
        return userID;
    }

    public void setUserID(@Validated String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(@Validated String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void updateUserBadges() {
        if (score >= 1 && score <= 30 && !badges.contains("Code Ninja")) {
            badges.add("Code Ninja");
        } else if (score > 30 && score <= 60 && !badges.contains("Code Champ")) {
            badges.add("Code Champ");
        } else if (score > 60 && score <= 100 && !badges.contains("Code Master")) {
            badges.add("Code Master");
        }
    }
}
