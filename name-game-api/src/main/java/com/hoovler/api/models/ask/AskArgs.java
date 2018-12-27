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

import com.hoovler.api.resources.GameUtils;
import com.hoovler.api.resources.Mode;

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
	 * Populate the value of the calling AskArgs objects with values from
	 * the passed AskArgs object.
	 * <p>This method enables an API feature whereby API consumers may pass values into
	 * the framework via URI arguments or the request body itself.  How this method determines
	 * which object's value trumps the other is determined by giving default priority to
	 * the calling object.
	 * </p>
	 * <p>In other words, if the consumer chooses to set required variables in both the
	 * URI parameters, as well as the content of the request body, the object which calls this
	 * method takes priority in setting the values of the object passed back to the calling
	 * method:</p>
	 * 
	 * <p><b><u>Examples</u></b></p>
	 * <pre>
	 * AskArgs argsA = new AskArgs();
	 * AskArgs argsB = new AskArgs();
	 * 
	 * argsA.setPlayerEmail("foo@bar.com");
	 * argsA.setMode(7)
	 * argsA.setMattsOnly(false);
	 * 
	 * argsB.setPlayerEmail("willy.wonka@chocolate.fac");
	 * argsB.setMode(1);
	 * argsB.setMattsOnly(true)
	 * </pre>
	 * Now, suppose <code>argsA</code> came from the URI params, while
	 * argsB came from the request body.  If we want the URI param values 
	 * to take precedence over JSON values in the body, we would execute the
	 * following to create the final arguments list:
	 * 
	 * <pre>
	 * AskArgs finalArgs = argsA.validate(argsB);
	 * </pre>
	 * 
	 * Otherwise, we would execute:
	 * 
	 * <pre>
	 * AskArgs finalArgs = argsB.validate(argsA);
	 * </pre>
	 *
	 * @param suppliment the supplemental arguments to use in place of null or
	 * non-valid values.
	 * @return the ask args
	 */
	public AskArgs validate(AskArgs suppliment) {
		

		// setting playerEmail
		if (StringUtils.isBlank(this.playerEmail)) {
			if (StringUtils.isBlank(suppliment.getPlayerEmail())) {
				// assuming the default value is "", this will still be
				// set to empty... but its unlikely that this method would
				// even be called unless a random person happens across
				// the API's URL
				this.setPlayerEmail(GameUtils.Default.EMAIL);
			} else {
				this.setPlayerEmail(suppliment.getPlayerEmail());
			}	
		}
		
		// setting mode
		if (this.mode < 0 || this.mode > Mode.values().length - 1) {
			if (suppliment.getMode() < 0 || suppliment.getMode() > Mode.values().length - 1) {
				this.setMode(GameUtils.Default.MODE);
			} else {
				this.setMode(suppliment.getMode());
			}
		}
		
		// setting mattsOnly
		if (StringUtils.isBlank(this.getMattsOnly())) {
			if (StringUtils.isBlank(suppliment.getMattsOnly())) {
				this.setMattsOnly(GameUtils.Default.MATTS);
			} else {
				this.setMattsOnly(suppliment.getMattsOnly());
			}
		}
		return this;
	}
	
	/**
	 * Converts mode from an integer into a proper Mode value
	 *
	 * @return the mode
	 */
	public Mode properMode() {
		return Mode.values()[this.mode];
	}
	
	/**
	 * Converts mattsOnly from a String into a boolean
	 *
	 * @return true, if successful
	 */
	public boolean properMattsOnly() {
		if (StringUtils.isBlank(this.getMattsOnly())) return false;
		return Boolean.parseBoolean(this.getMattsOnly());
	}

}
