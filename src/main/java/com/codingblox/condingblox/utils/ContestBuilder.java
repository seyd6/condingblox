package com.codingblox.condingblox.utils;

import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.Question;
import com.codingblox.condingblox.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configurable
public class ContestBuilder {
    final ContestService contestService;

    public ContestBuilder(final ContestService contestService) {
        this.contestService = contestService;
    }

    public Contest buildContest(Contest contest) {
        List<Question> questions = contestService.getQuestions(contest.getDifficultyLevel().toString());

        contest.getQuestions().addAll(questions);
        contest.addContestant(contest.getCreator());

        return contest;
    }
}
