package com.codingblox.condingblox.utils.impl;

import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.Question;
import com.codingblox.condingblox.model.User;
import com.codingblox.condingblox.utils.ContestRunnerStrategy;
import com.codingblox.condingblox.utils.ScoringStrategy;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class DefaultContestRunnerStrategy implements ContestRunnerStrategy {
    private final ScoringStrategy scoringStrategy;

    public DefaultContestRunnerStrategy(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    @Override
    public void runContest(Contest contest) {
        List<Question> questions = contest.getQuestions();
        Set<User> contestants = contest.getContestants().keySet();

        contestants
                .stream()
                .forEach((contestant) -> {
                    IntStream.rangeClosed(1, (int) (questions.size() * Math.random()))
                                    .forEach(i -> contest.getQuestionsSolvedByContestant()
                                                            .get(contestant)
                                                            .add(questions.get( (int) (questions.size() * Math.random()))));
                    contestant.updateScore(contest
                                            .getQuestionsSolvedByContestant()
                                            .get(contestant)
                                            .stream()
                                            .mapToDouble(question -> question.getScore())
                                            .sum());
                });

        scoringStrategy.score(contest);
    }
}
