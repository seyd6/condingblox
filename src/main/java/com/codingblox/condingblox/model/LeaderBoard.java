package com.codingblox.condingblox.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderBoard {
    final Long id;
    final List<User> leaderboard;

    public LeaderBoard() {
        this.id = Long.valueOf(1);
        this.leaderboard = new ArrayList<>();
    }

    public void addUser(User user) {
        leaderboard.add(user);
    }

    public List<User> ascending() {
        Collections.sort(leaderboard, (a, b) -> (int)(a.getScore() - b.getScore()));
        return leaderboard;
    }

    public List<User> descending() {
        Collections.sort(leaderboard, (a, b) -> (int)(b.getScore() - a.getScore()));
        return leaderboard;
    }
}
