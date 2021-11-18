package com.codingblox.condingblox.model;

import com.codingblox.condingblox.model.enums.QuestionDifficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Question {
    Long id;
    QuestionDifficulty difficultyLevel;
    Double score;
    String questionText;
}
