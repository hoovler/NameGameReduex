/* 
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2018
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

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoovler.api.models.answer.AnswerArgs;
import com.hoovler.api.models.ask.AskArgs;
import com.hoovler.api.resources.AnswerQuestion;
import com.hoovler.api.resources.AskQuestion;
import com.hoovler.api.resources.Players;
import com.hoovler.api.resources.Questions;
import com.hoovler.api.utils.GameUtils;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

@RestController
@RequestMapping("/namegame/v" + GameUtils.API_VERSION)
public class GameController {

	private static Logger log = LogManager.getLogger(GameController.class.getName());
	
	/*
	 * frustratingly, all Spring annotation parameters must be a constant 
	 * value at runtime; therefore, any derived values and their constituent 
	 * components must be declared within the class in which they will be 
	 * used.
	 */
	

	protected static final String pathSep = "/";
	protected static final String pathVarOpen = "{";
	protected static final String pathVarClose = "}";


	protected static final String defaultAlpha = "";
	protected static final String defaultNumeric = "0";


	protected static final String emailParam = "email";
	protected static final String modeParam = "mode";
	protected static final String mattsParam = "matts";
	protected static final String questionIdParam = "question_id";
	protected static final String answerIdParam = "answer_id";


	protected static final String askResource = "ask";
	protected static final String answerResource = "answer";
	protected static final String questionsResource = "questions";
	protected static final String playersResource = "players";


	protected static final String askPath = pathSep + askResource;
	protected static final String questionsPath = pathSep + questionsResource;
	protected static final String playersPath = pathSep + playersResource;
	
	// variable endpoints
	protected static final String answerPath = pathSep + answerResource 
			+ pathSep + pathVarOpen + questionIdParam + pathVarClose;
	protected static final String questionPath = pathSep + questionsResource 
			+ pathSep + pathVarOpen + questionIdParam + pathVarClose;
	protected static final String playerPath = pathSep + playersResource 
			+ pathSep + pathVarOpen + emailParam + pathVarClose;
	

	// private member variables
	private final DefaultProfileDao profileService;
	private final Players playerService;
	private final Questions questionService;

	@Autowired
	public GameController(DefaultProfileDao profileService, Players playerService, Questions questionService) {
		this.profileService = profileService;
		this.playerService = playerService;
		this.questionService = questionService;
	}

	// GET: question posed to API consumer
	@GetMapping(path = askPath)
	public AskQuestion ask(@RequestParam(value = emailParam, defaultValue = defaultAlpha) String playerEmail,
			@RequestParam(value = modeParam, defaultValue = defaultNumeric) int mode,
			@RequestParam(value = mattsParam, defaultValue = defaultNumeric) String mattsOnly) {
		AskArgs askParams = new AskArgs(playerEmail, mode, mattsOnly);

		log.info("Receiving GET params as request for a new Question object: ");
		log.info(emailParam + " = " + askParams.getPlayerEmail());
		log.info(modeParam + " = " + askParams.getMode());
		log.info(mattsParam + " = " + askParams.getMattsOnly());

		// init a new Ask Response
		return new AskQuestion(askParams, this.profileService, this.playerService, this.questionService);
	}

	// POST: API consumer's answer to persistence model
	@PostMapping(path = answerPath)
	public AnswerQuestion answer(@PathVariable(questionIdParam) long questionId,
			@RequestBody AnswerArgs answerBody) {
		answerBody.setQuestionId(questionId);

		// log POST values
		log.info("Receiving POST values in answer to a question: ");
		log.info(answerIdParam + " = " + answerBody.getAnswerId());
		log.info(emailParam + " = " + answerBody.getPlayerEmail());
		log.info(questionIdParam + " = " + answerBody.getQuestionId());

		return new AnswerQuestion(answerBody, this.playerService, this.questionService);
	}

	// diagnostic endpoints
	@RequestMapping(path = questionsPath)
	public ArrayList<Question> questions() {
		return questionService.questionList();
	}

	@RequestMapping(path = questionsPath)
	public ArrayList<Question> questions(int length) {
		return questionService.questionList();
	}

	@RequestMapping(path = questionsPath)
	public ArrayList<Question> questions(int length, int skip) {
		return questionService.questionList();
	}

	@RequestMapping(path = questionsPath)
	public ArrayList<Question> questions(int length, int skip, int reverse) {
		return questionService.questionList();
	}
	
	@RequestMapping(path = questionPath)
	public Question question(@PathVariable(questionIdParam) long qId) {
		return questionService.getQuestion(qId);
	}	

	@RequestMapping(path = playersPath)
	public ArrayList<Player> players() {
		return playerService.playerList();
	}
}
