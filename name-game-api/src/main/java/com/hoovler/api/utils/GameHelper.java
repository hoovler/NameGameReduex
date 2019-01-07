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
package com.hoovler.api.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.hoovler.utils.globals.Const;
import com.hoovler.utils.impl.SecurityUtils;

/** The singleton class which provides static global constants and static utility methods specific to the NameGameRedeux API 
 * @author Michael Hoovler &lt;hoovlermichael@gmail.com&gt;*/
public class GameHelper {
	private static Logger log = LogManager.getLogger(GameHelper.class.getName());

	public static final String MSG_RESOURCE_NAME = "api_messages";
	public static final String WRN_MISSING_RESOURCE = "WARNING: NO MESSAGE RESOURCE BUNDLE NAMED ";

	/** The Constant NUM_OPTIONS. */
	public static final int NUM_OPTIONS = 6;
	/** The Constant API_PREFIX. */
	public static final String API_PREFIX = "/namegame/v";
	/** The Constant API_VERSION. */
	public static final String API_VERSION = "2.0.0";
	/** The Constant PRIME. */
	public static final Integer PRIME = 31;
	/** The Constant MATTS_ONLY_VALUE_PREFIX. */
	public static final String MATTS_ONLY_VALUE_PREFIX = "mat";

	/* DEFAULT PARAMETER VALUES */

	/** The default value for missing or empty String parameters. */
	public static final String VAL_DEFAULT_ALPHA = "";

	/** The default value for missing or empty numeric parameters, including boolean params. */
	public static final String VAL_DEFAULT_NUM = "0";

	public static final String VAL_DEFAULT_BOOL = "false";
	/* PARAMETER NAMES */

	/** The request parameter name for the player email string value. <p><em>A parameter name used: <ul><li>in the response body of a question</li><li>in the request body when submitting an answer</li><ul></em></p> */
	public static final String PARAM_EMAIL = "email";

	/** The request parameter name for the selected mode integer value. <p><em>A parameter name used: <ul><li>in the response body of a question</li><ul></em></p> */
	public static final String PARAM_REVERSE = "reverse";

	/** The request parameter name for the <code>matts</code> boolean value. <p>The name of a parameter value passed along with the request for a question. While technically a string on the part of the sender, this value is treated a boolean, and thus may only be values which may be parsed into a boolean.</p> <p>For example:<pre>matts=1 || matts=true || matts=yes</pre><pre>matts=0 || matts=false || matts=no</pre></p> */
	public static final String PARAM_MATTS = "matts";

	public static final String PARAM_PLAYER_ID = "player_id";

	/** The Constant answerIdParam. <p>A parameter name used: <ul><li>when submitting an answer</li><ul><li>Should be a string value that is unique to the question's chosen option, and must match that option's <code>option_id</code></li></ul><ul></p> */
	public static final String BODY_ANSWER_ID = "answer_id";

	/** The Constant questionIdBodyParam. <p>A parameter name used: <ul><li>in the response body of a question<ul><li>A unique, hexidecimal ID for the question being asked</li></ul></li><li>in the request body when submitting an answer<ul><li>Must match the <code>question_id</code> received</li><li>Unique to, and may only be submitted for, the player to whom the question was asked.</li></ul></li><ul></p> */
	public static final String BODY_QUESTION_ID = "question_id";

	public static final String PARAM_START = "start";
	public static final String PARAM_STOP = "stop";
	public static final String PARAM_VELOCITY = "velocity";

	/* RESOURCE NAMES & BASE PATH VALUES */

	/** The Constant askResource. <p><em>A NameGame API resource...</em></p> */
	public static final String ENDPOINT_ASK = "/ask";

	/** The Constant answerResource. <p><em>A NameGame API resource...</em></p> */
	public static final String ENDPOINT_ANSWER = "/answer";

	/** The Constant questionsResource. <p><em>A NameGame API resource...</em></p> */
	public static final String ENDPOINT_QUESTIONS_FULL = "/questions";
	public static final String ENDPOINT_QUESTIONS_PARED = "/questions/pared";

	/** The Constant playersResource. <p><em>A NameGame API resource...</em></p> */
	public static final String ENDPOINT_PLAYERS = "/players";
	public static final String ENDPOINT_LEADERS = "/leaders";

	/* JSON-related values */

	/** The class name of JsonParseException from the <code>com.google.gson</code> package */
	public static final String JSON_PARSE_E = "JsonParseException";

	/** The class name of JsonSyntaxException from the <code>com.google.gson</code> package */
	public static final String JSON_SYNTAX_E = "JsonSyntaxException";

	public static final String JSON_UNEXPECTED_TYPE_E = "IllegalStateException";
	
	/**
	 * Converts any object to a JSON String.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String toJson(Object obj) {
		return new Gson().toJson(obj);
	}

	/** Parses the answer body.
	 *
	 * @param  answerBody the answer body
	 * @return            the json object */
	public static JsonObject parseAnswerBody(String answerBody) {
		if (isJsonObject(answerBody))
			return new JsonParser().parse(answerBody).getAsJsonObject();
		return null;
	}

	/** Checks if is json object.
	 *
	 * @param  val the val
	 * @return     true, if is json object */
	public static boolean isJsonObject(String val) {
		return isJson(val) && isJsonObject(new JsonParser().parse(val));
	}

	/** Checks if is a given JSON element is a JSON object.
	 *
	 * @param  jsonElement the json element
	 * @return             true, if is json object */
	public static boolean isJsonObject(JsonElement jsonElement) {
		JsonObject obj = null;
		try {
			obj = jsonElement.getAsJsonObject();
		} catch (Exception e) {
			if (StringUtils.equals(e.getClass().getSimpleName(), JSON_UNEXPECTED_TYPE_E)) {
				log.warn(Message.WARN_JSON_TYPE_UNEXPECTED.s() + Message.WARN_JSON_SUFFIX.s());
			} else {
				log.warn(Message.WARN_JSON_ERROR.s() + Message.WARN_JSON_SUFFIX.s());
			}
			log.warn(e.getMessage());
		}
		return obj != null;
	}

	/** Checks if the given string is valid JSON
	 *
	 * @param  val the value tested.
	 * @return     <code>true</code> if the string is parsable JSON; <code>false</code> otherwise. If the object is not parsable, more details will be printed to the console / logging appender specified. */
	public static boolean isJson(String val) {
		JsonElement jElem;
		try {
			jElem = new JsonParser().parse(val);
		} catch (JsonParseException e) {
			String errorType = e.getClass().getSimpleName();
			switch (errorType) {
			case Const.JSON_PARSE_E:
				log.warn(Message.WARN_JSON_PARSE.s() + Message.WARN_JSON_SUFFIX.s());
				break;
			case Const.JSON_SYNTAX_E:
				log.warn(Message.WARN_JSON_SYNTAX.s() + Message.WARN_JSON_SUFFIX.s());
				break;
			default:
				log.warn(Message.WARN_JSON_ERROR.s() + Message.WARN_JSON_SUFFIX.s());
			}
			log.warn(e.getMessage());
			return false;
		}
		return jElem != null;
	}

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

	/** Companion method to <code>decodeFromHexWithSalt</code>
	 * 
	 * <p>This method works by performing the opposite operations on the encoded value in reverse order, thereby
	 * canceling out all changes made to the original <code>Question.qId</code> value that produced the encoded
	 * value.</p>
	 * 
	 * <p>If the questionPlayerConnection passed into this method is a POST argument that was given in the URL
	 * path of the same player to whom the question was originally asked, the resulting value will be the Question ID
	 * of a question that was posed to the player. Otherwise, the result will be gibberish, which means the player is
	 * using a different email to submit a POST, the player was never asked the question, OR - and this is unlikely,
	 * but the most consequential - <b>there was a problem with storing the question properly in persistence.</b></p>
	 *
	 * @param  questionPlayerConnection the question player connection
	 * @param  playerEmail              the player email
	 * @return                          a String value that represents a unique Question-to-Player connection. */
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
	protected GameHelper() {
		// no-op
	}
}
