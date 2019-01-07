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
package com.hoovler.api.models;

import org.apache.commons.lang3.StringUtils;

import com.hoovler.dao.models.Player;

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
public class AnswerResponseBody {

	private boolean correct;
	private Player player;
	private String message;

	
	/**
	 * Checks if is correct.
	 *
	 * @return true, if is correct
	 */
	public boolean isCorrect() {
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
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	/** Sets AnswerQuestion.player
	 *
	 * @param player the new player */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/** Sets AnswerQuestion.message
	 *
	 * @param message the new message */
	public void setMessage(String message) {
		this.message = message;
	}

	/** Instantiates a new answer question. */
	public AnswerResponseBody() {
		this.correct = false;
		this.player = new Player();
		this.message = StringUtils.EMPTY;
	}

	/** Instantiates a new answer question.
	 *
	 * @param message the message */
	public AnswerResponseBody(String message) {
		this.correct = false;
		this.player = new Player();
		this.message = message;
	}

	/** Instantiates a new answer question.
	 *
	 * @param message the message
	 * @param player  the player */
	public AnswerResponseBody(String message, Player player) {
		this.correct = false;
		this.player = player;
		this.message = message;
	}

	/** Instantiates a new answer question.
	 *
	 * @param message   the message
	 * @param player    the player
	 * @param isCorrect the is correct */
	public AnswerResponseBody(String message, Player player, boolean isCorrect) {
		this.correct = isCorrect;
		this.player = player;
		this.message = message;
	}
}
