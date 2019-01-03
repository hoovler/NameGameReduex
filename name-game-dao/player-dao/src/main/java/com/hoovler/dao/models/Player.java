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
package com.hoovler.dao.models;

import java.util.Date;

// TODO: Auto-generated Javadoc
/** <p> <h3>Player</h3> </p> <p> <b><u>Purpose:</u></b> TODO: add purpose... </p> <p> <b><u>Information:</u></b> TODO: add info... </p> */
public class Player {
	/** <p> TODO: add desc </p> . */
	private String email;

	/** <p> TODO: add desc </p> . */
	private Stats stats;

	/** Gets the email.
	 *
	 * @return the email */
	public String getEmail() {
		return email;
	}

	/** Gets the stats.
	 *
	 * @return the stats */
	public Stats getStats() {
		return stats;
	}

	// ******************** SETTERS ********************

	/** Sets the email.
	 *
	 * @param email the new email */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Sets the stats.
	 *
	 * @param stats the new stats */
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	/** <p>
	 * Update all player statistics with a minimal set of arguments.
	 * </p>
	 * 
	 * This method will indirectly update the following:
	 * <ul>
	 * <li>increment <code>this.stats.numberAnswered</code></li>
	 * <li>increment <code>this.stats.numberCorrect</code> (if required)</li>
	 * <li>recalculate <code>this.stats.percentCorrect</code> (if required)</li>
	 * <li>set <code>this.stats.lastAskedTime</code></li>
	 * <li>set <code>this.stats.lastAnswerTime</code></li>
	 * <li>add duration (in seconds) to <code>this.stats.answerTimes</code></li>
	 * <li>recalculate <code>this.stats.averageResponseTime</code></li>
	 * </ul>
	 *
	 * @param askedTime         the asked time
	 * @param answerTime        the answer time
	 * @param withCorrectAnswer the with correct answer
	 * 
	 * @see
	 * 							com.hoovler.dao.models.Stats */
	public void updateStats(Date askedTime, Date answerTime, boolean withCorrectAnswer) {
		this.stats.incrementAnswered();
		if (withCorrectAnswer) {
			this.stats.incrementCorrect();
		}
		this.stats.updateTimes(askedTime, answerTime);
		
		// update score
		int numberCorrect=this.stats.getNumberCorrect();
		int numberAnswered=this.stats.getNumberAnswered();
		int multiplier=100;
		
		// calculated by a simple metrics
		if (numberAnswered == 0 || numberCorrect == 0) {
			this.stats.setScore(0.0);
		} else {
			double score = (double) numberCorrect / numberAnswered * multiplier;
			this.stats.setScore(score);
		}
	}
	
	public double scoreFromPlayerStats() {
		return this.stats.getScore();
	}

	/** Update score.
	 *
	 * @param score the score */
	public void updateScore(Double score) {
		this.stats.setScore(score);
	}

	/** Update stats.
	 *
	 * @param askedTime         the time the question was asked.
	 * @param incrementAskCount the increment ask count */
	public void updateStats(Date askedTime, boolean incrementAskCount) {
		this.stats.setLastAskedTime(askedTime);
		if (incrementAskCount) {
			this.stats.incrementAsked();
		}
	}
	// ******************** CONSTRUCTORS ********************

	/** Instantiates a new player. */
	public Player() {
		this.stats = new Stats();
	}
	
	/**
	 * Instantiates a new player.
	 *
	 * @param email the email
	 */
	public Player(String email) {
		this.email = email;
		this.stats = new Stats();
	}

	/** Instantiates a new player.
	 *
	 * @param email the email
	 * @param stats the stats */
	public Player(String email, Stats stats) {
		this.email = email;
		this.stats = stats;
	}
}
