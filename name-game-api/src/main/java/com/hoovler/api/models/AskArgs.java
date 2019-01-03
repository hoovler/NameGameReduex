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
package com.hoovler.api.models;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/** The Class AskArgs. */
public class AskArgs {
	
	private static final String[] TRUE_BOOL = new String[] {"1", "yes", "true", "y", "t"};
	
	private String playerEmail;
	private String reverse;
	private String mattsOnly;

	/** Getters.
	 *
	 * @return the player email */
	public String getPlayerEmail() {
		return playerEmail;
	}

	/** Gets AskArgs.mode
	 *
	 * @return the mode */
	public String getReverse() {
		return reverse;
	}

	/** Gets AskArgs.mattsOnly
	 *
	 * @return the matts only */
	public String getMattsOnly() {
		return mattsOnly;
	}

	/** Sets AskArgs.playerEmail
	 *
	 * @param playerEmail the new player email */
	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	/** Sets AskArgs.mode
	 *
	 * @param mode the new mode */
	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
	
	/** Sets AskArgs.mattsOnly
	 *
	 * @param mattsOnly the new matts only */
	public void setMattsOnly(String mattsOnly) {
		this.mattsOnly = mattsOnly;
	}

	/** Instantiates a new ask args. */
	public AskArgs() {
		this.playerEmail = StringUtils.EMPTY;
		this.reverse = StringUtils.EMPTY;
		this.mattsOnly = StringUtils.EMPTY;
	}

	/** Instantiates a new ask args.
	 *
	 * @param playerEmail the player email */
	public AskArgs(String playerEmail) {
		this.playerEmail = playerEmail;
		this.reverse = StringUtils.EMPTY;
		this.mattsOnly = StringUtils.EMPTY;
	}

	/** Instantiates a new ask args.
	 *
	 * @param playerEmail the player email
	 * @param mode        the mode */
	public AskArgs(String playerEmail, String mode) {
		this.playerEmail = playerEmail;
		this.reverse = mode;
		this.mattsOnly = StringUtils.EMPTY;
	}

	/** Instantiates a new ask args.
	 *
	 * @param playerEmail the player email
	 * @param mode        the mode
	 * @param mattsOnly   the matts only */
	public AskArgs(String playerEmail, String mode, String mattsOnly) {
		this.playerEmail = playerEmail;
		this.reverse = mode;
		this.mattsOnly = mattsOnly;
	}

	/** Grab the boolean value of <code>reverse</code>
	 *
	 * @return The value of <code>reverse</code> parsed into a boolean */
	public boolean isReverse() {
		return Boolean.parseBoolean(this.reverse);
	}

	/** Grab the boolean value of <code>mattsOnly</code>
	 *
	 * @return The value of <code>mattsOnly</code> parsed into a boolean */
	public boolean isMattsOnly() {
		return Boolean.parseBoolean(this.mattsOnly);
	}
	
	
}
