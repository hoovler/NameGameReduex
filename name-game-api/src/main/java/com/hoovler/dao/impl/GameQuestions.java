/*
 * Copyright (c) Michael Hoovler <hoovlermichael@gmail.com> 2019
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
package com.hoovler.dao.impl;

import static com.hoovler.api.utils.Message.INFO_ASK_PARAMS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;
import com.hoovler.api.models.AnswerResponseBody;
import com.hoovler.api.models.AskResponseBody;
import com.hoovler.api.utils.GameHelper;
import com.hoovler.api.utils.Message;
import com.hoovler.api.utils.QuestionsHelper;
import com.hoovler.dao.generic.QuestionParedDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;
import com.hoovler.utils.impl.BoolUtils;
import com.hoovler.utils.impl.ListUtils;

// TODO: Auto-generated Javadoc
/** The Class QuestionsAsked. */
public class GameQuestions implements QuestionParedDao {
	private static Logger log = LogManager.getLogger(QuestionParedDao.class.getName());

	private static final String LINE = " | ";
	private static final String SEP = " = ";

	private HashMap<String, AskResponseBody> questionsAskedMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#getQuestionAsked(java.lang.String)
	 */
	@Override
	public AskResponseBody getQuestionAsked(String questionId) {
		return questionsAskedMap.get(questionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#addQuestionAsked(com.hoovler.api.resources.AskQuestion)
	 */
	@Override
	public AskResponseBody addQuestionAsked(AskResponseBody questionAsked) {
		return questionsAskedMap.put(questionAsked.getQuestionId(), questionAsked);
	}

	/** Adds the question asked.
	 *
	 * @param  email         the email
	 * @param  questionAsked the question asked
	 * @return               the ask question */
	public AskResponseBody addQuestionAsked(String email, AskResponseBody questionAsked) {
		return questionsAskedMap.put(email, questionAsked);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#updateQuestion(java.lang.String, com.hoovler.api.resources.AskQuestion)
	 */
	@Override
	public AskResponseBody updateQuestion(String questionId, AskResponseBody questionAsked) {
		return !questionsAskedMap.containsKey(questionId) ? null : questionsAskedMap.put(questionId, questionAsked);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#questionAsked(java.lang.String)
	 */
	@Override
	public boolean questionAsked(String questionId) {
		return questionsAskedMap.containsKey(questionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#deleteQuestionAsked(java.lang.String)
	 */
	@Override
	public boolean deleteQuestionAsked(String questionId) {
		return questionsAskedMap.remove(questionId) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.api.persistence.AskQuestionDao#questionsAsked()
	 */
	@Override
	public ArrayList<AskResponseBody> questionsAsked() {
		return new ArrayList<>(questionsAskedMap.values());
	}

	/** Instantiates a new questions asked.
	 *
	 * @param initQ the init Q */
	public GameQuestions(AskResponseBody initQ) {
		this.questionsAskedMap = new HashMap<>();
		this.questionsAskedMap.put(initQ.getQuestionId(), initQ);
	}

	/** Instantiates a new service. */
	public GameQuestions() {
		this.questionsAskedMap = new HashMap<>();
	}

	/** Adds the from endpoint.
	 *
	 * @param  requestBody the request body
	 * @return             the ask response body */
	public AskResponseBody endpointPOST(String requestBody) {
		AskResponseBody body = new AskResponseBody();
		body.parseJson(requestBody);
		addQuestionAsked(body.getQuestionId(), body);
		return body;
	}

	/** Update from endpoint.
	 *
	 * @param  requestBody the request body
	 * @return             the ask response body */
	public AskResponseBody updateFromEndpoint(String requestBody) {
		AskResponseBody body = new AskResponseBody();
		body.parseJson(requestBody);
		if (questionAsked(body.getQuestionId())) {
			body = updateQuestion(body.getQuestionId(), body);
		} else {
			addQuestionAsked(body);
		}
		return body;
	}

	/** Delete from endpoint.
	 *
	 * @param  questionId the question id
	 * @return            true, if successful */
	public boolean deleteFromEndpoint(String questionId) {
		// TODO: add response model, and sufficient input validation
		return deleteQuestionAsked(questionId);
	}

	/** Endpoint handler.
	 *
	 * @param  email      the email
	 * @param  reverseVal the reverse val
	 * @param  mattsVal   the matts val
	 * @param  profiles   the profiles
	 * @param  players    the players
	 * @param  questions  the questions
	 * @return            the ask response body */
	public AskResponseBody endpointHandler(String email, String reverseVal, String mattsVal, DefaultProfileDao profiles,
			Players players, FullQuestions questions) {

		AskResponseBody body = new AskResponseBody();

		boolean reversed = BoolUtils.parseBool(reverseVal);
		boolean matts = BoolUtils.parseBool(mattsVal);

		log.info(INFO_ASK_PARAMS.s());
		log.info(GameHelper.PARAM_EMAIL + " = " + email);
		log.info(GameHelper.PARAM_REVERSE + " = " + reverseVal + " | " + reversed);
		log.info(GameHelper.PARAM_MATTS + " = " + mattsVal + " | " + matts);

		// set up a new question and update persistence
		Question q = QuestionsHelper.generateQuestion(profiles,
				matts ? GameHelper.MATTS_ONLY_VALUE_PREFIX : StringUtils.EMPTY);

		// set member values
		body.setOptions(QuestionsHelper.getQuestionOptions(reversed, q));
		body.setTarget(QuestionsHelper.getQuestionTarget(reversed, q));
		body.setQuestionId(GameHelper.encodeToHexWithSalt(q.getId(), email));

		// finally, update all persistence objects
		addQuestionAsked(body);
		questions.addQuestion(q);
		Player player = players.getOrAddPlayer(email);
		player.updateStats(new Date(), true);
		players.updatePlayer(email, player);

		return body;
	}

	/** Endpoint handler.
	 * <p>If the payload is valid JSON, then the persistence objects and payload begin the first level of the Dungeon of Sorrows... aka - the three levels of determinition logic that was too confusing to fit into one method.</p>
	 * <p>Each level performs an input validation check and, if that check passes, passes everything to the next level.</p>
	 *
	 * @param  payload   the payload
	 * @param  players   the players
	 * @param  questions the questions
	 * @return           the answer response body */
	public AnswerResponseBody endpointHandler(String payload, Players players, FullQuestions questions) {
		JsonObject args = GameHelper.parseAnswerBody(payload);
		if (args != null) {
			// pull args & prep vars
			String answerId = args.get(GameHelper.BODY_ANSWER_ID).getAsString();
			String email = args.get(GameHelper.PARAM_EMAIL).getAsString();
			String gameQuestionId = args.get(GameHelper.BODY_QUESTION_ID).getAsString();

			// log POST values
			log.info(Message.INFO_ANSWER_PARAMS.s());
			log.info(GameHelper.BODY_ANSWER_ID + " = " + answerId);
			log.info(GameHelper.PARAM_EMAIL + " = " + email);
			log.info(GameHelper.BODY_QUESTION_ID + " = " + gameQuestionId);

			// move to the next level!
			return wasQuestionAsked(answerId, email, gameQuestionId, players, questions);
		} else {
			// invalid json body
			AnswerResponseBody answerBody = new AnswerResponseBody();
			StringBuilder message = new StringBuilder();

			message.append(Message.MSG_INVALID_ANSWER_BODY.s()).append(LINE);
			message.append(payload).append(LINE).append(Message.MSG_ENCOURAGE.s());

			answerBody.setMessage(message.toString());
			answerBody.setPlayer(new Player());
			answerBody.setCorrect(false);

			return answerBody;
		}
	}

	/** Answer response first level.
	 * 
	 * <p>In this first check, we're making sure the question hasn't already been answered. There's no point spending further compute, space, or network resources on actions which aren't applicable.s</p>
	 *
	 * @param  payload   the payload
	 * @param  players   the players
	 * @param  questions the questions
	 * @return           the answer response body */
	private AnswerResponseBody wasQuestionAsked(String answerId, String email, String gameQuestionId, Players players,
			FullQuestions questions) {
		AnswerResponseBody answerBody = new AnswerResponseBody();
		StringBuilder message = new StringBuilder();

		if (questionAsked(gameQuestionId)) {
			AskResponseBody questionAsked = getQuestionAsked(gameQuestionId);
			if (questionAsked.isAnswered()) {
				// already answered... {queue 'wah waaaah' trombone slide}
				message.append(Message.MSG_Q_ALREADY_ANSWERED.s());
			} else {
				// move to the next level, again!!
				return hasValidArguments(answerId, email, gameQuestionId, players, questions);
			}
		} else {
			// question was never even asked... for shame.
			message.append(Message.MSG_Q_NOT_EXISTS.s()).append(gameQuestionId);
		}

		answerBody.setPlayer(new Player());
		answerBody.setMessage(message.toString());
		answerBody.setCorrect(false);
		return answerBody;
	}

	/** Answer response second level.
	 *
	 * <p>The focus of this method in the chain is to ensure the arguments within the JSON body are valid.</p>
	 * 
	 * @param  answerId        the answer id
	 * @param  email           the email
	 * @param  paredQuestionId the pared question id
	 * @param  players         the players
	 * @param  questions       the questions
	 * @return                 the answer response body */
	private AnswerResponseBody hasValidArguments(String answerId, String email, String paredQuestionId, Players players,
			FullQuestions questions) {
		long fullQuestionId;
		StringBuilder message = new StringBuilder();
		AnswerResponseBody body = new AnswerResponseBody();

		log.info("StringUtils.isNumeric(questionId) = " + StringUtils.isAlphanumeric(paredQuestionId));
		if (StringUtils.isNoneBlank(answerId, email, paredQuestionId) && StringUtils.isAlphanumeric(paredQuestionId)) {
			// calculate the relevant qId from questionId and playerEmail
			String fullQuestionIdStr = GameHelper.decodeFromHexWithSalt(paredQuestionId, email);
			if (isValidQuestionId(fullQuestionIdStr)) {
				fullQuestionId = Long.parseLong(fullQuestionIdStr);
				// ensure existence of player and question
				if (players.playerExists(email) && questions.questionExists(fullQuestionId)) {
					return isAnswerCorrect(answerId, email, paredQuestionId, fullQuestionId, players, questions);
				} else {
					message.append(Message.MSG_INVALID_ARG.s());
					message.append(LINE).append(GameHelper.PARAM_EMAIL).append(SEP).append(email);
					message.append(LINE).append(GameHelper.BODY_QUESTION_ID).append(SEP).append(email);
				}
			} else {
				message.append(Message.MSG_Q_NOT_ASKED);
			}
		} // end if none blank and isNumeric
		else {
			message.append(Message.MSG_INVALID_ARG.s());
			message.append(LINE).append(GameHelper.PARAM_EMAIL).append(SEP).append(email);
			message.append(LINE).append(GameHelper.BODY_ANSWER_ID).append(SEP).append(answerId);
			message.append(LINE).append(GameHelper.BODY_QUESTION_ID).append(SEP).append(paredQuestionId);
		}

		body.setCorrect(false);
		body.setMessage(message.toString());
		body.setPlayer(new Player());
		return body;
	}

	/** Enter the deep...
	 * 
	 * <p><em>All hope lost, oh ye that these words see; For naught but souls accursed in the method three!</em></p>
	 * <p>Just messin', this is the only method from which a positive response is made!
	 *
	 * @param  answerId        the answer id
	 * @param  email           the email
	 * @param  paredQuestionId the pared question id
	 * @param  fullQuestionId  the full question id
	 * @param  players         the players
	 * @param  questions       the questions
	 * @return                 the answer response body */
	private AnswerResponseBody isAnswerCorrect(String answerId, String email, String paredQuestionId,
			long fullQuestionId, Players players, FullQuestions questions) {
		AnswerResponseBody body = new AnswerResponseBody();
		StringBuilder message = new StringBuilder();
		boolean isCorrect = false;
		Player p = new Player();

		// ensure existence of player and question
		if (players.playerExists(email) && questions.questionExists(fullQuestionId)) {
			p = players.getPlayer(email);

			isCorrect = questions.isCorrectAnswer(fullQuestionId, answerId);
			message.append(isCorrect ? Message.MSG_CORRECT_ANSWER.s() : Message.MSG_INCORRECT_ANSWER.s());

			// persistence objects updates
			p.updateStats(questions.getQuestion(fullQuestionId).getCreated(), new Date(), isCorrect);
			players.updatePlayer(email, p);
			AskResponseBody askBody = questionsAskedMap.get(paredQuestionId);
			askBody.answered(isCorrect);
			questionsAskedMap.put(paredQuestionId, askBody);

		} else {
			message.append(Message.MSG_INVALID_ARG.s());
			message.append(LINE).append(GameHelper.PARAM_EMAIL).append(SEP).append(email);
			message.append(LINE).append(GameHelper.BODY_QUESTION_ID).append(SEP).append(email);
		}
		body.setCorrect(isCorrect);
		body.setPlayer(p);
		body.setMessage(message.toString());
		return body;
	}

	/** Possibly question id.
	 *
	 * @param  possibleId the possible id
	 * @return            true, if successful */
	private boolean isValidQuestionId(String possibleId) {
		// qId should always be an unsigned long; therefore, no double or negative values expected...
		return StringUtils.isNumeric(possibleId);
	}

	/** A diagnostic endpoint handler; not for standard use.
	 *
	 * @param  idVal    the id val
	 * @param  startVal the start val
	 * @param  stopVal  the stop val
	 * @param  velVal   the vel val
	 * @return          the array list */
	public ArrayList<AskResponseBody> endpointGET(String idVal, String startVal, String stopVal, String velVal) {
		// com.hoovler.utils.impl.BoolUtils has a more robust check for numeric values than StringUtils...
		int start = BoolUtils.isNumeric(startVal) ? Integer.parseInt(startVal) : 0;
		int stop = BoolUtils.isNumeric(stopVal) ? Integer.parseInt(stopVal) : 0;
		int velocity = BoolUtils.isNumeric(velVal) ? Integer.parseInt(velVal) : 0;

		// looking for only one question?
		if (StringUtils.isBlank(idVal)) {
			// return the list, or a subset thereof
			if (start == 0 && stop == 0 && velocity == 0) {
				// return whole list
				return questionsAsked();
			} else if (stop == 0) {
				// return a directional subset
				return (ArrayList<AskResponseBody>) ListUtils.subset(questionsAsked(), start, velocity);
			} else if (velocity == 0 || stop > 0) {
				// return a index-based subset
				return (ArrayList<AskResponseBody>) questionsAsked().subList(start, stop);
			} else {
				return questionsAsked();
			}
		} else {
			// return list with the desired question
			ArrayList<AskResponseBody> qList = new ArrayList<>();
			AskResponseBody q = getQuestionAsked(idVal);
			qList.add(q);
			return qList;
		}
	}

}
