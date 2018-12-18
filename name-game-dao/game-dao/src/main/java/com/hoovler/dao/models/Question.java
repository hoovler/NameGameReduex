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

import org.apache.commons.lang3.StringUtils;

public class Question {
	
	/** <p>The unique id of the <code>Question</code> object.</p> 
	 * It is generated using <code>Question.hashCode()</code>, the overridden 
	 * <code>Object.hashCode()</code>, by using the values of the other <code>Question</code> member variables.
	 * To ensure a unique ID, the timestamp - as MS - is appended to the normal hash code. */
	private int questionId;

	/** 
	 * <p>The object against which an <code>options</code> object is matched for a selection.</p>
	 * <p><b><u>Example</u></b></p>
	 * <table border='0'>
	 * 	<tr><td><code>options</code></td><td>> <code>1,red|2,purple|3,orange|4,pink|5,green|6,blue</code></td></tr>
	 * 	<tr><td><b><u><code>target</code></u></b></td><td>> <code>fruit</code></td></tr>
	 * 	<tr><td><code>answer</code> id</td><td>> <code>3</code></td></tr>
	 * </table>
	 **/
	NGSubject target;
	
	/**
	 * <p>The list of objects against which a <code>target</code> object is matched.</p>
	 * <p><b><u>Example</u></b></p>
	 * <table border='0'>
	 * 	<tr><td><b><u><code>options</code></u></b></td><td>> <code>1,red|2,purple|3,orange|4,pink|5,green|6,blue</code></td></tr>
	 * 	<tr><td><code>target</code></td><td>> <code>fruit</code></td></tr>
	 * 	<tr><td><code>answer</code> id</td><td>> <code>3</code></td></tr>
	 * </table>
	 * */
	private ArrayList<NGSubject> options;
	
	/** 
	 * <p>The date and time the specific <code>Question</code> object was created, which is ostensibly 
	 * the same as when it was posed to the user.</p>
	 *  */
	private Date timeAsked;
	
	public int getQuestionId() {
		return this.questionId;
	}
	
	public NGSubject getTarget() {
		return target;
	}
	public ArrayList<NGSubject> getOptions() {
		return options;
	}
	public Date getTimeAsked() {
		return timeAsked;
	}
	
	public boolean setQuestionId() {
		if (timeAsked != null && options != null & target != null) {
			if (!options.isEmpty() && StringUtils.isNotBlank(target.getName())) {
				
			}
		}
		return false;
	}
	
	public void setTarget(NGSubject target) {
		this.target = target;
	}
	public void setOptions(ArrayList<NGSubject> options) {
		this.options = options;
	}
	public void setTimeAsked(Date timeAsked) {
		this.timeAsked = timeAsked;
	}
	
	public Question() {
		// based on game mode,
		
	}
	
	public Question(NGSubject target, ArrayList<NGSubject> options, Date timeAsked) {
		setTarget(target);
		setOptions(options);
		setTimeAsked(timeAsked);
	}
	
	// ********************************* OBJECT OVERRIDES *********************************
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result + ((timeAsked == null) ? 0 : timeAsked.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Question other = (Question) obj;
		if (options == null) {
			if (other.options != null) {
				return false;
			}
		} else if (!options.equals(other.options)) {
			return false;
		}
		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}
		if (timeAsked == null) {
			if (other.timeAsked != null) {
				return false;
			}
		} else if (!timeAsked.equals(other.timeAsked)) {
			return false;
		}
		return true;
	}
	
}
