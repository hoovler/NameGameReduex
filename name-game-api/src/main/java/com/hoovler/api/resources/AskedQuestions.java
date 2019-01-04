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

import java.util.ArrayList;
import java.util.HashMap;

import com.hoovler.dao.AskQuestionDao;

/** The Class QuestionsAsked. */
public class AskedQuestions implements AskQuestionDao {

	private HashMap<String, AskQuestion> questionsAskedMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#getQuestionAsked(java.lang.String)
	 */
	@Override
	public AskQuestion getQuestionAsked(String questionId) {
		return questionsAskedMap.get(questionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#addQuestionAsked(com.hoovler.api.resources.AskQuestion)
	 */
	@Override
	public AskQuestion addQuestionAsked(AskQuestion questionAsked) {
		return questionsAskedMap.put(questionAsked.getQuestionId(), questionAsked);
	}

	/** Adds the question asked.
	 *
	 * @param  email         the email
	 * @param  questionAsked the question asked
	 * @return               the ask question */
	public AskQuestion addQuestionAsked(String email, AskQuestion questionAsked) {
		return questionsAskedMap.put(email, questionAsked);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#updateQuestion(java.lang.String, com.hoovler.api.resources.AskQuestion)
	 */
	@Override
	public AskQuestion updateQuestion(String questionId, AskQuestion questionAsked) {
		return !questionsAskedMap.containsKey(questionId) ? null : questionsAskedMap.put(questionId, questionAsked);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#questionAsked(java.lang.String)
	 */
	@Override
	public boolean questionAsked(String questionId) {
		return questionsAskedMap.containsKey(questionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#deleteQuestionAsked(java.lang.String)
	 */
	@Override
	public boolean deleteQuestionAsked(String questionId) {
		return questionsAskedMap.remove(questionId) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#questionsAsked()
	 */
	@Override
	public ArrayList<AskQuestion> questionsAsked() {
		return new ArrayList<>(questionsAskedMap.values());
	}
	

	/** Instantiates a new questions asked.
	 *
	 * @param initQ the init Q */
	public AskedQuestions(AskQuestion initQ) {
		this.questionsAskedMap = new HashMap<>();
		this.questionsAskedMap.put(initQ.getQuestionId(), initQ);
	}

	/** Instantiates a new questions asked. */
	public AskedQuestions() {
		this.questionsAskedMap = new HashMap<>();
	}
}