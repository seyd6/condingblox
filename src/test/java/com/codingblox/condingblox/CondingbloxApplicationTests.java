package com.codingblox.condingblox;

import com.codingblox.condingblox.api.PublicAPI;
import com.codingblox.condingblox.model.Contest;
import com.codingblox.condingblox.model.ErrorResponse;
import com.codingblox.condingblox.model.Question;
import com.codingblox.condingblox.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CondingbloxApplicationTests {
	@Autowired
	PublicAPI api;

	@BeforeEach
	public void setup() {
		setupUserData();
		setupContestData();
	}

	private void setupUserData() {
		List.of("jesus", "mohammed", "buddha", "hanuman", "helena", "zeus", "heracles")
				.stream()
				.forEach((userName) -> api.createUser(userName));
	}

	private void setupContestData() {
		api.createContest("jan", "low", "helena");
		api.createContest("feb", "high", "heracles");
		api.createContest("september", "high", "jesus");
	}

	@Test
	public void usersTest() {
		String userName = "jammy";

		assertEquals(((User) api.createUser(userName).getBody()).getUserName(), userName);
		assertTrue(((ErrorResponse) api.createUser(userName).getBody()).getMessage().equals("Entered username already exists, please pick a new username"));
		assertFalse(((List<User>) api.getUsers().getBody()).isEmpty());
	}

	@Test
	public void questionTest() {
		String difficulty = "medium";

		assertFalse(((List<Question>) api.getQuestions().getBody()).isEmpty());
		assertFalse(((List<Question>) api.createQuestions(difficulty).getBody()).isEmpty());
	}

	@Test
	public void createContestTest() {
		List<Contest> contests = (List<Contest>) api.getContests().getBody();
		String contestName = "october";
		String creatorUserName = "mohammed";
		String difficulty = "medium";

		assertTrue(((List<Contest>) api.getContest(difficulty).getBody()).isEmpty());
		assertEquals(((Contest) api.createContest(contestName, difficulty, creatorUserName).getBody()).getContestName(), contestName);
		assertTrue(((ErrorResponse) api.createContest("october", "low", "mohammed")
				.getBody())
				.getMessage()
				.equals("Contest name already exists, please pick a different name"));
		assertTrue(((ErrorResponse) api.createContest("may", "low", "jumba")
				.getBody())
				.getMessage()
				.equals("User with given username not found"));
	}

	@Test
	public void runContestTest() {
		List<Contest> contests = (List<Contest>) api.getContests().getBody();
		List<User> contestants = (List<User>) api.getUsers().getBody();

		assertEquals(((Contest) api.attendContest(contests.get(0).getId(), contestants.get(0).getUserName()).getBody()).getContestName(),
					contests.get(0).getContestName());
		assertEquals(((Contest) api.attendContest(contests.get(0).getId(), contestants.get(2).getUserName()).getBody()).getContestName(),
				contests.get(0).getContestName());
		assertEquals(((Contest) api.attendContest(contests.get(0).getId(), contestants.get(4).getUserName()).getBody()).getContestName(),
				contests.get(0).getContestName());
		assertEquals(((Contest) api.attendContest(contests.get(1).getId(), contestants.get(5).getUserName()).getBody()).getContestName(),
				contests.get(1).getContestName());
		assertEquals(((Contest) api.attendContest(contests.get(1).getId(), contestants.get(1).getUserName()).getBody()).getContestName(),
				contests.get(1).getContestName());

		assertEquals(((Contest) api.withdrawContest(contests.get(0).getId(), contestants.get(4).getUserName()).getBody()).getContestName(),
				contests.get(0).getContestName());

		Contest contest = (Contest) api.runContest(contests.get(0).getId(), contests.get(0).getCreator().getUserName()).getBody();

		assertTrue(((Contest) api.contestHistory(contest.getId()).getBody()).getId()
					.equals(contest.getId()));
	}
}
