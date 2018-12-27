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
public class GameUtils {
	
	/** <p><i>numOptions</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	public static final int NUM_OPTIONS = 6;

	/** <p><i>apiVersion</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	public static final String API_VERSION = "2.0.0";
	
	public static final Integer PRIME = 31;
	
	/**
	 * <p><h3>Path</h3>
	 * <p><b><u>Purpose</u></b></p>
	 * This Class ...</p>
	 * <p><b><u>Information</u></b><br />
	 * The <code>Path</code> object is...</p>
	 * <p><b><u>Examples</u></b></p>
	 * An example:
	 * <pre>some code</pre>
	 * Another example:
	 * <pre>some more code;</pre>
	 */
	public static class Path {
		/** <p><i>GET_MAPPING</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre> */
		public static final String GET = "/ask";
		
		/** <p><i>POST_MAPPING</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String POST = "/{" + Param.QUESTION_ID + "}";
		
		/**
		 * Instantiates a new param.
		 */
		private Path() {
			// good practice to hide implicit constructor with an empty private one
		}
	}
	
	/**
	 * <p><h3>Param</h3>
	 * <p><b><u>Purpose</u></b></p>
	 * This Class ...</p>
	 * <p><b><u>Information</u></b><br />
	 * The <code>Param</code> object is...</p>
	 * <p><b><u>Examples</u></b></p>
	 * An example:
	 * <pre>some code</pre>
	 * Another example:
	 * <pre>some more code;</pre>
	 */
	public static class Param {
		
		/** <p><i>EMAIL</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String EMAIL = "email";
		
		/** <p><i>MODE</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String MODE = "mode";
		
		/** <p><i>MATTS</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String MATTS = "mattsOnly";
		
		/** <p><i>QUESTION_ID</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String QUESTION_ID = "questionId";
		
		/** <p><i>ANSWER_ID</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String ANSWER_ID = "answerId";
		
		/**
		 * Instantiates a new param.
		 */
		private Param() {
			// good practice to hide implicit constructor with an empty private one
		}
	}

	/**
	 * <p><h3>Default</h3>
	 * <p><b><u>Purpose</u></b></p>
	 * This Class ...</p>
	 * <p><b><u>Information</u></b><br />
	 * The <code>Default</code> object is...</p>
	 * <p><b><u>Examples</u></b></p>
	 * An example:
	 * <pre>some code</pre>
	 * Another example:
	 * <pre>some more code;</pre>
	 */
	public static class Default {
		
		/** <p><i>EMAIL</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String EMAIL = "";
		
		/** <p><i>MODE</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final int MODE = 0;
		
		/** <p><i>MATTS</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String MATTS = "";
		
		/** <p><i>QUESTION_ID</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String QUESTION_ID = "0";
		
		/** <p><i>ANSWER_ID</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String ANSWER_ID = "0";
		
		/**
		 * Instantiates a new default.
		 */
		private Default() {
			// good practice to hide implicit constructor with an empty private one			
		}
	}
	
	/**
	 * <p><h3>DefaultStr</h3>
	 * <p><b><u>Purpose</u></b></p>
	 * This Class ...</p>
	 * <p><b><u>Information</u></b><br />
	 * The <code>DefaultStr</code> object is...</p>
	 * <p><b><u>Examples</u></b></p>
	 * An example:
	 * <pre>some code</pre>
	 * Another example:
	 * <pre>some more code;</pre>
	 */
	public static class DefaultStr {
		
		/** <p><i>EMAIL</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String EMAIL = "";
		
		/** <p><i>MODE</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String MODE = "0";
		
		/** <p><i>MATTS</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String MATTS = "0";
		
		/** <p><i>QUESTION_ID</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String QUESTION_ID = "0";
		
		/** <p><i>ANSWER_ID</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
		public static final String ANSWER_ID = "0";
		
		/**
		 * Instantiates a new default str.
		 */
		private DefaultStr() {
			// good practice to hide implicit constructor with an empty private one
		}
	}
	
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
	
	/**
	 * Instantiates a new game utils.
	 */
	private GameUtils() {
		// good practice to hide implicit constructor with an empty private one
	}
}
