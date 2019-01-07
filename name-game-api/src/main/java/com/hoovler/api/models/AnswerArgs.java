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

// TODO: Auto-generated Javadoc
/** <p><h3>AnswerArgs</h3>
 * <p><b><u>Purpose</u></b></p>
 * To provide a template for the <code>@RequestBody</code> annotation's <code>HttpMessageConverter</code>
 * <p><b><u>Information</u></b></p>
 * <p><code>AnswerArgs</code> simply provides the object into which Answer body attributes should be mapped.
 * It should be noted that parameters used in the creation of the POST uri, and / or any XML or JSON values in
 * the request body passed with the POST uri, should be named in accordance with this project's documented arguments.</p>
 * 
 * @see org.springframework.web.bind.annotation.RequestBody */
public class AnswerArgs {
	private String answer_id;
	private String email;
	private String question_id;

	/** Gets AnswerArgs.answerId
	 *
	 * @return the answer id */
	public String getAnswerId() {
		return this.answer_id;
	}

	/** Gets AnswerArgs.playerEmail
	 *
	 * @return the player email */
	public String getPlayerEmail() {
		return this.email;
	}

	/** Gets AnswerArgs.questionId
	 *
	 * @return the question id */
	public String getQuestionId() {
		return question_id;
	}

	/** Sets AnswerArgs.answerId
	 *
	 * @param answerId the new answer id */
	public void setAnswerId(String answerId) {
		this.answer_id = answerId;
	}

	/** Sets AnswerArgs.playerEmail
	 *
	 * @param playerEmail the new player email */
	public void setPlayerEmail(String playerEmail) {
		this.email = playerEmail;
	}

	/** Sets AnswerArgs.questionId
	 *
	 * @param questionId the new question id */
	public void setQuestionId(String questionId) {
		this.question_id = questionId;
	}

	/** Instantiates a new answer args. */
	public AnswerArgs() {
	}

	/** Instantiates a new answer args.
	 *
	 * @param answerId    the answer id
	 * @param playerEmail the player email */
	public AnswerArgs(String answerId, String playerEmail) {
		this.answer_id = answerId;
		this.email = playerEmail;
	}

	/** Instantiates a new answer args.
	 *
	 * @param answerId    the answer id
	 * @param playerEmail the player email
	 * @param questionId  the question id */
	public AnswerArgs(String answerId, String playerEmail, String questionId) {
		this.answer_id = answerId;
		this.email = playerEmail;
		this.question_id = questionId;
	}

	/**
	 * A compliment to the 'toString()' function inherited from Object.
	 *
	 * @return the object formatted as a parsable JSON string.
	 */
	public String toJson() {
		return "{'answer_id': '" + answer_id + "', 'email': '" + email + "', 'question_id': '" + question_id + "'}";
	}
	
	
}
