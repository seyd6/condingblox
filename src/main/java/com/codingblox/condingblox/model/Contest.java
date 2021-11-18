package com.codingblox.condingblox.model;

import com.codingblox.condingblox.model.enums.ContestDifficulty;
import lombok.Getter;

import java.util.*;

@Getter
public class Contest {
    final Long id;
    final String contestName;
    final User creator;
    final ContestDifficulty difficultyLevel;
    final List<Question> questions;
    final Map<User, Double> contestants;
    final Map<User, Set<Question>> questionsSolvedByContestant;

    public Contest(final Long id, final String contestName, final User creator, final ContestDifficulty difficultyLevel) {
        this.id = id;
        this.contestName = contestName;
        this.creator = creator;
        this.difficultyLevel = difficultyLevel;
        this.questions = new ArrayList<>();
        this.contestants = new HashMap<>();
        this.questionsSolvedByContestant = new HashMap<>();
    }

    public void addContestant(User user) {
        contestants.put(user, 0.0);
        questionsSolvedByContestant.put(user, new HashSet<>());
    }

    public void updateContestantScore(User user, Double score) {
        contestants.put(user, score);
    }
}
