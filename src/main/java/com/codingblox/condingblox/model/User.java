package com.codingblox.condingblox.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    final Long id;
    final String userName;
    final List<Contest> attendedContests;
    final List<Contest> conductedContests;
    Double score;

    public User(final Long id, final String userName) {
        this.id = id;
        this.userName = userName;
        this.score = 1500.0;
        this.attendedContests = new ArrayList<>();
        this.conductedContests = new ArrayList<>();
    }

    public void updateScore(Double newScore) {
        this.score = newScore;
    }
}
