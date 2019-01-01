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
package com.hoovler.api.models;

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

	public String getAnswerId() {
		return this.answer_id;
	}

	public String getPlayerEmail() {
		return this.email;
	}

	public String getQuestionId() {
		return question_id;
	}

	public void setAnswerId(String answerId) {
		this.answer_id = answerId;
	}

	public void setPlayerEmail(String playerEmail) {
		this.email = playerEmail;
	}

	public void setQuestionId(String questionId) {
		this.question_id = questionId;
	}

	public AnswerArgs() {
	}

	public AnswerArgs(String answerId, String playerEmail) {
		this.answer_id = answerId;
		this.email = playerEmail;
	}

	public AnswerArgs(String answerId, String playerEmail, String questionId) {
		this.answer_id = answerId;
		this.email = playerEmail;
		this.question_id = questionId;
	}
}
