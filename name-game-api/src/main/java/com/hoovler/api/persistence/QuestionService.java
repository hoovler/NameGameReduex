/* 
 * Copyright (c) ${author} 2018 
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
package com.hoovler.api.persistence;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.models.Subject;
import com.hoovler.dao.DefaultQuestionDao;
import com.hoovler.dao.models.Question;

public class QuestionService extends DefaultQuestionDao {
	
	// TODO: Use hibernate to store persistence objects; javax.persistence.EntityManager
	
	private static Logger log = LogManager.getLogger(QuestionService.class.getName());

	@Override
	public Question getQuestion(Long qId) {
		Question q = super.getQuestion(qId);
		if (q == null)
			return null;
		return q;
	}
	
	/**
	 * Question exists.
	 *
	 * @param qId the q id
	 * @return true, if successful
	 */
	public boolean questionExists(Long qId) {
		return super.getQuestion(qId) != null;
	}

	/**
	 * Cast the Question's target or an option from <code>class Object</code> to <code>class Target</code>. 
	 *
	 * @param 	questionObject An <code>Object</code> retrieved from <code>Question.getTarget</code>
	 * 			or from a member of the list <code>Question.getOptions()</code>.
	 * @return 	a <code>Subject</code> object, which is the class used within <code>Question</code> for the
	 * 			purposes of this API.
	 */
	public Subject subjectFromObject(Object questionObject) {
		return Subject.class.cast(questionObject);
	}

	/**
	 * Checks if is correct id.
	 *
	 * @param questionId the question id
	 * @param answerId the id value of the selected option
	 * @return true, if <code>answerId == Question(questionId).getTarget().getId()</code>
	 */
	public boolean isCorrectAnswer(long questionId, String answerId) {
		log.info("questionId = " + questionId);
		log.info("answerId = " + answerId);

		String correctId = "";

		Question q = getQuestion(questionId);

		if (q == null) {
			log.warn("Question with id " + questionId
					+ " was not retrieved... it was likely not stored in the Question persistence object.");
			return false;
		}

		Subject target = subjectFromObject(q.getTarget());

		log.info("Question with id " + questionId + " was found!");
		correctId = target.getId();
		return StringUtils.equalsIgnoreCase(StringUtils.deleteWhitespace(answerId),
				StringUtils.deleteWhitespace(correctId));
	}
}
