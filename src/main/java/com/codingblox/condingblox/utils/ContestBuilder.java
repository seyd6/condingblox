package com.codingblox.condingblox.utils;

import com.codingblox.condingblox.api.PublicAPI;
import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.Question;
import com.codingblox.condingblox.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

public class ContestBuilder {
    @Autowired
    ContestService contestService;

    public Contest buildContest(Contest contest) {
        List<Question> questions = contestService.getQuestions(contest.getDifficultyLevel().toString());

        contest.getQuestions().addAll(questions);
        contest.getContestants().put(contest.getCreator(), 0.0);

        return contest;
    }
}
