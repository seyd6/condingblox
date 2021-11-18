package com.codingblox.condingblox.utils.impl;

import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.enums.ContestDifficulty;
import com.codingblox.condingblox.repository.ContestRepository;
import com.codingblox.condingblox.repository.UserRepository;
import com.codingblox.condingblox.utils.ScoringStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class DefaultScoringStrategy implements ScoringStrategy {
    @Autowired
    ContestRepository contestRepository;

    @Autowired
    UserRepository userRepository;

    private final Map<ContestDifficulty, Integer> deductions;

    public DefaultScoringStrategy() {
        this.deductions = Map.ofEntries(
                entry(ContestDifficulty.LOW, 50),
                entry(ContestDifficulty.MEDIUM, 30),
                entry(ContestDifficulty.HIGH, 0)
        );
    }

    @Override
    public void score(Contest contest) {
        contest
                .getContestants()
                .keySet()
                .stream()
                .forEach((contestant) -> {
                    Double contestScore = Double.valueOf(contest.getQuestionsSolvedByContestant().get(contestant).size()
                                                            - deductions.get(contest.getDifficultyLevel()));

                    contest.updateContestantScore(contestant, contestScore);
                    contestant.updateScore(contestant.getScore() + contestScore);
                });
    }
}
