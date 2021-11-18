package com.codingblox.condingblox.repository;

import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.Question;
import com.codingblox.condingblox.model.User;
import com.codingblox.condingblox.model.enums.ContestDifficulty;
import com.codingblox.condingblox.model.enums.QuestionDifficulty;
import com.codingblox.condingblox.model.exception.ContestNotFoundException;
import com.sun.source.doctree.SeeTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Repository
public class ContestRepository {
    Long contestIdCounter;
    Map<ContestDifficulty, List<Contest>> contests;
    Map<QuestionDifficulty, List<Question>> questions;

    @Autowired
    UserRepository userRepository;

    public ContestRepository() {
        this.contestIdCounter = Long.valueOf(1);
        this.contests = Map.ofEntries(
                entry(ContestDifficulty.LOW, new ArrayList<>()),
                entry(ContestDifficulty.MEDIUM, new ArrayList<>()),
                entry(ContestDifficulty.HIGH, new ArrayList<>())
        );
        this.questions = Map.ofEntries(
                entry(QuestionDifficulty.LOW, new ArrayList<>()),
                entry(QuestionDifficulty.MEDIUM, new ArrayList<>()),
                entry(QuestionDifficulty.HIGH, new ArrayList<>())
        );
    }

    public void addQuestion(QuestionDifficulty questionDifficulty, Question question) {
        questions.get(questionDifficulty).add(question);
    }

    public boolean contestNameExists(String contestName) {
        return contests
                .values()
                .stream()
                .flatMap(List::stream)
                .anyMatch(contest -> contest.getContestName().equals(contestName));
    }

    public void addContest(Contest contest) {
        contests.get(contest.getDifficultyLevel()).add(contest);
        contestIdCounter++;
    }

    public Long getContestIdCounter() {
        return contestIdCounter;
    }

    public List<Question> getQuestions() {
        return questions
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Question> getQuestions(QuestionDifficulty questionDifficulty) {
        return questions.get(questionDifficulty);
    }

    public List<Contest> getContests() {
        return contests
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Contest> getContests(ContestDifficulty contestDifficulty) {
        return contests.get(contestDifficulty);
    }

    public Contest getContestById(Long contestId) throws ContestNotFoundException {
        return contests
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(contest -> contest.getId().equals(contestId))
                .findFirst()
                .orElseThrow(() -> new ContestNotFoundException("Contest with entered name not found"));
    }

    public Map<User, Double> getContestantsByContestId(Long contestId) throws Exception {
        Contest contest = getContestById(contestId);

        return contest.getContestants();
    }
}