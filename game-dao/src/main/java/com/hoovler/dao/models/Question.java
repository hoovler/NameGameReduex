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
package com.hoovler.dao.models;

import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Question {
	private static Logger log = LogManager.getLogger(Question.class.getName());
		private String questionId;
		private ArrayList<String> optionIds;
		private String answerId;
		private Date timeAsked;
		private Date timeAnswered;
		private String playerEmail;

	public String getQuestionId() {
		return questionId;
	}

	public ArrayList<String> getSelectionIds() {
		return optionIds;
	}

	public String getAnswerId() {
		return answerId;
	}

	public Date getTimeAsked() {
		return timeAsked;
	}

	public Date getTimeAnswered() {
		return timeAnswered;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setQuestionId(String questionId) {
		
		this.questionId = questionId;
	}

	public void setSelectionIds(ArrayList<String> selectionIds) {
		this.optionIds = selectionIds;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public void setTimeAsked(Date timeAsked) {
		this.timeAsked = timeAsked;
	}

	public void setTimeAnswered(Date timeAnswered) {
		this.timeAnswered = timeAnswered;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}
	// ******************** OBJECT OVERRIDES ********************
	
	// ******************** OBJECT OVERRIDES ********************

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerEmail == null) ? 0 : playerEmail.hashCode());
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result + ((optionIds == null) ? 0 : optionIds.hashCode());
		result = prime * result + ((timeAnswered == null) ? 0 : timeAnswered.hashCode());
		result = prime * result + ((timeAsked == null) ? 0 : timeAsked.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (playerEmail == null) {
			if (other.playerEmail != null)
				return false;
		} else if (!playerEmail.equals(other.playerEmail))
			return false;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (optionIds == null) {
			if (other.optionIds != null)
				return false;
		} else if (!optionIds.equals(other.optionIds))
			return false;
		if (timeAnswered == null) {
			if (other.timeAnswered != null)
				return false;
		} else if (!timeAnswered.equals(other.timeAnswered))
			return false;
		if (timeAsked == null) {
			if (other.timeAsked != null)
				return false;
		} else if (!timeAsked.equals(other.timeAsked))
			return false;
		return true;
	}
	
	
}
