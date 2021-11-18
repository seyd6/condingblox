package com.codingblox.condingblox.service;

import com.codingblox.condingblox.model.User;
import com.codingblox.condingblox.model.exception.UserNameAlreadyExistsException;
import com.codingblox.condingblox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(String userName) throws Exception{
        if (userRepository.userNameExists(userName))
            throw new UserNameAlreadyExistsException("Entered username already exists, please pick a new username");

        User user = new User(userRepository.getUserIdCounter(), userName);
        userRepository.addUser(user);

        return user;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public List<User> getLeaderboard(String sortingOrder) {
        if (sortingOrder.equals("DESC"))
            return userRepository.getLeaderboardDescending();

        return userRepository.getLeaderboardAscending();
    }

}
