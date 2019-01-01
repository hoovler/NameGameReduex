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

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.models.AnswerArgs;
import com.hoovler.api.utils.GameUtils;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;
import com.hoovler.dao.models.Stats;
import com.hoovler.dao.resources.ScoreBy;

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
 * 	"answer_id": "&lt;option_id&gt",
 * 	"email": "&lt;email&gt";
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
 * </pre>
 */
public class AnswerQuestion {
	
	private static Logger log = LogManager.getLogger(AnswerQuestion.class.getName());

	private boolean result;

	private Player player;
	
	private String message;

	public boolean getResult() {
		return result;
	}

	public Player getPlayer() {
		return player;
	}
	
	public String getMessage() {
		return this.message;
	}

	/** Instantiates a new answer.
	 *
	 * @param args            the args
	 * @param playerService   the player service
	 * @param questionService the question service */
	public AnswerQuestion(AnswerArgs args, Players players, Questions questions) {
		
		// initialize values
		this.player = new Player(StringUtils.EMPTY, new Stats());
		this.result = false;
		
		// argument values
		String email = args.getPlayerEmail();
		String answerId = args.getAnswerId();
		String questionId = args.getQuestionId();
		
		log.debug("***** AnswerQuestion() *****");
		// local variables
		Player playerObj;
		Question q;
		
		// ensure the player referenced by the given email exists
		if (players.playerExists(email)) {
			log.debug("Player exists: " + email);
			long qId = Long.parseLong(GameUtils.decodeFromHexWithSalt(questionId, email));
			playerObj = players.getPlayer(email);
			
			// ensure the question referenced in the questionId arg exists
			if (questions.questionExists(qId)) {
				log.debug("Question exists: " + qId);
				// check answer and grab question
				this.result = questions.isCorrectAnswer(qId, answerId);
				q = questions.getQuestion(qId);
				
				// update player stats
				playerObj.updateStats(q.getCreated(), new Date(), this.result);
				playerObj.updateScore(ScoreBy.CORRECT_WEIGHTED_TIME, 1000);
				
				// finalize the changes
				players.updatePlayer(email, playerObj);
				this.player = playerObj;
			}
		}
		
		log.debug("this.playerEmail = " + this.player.getEmail());
		log.debug("this.result = " + this.result);
	}
}
