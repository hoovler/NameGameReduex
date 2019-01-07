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

import java.util.ArrayList;

/** The response model for the {@code /ask} endpoint. 
 * */
public class AskResponseBody extends ObjectResponseModel{

	/** Tracks whether or not the question has been answered. */
	protected boolean answered;
	
	/** The pared-down game question's unique ID. */
	protected String question_id;
	
	/** The value for which to find a matching record in the {@code options}. */
	protected String target;
	
	/** The options. */
	protected ArrayList<QuestionOption> options;

	/** Checks if is answered.
	 *
	 * @return true, if is answered */
	public boolean isAnswered() {
		return this.answered;
	}

	/** Gets AskQuestion.questionId
	 *
	 * @return the question id */
	public String getQuestionId() {
		return question_id;
	}

	/** Gets AskQuestion.target
	 *
	 * @return the target */
	public String getTarget() {
		return target;
	}

	/** Gets AskQuestion.options
	 *
	 * @return the options */
	public ArrayList<QuestionOption> getOptions() {
		return options;
	}

	/** Answered.
	 *
	 * @param answer the answer */
	public void answered(boolean answer) {
		this.answered = answer;
	}

	/** Sets AskResponseBody.questionId
	 *
	 * @param questionId the new question id */
	public void setQuestionId(String questionId) {
		this.question_id = questionId;
	}

	/** Sets AskQuestion.target
	 *
	 * @param target the new target */
	public void setTarget(String target) {
		this.target = target;
	}

	/** Sets AskQuestion.options
	 *
	 * @param options the new options */
	public void setOptions(ArrayList<QuestionOption> options) {
		this.options = options;
	}

	/** Instantiates a new ask response body. */
	public AskResponseBody() {
		this.options = new ArrayList<>();
	}

	/**
	 * Instantiates a new ask response body.
	 *
	 * @param answered the answered
	 */
	public AskResponseBody(boolean answered) {
		this.answered = answered;
	}

	/**
	 * Instantiates a new ask response body.
	 *
	 * @param answered the answered
	 * @param message the message
	 */
	public AskResponseBody(boolean answered, String message) {
		this.answered = answered;
		this.message = message;
	}

	/**
	 * Instantiates a new ask response body.
	 *
	 * @param answered the answered
	 * @param question_id the question id
	 * @param message the message
	 */
	public AskResponseBody(boolean answered, String question_id, String message) {
		this.answered = answered;
		this.question_id = question_id;
		this.message = message;
	}

	/**
	 * Instantiates a new ask response body.
	 *
	 * @param answered the answered
	 * @param question_id the question id
	 * @param target the target
	 * @param message the message
	 */
	public AskResponseBody(boolean answered, String question_id, String target, String message) {
		this.answered = answered;
		this.question_id = question_id;
		this.target = target;
		this.message = message;
	}

	/**
	 * Instantiates a new ask response body.
	 *
	 * @param answered the answered
	 * @param question_id the question id
	 * @param target the target
	 * @param message the message
	 * @param options the options
	 */
	public AskResponseBody(boolean answered, String question_id, String target, String message,
			ArrayList<QuestionOption> options) {
		this.answered = answered;
		this.question_id = question_id;
		this.target = target;
		this.message = message;
		this.options = options;
	}
	
	/**
	 * Parses the json.
	 *
	 * @param json the json
	 * @return the ask response body
	 */
	public AskResponseBody parseJson(String json) {
		// TODO: parse from Json
		return this;
	}

}
