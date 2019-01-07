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

/** The sole controller for the NameGameApi, handling all RESTful service requests for, or affecting, any NameGame-related operation on the {@code PlayerDao}, {@code QuestionDao}, and {@code ProfileDao} resources.
 * 
 * <p>Two items remain the same: providing no header/URI params for list resources still returns all objects in the related repository, and providing an integer value for the {@code start} parameter will skip over {@code start} number of items before aggregating the returned list; however, a special note is warrented for those endpoints for which a list of objects may be returned.
 * <p>All list-returning endpoints now include <b>three numeric subsetting header / URI parameters:</b> {@code start}. {@code stop}, and {@code velocity}.
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
 * <p><u><b>Context of Combinations</b></u>
 * <ul>
 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
 * <li>{@code start} and {@code start}: Both values must be positive integers. If {@code stop > returnList.size()}, then {@code stop} is recast as {@code returnList.size() - 1}
 * <li>{@code start} and {@code velocity}: Either value may be a positive or a negative integer. If {@code Math.abs(velocity) > returnList.size() && velocity < 0}, then {@code returnList()} will contain the full list in reverse, and as many repeating full reverse lists / elements in reverse as necessary to meet the absolute value (magnitude) of {@code velocity}. If the direction is positive ({@code velocity < 0}, the the returned list will be in the forward direction.
 * <li>{@code start} and {@code stop} and {@code velocity}: only the value of {@code start} and {@code stop} are used. */
@RestController
@RequestMapping(GameHelper.API_PREFIX + GameHelper.API_VERSION)
public class GameController {

	/* SPRING CONTROLLER BEAN INITIALIZATION */

	private final DefaultProfileDao profiles;
	private final Players players;
	private final FullQuestions fullQuestions;
	private final GameQuestions gameQuestions;

	/** Instantiates a new game controller with new instances of the DAO parameter objects.
	 *
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param profiles the DAO object that implements, inherits an implementation of, {@code ProfileDao}.
	 * @param players the DAO object that implements, inherits an implementation of, {@code PlayerDao}.
	 * @param fullQuestions the DAO object that implements, inherits an implementation of, {@code QuestionDao}.
	 * @param gameQuestions the DAO object that implements, inherits an implementation of, {@code GameQuestionDao}. */
	@Autowired
	public GameController(DefaultProfileDao profiles, Players players, FullQuestions fullQuestions,
			GameQuestions gameQuestions) {
		this.profiles = profiles;
		this.players = players;
		this.fullQuestions = fullQuestions;
		this.gameQuestions = gameQuestions;
	}

	// ***********************************************
	// ***********************************************
	// Endpoints that satisfy primary requirements
	// ***********************************************
	// ***********************************************

	/** The endpoint that generates and returns a question object. The only required parameter is {@code email}.
	 * <p> For the boolean parameter, acceptable values for {@code true = "1", "y", "yes", "t", "true", ...} and so on, regardless of case. They can also be left off the list of arguments for a default value of {@code FALSE} for both.
	 * 
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param email the email of the player asking the question.
	 * @param reverse a value of {@code TRUE / FALSE} <li>{@code FALSE || null} to show one face and six names <li>{@code TRUE} to show one name and six faces.
	 * @param mattsOnly a value of {@code TRUE / FALSE} <li>{@code FALSE || null} to pull the target and options from the pool of all profiles.<li>{@code TRUE} to use only the subset of profiles wherein the {@code Profile.firstName} starts with "mat", regardless of case. [<em>regex = {@code [m|M][a|A][t|T]}</em>]
	 * @return the ask question */
	@GetMapping(path = GameHelper.ENDPOINT_ASK)
	public AskResponseBody ask(
			@RequestParam(value = GameHelper.PARAM_EMAIL, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String email,
			@RequestParam(value = GameHelper.PARAM_REVERSE, defaultValue = GameHelper.VAL_DEFAULT_BOOL) String reverse,
			@RequestParam(value = GameHelper.PARAM_MATTS, defaultValue = GameHelper.VAL_DEFAULT_BOOL) String mattsOnly) {
		return gameQuestions.endpointHandler(email, reverse, mattsOnly, profiles, players, fullQuestions);
	}

	/** The endpoint that accepts a POST request body with a valid JSON answer, and returns a boolean indicating if the answer was correct, and a {@code Player} object corresponding to the JSON body's {@code email} value, along with updated player stats.
	 * <p>If an invalid request body is posted (malformed JSON, no body, bad JSON attribute names...), this endpoint will not throw an error. Rather, it will respond with a well-formed JSON response body in which are empty objects, {@code "correct": "false"}, and a message indicating the nature of the problem.
	 *
	 * @author Michael Hoovler
	 * @param payload the well-formed JSON representation of the {@code AnswerArgs} object model, which contains {@code email}, {@code answer_id}, and {@code question_id}.
	 * @return a well-formed JSON response body in which is a {@code Player} object with updated stats, {@code correct} boolean indicating if the answer is correct, and {@code message}, which gives more information if there was a problem with the {@code payload}. */
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

	/** The endpoint that returns a JSON array of {@code Player} objects that are sorted by descending order of score.
	 * <p>This will return a similar list as the {@code /players} endpoint, but sorted by rank.
	 *
	 * @author Michael Hoovler
	 * @param start the start
	 * @param stop the stop
	 * @param velocity the integer value indicating direction (go left ({@code velocity < 0}, right {@code velocity > 0} from the {@code start} index) and magnitude (
	 * @return the array list
	 * @see com.hoovler.utils.impl.ListUtils#subset(java.util.List, int, int) */
	@RequestMapping(path = GameHelper.ENDPOINT_LEADERS)
	public ArrayList<Player> leaderboard(
			@RequestParam(value = GameHelper.PARAM_START, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String start,
			@RequestParam(value = GameHelper.PARAM_STOP, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String stop,
			@RequestParam(value = GameHelper.PARAM_VELOCITY, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String velocity) {
		return players.endpointGET(start, stop, velocity, true);
	}

	/*
	 * ***********************************************
	 * Useful future endpoints.
	 * ***********************************************
	 * These are primarily diagnostic and administrative in nature, and should likewise be wrapped up in the
	 * appropriate security. I sketched out a simple security interface and an example XML ACL configuration
	 * file in general-utils, but it's nowhere near prime-time.
	 * 
	 * In the mean-time, some of these endpoints work with some minor bugs, but they are, by and large, not
	 * yet implemented.
	 * *********************************************
	 */

	/** Diagnostic: Full Questions.
	 * 
	 * <p>Return a list of all Question objects in their full format.
	 * <p><b>This diagnostic endpoint should be protected from casual players.</b> A security configuration methodology template is provided in the {@code general-utils} maven project, within the following classes: <ul><li>{@code com.hoovler.utils.impl.SecurityUtils}</li><li>{@code com.hoovler.utils.models.SecurityConfig}</li></ul>And an example configuration file: {@code src/main/resources/security.config}
	 * 
	 * @author Michael Hoovler
	 * @param qId the q id
	 * @param start the start
	 * @param stop the stop
	 * @param velocity the velocity
	 * @return the array list */
	@RequestMapping(path = GameHelper.ENDPOINT_QUESTIONS_FULL)
	public ArrayList<Question> questions(
			@RequestParam(value = GameHelper.BODY_QUESTION_ID, defaultValue = GameHelper.VAL_DEFAULT_NUM) String qId,
			@RequestParam(value = GameHelper.PARAM_START, defaultValue = GameHelper.VAL_DEFAULT_NUM) String start,
			@RequestParam(value = GameHelper.PARAM_STOP, defaultValue = GameHelper.VAL_DEFAULT_NUM) String stop,
			@RequestParam(value = GameHelper.PARAM_VELOCITY, defaultValue = GameHelper.VAL_DEFAULT_NUM) String velocity) {
		return fullQuestions.endpointGET(qId, start, stop, velocity);

	}

	/** Diagnostic: Players.
	 * 
	 * <p>Return a list of all Player objects, representing the full list of players to whom a question has been asked.
	 * <p><b>This diagnostic endpoint should be protected from casual players.</b> A security configuration methodology template is provided in the {@code general-utils} maven project, within the following classes: <ul><li>{@code com.hoovler.utils.impl.SecurityUtils}</li><li>{@code com.hoovler.utils.models.SecurityConfig}</li></ul>And an example configuration file: {@code src/main/resources/security.config}
	 * <p><b>Note:</b> To subset this list, simply use the /leaders endpoint with parameters.
	 * 
	 * @author Michael Hoovler <hoovlermichael@gmail.com>
	 * @param start the start
	 * @param stop the stop
	 * @param velocity the velocity
	 * @return the array list */
	@RequestMapping(path = GameHelper.ENDPOINT_PLAYERS)
	public ArrayList<Player> players(
			@RequestParam(value = GameHelper.PARAM_START, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String start,
			@RequestParam(value = GameHelper.PARAM_STOP, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String stop,
			@RequestParam(value = GameHelper.PARAM_VELOCITY, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String velocity) {
		return players.endpointGET(start, stop, velocity, false);
	}

	/** Adds a new player to the list of players.
	 * 
	 * <p><b>ADMINISTRATIVE ENDPOINT</b>
	 * 
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param payload the payload
	 * @return the string */
	@PostMapping(path = GameHelper.ENDPOINT_PLAYERS)
	public Player addPlayer(@RequestBody String payload) {
		return players.endpointPOST(payload);
	}

	/** Updates an existing player.
	 * <p><b>ADMINISTRATIVE ENDPOINT</b>
	 * 
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param email the email address of the player to update.
	 * @param payload the well-formed JSON representation of the {@code Player} object model, which contains the updates.
	 * @return the player */
	@PutMapping(path = GameHelper.ENDPOINT_PLAYERS)
	public Player updatePlayer(
			@RequestParam(value = GameHelper.PARAM_EMAIL, defaultValue = GameHelper.VAL_DEFAULT_NUM) String email,
			@RequestBody String payload) {
		return players.endpointPUT(email, payload);
	}

	/** Deletes a player.
	 * <p><b>ADMINISTRATIVE ENDPOINT</b>
	 * 
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param email the email of the player to delete.
	 * @return {@code TRUE}, if the player was successfully deleted. */
	@DeleteMapping(path = GameHelper.ENDPOINT_PLAYERS)
	public boolean deletePlayer(
			@RequestParam(value = GameHelper.PARAM_EMAIL, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String email) {
		return players.endpointDELETE(email);
	}

	/** Diagnostic: Pared Questions.
	 * <p><b>DIAGNOSTIC ENDPOINT</b>
	 * <p>Returns a list of the questions as presented to the consumer of the /ask endpoint.
	 * <p>While this list and the list available at the /questions endpoint are technically the same set of questions, these questions are pared down such that a user doesn't see the {@code target_id} of the target, which corresponds to a matching {@code option_id} in the options set.
	 * <p><u><b>Note on </b>{@code velocity}</u>
	 * <p>The value of {@code velocity} is interpreted as a non-scalar value containing both a <em>magnitude</em> and <em>direction</em>. <em>magnitude</em> is calculated as {@code Math.abs(velocity)}, and determines the length of the returned list. <em>direction</em> is calculated as {@code Integer.signum(velocity)}. A value of -1 is interpreted as LEFT, +1 as RIGHT, and 0 as NONE.
	 * <p>The {@code velocity} is indended to be used in the stead of {@code stop}. <em>If both {@code stop} and {@code velocity} are provided, the value of {@code stop} is used, and {@code velocity} <b>is ignored.</b></em>
	 * 
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param qId the q id
	 * @param start the start
	 * @param stop the index at which to end the subset.
	 * @param velocity an integer denoting magnitude and direction. See above.
	 * @return the array list */
	@RequestMapping(path = GameHelper.ENDPOINT_QUESTIONS_PARED)
	public ArrayList<AskResponseBody> questionsAsked(
			@RequestParam(value = GameHelper.BODY_QUESTION_ID, defaultValue = GameHelper.VAL_DEFAULT_ALPHA) String qId,
			@RequestParam(value = GameHelper.PARAM_START, defaultValue = GameHelper.VAL_DEFAULT_NUM) String start,
			@RequestParam(value = GameHelper.PARAM_STOP, defaultValue = GameHelper.VAL_DEFAULT_NUM) String stop,
			@RequestParam(value = GameHelper.PARAM_VELOCITY, defaultValue = GameHelper.VAL_DEFAULT_NUM) String velocity) {
		return gameQuestions.endpointGET(qId, start, stop, velocity);
	}

	/** Adds a new {@code GameQuestion} object.
	 * <p><b>ADMINISTRATIVE ENDPOINT</b>
	 * 
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param payload the payload
	 * @return the ask response body */
	@PostMapping(path = GameHelper.ENDPOINT_QUESTIONS_PARED)
	public AskResponseBody addParedQuestion(@RequestBody String payload) {
		return gameQuestions.endpointPOST(payload);
	}

	/** Update pared-down game question.
	 * <p><b>ADMINISTRATIVE ENDPOINT</b>
	 * 
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param payload the payload
	 * @return <em>Feature not implemented</em> */
	@PutMapping(path = GameHelper.ENDPOINT_QUESTIONS_PARED)
	public String updateGameQuestion(@RequestBody String payload) {
		return Message.MSG_NOT_IMPL.s();
	}

	/** Delete pared-down game question.
	 * <p><b>ADMINISTRATIVE ENDPOINT</b>
	 * 
	 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
	 * @param payload the payload
	 * @return <em>Feature not implemented</em> */
	@DeleteMapping(path = GameHelper.ENDPOINT_QUESTIONS_PARED)
	public String deleteGameQuestion(@RequestBody String payload) {
		return Message.MSG_NOT_IMPL.s();
	}
}
