/*
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2019
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
package com.hoovler.api.controllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hoovler.api.models.AnswerArgs;
import com.hoovler.api.models.AskArgs;
import com.hoovler.api.resources.AnswerQuestion;
import com.hoovler.api.resources.AskQuestion;
import com.hoovler.api.resources.Players;
import com.hoovler.api.resources.Questions;
import com.hoovler.api.utils.NameGameHelper;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

// TODO: Auto-generated Javadoc
/** The Class GameController. */
@RestController
@RequestMapping(NameGameHelper.API_PREFIX + NameGameHelper.API_VERSION)
public class GameController {
	private static Logger log = LogManager.getLogger(GameController.class.getName());

	/** The standard URI path separator. */
	protected static final String pathSep = "/";

	/** The character denoting the start of a URI path variable. */
	protected static final String pathVarOpen = "{";

	/** The character denoting the end of a URI path variable. */
	protected static final String pathVarClose = "}";

	/** The default value for missing or empty String parameters. */
	protected static final String defaultAlpha = "";

	/** The default value for missing or empty numeric parameters, including boolean params. */
	protected static final String defaultNumeric = "0";

	/** The request parameter name for the player email string value. */
	protected static final String emailParam = "email";

	/** The request parameter name for the selected mode integer value. */
	protected static final String reverseParam = "reverse";

	/** The request parameter name for the matts boolean value. */
	protected static final String mattsParam = "matts";

	// protected static final String questionIdParam = "question_id";

	/** The Constant answerIdParam. */
	protected static final String answerIdBodyParam = "answer_id";

	/** The Constant questionIdBodyParam. */
	protected static final String questionIdBodyParam = "question_id";

	/** The Constant askResource. */
	protected static final String askResource = "ask";

	/** The Constant answerResource. */
	protected static final String answerResource = "answer";

	/** The Constant questionsResource. */
	protected static final String questionsResource = "questions";

	/** The Constant playersResource. */
	protected static final String playersResource = "players";

	/** The Constant askPath. */
	protected static final String askPath = pathSep + askResource;

	/** The Constant questionsPath. */
	protected static final String questionsPath = pathSep + questionsResource;

	/** The Constant playersPath. */
	protected static final String playersPath = pathSep + playersResource;

	/** The Constant altAnswerPath. */
	protected static final String altAnswerPath = pathSep + answerResource;

	/** * Private controller resources. */

	private final DefaultProfileDao profileService;
	private final Players playerService;
	private final Questions questionService;

	// ******************** constructors ********************

	/** Instantiates a new game controller.
	 *
	 * @param profileService  the profile service
	 * @param playerService   the player service
	 * @param questionService the question service */
	@Autowired
	public GameController(DefaultProfileDao profileService, Players playerService, Questions questionService) {
		this.profileService = profileService;
		this.playerService = playerService;
		this.questionService = questionService;
	}

	// ******************** standard game endpoints ********************

	/** Ask.
	 *
	 * @param  playerEmail the player email
	 * @param  reverse     whether or not to reverse the question
	 * @param  mattsOnly   the matts only
	 * @return             the ask question */
	@GetMapping(path = askPath)
	public AskQuestion ask(@RequestParam(value = emailParam, defaultValue = defaultAlpha) String playerEmail,
			@RequestParam(value = reverseParam, defaultValue = defaultNumeric) String reverse,
			@RequestParam(value = mattsParam, defaultValue = defaultNumeric) String mattsOnly) {
		AskArgs askParams = new AskArgs(playerEmail, reverse, mattsOnly);

		log.info("Receiving GET params as request for a new Question object: ");
		log.info(emailParam + " = " + askParams.getPlayerEmail());
		log.info(reverseParam + " = " + askParams.getMode());
		log.info(mattsParam + " = " + askParams.getMattsOnly());

		// init a new Ask Response
		return new AskQuestion(askParams, this.profileService, this.playerService, this.questionService);
	}

	/** Answer.
	 *
	 * @param  payload the payload
	 * @return         the answer question */
	@PostMapping(path = altAnswerPath)
	public AnswerQuestion answer(@RequestBody String payload) {
		AnswerArgs answerBody = new AnswerArgs();

		JsonElement jBody = new JsonParser().parse(payload);
		JsonObject args = jBody.getAsJsonObject();

		answerBody.setAnswerId(args.get(answerIdBodyParam).getAsString());
		answerBody.setPlayerEmail(args.get(emailParam).getAsString());
		answerBody.setQuestionId(args.get(questionIdBodyParam).getAsString());

		// log POST values
		log.info("Receiving POST values in answer to a question: ");
		log.info(answerIdBodyParam + " = " + answerBody.getAnswerId());
		log.info(emailParam + " = " + answerBody.getPlayerEmail());
		log.info(questionIdBodyParam + " = " + answerBody.getQuestionId());

		return new AnswerQuestion(answerBody, this.playerService, this.questionService);
	}

	// ******************** diagnostic endpoints ********************

	/** Return a list of all Question objects in their full format.
	 *
	 * @param  start the starts
	 * @param  stop  the stop
	 * @return       the array list */
	@RequestMapping(path = questionsPath)
	public ArrayList<Question> questions(@RequestParam(value = reverseParam, defaultValue = defaultNumeric) int start,
			@RequestParam(value = reverseParam, defaultValue = defaultNumeric) int stop) {
		return questionService.questionList();
	}

	/** Players.
	 *
	 * @return the array list */
	@RequestMapping(path = playersPath)
	public ArrayList<Player> players() {
		return playerService.playerList();
	}
}
