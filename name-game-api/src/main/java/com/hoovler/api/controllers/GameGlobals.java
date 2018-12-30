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

import com.hoovler.api.models.RestParam;

/**
 * <p><h3>GameUtils</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>GameUtils</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * An example:
 * <pre>some code</pre>
 * Another example:
 * <pre>some more code;</pre>
 */
public abstract class GameGlobals {
	
	/** <p><i>numOptions</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	public static final int NUM_OPTIONS = 6;

	/** <p><i>apiVersion</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	public static final String API_VERSION = "2.0.0";
	
	public static final Integer PRIME = 31;
	

	
	// constant parameters with default values
	public static final RestParam EMAIL = new RestParam("email", "");
	public static final RestParam MODE = new RestParam("mode", "0");
	public static final RestParam MATTS = new RestParam("matts", "0");
	public static final RestParam QUESTION_ID = new RestParam("idquestion", "0");
	public static final RestParam ANSWER_ID = new RestParam("idanswer", "0");

	/**
	 * Similar to a password-based encryption algorithm or a salted hash, the goal of this method is to create
	 * an ID-like string using the questionId and playerEmail such that the resulting string can be used to derive
	 * the QuestionId if given the playerEmail as the key.  AKA, a reversible hash.
	 *
	 * @param questionId the question id
	 * @param playerEmail the player email
	 * @return the string
	 */
	public static Long encodeQuestionPlayerConnection(Long questionId, String playerEmail) {
		return Long.rotateLeft(Long.sum(questionId, encodePlayerEmail(playerEmail)), 31);
	}
	
	/**
	 * Companion method to <code>encodeQuestionPlayerConnection</code>
	 * 
	 * <p>This method works by performing the opposite operations on the encoded value in reverse order, thereby
	 * canceling out all changes made to the original <code>Question.qId</code> value that produced the encoded
	 * value.</p>
	 * 
	 * <p>If the questionPlayerConnection passed into this method is a POST argument that was given in the URL
	 * path of the same player to whom the question was originally asked, the resulting value will be the Question ID
	 * of a question that was posed to the player.  Otherwise, the result will be gibberish, which means the player is
	 * using a different email to submit a POST, the player was never asked the question, OR - and this is unlikely,
	 * but the most consequential - <b>there was a problem with storing the question properly in persistence.</b></p>
	 *
	 * @param 	questionPlayerConnection the value generated by <code>encodeQuestionPlayerConnection</code> with the
	 * 			same email.
	 * @param playerEmail the same email used to generate the value previously.
	 * @return a String value that represents a unique Question-to-Player connection.
	 */
	public static Long decodeQuestionPlayerConnection(Long questionPlayerConnection, String playerEmail) {
		return Long.rotateRight(questionPlayerConnection, 31) - encodePlayerEmail(playerEmail);
	}
	
	private static Long encodePlayerEmail(String playerEmail) {
		return (long) playerEmail.toLowerCase().hashCode();
	}
	
	/** Instantiates a new game utils. */
	private GameGlobals() {
		// good practice to hide implicit constructor with an empty private one
	}
}
