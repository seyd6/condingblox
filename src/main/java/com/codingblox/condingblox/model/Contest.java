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
    final Map<User, List<Question>> questionsSolvedByContestant;

    public Contest(final Long id, final String contestName, final User creator, final ContestDifficulty difficultyLevel) {
        this.id = id;
        this.contestName = contestName;
        this.creator = creator;
        this.difficultyLevel = difficultyLevel;
        this.questions = new ArrayList<>();
        this.contestants = Map.of(creator, 0.0);
        this.questionsSolvedByContestant = Map.of(creator, new ArrayList<>());
    }

    public void addContestant(User user) {
        contestants.put(user, 0.0);
        questionsSolvedByContestant.put(user, new ArrayList<>());
    }

    public void updateContestantScore(User user, Double score) {
        contestants.put(user, score);
    }

    public void questionSolved(User user, Question question) {
        questionsSolvedByContestant.get(user).add(question);
    }
}
