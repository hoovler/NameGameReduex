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

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import com.hoovler.dao.resources.ScoreBy;

/** <p><h3>Stat</h3> <p><b><u>Purpose</u></b></p> This Class ...</p> <p><b><u>Information</u></b><br /> The <code>Stat</code> object is...</p> <p><b><u>Examples</u></b></p> An example: <pre>some code</pre> Another example: <pre>some more code;</pre> */
public class Stats {
			private Integer numberAsked;
		private Integer numberAnswered;
		private Integer numberCorrect;
		private Double ratioCorrect;
		private ArrayList<Long> responseTimes;
		private Duration averageResponseTime;
		private Date lastAskedTime;
		private Date lastAnswerTime;
		private Double score;
		/** * Non-standard member modifiers. */
		public void incrementAsked() {
		numberAsked++;
	}
		public void incrementAnswered() {
		numberAnswered++;
	}
		public void incrementCorrect() {
		numberCorrect++;
		updateCorrectPercent();
	}
		public void updateCorrectPercent() {
		this.ratioCorrect = numberAsked / (double)(numberAnswered - numberCorrect); 
	}
		/**
	 * Adds a duration to the list of previous answer durations.  Can be called directly 
	 * with any chosen unit, or called by proxy via: 
	 * <pre>updateTimes(Date askedTime, Date answerTime)</pre>
	 * <p>After adding to the list, this method then calculates the new value for
	 * <code>averageResponseTimes</code>.</p>
	 *
	 * @param answerTime the answer time in seconds
	 * 
	 * @see
	 *  Stats#updateTimes(Date, Date)
	 */
	public void addAnswerTime(long answerTime) {
		this.responseTimes.add(answerTime);
		this.averageResponseTime = Duration.ofSeconds(sumAnswerTimes() / responseTimes.size());
	}
		/**
	 * Update lastAskedTime, lastAnswerTime, adds a duration element (in seconds' difference) 
	 * to answerTimes, and updates averageResponseTime.
	 *
	 * @param askedTime 
	 * 			the time the question was asked, represented as a Date object
	 * @param answerTime 
	 * 			the time the question was answered, represented as a Date object
	 */
	public void updateTimes(Date askedTime, Date answerTime) {
		this.lastAskedTime = askedTime;
		this.lastAnswerTime = answerTime;
				long durationMs = answerTime.getTime() - askedTime.getTime();
		addAnswerTime(Duration.ofMillis(durationMs).getSeconds());
	}
		/**
	 * Recursively calculate the average answer time (in seconds).
	 *
	 * @param responseTimes the answer times
	 * @param startIndex the start index
	 * @return the sum of all the time durations within <code>ArrayList&lt;Long&gt; answerTimes</code>
	 */
	public Long sumAnswerTimes() {
		long result = 0;
		for (long time : this.responseTimes) {
			result += time;
		}
		return result;
	}
		/**
	 * Calculates, sets, and returns a score using the ScoreBy's documented algorithm. 
	 *
	 * @param scoreBy the ScoreBy object set as one of the enumerated values; each value has a documented algorithm.
	 * @param multiplier the value to multiply by the algorithm's resulting value - which can be a number that is QUITE small.
	 * @return A double value that represents the score.  If a multiplier of 1000 is passed in (my recommended value), then 
	 * any player with a reasonable response time and correct ratio would receive a score between 100 and 1000, while
	 * a player with very poor performance may receive scores lower than 1.  For example:
	 * <pre>
	 * playerA.ratioCorrect = .98334;
	 * playerA.averageResponseTime = 2;
	 * -------------
	 * double score_A1 = playerA.setScore(ScoreBy.CORRECT_TO_TOTAL, 1000);
	 * score_A1 == 491.67
	 * 
	 * double score_A2 = playerA.setScore(ScoreBy.CORRECT_TO_TOTAL, 100);
	 * score_A2 == 49.167
	 * 
	 * double score_A3 = playerA.setScore(ScoreBy.CORRECT_TO_TOTAL, 0);
	 * score_A3 == .49167
	 * --------------------------
	 * </pre>
	 * <pre>
	 * playerB.ratioCorrect = .4658;
	 * playerB.averageResponseTime = 7;
	 * -------------
	 * double score = playerA.setScore(ScoreBy.CORRECT_TO_TOTAL, 1000);
	 * score == 66.5427906
	 * --------------------------
	 * </pre>
	 * <pre>
	 * playerC.ratioCorrect = 1;
	 * playerC.averageResponseTime = 19384;
	 * -------------
	 * double score = playerA.setScore(ScoreBy.CORRECT_TO_TOTAL, 1000);
	 * score == 0.0516
	 * --------------------------
	 * </pre>
	 * In order to select the right <code>ScoreBy</code> value, the following table provides a description
	 * of how each option calculates the score:
	 * <table border=1>
	 * <tr>
	 * 		<th><code>ScoreBy</code> Value</th>
	 * 		<th>Algorithm description</th>
	 * 		<th>Calculation (without multiplier)</th>
	 * </tr>
	 * <tr>
	 * 		<td><code>ScoreBy.CORRECT_TO_TOTAL</code></td>
	 * 		<td>The ratio of number of correct answers to the total number of answers submitted, as the base
	 * 			percentage as a value between 0 and 1.</td>
	 * 		<td><code>this.numCorrectAnswers / this.numQuestionsAnswered</code></td>
	 * </tr>
	 * <tr>
	 * 		<td><code>ScoreBy.RESPONSE_TIME_AVERAGE</code></td>
	 * 		<td>The average duration, in seconds, of the time between when a question is asked, and
	 * 			when the answer is submitted (whether correct or not).  <b><em>Questions NOT answered are 
	 * 			NOT factored in.</em></b> </td>
	 * 		<td><code>1 / (this.averageResponseTime.getSeconds())</code></td>
	 * </tr>
	 * <tr>
	 * 		<td><code>ScoreBy.CORRECT_WEIGHTED_TIME</code></td>
	 * 		<td>This algorithm produces a score that is the percentage correct <b><em>weighted</b></em> 
	 * 			by the inverse of the average response time.  In other words, where the ratio of correct 
	 * 			answers to total number of answers = <b><em>r</em></b> and the inverse of the average 
	 * 			duration (in seconds) of response times = <b><em>t</em></b>, this score is calculated by 
	 * 			multiplying <b><em>r</em></b> by <b><em>t</em></b>.</td>
	 * 		<td><code>(this.numberCorrect / this.numberAnswered) *<br/>
	 * 			(1 / this.averageResponseTime.getSeconds())</code></td>
	 * </tr>
	 * </table>
	 * If none of the scoring methods suit the purposes required, the setter function 
	 * <code>Stats.setScore(Double score)</code> is available for any developer to write their own algorithm
	 * to calculate the score, and set it directly.  Unlike this method, however, the
	 * <code>Stats.setScore(Double score)</code> does not return the value of the score.
	 * @see com.hoovler.dao.resources.ScoreBy
	 */
	public Double setScore(ScoreBy scoreBy, int multiplier) {
		switch (scoreBy) {
		case CORRECT_WEIGHTED_TIME:
			this.score = (double)this.numberCorrect / this.numberAnswered * 
					multiplier;
			return this.score;
		case RESPONSE_TIME_AVERAGE:
			this.score = 1d / this.averageResponseTime.getSeconds() * 
					multiplier;
			return this.score;
		case CORRECT_TO_TOTAL: default:
			this.score = ((double)this.numberCorrect / this.numberAnswered) * 
					(1d / this.averageResponseTime.getSeconds()) * 
					multiplier;
			return this.score;
		}
	}
		/** * Setters. */
		public void setLastAskedTime(Date lastAskedTime) {
		this.lastAskedTime = new Date();
		this.lastAskedTime = lastAskedTime;
	}

	public void setLastAnswerTime(Date lastAnswerTime) {
		this.lastAnswerTime = new Date();
		this.lastAnswerTime = lastAnswerTime;
	}
		public void setScore(Double score) {
		this.score = score;
	}
		/** * Getters. */

	public Integer getNumberAsked() {
		return numberAsked;
	}

	public Integer getNumberAnswered() {
		return numberAnswered;
	}

	public Integer getNumberCorrect() {
		return numberCorrect;
	}
		public ArrayList<Long> getAnswerTimes() {
		return this.responseTimes;
	}

	public Double getPercentCorrect() {
		return ratioCorrect;
	}

	public Duration getAverageResponseTime() {
		return averageResponseTime;
	}

	public Date getLastAskedTime() {
		return lastAskedTime;
	}

	public Date getLastAnswerTime() {
		return lastAnswerTime;
	}
		public Double getScore(Double score) {
		return this.score;
	}
		/** Instantiates a new stat. */
	public Stats() {
		this.numberAsked = 0;
		this.numberAnswered = 0;
		this.numberCorrect = 0;
		this.ratioCorrect = 0.0;
		this.responseTimes = new ArrayList<>();
		this.averageResponseTime = Duration.ZERO;
	}
}
