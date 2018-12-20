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

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Question {

	/** <p>The unique id of the <code>Question</code> object.</p> 
	 * It is generated using <code>Question.hashCode()</code>, the overridden 
	 * <code>Object.hashCode()</code>, by using the values of the other <code>Question</code> member variables.
	 * To ensure a unique ID, the timestamp - as MS - is appended to the normal hash code. */
	private long qId;

	/** 
	 * <p>The object against which an <code>options</code> object is matched for a selection.</p>
	 * <p><b><u>Example</u></b></p>
	 * <table border='0'>
	 * 	<tr><td><code>options</code></td><td>> <code>1,red|2,purple|3,orange|4,pink|5,green|6,blue</code></td></tr>
	 * 	<tr><td><b><u><code>target</code></u></b></td><td>> <code>fruit</code></td></tr>
	 * 	<tr><td><code>answer</code> id</td><td>> <code>3</code></td></tr>
	 * </table>
	 **/
	Object target;

	/**
	 * <p>The list of objects against which a <code>target</code> object is matched.</p>
	 * <p><b><u>Example</u></b></p>
	 * <table border='0'>
	 * 	<tr><td><b><u><code>options</code></u></b></td><td>> <code>1,red|2,purple|3,orange|4,pink|5,green|6,blue</code></td></tr>
	 * 	<tr><td><code>target</code></td><td>> <code>fruit</code></td></tr>
	 * 	<tr><td><code>answer</code> id</td><td>> <code>3</code></td></tr>
	 * </table>
	 * */
	private ArrayList<Object> options;

	/** 
	 * <p>The date and time the specific <code>Question</code> object was created, which is ostensibly 
	 * the same as when it was posed to the user.</p>
	 *  */
	private Date timeCreated;

	/** <p><i>timeAsked</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private Date timeAsked;

	public long getId() {
		return this.qId;
	}

	public Object getTarget() {
		return target;
	}

	public ArrayList<Object> getOptions() {
		return options;
	}

	public Date getAsked() {
		return timeAsked;
	}

	public Date getCreated() {
		return timeCreated;
	}

	protected void setId(long id) {
		this.qId = id;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setOptions(ArrayList<Object> options) {
		this.options = options;
	}

	public void setAsked(Date timeAsked) {
		this.timeAsked = timeAsked;
	}

	public Question() {
		this.timeCreated = new Date();
		this.qId = generateId();
	}
	
	public Question(Object target, ArrayList<Object> options) {
		this.target = target;
		this.options = options;
	}

	public Question(Object target, ArrayList<Object> options, Date timeAsked) {
		this.target = target;
		this.options = options;
		this.timeAsked = timeAsked;
	}

	/**
	 * Generate the <code>Question.questionId</code> in a manner similar to Object.hashCode(), but without relying
	 * on all the Question object's member variables to be set.
	 *
	 * @return 	<p>The <b>positive</b> <code>long</code> to be used for the <code>Question.questionId</code>;
	 * 			is generated via the following values:</p> 
	 * <table border=1>
	 * <tr>	
	 * 		<td><pre>Question.getCreated()</pre></td>
	 * 		<td>Used as the initial value of <code>result</code></td>
	 * </tr>
	 * <tr>	
	 * 		<td><pre>int prime = 31</pre></td>
	 * 		<td>The prime number value used in the creation of a random number for each iteration.</td>
	 * </tr>
	 * <tr>	
	 * 		<td><pre>int iterations = 16</pre></td>
	 * 		<td>A pre-configured number of iterations of:
	 * 			<pre>for (itr : iterations) { result = prime * result + new Random().nextInt(itr * prime) }</pre></td>
	 * </tr>
	 * <tr>	
	 * 		<td><pre>new Date().getTime()</pre></td>
	 * 		<td>This <code>long</code> value, the number of milliseconds since origin (Jan 1, 1970), is set as the result prefix in order to avoid collision:
	 * 			<pre>result = Long.valueOf(String.valueOf(new Date().getTime()) + String.valueOf(result));</pre></td>
	 * </tr>
	 * </table>
	 * <p>The two primary resulting value differences between this method and <code>Object.hashCode()</code> are: 
	 * <ul>
	 * 	<li>the value returned is always positive</li>
	 * 	<li>the value returned is of type <code>long</code> rather than type <code>int</code></li>
	 * </ul>
	 * is that this method will
	 * always return a positive integer value IF the following values have been set (are not <code>null</code>):</p>
	 * <p>If any one of these values has not been initialized, this method will return -1 in order to give the calling
	 * method a mechanism to test success.</p> 
	 */
	protected long generateId() {
		final int prime = 31;
		final int itr = 16;
		long result = this.getCreated().getTime();
		
		for (int i = 0; i < itr; i++) {
			result = prime * result + new Random().nextInt(i * prime);
		}

		// add current time (ms since origin) to help prevent collision
		result = Long.valueOf(String.valueOf(new Date().getTime()) + String.valueOf(result));

		// ensure result is positive
		return Math.abs(result);
	}

}
