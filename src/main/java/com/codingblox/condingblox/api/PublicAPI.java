package com.codingblox.condingblox.api;

import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.Question;
import com.codingblox.condingblox.model.User;
import com.codingblox.condingblox.model.enums.QuestionDifficulty;
import com.codingblox.condingblox.service.ContestService;
import com.codingblox.condingblox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class PublicAPI {
    @Autowired
    UserService userService;

    @Autowired
    ContestService contestService;

    @RequestMapping(path = "create-user",
                    method = RequestMethod.POST)
    public User createUser(String userName) {
        User user = null;
        try {
            user = userService.createUser(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @RequestMapping(path = "create-question",
                    method = RequestMethod.POST)
    public List<Question> createQuestions(String difficultyLevel) {
        return contestService.createQuestions(difficultyLevel);
    }

    @RequestMapping(path = "list-questions",
                    method = RequestMethod.GET)
    public List<Question> listQuestions() {
        return contestService.getQuestions();
    }

    @RequestMapping(path = "list-questions-level",
                    method = RequestMethod.GET)
    public List<Question> listQuestions(String difficultyLevel) {
        return contestService.getQuestions(difficultyLevel);
    }

    @RequestMapping(path = "create-contest",
                    method = RequestMethod.POST)
    public Contest createContest(String contestName, String difficultyLevel, String creatorUserName) {
        Contest contest = null;
        try {
            contest = contestService.createContest(contestName, difficultyLevel, creatorUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contest;
    }

    @RequestMapping(path = "list-contest",
                    method = RequestMethod.GET)
    public List<Contest> listContest() {
        return contestService.getContests();
    }

    @RequestMapping(path = "list-contest-level",
                    method = RequestMethod.GET)
    public List<Contest> listContest(String difficultyLevel) {
        return contestService.getContests(difficultyLevel);
    }

    @RequestMapping(path = "attend-contest",
                    method = RequestMethod.POST)
    public Contest attendContest(Long contestId, String userName) {
        Contest contest = null;
        try {
            contest = contestService.attendContest(contestId, userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contest;
    }

    @RequestMapping(path = "withdraw-contest",
            method = RequestMethod.POST)
    public Contest withdrawContest(Long contestId, String userName) {
        Contest contest = null;
        try {
            contest = contestService.withdrawContest(contestId, userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contest;
    }

    @RequestMapping(path = "run-contest",
                    method = RequestMethod.POST)
    public void runContest(Long contestId, String creatorUserName) {
        try {
            contestService.runContest(contestId, creatorUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "leaderboard",
                    method = RequestMethod.POST)
    public List<User> leaderboard(String sortingOrder) {
        return userService.getLeaderboard(sortingOrder);
    }

    @RequestMapping(path = "contest-history",
                    method = RequestMethod.POST)
    public Contest contestHistory(Long contestId) {
        Contest contest = null;
        try {
            contest = contestService.contestHistory(contestId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contest;
    }
}
