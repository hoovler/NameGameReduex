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
package com.hoovler.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.models.Question;

/** <p> <h3>DefaultQuestionDao</h3> </p> <p> <b><u>Purpose:</u></b> TODO: add purpose... </p> <p> <b><u>Information:</u></b> TODO: add info... </p> */
public class DefaultQuestionDao implements QuestionDao {
	/** <p> Static member: log </p> <p> TODO: add description </p> . */
	private static Logger log = LogManager.getLogger(DefaultQuestionDao.class.getName());

	/** <p> Member variable: questionMap </p> <p> TODO: add description </p> */
	private Map<Long, Question> questionMap;

	// *********************************************** Constructors

	/** Instantiates a new default question dao. */
	public DefaultQuestionDao() {
		// just instantiate
		this.questionMap = new HashMap<>();
	}

	// *********************************************** overrides

	/** Gets the question.
	 *
	 * @param  qId the q id
	 * @return     the question */
	@Override
	public Question getQuestion(Long qId) {
		return questionMap.get(qId);
	}

	/** Adds the question.
	 *
	 * @param  q the q
	 * @return   the question */
	@Override
	public Question addQuestion(Question q) {
		if (q == null || q.getId() < 1) {
			log.warn("Question must have a valid ID...");
		} else {
			log.info("Adding Question object: " + q.getId());
			questionMap.put(q.getId(), q);
		}
		return q;
	}

	/** Update question.
	 *
	 * @param  id       the id
	 * @param  updatedQ the updated Q
	 * @return          the question */
	@Override
	public Question updateQuestion(Long id, Question updatedQ) {
		if (!questionMap.containsKey(id)) {
			log.warn("No Question with id=" + id + " found... not updating Question.");
			return null;
		} else {
			return questionMap.put(id, updatedQ);
		}
	}

	/** Delete question.
	 *
	 * @param  qId the q id
	 * @return     true, if successful */
	@Override
	public boolean deleteQuestion(Long qId) {
		Question removedQ = this.questionMap.remove(qId);
		return removedQ != null;
	}

	/** Question list.
	 *
	 * @return the array list */
	@Override
	public ArrayList<Question> questionList() {
		return new ArrayList<>(questionMap.values());
	}

	// *********************************************** new methods

	// return sorted by date

	/** Get the Questions list, sorted by date created
	 *
	 * @return the sorted questions list, sorted on Question.getCreated() */
	public ArrayList<Question> getSorted() {
		ArrayList<Question> all = questionList();
		all.sort(Comparator.comparing(Question::getCreated));
		return all;
	}
}
