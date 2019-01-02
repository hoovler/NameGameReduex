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
package com.hoovler.api.resources;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.hoovler.api.models.AskArgs;
import com.hoovler.api.models.QuestionOption;
import com.hoovler.api.utils.NameGameHelper;
import com.hoovler.api.utils.QuestionsHelper;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

// TODO: Auto-generated Javadoc
/** <p><h3>Ask</h3> <p><b><u>Purpose</u></b></p> This Class ...</p> <p><b><u>Information</u></b><br /> The <code>Ask</code> object is...</p> <p><b><u>Examples</u></b></p> <pre> GET: http://localhost/namegame/v2.0.0/ask?email=foo@bar.com&mode=2 </pre> */
public class AskQuestion {
	
	private boolean answered;
	
	private String player_asked;
	
	/** Not to be confused with 'qId'; 'questionId' is the hex value derived from the 'qId' and 'email' variables. */
	private String question_id;

	private String target;

	private ArrayList<QuestionOption> options;

	public boolean isAnswered() {
		return this.answered;
	}
	public String getPlayerAsked() {
		return this.player_asked;
	}
	/** Gets AskQuestion.questionId
	 *
	 * @return the question id */
	public String getQuestionId() {
		return question_id;
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
	
	public void setQuestionId(String questionId) {
		this.question_id = questionId;
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
	
	public AskQuestion() {
		this.options = new ArrayList<>();
	}

	/** Instantiates a new response object for the API GET verb.
	 *
	 * @param values    the values
	 * @param profiles  the profiles
	 * @param players   the players
	 * @param questions the questions */
	public AskQuestion(AskArgs values, DefaultProfileDao profiles, Players players, Questions questions) {
		String email = values.getPlayerEmail();
		boolean reversed = values.reverseBool();
		boolean mattsOnly = values.mattsOnlyBool();
		
		// set up a new question and update persistence
		Question q = initQuestion(email, mattsOnly, profiles);
		questions.addQuestion(q);
		
		// set member values
		this.options = QuestionsHelper.getQuestionOptions(reversed, q);
		this.target = QuestionsHelper.getQuestionTarget(reversed, q);
		this.question_id = NameGameHelper.encodeToHexWithSalt(q.getId(), values.getPlayerEmail());
		
		// update player persistence
		Player player = players.getOrAddPlayer(values.getPlayerEmail());
		player.updateStats(new Date(), true);
		players.updatePlayer(player.getEmail(), player);
	}
	
	private Question initQuestion(String playerEmail, boolean mattsOnly, DefaultProfileDao profiles) {
		this.options = new ArrayList<>();
		this.answered = false;
		this.player_asked = playerEmail;
		
		return QuestionsHelper.generateQuestion(profiles, mattsOnly ? NameGameHelper.MATTS_ONLY_VALUE_PREFIX : StringUtils.EMPTY);
	}
}
