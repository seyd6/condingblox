package com.codingblox.condingblox.utils;

import com.codingblox.condingblox.model.Question;
import com.codingblox.condingblox.model.enums.QuestionDifficulty;

import java.util.UUID;

public class QuestionFactory {
    public Question createQuestion(int i, QuestionDifficulty difficultyLevel) {
        return new Question(Long.valueOf(i), difficultyLevel, 100 * Math.random(), UUID.randomUUID().toString());
    }
}
