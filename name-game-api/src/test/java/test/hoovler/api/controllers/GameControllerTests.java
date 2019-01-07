/*
 * Copyright (c) Michael Hoovler <hoovlermichael@gmail.com> 2019
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package test.hoovler.api.controllers;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoovler.api.controllers.GameController;
import com.hoovler.api.models.AnswerArgs;
import com.hoovler.api.models.AnswerResponseBody;
import com.hoovler.api.models.AskArgs;
import com.hoovler.api.models.AskResponseBody;
import com.hoovler.api.models.QuestionOption;
import com.hoovler.api.models.Subject;
import com.hoovler.api.utils.GameHelper;
import com.hoovler.dao.impl.DefaultProfileDao;
import com.hoovler.dao.impl.FullQuestions;
import com.hoovler.dao.impl.GameQuestions;
import com.hoovler.dao.impl.Players;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

class GameControllerTests {
	private static Logger log = LogManager.getLogger(GameControllerTests.class.getName());

	// static game objects
	//protected static GameController primaryController;
	protected static DefaultProfileDao profileService;
	protected static Players playerService;
	protected static FullQuestions fullQuestionService;
	protected static GameQuestions gameQuestionService;

	// for reset after each test
	protected static GameController controller;

	// primary endpoints
	protected static AskResponseBody askEndpointReponse;
	protected static AnswerResponseBody answerEndpointResponse;

	// leaderboard endpoint
	protected static ArrayList<Player> leadersEndpointResponse;

	// diagnostic endpoints
	protected static ArrayList<Question> fullQuestionsEndpointResponse;
	protected static ArrayList<AskResponseBody> gameQuestionsEndpointResponse;
	protected static ArrayList<Player> playersEndpointResponse;

	// primary endpoint comms
	protected static AskArgs askRequest;
	protected static AskResponseBody askResponse;
	protected static AnswerArgs answerRequest;
	protected static AnswerResponseBody answerResponse;

	protected static final String mattsPrefix = "mat";

	// test values
	protected static final String valEmptyString = "";
	protected static final String valInvalidAlpha = "10";
	protected static final String valInvalidNumeric = "num";
	protected static final String valInvalidId1 = "123456789";
	protected static final String valInvalidId2 = "-1";
	protected static final String valInvalidEmail = "0";
	protected static final String valInvalidTrueBool = "sounds great!";

	protected static final String valEmail1 = "player1@foo.com";
	protected static final String valEmail2 = "second.p@bar.com";
	protected static final String valEmail3 = "p3@bar.com";
	protected static final String valEmail4 = "foo@barTheFourth.com";
	protected static final String valEmail5 = "FIVE@five.com";
	protected static final String valEmail6 = "foo.6.bar@raboof.com";

	protected static final String valBoolTrue1 = "1";
	protected static final String valBoolTrue2 = "y";
	protected static final String valBoolTrue3 = "Yes";
	protected static final String valBoolTrue4 = "true";
	protected static final String valBoolTrue5 = "ok";
	protected static final String valBoolTrue6 = "+";

	protected static final String valBoolFalse1 = "0";
	protected static final String valBoolFalse2 = "-1";
	protected static final String valBoolFalse3 = "down";
	protected static final String valBoolFalse4 = "no";
	protected static final String valBoolFalse5 = "FALSE";
	protected static final String valBoolFalse6 = "absolutely not!";

	/*
	 * *************************************
	 * Before & After methods
	 ***************************************/

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mockNameGameApp();
	}

	@BeforeEach
	void setUp() throws Exception {
		clearValues();
	}

	/*
	 * *************************************
	 * Static test helper methods
	 ***************************************/

	/** Mock-up application initialization */
	private static void mockNameGameApp() {
		profileService = new DefaultProfileDao(true);
		playerService = new Players();
		fullQuestionService = new FullQuestions();
		gameQuestionService = new GameQuestions();

		controller = new GameController(profileService, playerService, fullQuestionService, gameQuestionService);
	}

	private static void clearValues() {
		askResponse = null;
		answerResponse = null;
		askRequest = null;
		answerRequest = null;
	}

	private static AskResponseBody generateTestQuestion(GameController controller, String email, String reverse,
			String matts) {
		return controller.ask(email, reverse, matts);
	}

	private static boolean isAskResponseValid(AskResponseBody askResponse) {
		return askResponse.getOptions() != null && askResponse.getTarget() != null
				&& StringUtils.isNotBlank(askResponse.getQuestionId());
	}

	/** Format the given parameters into a valid JSON string for the answer's request body.
	 *
	 * @param  email      the email
	 * @param  questionId the question id
	 * @param  answerId   the answer id
	 * @return            the string */
	private static String answerBodyJson(String email, String questionId, String answerId) {
		AnswerArgs answerBodyObj = new AnswerArgs();
		answerBodyObj.setAnswerId(answerId);
		answerBodyObj.setPlayerEmail(email);
		answerBodyObj.setQuestionId(questionId);

		String answerBody = GameHelper.toJson(answerBodyObj);
		log.info("answerBody = " + answerBody);
		return answerBody;
	}

	/** Get the correct answer given the ask endpoint's response body, and the email used to obtain it.
	 *
	 * @param  responseBody the object that models the JSON body of the ask endpoint's response object
	 * @param  email        the email string used to ask the question
	 * @return              the ID string of the correct answer */
	private static String correctAnswerId(AskResponseBody responseBody, String email) {
		Question fullQ = getFullQuestion(responseBody.getQuestionId(), email);
		return Subject.class.cast(fullQ.getTarget()).getId();
	}

	/** Gets the full question from the game question's ID, and the email used to ask the question.
	 *
	 * @param  gameQuestionId the game question id
	 * @param  email          the email string used to ask the question
	 * @return                the game question's corresponding full question, from which extra question data may be retrieved */
	private static Question getFullQuestion(String gameQuestionId, String email) {
		long qId = Long.parseLong(GameHelper.decodeFromHexWithSalt(gameQuestionId, email));
		return fullQuestionService.getQuestion(qId);
	}

	/** Checks if the body of an <em>/ask</em> endpoint was created with <code>reverse=true</code> AND <code>matts=true</code>
	 *
	 * @param  responseBody the object that models the JSON body of the ask endpoint's response object
	 * @return              true, if is reverse and matts */
	private static boolean isReverse(AskResponseBody askResponse) {
		if (isAskResponseValid(askResponse)) {
			// normal mode is 1 image URL, 6 names...
			return !StringUtils.startsWithIgnoreCase(askResponse.getTarget(), "http://");
		}
		return false;
	}

	/** Checks if the body of an <em>/ask</em> endpoint was created with <code>reverse=true</code> AND <code>matts=true</code>
	 *
	 * @param  responseBody the object that models the JSON body of the ask endpoint's response object
	 * @return              true, if is reverse and matts */
	private static boolean isMatts(AskResponseBody askResponse) {
		// if matts=true, then the target / names start with 'mat'
		if (isAskResponseValid(askResponse)) {
			// if reverse, just target
			if (isReverse(askResponse))
				return StringUtils.startsWithIgnoreCase(askResponse.getTarget(), "mat");

			// otherwise, ALL option names
			String[] opts = ArrayUtils.EMPTY_STRING_ARRAY;
			ArrayList<QuestionOption> options = askResponse.getOptions();
			for (QuestionOption option : options) {
				if (StringUtils.startsWithIgnoreCase(option.getOptionValue(), mattsPrefix))
					ArrayUtils.add(opts, option.getOptionValue());
			}
			return opts.length == GameHelper.NUM_OPTIONS;
		}
		return false;
	}

	/*
	 * *************************************
	 * Test cases.
	 ***************************************/

	@Test
	void testAskEndpointEmptyStrings() {

		// with all empty
		askResponse = generateTestQuestion(controller, valEmptyString, valEmptyString, valEmptyString);
		log.info(askResponse.getTarget());
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(!isReverse(askResponse));
		assertTrue(!isMatts(askResponse));

		// with just email
		askResponse = generateTestQuestion(controller, valEmail1, valEmptyString, valEmptyString);
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(!isReverse(askResponse));
		assertTrue(!isMatts(askResponse));

		// with email and reverse=false
		askResponse = generateTestQuestion(controller, valEmail1, valBoolFalse1, valEmptyString);
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(!isReverse(askResponse));
		assertTrue(!isMatts(askResponse));

		// with email and matts=false
		askResponse = generateTestQuestion(controller, valEmail1, valEmptyString, valBoolFalse1);
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(!isReverse(askResponse));
		assertTrue(!isMatts(askResponse));

	}

	@Test
	void testAskEndpointValidArgs() {
		// sending reverse=0, matts=0
		askResponse = generateTestQuestion(controller, valEmail1, valBoolFalse1, valBoolFalse1);
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(!isReverse(askResponse));
		assertTrue(!isMatts(askResponse));

		// sending reverse=1, matts=0
		askResponse = generateTestQuestion(controller, valEmail1, valBoolTrue1, valBoolFalse1);
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(isReverse(askResponse));
		assertTrue(!isMatts(askResponse));

		// sending reverse=Yes, matts=no
		askResponse = generateTestQuestion(controller, valEmail1, valBoolTrue3, valBoolFalse4);
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(!isMatts(askResponse));

		// sending reverse=+, matts=ok (true, true)
		askResponse = generateTestQuestion(controller, valEmail1, valBoolTrue6, valBoolTrue5);
		assertTrue(isAskResponseValid(askResponse));
		assertTrue(isReverse(askResponse));
		assertTrue(isMatts(askResponse));
	}

	@Test
	void testAskEndpointInvalidArgs() {

		// sending all false
		askResponse = generateTestQuestion(controller, valEmail1, valInvalidTrueBool, valBoolTrue1);
		assertTrue(isAskResponseValid(askResponse));

		// sending reverse=1, matts=0
		askResponse = generateTestQuestion(controller, valEmail1, valBoolTrue1, valBoolFalse1);
		assertTrue(isAskResponseValid(askResponse));

		// sending reverse=1, matts=1
		askResponse = generateTestQuestion(controller, valEmail1, valBoolTrue1, valBoolTrue1);
		assertTrue(isAskResponseValid(askResponse));
	}

	@Test
	void testCorrectAnswer() {
		askResponse = generateTestQuestion(controller, valEmail1, valBoolFalse1, valBoolFalse1);

		// get correct answer information
		String answerId = correctAnswerId(askResponse, valEmail1);
		String questionId = askResponse.getQuestionId();

		// get request body for the right answer, and submit
		String answerBody = answerBodyJson(valEmail1, questionId, answerId);
		answerResponse = controller.answer(answerBody);

		assertTrue(answerResponse.isCorrect());
	}

	@Test
	void testIncorrectAnswerWrongAnswerId() {
		askResponse = generateTestQuestion(controller, valEmail1, valBoolFalse1, valBoolFalse1);

		// get correct answer information
		String answerId = valInvalidId1;
		String questionId = askResponse.getQuestionId();

		// get request body with bad answer id, and submit
		String answerBody = answerBodyJson(valEmail1, questionId, answerId);
		answerResponse = controller.answer(answerBody);

		assertTrue(!answerResponse.isCorrect());
	}

/*	@Test
	void testIncorrectAnswerWrongQuestionId() {
		askResponse = generateTestQuestion(tempController, valEmail1, valBoolFalse1, valBoolFalse1);

		// get correct answer information
		String answerId = correctAnswerId(askResponse, valEmail1);
		String questionId = valInvalidId2;

		// get request body with a bad question ID, and submit
		String answerBody = answerBodyJson(valEmail1, questionId, answerId);
		answerResponse = tempController.answer(answerBody);

		assertTrue(!answerResponse.getResult());
	}*/

	@Test
	void testQuestionAlreadyAnswered() {
		askResponse = generateTestQuestion(controller, valEmail1, valBoolFalse1, valBoolFalse1);

		// get correct answer information
		String answerId = correctAnswerId(askResponse, valEmail1);
		String questionId = askResponse.getQuestionId();

		// get request body for the right answer, and submit
		String answerBody = answerBodyJson(valEmail1, questionId, answerId);
		answerResponse = controller.answer(answerBody);
		answerResponse = controller.answer(answerBody);
		assertTrue(!answerResponse.isCorrect());
	}

	@Test
	void testBadAnswerBodyFormat() {
		askResponse = generateTestQuestion(controller, valEmail1, valBoolFalse1, valBoolFalse1);

		// submit wrong object model
		String answerBody = askResponse.toString();
		answerResponse = controller.answer(answerBody);

		assertTrue(!answerResponse.isCorrect());
	}


}
