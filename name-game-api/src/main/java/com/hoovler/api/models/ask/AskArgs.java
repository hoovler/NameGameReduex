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
package com.hoovler.api.models.ask;

import org.apache.commons.lang3.StringUtils;

import com.hoovler.api.utils.Mode;

public class AskArgs {
	private String playerEmail;
	private int mode;
	private String mattsOnly;

	// getters
	public String getPlayerEmail() {
		return playerEmail;
	}

	public int getMode() {
		return mode;
	}

	public String getMattsOnly() {
		return mattsOnly;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void setMattsOnly(String mattsOnly) {
		this.mattsOnly = mattsOnly;
	}

	public AskArgs() {
		this.playerEmail = StringUtils.EMPTY;
		this.mode = -1;
		this.mattsOnly = StringUtils.EMPTY;
	}

	public AskArgs(String playerEmail) {
		this.playerEmail = playerEmail;
		this.mode = -1;
		this.mattsOnly = StringUtils.EMPTY;
	}

	public AskArgs(String playerEmail, int mode) {
		this.playerEmail = playerEmail;
		this.mode = mode;
		this.mattsOnly = StringUtils.EMPTY;
	}

	public AskArgs(String playerEmail, int mode, String mattsOnly) {
		this.playerEmail = playerEmail;
		this.mode = mode;
		this.mattsOnly = mattsOnly;
	}
	
	/**
	 * Converts mode from an integer into a proper Mode value
	 *
	 * @return the mode
	 */
	public Mode modeEnum() {
		return Mode.values()[this.mode];
	}
	
	/**
	 * Converts mattsOnly from a String into a boolean
	 *
	 * @return true, if successful
	 */
	public boolean mattsOnlyEnum() {
		if (StringUtils.isBlank(this.getMattsOnly())) return false;
		return Boolean.parseBoolean(this.getMattsOnly());
	}

}
