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
package com.hoovler.dao.resources;

public enum ScoreBy {

	/** <p>Used when score should be the ratio of number of correct answers to the total number of answers submitted.</p>
	 * <pre>double_score = 1000 * (numCorrectAnswers / numQuestionsAnswered)</pre> */
	CORRECT_TO_TOTAL,
	
	/** <p>Used when score should be the average duration, in seconds, of the time between when a question is asked, and
	 * when the answer is submitted (whether correct or not).  <b><em>Questions NOT answered are NOT factored in.</em></b></p>
	 * <p>This method calculates the score as the <b><em>inverse</em></b> of that average response time, meaning that the 
	 * shorter the average duration, the higher the score.</p> 
	 * <pre>score = 1000 * (1 / (sum(responseTimesList) / len(responseTimesList)))</pre>*/
	RESPONSE_TIME_AVERAGE,
	
	/** <p>Used when score should include both the ratio of correct answers to total number of answers <b><em>[r]</em></b> 
	 * and the inverse of the average duration (in seconds) of response times <b><em>[t]</em></b>.<p>
	 * <p>For this scoring method, the score is calculated by multiplying <b><em>r</em></b> by <b><em>t</em></b>.</p>
	 * <pre>score = 1000 * (
	 *     (numCorrectAnswers / numQuestionsAnswered) * 
	 *     (1 / (sum(responseTimesList) / len(responseTimesList))
	 * )</pre> */
	CORRECT_WEIGHTED_TIME,
	
}
