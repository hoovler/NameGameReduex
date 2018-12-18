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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.models.Question;

public class DefaultQuestionDao implements QuestionDao {
	private static Logger log = LogManager.getLogger(DefaultQuestionDao.class.getName());
	
	private Map<Integer, Question> questionMap;
	
	public DefaultQuestionDao() {
		// just instantiate
		this.questionMap = new HashMap<>();
	}

	@Override
	public Question getQuestion(Integer qId) {
		return questionMap.get(qId);
	}

	@Override
	public Question addQuestion(Question q) {
		if (q == null || q.getQuestionId() < 1) {
			log.warn("Question must have a valid ID...");
		} else {
			log.info("Adding Question object: " + q.getQuestionId());
			questionMap.put(q.getQuestionId(), q);
		}
		return q;
	}

	@Override
	public Question updateQuestion(Integer id, Question updatedQ) {
		if (!questionMap.containsKey(id)) {
			log.warn("No Question with id=" + id + " found... not updating Question.");
			return null;
		} else {
			return questionMap.put(id, updatedQ);
		}
	}

	@Override
	public boolean deleteQuestion(Integer qId) {
		Question removedQ = this.questionMap.remove(qId);
		return removedQ != null;
	}

	@Override
	public List<Question> questionList() {
		return new ArrayList<>(questionMap.values());
	}

}
