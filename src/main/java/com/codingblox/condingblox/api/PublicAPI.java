package com.codingblox.condingblox.api;

import com.codingblox.condingblox.model.ErrorResponse;
import com.codingblox.condingblox.service.ContestService;
import com.codingblox.condingblox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class PublicAPI {
    @Autowired
    UserService userService;

    @Autowired
    ContestService contestService;

    @RequestMapping(path = "create-user/{name}",
                    method = RequestMethod.GET)
    public ResponseEntity createUser(@PathVariable("name") String name) {
        Object response = null;
        try {
            response = userService.createUser(name);
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "get-users",
            method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        Object response = null;
        try {
            response = userService.getUsers();
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "create-questions/{level}",
                    method = RequestMethod.GET)
    public ResponseEntity createQuestions(@PathVariable("level") String difficultyLevel) {
        Object response = null;
        try {
            response = contestService.createQuestions(difficultyLevel.toUpperCase());
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "get-questions",
                    method = RequestMethod.GET)
    public ResponseEntity getQuestions() {
        Object response = null;
        try {
            response = contestService.getQuestions();
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "get-questions/{level}",
                    method = RequestMethod.GET)
    public ResponseEntity getQuestions(@PathVariable("level") String difficultyLevel) {
        Object response = null;
        try {
            response = contestService.getQuestions(difficultyLevel.toUpperCase());
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "create-contest/{name}/{level}/{creator}",
                    method = RequestMethod.GET)
    public ResponseEntity createContest(@PathVariable("name") String contestName,
                                 @PathVariable("level") String difficultyLevel,
                                 @PathVariable("creator") String creatorUserName) {
        Object response = null;
        try {
            response = contestService.createContest(contestName, difficultyLevel.toUpperCase(), creatorUserName);
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "get-contests",
                    method = RequestMethod.GET)
    public ResponseEntity getContests() {
        Object response = null;
        try {
            response = contestService.getContests();
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "get-contests/{level}",
                    method = RequestMethod.GET)
    public ResponseEntity getContest(@PathVariable("level") String difficultyLevel) {
        Object response = null;
        try {
            response = contestService.getContests(difficultyLevel.toUpperCase());
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "attend-contest/{id}/{name}",
                    method = RequestMethod.GET)
    public ResponseEntity attendContest(@PathVariable("id") Long contestId,
                                 @PathVariable("name") String userName) {
        Object response = null;
        try {
            response = contestService.attendContest(contestId, userName);
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "withdraw-contest/{id}/{name}",
            method = RequestMethod.GET)
    public ResponseEntity withdrawContest(@PathVariable("id") Long contestId,
                                   @PathVariable("name") String userName) {
        Object response = null;
        try {
            response = contestService.withdrawContest(contestId, userName);
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "run-contest/{id}/{name}",
                    method = RequestMethod.GET)
    public ResponseEntity runContest(@PathVariable("id") Long contestId,
                           @PathVariable("name") String creatorUserName) {
        Object response = null;
        try {
            response = contestService.runContest(contestId, creatorUserName);
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "leaderboard/{order}",
                    method = RequestMethod.GET)
    public ResponseEntity leaderboard(@PathVariable("order") String sortingOrder) {
        Object response = null;
        try {
            response = userService.getLeaderboard(sortingOrder.toUpperCase());
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "contest-history/{id}",
                    method = RequestMethod.GET)
    public ResponseEntity contestHistory(@PathVariable("id") Long contestId) {
        Object response = null;
        try {
            response = contestService.contestHistory(contestId);
        } catch (Exception e) {
            response = new ErrorResponse(e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/error",
                    method = RequestMethod.GET)
    public ResponseEntity error() {
        return ResponseEntity.ok(new ErrorResponse("Invalid api call"));
    }
}
