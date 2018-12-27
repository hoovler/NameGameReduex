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
package com.hoovler.api.models.ask;

import java.util.ArrayList;

public class AskResponse {
	
	private long questionId;
	
	private String target;
	
	private ArrayList<AskOption> options;

	public long getQuestionId() {
		return questionId;
	}

	public String getTarget() {
		return target;
	}

	public ArrayList<AskOption> getOptions() {
		return options;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setOptions(ArrayList<AskOption> options) {
		this.options = options;
	}

	public AskResponse() {
		
	}

	public AskResponse(long questionId) {
		this.questionId = questionId;
	}

	public AskResponse(long questionId, String target) {
		this.questionId = questionId;
		this.target = target;
	}

	public AskResponse(long questionId, String target, ArrayList<AskOption> options) {
		this.questionId = questionId;
		this.target = target;
		this.options = options;
	}
	
	
}
