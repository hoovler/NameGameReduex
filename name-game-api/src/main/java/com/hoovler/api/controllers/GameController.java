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
package com.hoovler.api.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoovler.api.data.DataService;
import com.hoovler.api.models.Answer;
import com.hoovler.api.models.AnswerBody;
import com.hoovler.api.models.Ask;
import com.hoovler.api.models.Mode;

@RestController
@RequestMapping("/namegame/v" + DataService.apiVersion)
public class GameController {
	private static Logger log = LogManager.getLogger(GameController.class.getName());
	
	private DataService persist;
	
	@Autowired
	public GameController(DataService persist) {
		this.persist = persist;
	}
	
	// GET: question posed to API consumer
	@RequestMapping(path = "/ask", method = RequestMethod.GET)
	public Ask Ask(
			@RequestParam(value="email") String playerEmail,
			@RequestParam(value="mode") String mode) {
		
		if(this.persist == null) this.persist = new DataService();
		
		// currently, only 2 modes available: 0, 1
		if (Integer.valueOf(mode) > 1) mode = "0";
		
		// convert string to enum
		Mode gameMode = Mode.values()[Integer.valueOf(mode)];
		
		return new Ask(playerEmail, gameMode, this.persist);
	}
	
	// POST: API consumer's answer to persistence model
	@PostMapping(path = "/{questionId}")
	@ResponseBody
	public Answer getAnswer(
			@PathVariable("questionId") long questionId,
			@RequestParam(value="email") String playerEmail,
			@RequestBody AnswerBody answerBody){
		
		if(this.persist == null) this.persist = new DataService();
		
		return new Answer(this.persist, answerBody.getPlayerEmail(), questionId, answerBody.getAnswerId());
	}
}
