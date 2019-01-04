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
package com.hoovler.api.controllers;

import static com.hoovler.api.utils.Message.INFO_ANSWER_PARAMS;
import static com.hoovler.api.utils.Message.INFO_ASK_PARAMS;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
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
import com.hoovler.api.resources.QuestionsAsked;
import com.hoovler.api.utils.NameGameHelper;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

// TODO: Don't allow player to answer question more than once
// TODO: Update comments
// TODO: Generate Javadocs
// TODO: Figure out how to document the API

/** The Class GameController. */
@RestController
@RequestMapping(NameGameHelper.API_PREFIX + NameGameHelper.API_VERSION)
public class GameController {
	private static Logger log = LogManager.getLogger(GameController.class.getName());

	/*
	 * cannot store Spring annotation values, or constituent parts, within an external
	 * properties file, or another constants class; Spring requires that all values within
	 * annotations must constant, as well as known at runtime initialization: this
	 * frustrates a developer's desire to refactor their code down into the most concise
	 * fragments possible...
	 */

	/* CONSTITUENT COMPONENTS */

	/** The standard URI path separator. */
	protected static final String pathSep = "/";

	/** The character denoting the start of a URI path variable. */
	protected static final String pathVarOpen = "{";

	/** The character denoting the end of a URI path variable. */
	protected static final String pathVarClose = "}";

	/* DEFAULT PARAMETER VALUES */

	/** The default value for missing or empty String parameters. */
	protected static final String defaultAlpha = "";

	/** The default value for missing or empty numeric parameters, including boolean params. */
	protected static final String defaultNumeric = "0";

	protected static final String defaultBoolean = "false";
	/* PARAMETER NAMES */

	/** The request parameter name for the player email string value. <p><em>A parameter name used: <ul><li>in the response body of a question</li><li>in the request body when submitting an answer</li><ul></em></p> */
	protected static final String emailParam = "email";

	/** The request parameter name for the selected mode integer value. <p><em>A parameter name used: <ul><li>in the response body of a question</li><ul></em></p> */
	protected static final String reverseParam = "reverse";

	/** The request parameter name for the <code>matts</code> boolean value. <p>The name of a parameter value passed along with the request for a question. While technically a string on the part of the sender, this value is treated a boolean, and thus may only be values which may be parsed into a boolean.</p> <p>For example:<pre>matts=1 || matts=true || matts=yes</pre><pre>matts=0 || matts=false || matts=no</pre></p> */
	protected static final String mattsParam = "matts";

	/** The Constant answerIdParam. <p>A parameter name used: <ul><li>when submitting an answer</li><ul><li>Should be a string value that is unique to the question's chosen option, and must match that option's <code>option_id</code></li></ul><ul></p> */
	protected static final String answerIdBodyParam = "answer_id";

	/** The Constant questionIdBodyParam. <p>A parameter name used: <ul><li>in the response body of a question<ul><li>A unique, hexidecimal ID for the question being asked</li></ul></li><li>in the request body when submitting an answer<ul><li>Must match the <code>question_id</code> received</li><li>Unique to, and may only be submitted for, the player to whom the question was asked.</li></ul></li><ul></p> */
	protected static final String questionIdBodyParam = "question_id";

	protected static final String startParam = "start";
	protected static final String stopParam = "stop";

	/* RESOURCE NAMES & BASE PATH VALUES */

	/** The Constant askResource. <p><em>A NameGame API resource...</em></p> */
	protected static final String askResource = "ask";

	/** The Constant answerResource. <p><em>A NameGame API resource...</em></p> */
	protected static final String answerResource = "answer";

	/** The Constant questionsResource. <p><em>A NameGame API resource...</em></p> */
	protected static final String questionsResource = "questions";
	protected static final String questionsAskedResource = "questions/asked";

	/** The Constant playersResource. <p><em>A NameGame API resource...</em></p> */
	protected static final String playersResource = "players";

	/* PATH VALUE CONSTRUCTION */

	/** The Constant askPath <p><em>A path derived from a named resource...</em></p> */
	protected static final String askPath = pathSep + askResource;

	/** The Constant questionsPath. <p><em>A path derived from a named resource...</em></p> */
	protected static final String questionsPath = pathSep + questionsResource;

	protected static final String questionsAskedPath = pathSep + questionsAskedResource;

	/** The Constant playersPath. <p><em>A path derived from a named resource...</em></p> */
	protected static final String playersPath = pathSep + playersResource;

	protected static final String leaderboardPath = "/leaderboard";

	/** The Constant altAnswerPath. <p><em>A path derived from a named resource...</em></p> */
	protected static final String answerPath = pathSep + answerResource;

	/* SPRING CONTROLLER BEAN INITIALIZATION */

	private final DefaultProfileDao profileService;
	private final Players playerService;
	private final Questions questionService;
	private final QuestionsAsked questionsAskedService;

	/** Instantiates a new game controller.
	 *
	 * @param profileService  the profile service
	 * @param playerService   the player service
	 * @param questionService the question service */
	@Autowired
	public GameController(DefaultProfileDao profileService, Players playerService, Questions questionService,
			QuestionsAsked questionsAskedService) {
		this.profileService = profileService;
		this.playerService = playerService;
		this.questionService = questionService;
		this.questionsAskedService = questionsAskedService;
	}

	/* STANDARD GAME ENDPOINTS */

	/** Ask.
	 *
	 * @param  playerEmail the player email
	 * @param  reverse     whether or not to reverse the question
	 * @param  mattsOnly   the matts only
	 * @return             the ask question */
	@GetMapping(path = askPath)
	public AskQuestion ask(@RequestParam(value = emailParam, defaultValue = defaultAlpha) String playerEmail,
			@RequestParam(value = reverseParam, defaultValue = defaultBoolean) String reverse,
			@RequestParam(value = mattsParam, defaultValue = defaultBoolean) String mattsOnly) {
		AskArgs askParams = new AskArgs(playerEmail, reverse, mattsOnly);

		log.info(INFO_ASK_PARAMS.getValue());
		log.info(emailParam + " = " + askParams.getPlayerEmail());
		log.info(reverseParam + " = " + askParams.isReverse() + " | " + reverse);
		log.info(mattsParam + " = " + askParams.isMattsOnly() + " | " + mattsOnly);

		// init a new Ask Response
		AskQuestion questionAsked = new AskQuestion(askParams, this.profileService, this.playerService,
				this.questionService);
		this.questionsAskedService.addQuestionAsked(questionAsked);
		return questionAsked;
	}

	/** Answer.
	 *
	 * @param  payload the payload
	 * @return         the answer question */
	@PostMapping(path = answerPath)
	public AnswerQuestion answer(@RequestBody String payload) {
		AnswerArgs answerBody = new AnswerArgs();

		JsonElement jBody = new JsonParser().parse(payload);
		JsonObject args = jBody.getAsJsonObject();

		answerBody.setAnswerId(args.get(answerIdBodyParam).getAsString());
		answerBody.setPlayerEmail(args.get(emailParam).getAsString());
		answerBody.setQuestionId(args.get(questionIdBodyParam).getAsString());

		// log POST values
		log.info(INFO_ANSWER_PARAMS.getValue());
		log.info(answerIdBodyParam + " = " + answerBody.getAnswerId());
		log.info(emailParam + " = " + answerBody.getPlayerEmail());
		log.info(questionIdBodyParam + " = " + answerBody.getQuestionId());

		AskQuestion questionAsked;
		AnswerQuestion answeredQ;

		if (this.questionsAskedService.questionAsked(answerBody.getQuestionId())) {
			questionAsked = this.questionsAskedService.getQuestionAsked(answerBody.getQuestionId());
			if (questionAsked.isAnswered()) {
				answeredQ = new AnswerQuestion("This question has already been answered.");
			} else {
				answeredQ = new AnswerQuestion(answerBody, this.playerService, this.questionService);
				questionAsked.answered(answeredQ.getResult());
			}
		} else {
			answeredQ = new AnswerQuestion("The question with the given question_id does not exist: question_id="
					+ answerBody.getQuestionId());
		}

		return answeredQ;
	}

	/** A variant of the <code>players</code> endpoint, the Leaderboard endpoint allows players to obtain a list of players in descending rank.
	 * <p><u>Parameter: <b>int start</b></u></p>
	 * <p>The <code>start</code> parameter <em>(default=0)</em> is the literal starting index of the list returned. This value may be positive, 0, or negative.</p>
	 * <p>If negative, then the <code>startIndex</code> is presumed to be <code>|start|</code> number of players from the <em>end</em> of the list of players, and is calculated as <code>startIndex = players.size() - Math.abs(start)</code>.</p>
	 * <p><u>Parameter: <b>int velocity</b></u></p>
	 * <p>The value of <code>velocity</code> is interpreted as a non-scalar value containing both a <em>magnitude</em> and <em>direction</em>.</p>
	 * <p><em>magnitude</em> is calculated as <code>Math.abs(velocity)</code>, and determines the length of the returned list.
	 * <p><em>direction</em> is calculated as <code>Integer.signum(velocity)</code>. A value of -1 is interpreted as LEFT, +1 as RIGHT, and 0 as NONE.
	 * <p><u><b>Effect of Parameters</b></u></p>
	 * <p>Think of the entire list as being a chain of player objects that is curled into a ring; where the last player is brought back around to connect with the first. If the magnitude (total number of subset elements) is greater than the length of the entire original list, then an iterator grabbing list elements for the subset simply returns to the first element after adding the last. There is no limit to the number of loops / full lists, so use <code>velocity</code> wisely.
	 * <p>The derived <code>velocity</code>.<em>direction</em> determines whether to iterate forward or backwards from the <code>startIndex</code>, while <code>velocity</code>.<em>magnitude</em> determines <em>how far</em> to move in that direction. The subset will continue building - even after the last or first element is reached - until the subset size reaches the value of <code>magnitude</code>.</p>
	 * <p>See the underlying utility function, <code>com.hoovler.utils.impl.ListUtils.subset()</code> for more information on how the subset is derived.
	 * 
	 * @author          Michael Hoovler
	 * @param  start    the start
	 * @param  velocity An integer from which the non-scalar <code>velocity</code> is derived.
	 * @return          the array list
	 * @see             com.hoovler.utils.impl.ListUtils#subset(java.util.List, int, int) */
	@RequestMapping(path = leaderboardPath)
	public ArrayList<Player> leaderboard(@RequestParam(value = startParam, defaultValue = defaultNumeric) int start,
			@RequestParam(value = stopParam, defaultValue = defaultNumeric) int velocity) {
		return playerService.playerList(start, velocity);
	}

	/* DIAGNOSTIC GAME ENDPOINTS */

	/** Return a list of all Question objects in their full format. <p><b>This diagnostic endpoint should be protected from casual players.</b> A security configuration methodology template is provided in the <code>general-utils</code> maven project, within the following classes: <ul><li><code>com.hoovler.utils.impl.SecurityUtils</code></li><li><code>com.hoovler.utils.models.SecurityConfig</code></li></ul>And an example configuration file: <code>src/main/resources/security.config</code></p>
	 *
	 * @param  start the starts
	 * @param  stop  the stop
	 * @return       the array list */
	@RequestMapping(path = questionsPath)
	public ArrayList<Question> questions(
			@RequestParam(value = questionIdBodyParam, defaultValue = defaultNumeric) String qId) {

		if (StringUtils.equalsIgnoreCase(qId, defaultNumeric)) {
			// return list of all questions
			return questionService.questionList();
		} else if (!StringUtils.isNumeric(qId)) {
			// return empty list
			return new ArrayList<>();
		} else {
			// return list with the desired question
			ArrayList<Question> qList = new ArrayList<>();
			Question q = questionService.getQuestion(Long.parseLong(qId));
			qList.add(q);
			return qList;
		}

	}

	/** Return a list of all Player objects, representing the full list of players to whom a question has been asked. <p><b>This diagnostic endpoint should be protected from casual players.</b> A security configuration methodology template is provided in the <code>general-utils</code> maven project, within the following classes: <ul><li><code>com.hoovler.utils.impl.SecurityUtils</code></li><li><code>com.hoovler.utils.models.SecurityConfig</code></li></ul>And an example configuration file: <code>src/main/resources/security.config</code></p>
	 *
	 * @return the array list */
	@RequestMapping(path = playersPath)
	public ArrayList<Player> players() {
		return playerService.playerList();
	}

	@RequestMapping(path = questionsAskedPath)
	public ArrayList<Question> questionsAsked(
			@RequestParam(value = questionIdBodyParam, defaultValue = defaultNumeric) int start,
			@RequestParam(value = reverseParam, defaultValue = defaultNumeric) int stop) {
		return questionService.questionList();
	}
}
