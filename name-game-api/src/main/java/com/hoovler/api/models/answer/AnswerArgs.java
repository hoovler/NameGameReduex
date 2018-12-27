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
package com.hoovler.api.models.answer;

/**
 * <p><h3>AnswerArgs</h3>
 * <p><b><u>Purpose</u></b></p>
 * To provide a template for the <code>@RequestBody</code> annotation's <code>HttpMessageConverter</code> 
 * <p><b><u>Information</u></b></p>
 * <p><code>AnswerArgs</code> simply provides the object into which Answer body attributes should be mapped.
 * It should be noted that parameters used in the creation of the POST uri, and / or any XML or JSON values in 
 * the request body passed with the POST uri, should be named in accordance with this project's documented arguments.</p>
 * @see org.springframework.web.bind.annotation.RequestBody 
 * 		org.springframework.web.bind.annotation.RequestBody
 */
public class AnswerArgs {
	
	private String answerId;
	private String playerEmail;
	private Long questionId;
	
	public String getAnswerId() {
		return this.answerId;
	}
	
	public String getPlayerEmail() {
		return this.playerEmail;
	}
	
	public Long getQuestionId() {
		return questionId;
	}
	
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	
	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public AnswerArgs() {
		
	}
	
	public AnswerArgs(String answerId, String playerEmail) {
		this.answerId = answerId;
		this.playerEmail = playerEmail;
	}
	
	public AnswerArgs(String answerId, String playerEmail, Long questionId) {
		this.answerId = answerId;
		this.playerEmail = playerEmail;
		this.questionId = questionId;
	}
}
