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
package com.hoovler.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.models.Question;

/**
 * <p><h3>DefaultQuestionDao</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>DefaultQuestionDao</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * An example:
 * <pre>some code</pre>
 * Another example:
 * <pre>some more code;</pre>
 */
public class DefaultQuestionDao implements QuestionDao {

	// *********************************************** public members

	/**
	 * <p><h3>Temporally</h3>
	 * <p><b><u>Purpose</u></b></p>
	 * This Enum ...</p>
	 * <p><b><u>Information</u></b><br />
	 * The <code>Temporally</code> object is...</p>
	 * <p><b><u>Examples</u></b></p>
	 * An example:
	 * <pre>some code</pre>
	 * Another example:
	 * <pre>some more code;</pre>
	 */
	public enum Temporal {
		/** <p><i>BEFORE</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		BEFORE,
		/** <p><i>AFTER</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		AFTER;
	}

	public enum SortBy {
		ID,
		CREATED,
		ASKED
	}

	// *********************************************** private members

	/** <p><i>log</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private static Logger log = LogManager.getLogger(DefaultQuestionDao.class.getName());

	/** <p><i>questionMap</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private Map<Long, Question> questionMap;

	// *********************************************** Constructors

	/**
	 * Instantiates a new default question dao.
	 */
	public DefaultQuestionDao() {
		// just instantiate
		this.questionMap = new HashMap<>();
	}

	// *********************************************** overrides

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#getQuestion(java.lang.Long)
	 */
	@Override
	public Question getQuestion(Long qId) {
		return questionMap.get(qId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#addQuestion(com.hoovler.dao.models.Question)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#updateQuestion(java.lang.Long,
	 * com.hoovler.dao.models.Question)
	 */
	@Override
	public Question updateQuestion(Long id, Question updatedQ) {
		if (!questionMap.containsKey(id)) {
			log.warn("No Question with id=" + id + " found... not updating Question.");
			return null;
		} else {
			return questionMap.put(id, updatedQ);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#deleteQuestion(java.lang.Long)
	 */
	@Override
	public boolean deleteQuestion(Long qId) {
		Question removedQ = this.questionMap.remove(qId);
		return removedQ != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#questionList()
	 */
	@Override
	public ArrayList<Question> questionList() {
		return new ArrayList<>(questionMap.values());
	}

	// *********************************************** new methods

	// return sorted by date

	/**
	 * Get the Questions list, sorted by date created
	 *
	 * @return the sorted questions list, sorted on Question.getCreated()
	 */
	public ArrayList<Question> getSorted() {
		ArrayList<Question> all = questionList();
		all.sort(Comparator.comparing(Question::getCreated));
		return all;
	}

	/**
	 * Gets the Questions, sorted by the attribute given in the SortBy Enum.
	 *
	 * @param method - a <code>SortBy</code> enumerated value, which corresponds to the attribute on which to sort the list.</p>
	 * 
	 * @return <b>ArrayList&lt;Question&gt; questions</b> 
	 * 	<p>The list of Question objects sorted by the value indicated in the SortBy (method) enum.</p>
	 */
	public ArrayList<Question> getSorted(SortBy method) {
		ArrayList<Question> all = questionList();
		
		switch (method) {
		case ID:
			all.sort(Comparator.comparing(Question::getId));
			return all;
		case CREATED: default:
			all.sort(Comparator.comparing(Question::getCreated));
			return all;
		}
		
	}

}
