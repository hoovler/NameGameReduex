/*
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2018
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoovler.api.models.answer.AnswerArgs;
import com.hoovler.api.models.ask.AskArgs;
import com.hoovler.api.resources.AnswerQuestion;
import com.hoovler.api.resources.AskQuestion;
import com.hoovler.api.resources.Players;
import com.hoovler.api.resources.Questions;
import com.hoovler.api.utils.GameUtils;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

// TODO: Auto-generated Javadoc
/** The Class GameController. */
@RestController
@RequestMapping(GameUtils.API_PREFIX + GameUtils.API_VERSION)
public class GameController {

	/** The log. */
	private static Logger log = LogManager.getLogger(GameController.class.getName());

	/** The standard URI path separator. */
	protected static final String pathSep = "/";

	/**  The character denoting the start of a URI path variable. */
	protected static final String pathVarOpen = "{";

	/** The character denoting the end of a URI path variable. */
	protected static final String pathVarClose = "}";

	/**  The default value for missing or empty String parameters. */
	protected static final String defaultAlpha = "";

	/** The default value for missing or empty numeric parameters, including boolean params. */
	protected static final String defaultNumeric = "0";

	/**  The request parameter name for the player email string value. */
	protected static final String emailParam = "email";

	/**  The request parameter name for the selected mode integer value. */
	protected static final String modeParam = "mode";

	/**  The request parameter name for the matts boolean value. */
	protected static final String mattsParam = "matts";

	/**  The URI path variable's parameter name in an Answer POST. */
	protected static final String qIdParam = "q_id";

	// protected static final String questionIdParam = "question_id";

	/** The Constant answerIdParam. */
	protected static final String answerIdParam = "answer_id";

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

	/** The Constant questionPath. */
	protected static final String questionPath = pathSep + questionsResource + pathSep + pathVarOpen + qIdParam
			+ pathVarClose;

	/** The Constant playersPath. */
	protected static final String playersPath = pathSep + playersResource;
	
	/** The Constant playerPath. */
	protected static final String playerPath = pathSep + playersResource + pathSep + pathVarOpen + emailParam
			+ pathVarClose;
	
	/** The Constant answerPath. */
	protected static final String answerPath = pathSep + answerResource + pathSep + pathVarOpen + qIdParam
			+ pathVarClose;

	


	// ******************** private controller resources ********************

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
	 * @param  mode        the mode
	 * @param  mattsOnly   the matts only
	 * @return             the ask question */
	@GetMapping(path = askPath)
	public AskQuestion ask(@RequestParam(value = emailParam, defaultValue = defaultAlpha) String playerEmail,
			@RequestParam(value = modeParam, defaultValue = defaultNumeric) int mode,
			@RequestParam(value = mattsParam, defaultValue = defaultNumeric) String mattsOnly) {
		AskArgs askParams = new AskArgs(playerEmail, mode, mattsOnly);

		log.info("Receiving GET params as request for a new Question object: ");
		log.info(emailParam + " = " + askParams.getPlayerEmail());
		log.info(modeParam + " = " + askParams.getMode());
		log.info(mattsParam + " = " + askParams.getMattsOnly());

		// init a new Ask Response
		return new AskQuestion(askParams, this.profileService, this.playerService, this.questionService);
	}

	/** Answer.
	 *
	 * @param  questionId the question id
	 * @param  answerBody the answer body
	 * @return            the answer question */
	@PostMapping(path = answerPath)
	public AnswerQuestion answer(@PathVariable(qIdParam) long questionId, @RequestBody AnswerArgs answerBody) {
		answerBody.setQuestionId(questionId);

		// log POST values
		log.info("Receiving POST values in answer to a question: ");
		log.info(answerIdParam + " = " + answerBody.getAnswerId());
		log.info(emailParam + " = " + answerBody.getPlayerEmail());
		log.info(qIdParam + " = " + answerBody.getQuestionId());

		return new AnswerQuestion(answerBody, this.playerService, this.questionService);
	}

	// ******************** diagnostic endpoints ********************

	/** Return a list of all Question objects in their full format.
	 *
	 * @param  start the start
	 * @param  stop  the stop
	 * @return       the array list */
	@RequestMapping(path = questionsPath)
	public ArrayList<Question> questions(@RequestParam(value = modeParam, defaultValue = defaultNumeric) int start,
			@RequestParam(value = modeParam, defaultValue = defaultNumeric) int stop) {
		return questionService.questionList();
	}

	/** Given the ID, return a single Question object in it's full format. 
	 *
	 * @param  qId the question's id
	 * @return     the fully-formatted Question object */
	@RequestMapping(path = questionPath)
	public Question question(@PathVariable(qIdParam) long qId) {
		return questionService.getQuestion(qId);
	}

	/** Players.
	 *
	 * @return the array list */
	@RequestMapping(path = playersPath)
	public ArrayList<Player> players() {
		return playerService.playerList();
	}
	
	/** Players.
	 *
	 * @return the array list */
	@RequestMapping(path = playerPath)
	public Player player(@PathVariable(emailParam) String email) {
		return playerService.getPlayer(email);
	}
}
