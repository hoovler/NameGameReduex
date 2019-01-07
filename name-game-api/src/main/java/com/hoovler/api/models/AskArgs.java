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

import com.hoovler.utils.impl.BoolUtils;

// TODO: Auto-generated Javadoc
/** The Class AskArgs. */
public class AskArgs {

	private String playerEmail;
	private boolean reverse;
	private boolean mattsOnly;

	/** Getters.
	 *
	 * @return the player email */
	public String getPlayerEmail() {
		return playerEmail;
	}

	/** Gets AskArgs.reverse
	 *
	 * @return the reverse */
	public boolean isReverse() {
		return reverse;
	}

	/** Gets AskArgs.mattsOnly
	 *
	 * @return the matts only */
	public boolean isMattsOnly() {
		return mattsOnly;
	}

	/** Sets AskArgs.playerEmail
	 *
	 * @param playerEmail the new player email */
	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	/** Sets AskArgs.reverse
	 *
	 * @param reverse the new reverse */
	public void setReverse(String reverse) {
		this.reverse = BoolUtils.parseBool(reverse);
	}

	/** Sets AskArgs.mattsOnly
	 *
	 * @param mattsOnly the new matts only */
	public void setMattsOnly(String mattsOnly) {
		this.mattsOnly = BoolUtils.parseBool(mattsOnly);
	}

	/** Instantiates a new ask args. */
	public AskArgs() {
		this.playerEmail = StringUtils.EMPTY;
		this.reverse = false;
		this.mattsOnly = false;
	}

	/** Instantiates a new ask args.
	 *
	 * @param playerEmail the player email */
	public AskArgs(String playerEmail) {
		this.playerEmail = playerEmail;
		this.reverse = false;
		this.mattsOnly = false;
	}

	/** Instantiates a new ask args.
	 *
	 * @param playerEmail the player email
	 * @param reverse     the reverse */
	public AskArgs(String playerEmail, String reverse) {
		this.playerEmail = playerEmail;
		this.reverse = BoolUtils.parseBool(reverse);
		this.mattsOnly = false;
	}

	/** Instantiates a new ask args.
	 *
	 * @param playerEmail the player email
	 * @param reverse     the reverse
	 * @param mattsOnly   the matts only */
	public AskArgs(String playerEmail, String reverse, String mattsOnly) {
		this.playerEmail = playerEmail;
		this.reverse = BoolUtils.parseBool(reverse);
		this.mattsOnly = BoolUtils.parseBool(mattsOnly);
	}
	
	/**
	 * A compliment to the 'toString()' function inherited from Object.
	 *
	 * @return the object formatted as a parsable JSON string.
	 */
	public String toJson() {
		return "{'email': '" + playerEmail + "', 'reverse': '" + reverse + "', 'matts': '" + mattsOnly + "'}";
	}
}
