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

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoovler.api.models.AnswerResponseBody;
import com.hoovler.api.models.AskResponseBody;
import com.hoovler.api.utils.GameHelper;
import com.hoovler.api.utils.Message;
import com.hoovler.dao.impl.DefaultProfileDao;
import com.hoovler.dao.impl.FullQuestions;
import com.hoovler.dao.impl.GameQuestions;
import com.hoovler.dao.impl.Players;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

// completed: Don't allow player to answer question more than once
// completed: consolidate DAO objects into single external projects
// not going to do: break endpoint methods out into their own controller classes, based on resource
// TODO: Update javadoc comments
// TODO: Generate Javadocs
// TODO: finish test cases
// TODO: Document the API with spring test suite or postman

/** The Class GameController. */
@RestController
@RequestMapping(GameHelper.API_PREFIX + GameHelper.API_VERSION)
public class GameController {

	/* SPRING CONTROLLER BEAN INITIALIZATION */

	private final DefaultProfileDao profiles;
	private final Players players;
	private final FullQuestions fullQuestions;
	private final GameQuestions gameQuestions;

	/**
	 *  Instantiates a new game controller.
	 *
	 * @author Michael Hoovler
	 * @param profileService  the profile service
	 * @param playerService   the player service
	 * @param questionService the question service
	 * @param questionsAskedService the questions asked service
	 */
	@Autowired
	public GameController(DefaultProfileDao profileService, Players playerService, FullQuestions questionService,
			GameQuestions questionsAskedService) {
		this.profiles = profileService;
		this.players = playerService;
		this.fullQuestions = questionService;
		this.gameQuestions = questionsAskedService;
	}

	// ***********************************************
	// ***********************************************
	// Endpoints that satisfy primary requirements
	// ***********************************************
	// ***********************************************

	/**
	 *  The endpoint that generates and returns a question object.  The only required parameter is {@code email}.
	 * @author Michael Hoovler
	 * @param email the email
	 * @param reverse the reverse
	 * @param mattsOnly the matts only
	 * @return             the ask question
	 */
	@GetMapping(path = GameHelper.ENDPOINT_ASK)
	public AskResponseBody ask(
			@RequestParam(value = GameHelper.PARAM_EMAIL, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String email,
			@RequestParam(value = GameHelper.PARAM_REVERSE, defaultValue = GameHelper.VAL_DEFAULT_BOOL) String reverse,
			@RequestParam(value = GameHelper.PARAM_MATTS, defaultValue = GameHelper.VAL_DEFAULT_BOOL) String mattsOnly) {
		return gameQuestions.endpointHandler(email, reverse, mattsOnly, profiles, players, fullQuestions);
	}

	/**
	 *  The endpoint that accepts a POST request body with a valid JSON answer, and returns a boolean indicating if the answer was correct, and a {@code Player} object corresponding to the JSON body's {@code email} value, along with updated player stats.
	 * <p>If an invalid request body is posted (malformed JSON, no body, bad JSON attribute names...), this endpoint will not throw an error.  Rather, it will respond with a well-formed JSON response body in which are empty objects, {@code "correct": "false"}, and a message indicating the nature of the problem.
	 *
	 * @author Michael Hoovler
	 * @param payload the payload
	 * @return         the answer question
	 */
	@PostMapping(path = GameHelper.ENDPOINT_ANSWER)
	public AnswerResponseBody answer(@RequestBody String payload) {
		return gameQuestions.endpointHandler(payload, players, fullQuestions);
	}

	// ***********************************************
	// ***********************************************
	// Extra-credit endpoint.
	// ***********************************************
	// The 'leaderboad' endpoint is an extension
	// of the regular 'players' persistence object. It simply accepts
	// the desired parameters around subsetting the
	// list of players, and sorts the list in descending
	// player score. The part I'm really proud of here
	// is the 'velocity' parameter, which is, in practice
	// a signed int from which list length and direction
	// from start (left or right) is derived. More
	// info in the javadocs.
	// ***********************************************

	/**
	 *  Leaderboard.
	 * A variant of the {@code players} endpoint, the Leaderboard endpoint allows players to obtain a list of players in descending rank.
	 * <p><u>Parameter: <b>int start</b></u>
	 * <p>The {@code start} parameter <em>(default=0)</em> is the literal starting index of the list returned. This value may be positive, 0, or negative.
	 * <p>If negative, then the {@code startIndex} is presumed to be {@code |start|} number of players from the <em>end</em> of the list of players, and is calculated as {@code startIndex = players.size() - Math.abs(start)}.
	 * <p><u>Parameter: <b>int velocity</b></u>
	 * <p>The value of {@code velocity} is interpreted as a non-scalar value containing both a <em>magnitude</em> and <em>direction</em>.
	 * <p><em>magnitude</em> is calculated as {@code Math.abs(velocity)}, and determines the length of the returned list.
	 * <p><em>direction</em> is calculated as {@code Integer.signum(velocity)}. A value of -1 is interpreted as LEFT, +1 as RIGHT, and 0 as NONE.
	 * <p><u><b>Effect of Parameters</b></u>
	 * <p>Think of the entire list as being a chain of player objects that is curled into a ring; where the last player is brought back around to connect with the first. If the magnitude (total number of subset elements) is greater than the length of the entire original list, then an iterator grabbing list elements for the subset simply returns to the first element after adding the last. There is no limit to the number of loops / full lists, so use {@code velocity} wisely.
	 * <p>The derived {@code velocity}.<em>direction</em> determines whether to iterate forward or backwards from the {@code startIndex}, while {@code velocity}.<em>magnitude</em> determines <em>how far</em> to move in that direction. The subset will continue building - even after the last or first element is reached - until the subset size reaches the value of {@code magnitude}.
	 * <p>See the underlying utility function, {@code com.hoovler.utils.impl.ListUtils.subset()} for more information on how the subset is derived.
	 *
	 * @author          Michael Hoovler
	 * @param start the start
	 * @param stop the stop
	 * @param velocity the velocity
	 * @return          the array list
	 * @see             com.hoovler.utils.impl.ListUtils#subset(java.util.List, int, int)
	 */
	@RequestMapping(path = GameHelper.ENDPOINT_LEADERS)
	public ArrayList<Player> leaderboard(
			@RequestParam(value = GameHelper.PARAM_START, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String start,
			@RequestParam(value = GameHelper.PARAM_STOP, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String stop,
			@RequestParam(value = GameHelper.PARAM_VELOCITY, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String velocity) {
		return players.endpointHandler(start, stop, velocity, true);
	}

	/* ***********************************************
	 * Useful future endpoints.
	 * ***********************************************
	 * These are primarily diagnostic and administrative in nature, and should likewise be wrapped up in the 
	 * appropriate security. I sketched out a simple security interface and an example XML ACL configuration 
	 * file in general-utils, but it's nowhere near prime-time.
	 * 
	 * In the mean-time, some of these endpoints work with some minor bugs, but they are, by and large, not
	 * yet implemented.
	 * ********************************************* */

	/**
	 *  Diagnostic: Full Questions.
	 * 
	 * <p>Return a list of all Question objects in their full format.
	 * <p><b>This diagnostic endpoint should be protected from casual players.</b> A security configuration methodology template is provided in the {@code general-utils} maven project, within the following classes: <ul><li>{@code com.hoovler.utils.impl.SecurityUtils}</li><li>{@code com.hoovler.utils.models.SecurityConfig}</li></ul>And an example configuration file: {@code src/main/resources/security.config}
	 * @author Michael Hoovler
	 * @param qId the q id
	 * @param start the start
	 * @param stop the stop
	 * @param velocity the velocity
	 * @return     the array list
	 */
	@RequestMapping(path = GameHelper.ENDPOINT_QUESTIONS_FULL)
	public ArrayList<Question> questions(
			@RequestParam(value = GameHelper.BODY_QUESTION_ID, defaultValue = GameHelper.VAL_DEFAULT_NUM) String qId,
			@RequestParam(value = GameHelper.PARAM_START, defaultValue = GameHelper.VAL_DEFAULT_NUM) String start,
			@RequestParam(value = GameHelper.PARAM_STOP, defaultValue = GameHelper.VAL_DEFAULT_NUM) String stop,
			@RequestParam(value = GameHelper.PARAM_VELOCITY, defaultValue = GameHelper.VAL_DEFAULT_NUM) String velocity) {
		return fullQuestions.endpointHandler(qId, start, stop, velocity);

	}

	/** Diagnostic: Players.
	 * 
	 * <p>Return a list of all Player objects, representing the full list of players to whom a question has been asked.
	 * <p><b>This diagnostic endpoint should be protected from casual players.</b> A security configuration methodology template is provided in the {@code general-utils} maven project, within the following classes: <ul><li>{@code com.hoovler.utils.impl.SecurityUtils}</li><li>{@code com.hoovler.utils.models.SecurityConfig}</li></ul>And an example configuration file: {@code src/main/resources/security.config}
	 * <p><b>Note:</b> To subset this list, simply use the /leaders endpoint with parameters.
	 * 
	 * @return the array list */
	@RequestMapping(path = GameHelper.ENDPOINT_PLAYERS)
	public ArrayList<Player> players(
			@RequestParam(value = GameHelper.PARAM_START, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String start,
			@RequestParam(value = GameHelper.PARAM_STOP, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String stop,
			@RequestParam(value = GameHelper.PARAM_VELOCITY, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String velocity) {
		return players.endpointHandler(start, stop, velocity, false);
	}

	@PostMapping(path = GameHelper.ENDPOINT_PLAYERS)
	public String addPlayer(@RequestBody String payload) {
		return Message.MSG_NOT_IMPL.s();
	}

	/** Administrative: Update Player
	 *
	 * @param  email   the email
	 * @param  payload the payload
	 * @return         the player */
	@PutMapping(path = GameHelper.ENDPOINT_PLAYERS)
	public Player updatePlayer(
			@RequestParam(value = GameHelper.PARAM_EMAIL, defaultValue = GameHelper.VAL_DEFAULT_NUM) String email,
			@RequestBody String payload) {
		return players.endpointHandler(email, payload);
	}

	@DeleteMapping(path = GameHelper.ENDPOINT_PLAYERS)
	public String deletePlayer(@RequestBody String payload) {
		return Message.MSG_NOT_IMPL.s();
	}

	/** Diagnostic: Pared Questions.
	 * 
	 * <p>Returns a list of the questions as presented to the consumer of the /ask endpoint.
	 * <p>While this list and the list available at the /questions endpoint are technically the same set of questions, these questions are pared down such that a user doesn't see the {@code target_id} of the target, which corresponds to a matching {@code option_id} in the options set.
	 * <p><u><b>Note on </b>{@code velocity}</u>
	 * <p>The value of {@code velocity} is interpreted as a non-scalar value containing both a <em>magnitude</em> and <em>direction</em>. <em>magnitude</em> is calculated as {@code Math.abs(velocity)}, and determines the length of the returned list. <em>direction</em> is calculated as {@code Integer.signum(velocity)}. A value of -1 is interpreted as LEFT, +1 as RIGHT, and 0 as NONE.
	 * <p>The {@code velocity} is indended to be used in the stead of {@code stop}. <em>If both {@code stop} and {@code velocity} are provided, the value of {@code stop} is used, and {@code velocity} <b>is ignored.</b></em>
	 * 
	 * @param  qId      the q id
	 * @param  start    the start
	 * @param  stop     the index at which to end the subset.
	 * @param  velocity an integer denoting magnitude and direction. See above.
	 * @return          the array list */
	@RequestMapping(path = GameHelper.ENDPOINT_QUESTIONS_PARED)
	public ArrayList<AskResponseBody> questionsAsked(
			@RequestParam(value = GameHelper.BODY_QUESTION_ID, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String qId,
			@RequestParam(value = GameHelper.PARAM_START, defaultValue = GameHelper.VAL_DEFAULT_NUM) String start,
			@RequestParam(value = GameHelper.PARAM_STOP, defaultValue = GameHelper.VAL_DEFAULT_NUM) String stop,
			@RequestParam(value = GameHelper.PARAM_VELOCITY, defaultValue = GameHelper.VAL_DEFAULT_NUM) String velocity) {
		return gameQuestions.endpointHandler(qId, start, stop, velocity);
	}

	@PostMapping(path = GameHelper.ENDPOINT_QUESTIONS_PARED)
	public AskResponseBody addParedQuestion(@RequestBody String payload) {
		return gameQuestions.addFromEndpoint(payload);
	}

	@PutMapping(path = GameHelper.ENDPOINT_QUESTIONS_PARED)
	public String updateParedQuestion(@RequestBody String payload) {
		return Message.MSG_NOT_IMPL.s();
	}

	@DeleteMapping(path = GameHelper.ENDPOINT_QUESTIONS_PARED)
	public String deleteParedQuestion(@RequestBody String payload) {
		return Message.MSG_NOT_IMPL.s();
	}
}
