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

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

// TODO: Auto-generated Javadoc
/** <p><h3>Stat</h3> <p><b><u>Purpose</u></b></p> This Class ...</p> <p><b><u>Information</u></b><br /> The <code>Stat</code> object is...</p> <p><b><u>Examples</u></b></p> An example: <pre>some code</pre> Another example: <pre>some more code;</pre> */
public class Stats {
	private int numberAsked;
	private int numberAnswered;
	private int numberCorrect;
	//private double ratioCorrect;
	private ArrayList<Long> responseTimes;
	private Duration averageResponseTime;
	private Date lastAskedTime;
	private Date lastAnswerTime;
	private double score;

	/*
	 * -------------- SETTERS --------------
	 * There are only the class values which are directly updated; the
	 * rest are calculated.
	 */

	/** Directly set the score.
	 *
	 * @param score The new score. */
	public void setScore(double score) {
		this.score = score;
	}

	/** * Setters.
	 *
	 * @param lastAskedTime the new last asked time */
	public void setLastAskedTime(Date lastAskedTime) {
		this.lastAskedTime = new Date();
		this.lastAskedTime = lastAskedTime;
	}

	/** Sets Stats.lastAnswerTime
	 *
	 * @param lastAnswerTime the new last answer time */
	public void setLastAnswerTime(Date lastAnswerTime) {
		this.lastAnswerTime = new Date();
		this.lastAnswerTime = lastAnswerTime;
	}

	/* -------------- GETTERS -------------- */

	/** * Getters.
	 *
	 * @return the number asked */

	public int getNumberAsked() {
		return this.numberAsked;
	}

	/** Gets Stats.numberAnswered
	 *
	 * @return the number answered */
	public int getNumberAnswered() {
		return this.numberAnswered;
	}

	/** Gets Stats.numberCorrect
	 *
	 * @return the number correct */
	public int getNumberCorrect() {
		return this.numberCorrect;
	}

	/** Gets Stats.answerTimes
	 *
	 * @return the answer times */
	public ArrayList<Long> getAnswerTimes() {
		return this.responseTimes;
	}

	/** Gets Stats.averageResponseTime
	 *
	 * @return the average response time */
	public Duration getAverageResponseTime() {
		return this.averageResponseTime;
	}

	/** Gets Stats.lastAskedTime
	 *
	 * @return the last asked time */
	public Date getLastAskedTime() {
		return this.lastAskedTime;
	}

	/** Gets Stats.lastAnswerTime
	 *
	 * @return the last answer time */
	public Date getLastAnswerTime() {
		return this.lastAnswerTime;
	}

	/** Gets Stats.score
	 *
	 * @return the score */
	public Double getScore() {
		return this.score;
	}

	/* -------------- CONSTRUCTORS -------------- */

	/** Instantiates a new stat. */
	public Stats() {
		this.numberAsked = 0;
		this.numberAnswered = 0;
		this.numberCorrect = 0;
		this.responseTimes = new ArrayList<>();
		this.averageResponseTime = Duration.ZERO;
		this.lastAskedTime = new Date();
		this.lastAnswerTime = new Date(0);
		this.score=0;
	}

	/* -------------- PUBLIC UTILITY MEMBER METHODS -------------- */

	/** * Non-standard member modifiers. */
	public void incrementAsked() {
		numberAsked++;
	}

	/** Increment answered. */
	public void incrementAnswered() {
		numberAnswered++;
	}

	/** Increment correct. */
	public void incrementCorrect() {
		numberCorrect++;
	}

	/** Adds a duration to the list of previous answer durations. Can be called directly
	 * with any chosen unit, or called by proxy via:
	 * <pre>updateTimes(Date askedTime, Date answerTime)</pre>
	 * <p>After adding to the list, this method then calculates the new value for
	 * <code>averageResponseTimes</code>.</p>
	 *
	 * @param answerTime the answer time in seconds
	 * 
	 * @see
	 * 					Stats#updateTimes(Date, Date) */
	public void addAnswerTime(long answerTime) {
		this.responseTimes.add(answerTime);
		this.averageResponseTime = Duration.ofSeconds(sumAnswerTimes() / responseTimes.size());
	}

	/** Update lastAskedTime, lastAnswerTime, adds a duration element (in seconds' difference)
	 * to answerTimes, and updates averageResponseTime.
	 *
	 * @param askedTime
	 *                   the time the question was asked, represented as a Date object
	 * @param answerTime
	 *                   the time the question was answered, represented as a Date object */
	public void updateTimes(Date askedTime, Date answerTime) {
		this.lastAskedTime = askedTime;
		this.lastAnswerTime = answerTime;
		long durationMs = answerTime.getTime() - askedTime.getTime();
		addAnswerTime(Duration.ofMillis(durationMs).getSeconds());
	}

	/* -------------- PRIVATE MEMBER METHODS -------------- */

	/** Recursively calculate the average answer time (in seconds).
	 *
	 * @return the sum of all the time durations within <code>ArrayList&lt;Long&gt; answerTimes</code> */
	private Long sumAnswerTimes() {
		long result = 0;
		for (long time : this.responseTimes) {
			result += time;
		}
		return result;
	}
}
