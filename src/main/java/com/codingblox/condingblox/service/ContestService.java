package com.codingblox.condingblox.service;

import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.Question;
import com.codingblox.condingblox.model.User;
import com.codingblox.condingblox.model.enums.ContestDifficulty;
import com.codingblox.condingblox.model.enums.QuestionDifficulty;
import com.codingblox.condingblox.model.exception.ContestNameAlreadyExists;
import com.codingblox.condingblox.model.exception.UserNotRegisteredToContest;
import com.codingblox.condingblox.repository.ContestRepository;
import com.codingblox.condingblox.repository.UserRepository;
import com.codingblox.condingblox.utils.ContestBuilder;
import com.codingblox.condingblox.utils.ContestRunnerStrategy;
import com.codingblox.condingblox.utils.impl.DefaultContestRunnerStrategy;
import com.codingblox.condingblox.utils.QuestionFactory;
import com.codingblox.condingblox.utils.impl.DefaultScoringStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ContestService {
    private QuestionFactory questionFactory;
    private ContestBuilder contestBuilder;
    private ContestRunnerStrategy contestRunnerStrategy;

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    UserRepository userRepository;

    public ContestService() {
        this.questionFactory = new QuestionFactory();
        this.contestBuilder = new ContestBuilder();
        this.contestRunnerStrategy = new DefaultContestRunnerStrategy(new DefaultScoringStrategy());
    }

    public List<Question> createQuestions(String difficultyLevel) {
        QuestionDifficulty questionDifficulty = QuestionDifficulty.valueOf(difficultyLevel);
        List<Question> questions = new ArrayList<>();

        IntStream.range(1, 10)
                .forEach((i) -> {
                    Question question = questionFactory.createQuestion(i, questionDifficulty);

                    contestRepository.addQuestion(questionDifficulty, question);
                    questions.add(question);
                });

        return questions;
    }

    public List<Question> getQuestions() {
        return contestRepository.getQuestions();
    }

    public List<Question> getQuestions(String difficultyLevel) {
        return contestRepository.getQuestions(QuestionDifficulty.valueOf(difficultyLevel));
    }

    public Contest createContest(String contestName, String difficultyLevel, String creatorUserName) throws Exception {
        if (contestRepository.contestNameExists(contestName))
            throw new ContestNameAlreadyExists("Contest name already exists, please pick a different name");

        Long contestId = contestRepository.getContestIdCounter();
        ContestDifficulty contestDifficulty = ContestDifficulty.valueOf(difficultyLevel);
        User creator = userRepository.getUser(creatorUserName);

        Contest contest = new Contest(contestId, contestName, creator, contestDifficulty);
        contestBuilder.buildContest(contest);
        contestRepository.addContest(contest);

        return contest;
    }

    public List<Contest> getContests() {
        return contestRepository.getContests();
    }

    public List<Contest> getContests(String difficultyLevel) {
        return contestRepository.getContests(ContestDifficulty.valueOf(difficultyLevel));
    }

    public Contest attendContest(Long contestId, String userName) throws Exception{
        Contest contest = contestRepository.getContestById(contestId);
        User user = userRepository.getUser(userName);

        contest.addContestant(user);

        return contest;
    }

    public Contest withdrawContest(Long contestId, String userName) throws Exception{
        Contest contest = contestRepository.getContestById(contestId);
        User user = userRepository.getUser(userName);

        if (!contest.getContestants().containsKey(user))
            throw new UserNotRegisteredToContest("The given user is not registered to the contest");

        contest.getContestants().remove(user);
        contest.getQuestionsSolvedByContestant().remove(user);

        return contest;
    }

    public void runContest(Long contestId, String creatorUserName) throws Exception {
        Contest contest = contestRepository.getContestById(contestId);
        User creator = userRepository.getUser(creatorUserName);

        contestRunnerStrategy.runContest(contest);
    }

    public Contest contestHistory(Long contestId) throws Exception {
        return contestRepository.getContestById(contestId);
    }

}
