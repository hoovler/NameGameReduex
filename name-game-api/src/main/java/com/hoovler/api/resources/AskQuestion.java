/*
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2019
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
package com.hoovler.api.resources;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.hoovler.api.models.ask.AskArgs;
import com.hoovler.api.models.ask.QuestionOption;
import com.hoovler.api.utils.GameUtils;
import com.hoovler.api.utils.QuestionsHelper;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

// TODO: Auto-generated Javadoc
/** <p><h3>Ask</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>Ask</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * 
 * <pre>
 * GET: http://localhost/namegame/v2.0.0/ask?email=foo@bar.com&mode=2
 * </pre>
 */
public class AskQuestion {

	// not to be confused with 'qId'; 'questionId' is the hex value derived from the 'qId' and 'email' variables
	private String questionId;

	private String target;

	private ArrayList<QuestionOption> options;

	/** Gets AskQuestion.questionId
	 *
	 * @return the question id */
	public String getQuestionId() {
		return questionId;
	}

	/** Gets AskQuestion.target
	 *
	 * @return the target */
	public String getTarget() {
		return target;
	}

	/** Gets AskQuestion.options
	 *
	 * @return the options */
	public ArrayList<QuestionOption> getOptions() {
		return options;
	}

	/** Sets AskQuestion.questionId
	 *
	 * @param questionId the new question id */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	/** Sets AskQuestion.target
	 *
	 * @param target the new target */
	public void setTarget(String target) {
		this.target = target;
	}

	/** Sets AskQuestion.options
	 *
	 * @param options the new options */
	public void setOptions(ArrayList<QuestionOption> options) {
		this.options = options;
	}

	/** Instantiates a new response object for the API GET verb.
	 *
	 * @param values    the values
	 * @param profiles  the profiles
	 * @param players   the players
	 * @param questions the questions */
	public AskQuestion(AskArgs values, DefaultProfileDao profiles, Players players, Questions questions) {

		// this.askResponseBody = new AskResponseBody();
		// setAskResponse(values, profileService, playerService, questionService);

		String namePrefix = (values.mattsOnlyBool()) ? GameUtils.MATTS_ONLY_VALUE_PREFIX : StringUtils.EMPTY;
		Question q = QuestionsHelper.generateQuestion(profiles, namePrefix);
		this.options = QuestionsHelper.getQuestionOptions(values.reverseBool(), q);
		this.target = null;
		this.questionId = GameUtils.encodeToHexWithSalt(q.getId(), values.getPlayerEmail());
		
		
	}

	/** Sets the ask response.
	 *
	 * @param values    the values
	 * @param profiles  the profile service
	 * @param players   the player service
	 * @param questions the question service */
	public void updateDaoObjs(String playerEmail, Players players, Question question, Questions questions) {

		// update player history
		Player player = players.getOrAddPlayer(playerEmail);
		player.updateStats(new Date(), true);
		players.updatePlayer(player.getEmail(), player);

		// update question history
		questions.addQuestion(question);
	}

}