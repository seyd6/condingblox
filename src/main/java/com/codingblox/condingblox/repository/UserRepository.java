package com.codingblox.condingblox.repository;

import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.LeaderBoard;
import com.codingblox.condingblox.model.User;
import com.codingblox.condingblox.model.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    Long userIdCounter;
    List<User> users;
    LeaderBoard leaderBoard;

    public UserRepository() {
        this.userIdCounter = Long.valueOf(1);
        this.users = new ArrayList<>();
        this.leaderBoard = new LeaderBoard();
    }

    public void addUser(User user) {
        users.add(user);
        leaderBoard.addUser(user);
        userIdCounter++;
    }

    public List<User> getUsers() {
        return users;
    }

    public Long getUserIdCounter() {
        return userIdCounter;
    }

    public boolean userNameExists(String userName) {
        return users
                .stream()
                .anyMatch(user -> user.getUserName().equals(userName));
    }

    public User getUser(String userName) throws UserNotFoundException {
        return users
                .stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with given username not found"));
    }

    public List<Contest> getContestsConductedByUser(String userName) throws Exception {
        User user = getUser(userName);

        return user.getConductedContests();
    }

    public List<Contest> getContestsAttendedByUser(String userName) throws Exception {
        User user = getUser(userName);

        return user.getAttendedContests();
    }

    public List<User> getLeaderboardAscending() {
        return leaderBoard.ascending();
    }

    public List<User> getLeaderboardDescending() {
        return leaderBoard.descending();
    }
}
