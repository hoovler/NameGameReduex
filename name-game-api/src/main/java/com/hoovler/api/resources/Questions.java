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
package com.hoovler.api.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.models.Subject;
import com.hoovler.dao.QuestionDao;
import com.hoovler.dao.models.Question;

/**
 * <p><h3>Questions</h3>
 * <p><b><u>Purpose</u></b></p>
 * <p>This class provides an implementation of the <code>QuestionDao</code> interface.</p>
 * <p><b><u>Information</u></b></p>
 * <p>Though this class could simply be one which extends  <code>DefaultQuestionDao</code>,
 * 		the state of the object's data isn't persistent when making calls to the <code>super</code>
 * 		class, as that class hasn't been autowired by the Spring Framework.</p>
 */
public class Questions implements QuestionDao {
	
	// TODO: Use a NoSql db to store persistence objects; javax.persistence.EntityManager
	// TODO: https://mapr.com/blog/data-modeling-guidelines-nosql-json-document-databases/
	
	/** <p><i>log</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private static Logger log = LogManager.getLogger(Questions.class.getName());
	
	/** <p><i>questions</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private HashMap <Long, Question> questionsMap;
	
	/**
	 * Instantiates a new questions.
	 */
	public Questions() {
		this.questionsMap = new HashMap<>();
	}
	
	// instantiated interface methods
	
	/* (non-Javadoc)
	 * @see com.hoovler.dao.QuestionDao#getQuestion(java.lang.Long)
	 */
	@Override
	public Question getQuestion(Long qId) {
		Question q = questionsMap.get(qId);
		if (q == null)
			return null;
		return q;
	}
	
	/* (non-Javadoc)
	 * @see com.hoovler.dao.QuestionDao#addQuestion(com.hoovler.dao.models.Question)
	 */
	@Override
	public Question addQuestion(Question q) {
		if (q == null || q.getId() < 1) {
			log.warn("Question must have a valid ID...");
		} else {
			log.info("Adding Question object: " + q.getId());
			questionsMap.put(q.getId(), q);
		}
		return q;
	}



	/* (non-Javadoc)
	 * @see com.hoovler.dao.QuestionDao#updateQuestion(java.lang.Long, com.hoovler.dao.models.Question)
	 */
	@Override
	public Question updateQuestion(Long id, Question updatedQ) {
		if (!questionsMap.containsKey(id)) {
			log.warn("No Question with id=" + id + " found... not updating Question.");
			return null;
		} else {
			return questionsMap.put(id, updatedQ);
		}
	}



	/* (non-Javadoc)
	 * @see com.hoovler.dao.QuestionDao#deleteQuestion(java.lang.Long)
	 */
	@Override
	public boolean deleteQuestion(Long qId) {
		Question removedQ = this.questionsMap.remove(qId);
		return removedQ != null;
	}



	/* (non-Javadoc)
	 * @see com.hoovler.dao.QuestionDao#questionList()
	 */
	@Override
	public ArrayList<Question> questionList() {
		return new ArrayList<>(questionsMap.values());
	}
	
	// additional class methods
	
	public ArrayList<Question> questionList(int length, int skip, boolean reverse){
		
		ArrayList<Question> questions = new ArrayList<>(questionsMap.values());
		
		// return an empty list
		if (skip + length > questions.size() 
				|| length > questions.size()
				|| skip > questions.size())
			return new ArrayList<>();
		
		ArrayList<Question> shallow = questions.subList(0, questions.size());
		Collections.reverse(questions);
		
		return new ArrayList<>(questions.subList(skip, skip + length));
	}
	
	/**
	 * Question exists.
	 *
	 * @param qId the q id
	 * @return true, if successful
	 */
	public boolean questionExists(Long qId) {
		return questionsMap.get(qId) != null;
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
