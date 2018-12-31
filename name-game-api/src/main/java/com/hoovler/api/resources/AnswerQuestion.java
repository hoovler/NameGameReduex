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
package com.hoovler.api.resources;

import java.util.Date;

import com.hoovler.api.models.answer.AnswerArgs;
import com.hoovler.api.models.answer.AnswerResponse;
import com.hoovler.api.utils.GameUtils;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;
import com.hoovler.dao.models.Stats;
import com.hoovler.dao.resources.ScoreBy;

/**
 * <p>
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
 * POST:
 * http://localhost:8080/namegame/v[apiVersion]/[<b>questionId</b>]/email=[<b>playerEmail</b>]&answer_id=[<b>answerId</b>]
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
 * POST: http://localhost:8080/namegame/v2.0.0/<b>12345678987654321</b>/email=[<b>playerEmail</b>]&answer_id=[<b>answerId</b>]
 * </pre>
 */
public class AnswerQuestion {
	// TODO: finish documenting

	private AnswerResponse answerResponse;

	/** Instantiates a new answer.
	 *
	 * @param args            the args
	 * @param playerService   the player service
	 * @param questionService the question service */
	public AnswerQuestion(AnswerArgs args, Players playerService, Questions questionService) {
		this.answerResponse = new AnswerResponse();
		setAnswerResponse(args, playerService, questionService);
	}

	/** Sets the answer response.
	 *
	 * @param args     the args
	 * @param pService the service
	 * @param qService the q service */
	public void setAnswerResponse(AnswerArgs args, Players pService, Questions qService) {
		// if player doesn't exist, answer shouldn't be calculated because the question wasn't given to the
		// player
		if (!checkPlayer(args, pService)) {
			// set dummy player with a message, and set the answer response
			this.answerResponse.setPlayer(new Player("NO_PLAYER_WITH_EMAIL " + args.getPlayerEmail(), new Stats()));
			this.answerResponse.setResult(false);
		} else {
			// derive questionId from the hashed ID passed in from the player
			String qIdStr = GameUtils.decodeFromHexWithSalt(String.valueOf(args.getQuestionId()),
					args.getPlayerEmail());
			Long qId = Long.valueOf(qIdStr);

			// check to see if the question with the ID given actually exists
			if (qService.questionExists(qId)) {
				// the player was asked this question - lets see if the answer is correct
				boolean isCorrect = qService.isCorrectAnswer(qId, args.getAnswerId());

				// grab relevant objects
				Player player = pService.getPlayer(args.getPlayerEmail());
				Question q = qService.getQuestion(qId);

				// update player accordingly; score is calculated as the percent correct weighted by average
				// response time
				player.updateStats(q.getCreated(), new Date(), isCorrect);
				player.updateScore(ScoreBy.CORRECT_WEIGHTED_TIME, 1000);
				pService.updatePlayer(args.getPlayerEmail(), player);

				// update answer response
				this.answerResponse.setPlayer(player);
				this.answerResponse.setResult(isCorrect);

			} else {
				// either bad URI path, or the player wasn't asked the question they're attempting to answer
				this.answerResponse.setPlayer(pService.getPlayer(args.getPlayerEmail()));
				this.answerResponse.setResult(false);
			}
		}
	}

	/** Check player.
	 *
	 * @param  args     the args
	 * @param  pService the service
	 * @return          true, if successful */
	public boolean checkPlayer(AnswerArgs args, Players pService) {
		return pService.getPlayer(args.getPlayerEmail()) == null;
	}
}
