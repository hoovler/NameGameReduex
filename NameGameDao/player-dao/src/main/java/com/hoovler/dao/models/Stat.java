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

import java.util.Date;

/**
 * <p><h3>Stat</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>Stat</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * An example:
 * <pre>some code</pre>
 * Another example:
 * <pre>some more code;</pre>
 */
public class Stat {
	
	/** <p><i>questionId</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private int questionId;
	
	/** <p><i>askedTime</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private Date askedTime;
	
	/** <p><i>answerTime</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private Date answerTime;
	
	/** <p><i>correct</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private boolean correct;
	
	/**
	 * Gets the question id.
	 *
	 * @return the question id
	 */
	public int getQuestionId() {
		return questionId;
	}
	
	/**
	 * Gets the asked time.
	 *
	 * @return the asked time
	 */
	public Date getAskedTime() {
		return askedTime;
	}
	
	/**
	 * Gets the answer time.
	 *
	 * @return the answer time
	 */
	public Date getAnswerTime() {
		return answerTime;
	}
	
	/**
	 * Checks if is correct.
	 *
	 * @return true, if is correct
	 */
	public boolean isCorrect() {
		return correct;
	}
	
	/**
	 * Sets the question id.
	 *
	 * @param questionId the new question id
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	/**
	 * Sets the asked time.
	 *
	 * @param askedTime the new asked time
	 */
	public void setAskedTime(Date askedTime) {
		this.askedTime = askedTime;
	}
	
	/**
	 * Sets the answer time.
	 *
	 * @param answerTime the new answer time
	 */
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	
	/**
	 * Sets the correct.
	 *
	 * @param isCorrect the new correct
	 */
	public void setCorrect(boolean isCorrect) {
		this.correct = isCorrect;
	}
	
	/**
	 * Instantiates a new stat.
	 */
	public Stat() {
		
	}
	
	/**
	 * Instantiates a new stat.
	 *
	 * @param questionId the question id
	 * @param askedTime the asked time
	 * @param answerTime the answer time
	 * @param isCorrect the is correct
	 */
	public Stat(int questionId, Date askedTime, Date answerTime, boolean isCorrect) {
		this.questionId = questionId;
		this.askedTime = askedTime;
		this.answerTime = answerTime;
		this.correct = isCorrect;
	}
	
	
	
}
