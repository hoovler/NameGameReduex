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
/** The response model for the {@code /answer} endpoint. 
 * @author <a href="https://github.com/hoovler">github.com/hoovler</a> */
public class AnswerResponseBody extends ObjectResponseModel {

	private boolean correct;
	private Player player;

	/** Checks if is correct.
	 *
	 * @return true, if is correct */
	public boolean isCorrect() {
		return correct;
	}

	/** Gets AnswerQuestion.player
	 *
	 * @return the player */
	public Player getPlayer() {
		return player;
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
	 * @param player the player */
	public AnswerResponseBody(String message, Player player) {
		this.correct = false;
		this.player = player;
		this.message = message;
	}

	/** Instantiates a new answer question.
	 *
	 * @param message the message
	 * @param player the player
	 * @param isCorrect the is correct */
	public AnswerResponseBody(String message, Player player, boolean isCorrect) {
		this.correct = isCorrect;
		this.player = player;
		this.message = message;
	}
}
