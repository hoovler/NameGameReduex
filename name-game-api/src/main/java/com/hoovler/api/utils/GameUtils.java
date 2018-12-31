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
package com.hoovler.api.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.utils.impl.SecurityUtils;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * <h3>GameUtils</h3>
 * <p>
 * <b><u>Purpose</u></b>
 * </p>
 * This Class ...
 * </p>
 * <p>
 * <b><u>Information</u></b><br />
 * The <code>GameUtils</code> object is...
 * </p>
 * <p>
 * <b><u>Examples</u></b>
 * </p>
 * An example:
 * 
 * <pre>
 * some code
 * </pre>
 * 
 * Another example:
 * 
 * <pre>
 * some more code;
 * </pre>
 */
public abstract class GameUtils {
	
	private static Logger log = LogManager.getLogger(GameUtils.class.getName());
	
	/** The Constant NUM_OPTIONS. */
	public static final int NUM_OPTIONS = 6;

	/** The Constant API_PREFIX. */
	public static final String API_PREFIX = "/namegame/v";
	
	/** The Constant API_VERSION. */
	public static final String API_VERSION = "2.0.0";
	
	/** The Constant PRIME. */
	public static final Integer PRIME = 31;

	/** Similar to a password-based encryption algorithm or a salted hash, the goal of this method is to
	 * create
	 * an ID-like string using the questionId and playerEmail such that the resulting string can be used
	 * to derive
	 * the QuestionId if given the playerEmail as the key. AKA, a reversible hash.
	 *
	 * @param  questionId  the question id
	 * @param  playerEmail the player email
	 * @return             the string */
	public static String encodeToHexWithSalt(Long questionId, String playerEmail) {
		log.debug("decodeFromHexWithSalt(Long " + questionId + ", String " + playerEmail + ")");
		String qIdHex = SecurityUtils.toHex(String.valueOf(questionId));
		log.debug("qIdHex = " + qIdHex);
		String playerEmailHex = SecurityUtils.toHex(playerEmail);
		log.debug("playerEmailHex = " + playerEmailHex);
		log.debug("Return value: " + playerEmailHex + qIdHex);
		return playerEmailHex + qIdHex;
	}

	/**
	 * Companion method to <code>decodeFromHexWithSalt</code>
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
	 * @param questionPlayerConnection the question player connection
	 * @param playerEmail the same email used to generate the value previously.
	 * @return a String value that represents a unique Question-to-Player connection.
	 */
	public static String decodeFromHexWithSalt(String questionPlayerConnection, String playerEmail) {
		log.debug("decodeFromHexWithSalt(String " + questionPlayerConnection + ", String " + playerEmail + ")");
		String playerEmailHex = SecurityUtils.toHex(playerEmail);
		log.debug("playerEmailHex = " + playerEmailHex);
		String qIdHex = StringUtils.remove(questionPlayerConnection, playerEmailHex);
		log.debug("qIdHex = " + qIdHex);
		return SecurityUtils.fromHex(qIdHex);
	}
	
	/** Encode question player connection.
	 *
	 * @param  questionId  the question id
	 * @param  playerEmail the player email
	 * @return             the string */
	public static String encodeToHexWithSalt(String questionId, String playerEmail) {
		return encodeToHexWithSalt(Long.valueOf(questionId), playerEmail);
	}



	/** Instantiates a new game utils. */
	private GameUtils() {
		// good practice to hide implicit constructor with an empty private one
	}
}
