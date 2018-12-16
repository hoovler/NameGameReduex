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
package com.hoovler.dao.models;

/**
 * <p><h3>Player</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>Player</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * An example:
 * <pre>some code</pre>
 * Another example:
 * <pre>some more code;</pre>
 */
public class Player {
	
	/** <p><i>email</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private String email;
	
	/** <p><i>numberGuesses</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private Long numberGuesses;
	
	/** <p><i>numberCorrect</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private Long numberCorrect;


	/**
	 * Gets the <p><i>email</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>.
	 *
	 * @return the <p><i>email</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the <p><i>email</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>.
	 *
	 * @param email the new <p><i>email</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the <p><i>numberGuesses</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>.
	 *
	 * @return the <p><i>numberGuesses</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>
	 */
	public Long getNumberGuesses() {
		return numberGuesses;
	}

	/**
	 * Sets the <p><i>numberGuesses</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>.
	 *
	 * @param numberGuesses the new <p><i>numberGuesses</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>
	 */
	public void setNumberGuesses(Long numberGuesses) {
		this.numberGuesses = numberGuesses;
	}

	/**
	 * Gets the <p><i>numberCorrect</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>.
	 *
	 * @return the <p><i>numberCorrect</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>
	 */
	public Long getNumberCorrect() {
		return numberCorrect;
	}

	/**
	 * Sets the <p><i>numberCorrect</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>.
	 *
	 * @param numberCorrect the new <p><i>numberCorrect</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>
	 */
	public void setNumberCorrect(Long numberCorrect) {
		this.numberCorrect = numberCorrect;
	}

	/**
	 * Instantiates a new player.
	 */
	public Player() {
		
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param id the id
	 * @param email the email
	 * @param numberGuesses the number guesses
	 * @param numberCorrect the number correct
	 */
	public Player(String email, Long numberGuesses, Long numberCorrect) {
		this.email = email;
		this.numberGuesses = numberGuesses;
		this.numberCorrect = numberCorrect;
	}
	
	
}
