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

import com.hoovler.api.models.answer.Answer;
import com.hoovler.api.models.answer.AnswerArgs;
import com.hoovler.api.models.ask.Ask;
import com.hoovler.api.models.ask.AskArgs;
import com.hoovler.api.persistence.PlayerService;
import com.hoovler.api.persistence.QuestionService;
import com.hoovler.api.resources.GameUtils;
import com.hoovler.dao.DefaultProfileDao;

@RestController
@RequestMapping("/namegame/v" + GameUtils.API_VERSION)
public class GameController {
	private static Logger log = LogManager.getLogger(GameController.class.getName());

	
	private final DefaultProfileDao profileService;
	private final PlayerService playerService;
	private final QuestionService questionService;


	@Autowired
	public GameController(
			DefaultProfileDao profileService,
			PlayerService playerService,
			QuestionService questionService) {
		this.profileService = profileService;
		this.playerService = playerService;
		this.questionService = questionService;
	}

	// GET: question posed to API consumer
	@RequestMapping(path = GameUtils.Path.GET, method = RequestMethod.GET)
	public Ask ask(
			@RequestParam(value = GameUtils.Param.EMAIL, defaultValue = GameUtils.DefaultStr.EMAIL) String playerEmail, 
			@RequestParam(value = GameUtils.Param.MODE, defaultValue = GameUtils.DefaultStr.MODE) int mode,
			@RequestParam(value = GameUtils.Param.MATTS, defaultValue = GameUtils.DefaultStr.MATTS) String mattsOnly) {
		AskArgs askParams = new AskArgs(playerEmail, mode, mattsOnly);
		
		log.info("playerEmail = " + playerEmail);
		log.info("mode = " + mode);
		log.info("mattsOnly = " + mattsOnly);
		
		log.info("Receiving GET params as request for a new Question object: ");
		log.info(GameUtils.Param.EMAIL + " = " + askParams.getPlayerEmail());
		log.info(GameUtils.Param.MODE + " = " + askParams.getMode());
		log.info(GameUtils.Param.MATTS + " = " + askParams.getMattsOnly());

		// init a new Ask Response
		return new Ask(askParams, this.profileService, this.playerService, this.questionService);
	}

	// POST: API consumer's answer to persistence model
	@PostMapping(path = GameUtils.Path.POST)
	@ResponseBody
	public Answer answer(
			@PathVariable(GameUtils.Param.QUESTION_ID) long questionId,
			@RequestBody AnswerArgs answerBody) {
		answerBody.setQuestionId(questionId);
		
		// log POST values
		log.info("Receiving POST values in answer to a question: ");
		log.info(GameUtils.Param.ANSWER_ID + " = " + answerBody.getAnswerId());
		log.info(GameUtils.Param.EMAIL + " = " + answerBody.getPlayerEmail());
		log.info(GameUtils.Param.QUESTION_ID + " = " + answerBody.getQuestionId());
		
		return new Answer(answerBody, this.playerService, this.questionService);
	}
}
