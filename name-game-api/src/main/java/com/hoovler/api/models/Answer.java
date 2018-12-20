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
package com.hoovler.api.models;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.data.DataService;
import com.hoovler.dao.models.Stat;

/**
 * <p><h3>Answer</h3>
 * <p><b><u>Purpose</u></b></p>
 * This class represents the object presented when the API's POST verb is invoked.
 * <p><b><u>Information</u></b><br />
 * The <code>Answer</code> object is comprised of the following member variables, which are transposed into the JSON result:
 * <ul>
 * <li><code>String result</code></li>
 * <li><code>String playerEmail</code></li>
 * <li><code>Stat stat</code></li>
 * </ul>
 * <p>POST: http://localhost:8080/namegame/v[apiVersion]/[<b>questionId</b>]/email=[<b>playerEmail</b>]&answer_id=[<b>answerId</b>] </p>
 * The variables from the above URI are ultimately sent here, where the answer is evaluated and the response formulated.
 * <p><b><u>Examples</u></b></p>
 * <pre>GET:</pre>
 * <pre>POST: http://localhost:8080/namegame/v2.0.0/<b>12345678987654321</b>/email=[<b>playerEmail</b>]&answer_id=[<b>answerId</b>]</pre>
 */
public class Answer {
	// TODO: finish documenting
	private static Logger log = LogManager.getLogger(Answer.class.getName());
	
	private String result;
	private String playerEmail;
	private Stat stat;
	
	
	public String getResult() {
		return this.result;
	}
	
	public String getPlayerEmail() {
		return this.playerEmail;
	}
	
	public Stat getStat() {
		return this.stat;
	}
	
	public Answer(DataService data, String playerEmail, long questionId, String answerId) {
		
		boolean isCorrect = data.isCorrectAnswer(questionId, answerId);
		this.stat = new Stat(questionId, data.getQuestion(questionId).getCreated(), new Date(), isCorrect);
		this.playerEmail = playerEmail;
		
		if(isCorrect) {
			this.result = "CORRECT";
		} else {
			this.result = "INCORRECT";
		}
	}
}
