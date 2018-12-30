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
package com.hoovler.api.resources;

import java.util.Date;

import com.hoovler.api.controllers.GameGlobals;
import com.hoovler.api.models.ask.AskArgs;
import com.hoovler.api.models.ask.AskResponse;
import com.hoovler.api.utils.Mode;
import com.hoovler.api.utils.QuestionsHelper;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Question;

/**
 * <p><h3>Ask</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>Ask</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * <pre>GET: http://localhost/namegame/v2.0.0/ask?email=foo@bar.com&mode=2</pre>
 */
public class Ask {
	//private static Logger log = LogManager.getLogger(Ask.class.getName());
	
	private AskResponse askResponse;
	
	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public AskResponse getAskResponse() {
		return this.askResponse;
	}
	
	/**
	 * Instantiates a new response object for the API GET verb.
	 *
	 * @param playerEmail the player email
	 * @param mode 	- the mode as determined by the URI argument <em>mode</em>.  The mode values are restricted to 0 and 1:
	 * <table border=1>
	 * <tr>
	 * 		<td><code>0; Mode.NORMAL</code></td>
	 * 		<td>The default value when no <em>mode</em> is supplied; the question will contain a random selection of options from the entire pool of profiles.</td>
	 * </tr>
	 * <tr>
	 * 		<td><code>1; Mode.REVERSE</code></td>
	 * 		<td>The question will contain a random selection of profiles wherein the value of Profile.getFirstName() matches the regex <code>[m|M][a|A][t|T][t|T]+.*</code></td>
	 * </tr>
	 * </table>
	 * <p>There is no accounting for a <em>REVERSE</em> mode because each question will contain both the <b>name</b> and <b>imageUrl</b> values for all
	 * <code>options</code> and the <code>target</code>. This enables a front-end game designer / client developer to implement a REVERSE mode if required, without
	 * the inflexibility of having such an option built into the back-end design.</p>
	 * @param data - the Data persistence object;
	 */
	public Ask(AskArgs values, DefaultProfileDao profileService, Players playerService, Questions questionService) {
		
		this.askResponse = new AskResponse();
		setAskResponse(values, profileService, playerService, questionService);
	}
	
	/**
	 * Sets the ask response.
	 *
	 * @param values the values
	 * @param profileService the profile service
	 * @param playerService the player service
	 * @param questionService the question service
	 */
	public void setAskResponse(
			AskArgs values, 
			DefaultProfileDao profileService, 
			Players playerService, 
			Questions questionService) {
		// determine question format from arguments passed in to the API
		Mode mode = values.modeEnum();
		String namePrefix = "";
		if (values.mattsOnlyEnum()) namePrefix = "matt";
		
		// generate a new question based on arguments
		Question q = QuestionsHelper.generateQuestion(profileService, namePrefix);
		this.askResponse = QuestionsHelper.formatQuestion(mode, q);
		
		// set the ID to a value from which the QuestionId can be derived for the purposes of checking an answer in the future
		this.askResponse.setQuestionId(GameGlobals.encodeQuestionPlayerConnection(q.getId(), values.getPlayerEmail()));
		
		// update player history
		Player player = playerService.getOrAddPlayer(values.getPlayerEmail());
		player.updateStats(new Date(), true);
		playerService.updatePlayer(player.getEmail(), player);
		
		// update question history
		questionService.addQuestion(q);
	}
	
}
