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
package com.hoovler.api.resources;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.models.AnswerArgs;
import com.hoovler.api.utils.NameGameHelper;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

// TODO: Auto-generated Javadoc
/** <p>
 * <h3>Answer</h3>
 * <p>
 * <b><u>Purpose</u></b>
 * </p>
 * This class represents the object presented when the API's POST verb is invoked.
 * <p>
 * <b><u>Information</u></b><br />
 * The <code>Answer</code> object is comprised of the following member variables, which are
 * transposed into the JSON result:
 * <ul>
 * <li><code>String result</code></li>
 * <li><code>String playerEmail</code></li>
 * <li><code>Stat stat</code></li>
 * </ul>
 * <p>
 * <b>METHOD = POST</b>
 * <p><b>Request URI:</b></p>
 * <pre>http://localhost:8080/namegame/v2.0.0/&lt;question_id&gt;</pre>
 * <p><b>Request body:</b></p>
 * <pre>
 * {
 * "answer_id": "&lt;option_id&gt",
 * "email": "&lt;email&gt";
 * }
 * </pre>
 * : /email=[<em>playerEmail</em>]&answer_id=[<em>answerId</em>]</li><li></li></ul>
 * /email=[<b>playerEmail</b>]&answer_id=[<b>answerId</b>]
 * </p>
 * The variables from the above URI are ultimately sent here, where the answer is evaluated and the
 * response formulated.
 * <p>
 * <b><u>Examples</u></b>
 * </p>
 * 
 * <pre>
 * GET:
 * </pre>
 * 
 * <pre>
 * <b><em>POST</em></b>
 * <ul><li><b>URI = </b>http://localhost:8080/namegame/v2.0.0/<b>12345678987654321</b>
 * </pre> */
public class AnswerQuestion {
	private static Logger log = LogManager.getLogger(AnswerQuestion.class.getName());

	private boolean correct;
	private Player player;
	private String message;

	/** Gets AnswerQuestion.result
	 *
	 * @return the result */
	public boolean getResult() {
		return correct;
	}

	/** Gets AnswerQuestion.player
	 *
	 * @return the player */
	public Player getPlayer() {
		return player;
	}

	/** Gets AnswerQuestion.message
	 *
	 * @return the message */
	public String getMessage() {
		return this.message;
	}

	/** Sets AnswerQuestion.correct
	 *
	 * @param correct the new correct */
	protected void setCorrect(boolean correct) {
		this.correct = correct;
	}

	/** Sets AnswerQuestion.player
	 *
	 * @param player the new player */
	protected void setPlayer(Player player) {
		this.player = player;
	}

	/** Sets AnswerQuestion.message
	 *
	 * @param message the new message */
	protected void setMessage(String message) {
		this.message = message;
	}

	/** Instantiates a new answer question. */
	public AnswerQuestion() {
		this.correct = false;
		this.player = new Player();
		this.message = StringUtils.EMPTY;
	}

	/** Instantiates a new answer question.
	 *
	 * @param message the message */
	public AnswerQuestion(String message) {
		this.correct = false;
		this.player = new Player();
		this.message = message;
	}

	/** Instantiates a new answer question.
	 *
	 * @param message the message
	 * @param player  the player */
	public AnswerQuestion(String message, Player player) {
		this.correct = false;
		this.player = player;
		this.message = message;
	}

	/** Instantiates a new answer question.
	 *
	 * @param message   the message
	 * @param player    the player
	 * @param isCorrect the is correct */
	public AnswerQuestion(String message, Player player, boolean isCorrect) {
		this.correct = isCorrect;
		this.player = player;
		this.message = message;
	}

	/** Instantiates a new answer.
	 *
	 * @param args      the args
	 * @param players   the players
	 * @param questions the questions */
	public AnswerQuestion(AnswerArgs args, Players players, Questions questions) {

		String answerId = args.getAnswerId();
		String email = args.getPlayerEmail();
		String questionId = args.getQuestionId();
		Question q;

		// temporarily hold member values in local variables
		StringBuilder msg = new StringBuilder();
		Player user = new Player();
		boolean isCorrect = false;

		log.info("StringUtils.isNumeric(questionId) = " + StringUtils.isAlphanumeric(questionId));

		if (StringUtils.isNoneBlank(answerId, email, questionId) && StringUtils.isAlphanumeric(questionId)) {
			// calculate the relevant qId from questionId and playerEmail
			long qId = Long.parseLong(NameGameHelper.decodeFromHexWithSalt(questionId, email));
			// ensure existence of player and question
			if (players.playerExists(email) && questions.questionExists(qId)) {
				user = players.getPlayer(email);

				isCorrect = questions.isCorrectAnswer(qId, answerId);
				msg.append(" Correct Answer? ").append(isCorrect);
				q = questions.getQuestion(qId);
				user.updateStats(q.getCreated(), new Date(), isCorrect);
				players.updatePlayer(email, user);

			} else {
				user = new Player();
				msg.append(" Invalid email or question_id:").append("email=" + email)
						.append(", question_id=" + questionId);
			}

		} // end if none blank and isNumeric
		else {
			msg.append(" An invalid argument was submitted:").append(" email=" + email).append(", answerId=" + answerId)
					.append(", question_id=" + questionId);
		}

		// set all member attributes from temp variables
		this.player = user;
		this.message = msg.toString();
		this.correct = isCorrect;
	}
}
