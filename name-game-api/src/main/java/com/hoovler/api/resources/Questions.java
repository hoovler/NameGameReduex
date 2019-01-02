/* 
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2019
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
package com.hoovler.api.resources;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.models.Subject;
import com.hoovler.dao.QuestionDao;
import com.hoovler.dao.models.Question;
import com.hoovler.utils.Messages;
import com.hoovler.utils.impl.ListUtils;

/** <p><h3>Questions</h3> <p><b><u>Purpose</u></b></p> <p>This class provides an implementation of the <code>QuestionDao</code> interface.</p> <p><b><u>Information</u></b></p> <p>Though this class could simply be one which extends <code>DefaultQuestionDao</code>, the state of the object's data isn't persistent when making calls to the <code>super</code> class, as that class hasn't been autowired by the Spring Framework.</p> */
public class Questions implements QuestionDao {
	// TODO: Use a NoSql db to store persistence objects; javax.persistence.EntityManager
	// TODO: https://mapr.com/blog/data-modeling-guidelines-nosql-json-document-databases/

	/** <p>The [value description]</p> <pre> some example use </pre> . */
	private static Logger log = LogManager.getLogger(Questions.class.getName());

	/** <p>The [value description]</p> <pre> some example use </pre> . */
	private HashMap<Long, Question> questionsMap;

	/** Instantiates a new questions. */
	public Questions() {
		this.questionsMap = new HashMap<>();
	}

	// instantiated interface methods

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#getQuestion(java.lang.Long)
	 */
	@Override
	public Question getQuestion(Long qId) {
		return questionsMap.get(qId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#addQuestion(com.hoovler.dao.models.Question)
	 */
	@Override
	public Question addQuestion(Question q) {
		if (q == null || q.getId() < 1) {
			log.warn(Messages.get(Messages.MSG_ID_BLANK));
		} else {
			log.info(Messages.get(Messages.MSG_ADD_QUESTION) + q.getId());
			questionsMap.put(q.getId(), q);
		}
		return q;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#updateQuestion(java.lang.Long, com.hoovler.dao.models.Question)
	 */
	@Override
	public Question updateQuestion(Long id, Question updatedQ) {
		if (!questionsMap.containsKey(id)) {
			log.warn(Messages.get(Messages.MSG_ID_NOT_FOUND) + id + Messages.get(Messages.MSG_NOT_UPDATING));
			return null;
		} else if (updatedQ.getId() != id) {
			log.warn(Messages.get(Messages.MSG_IDS_NON_MATCHING) + id + " != " + updatedQ.getId() + Messages.get(Messages.MSG_NOT_UPDATING));
			return null;
		} else {
			return questionsMap.put(id, updatedQ);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#deleteQuestion(java.lang.Long)
	 */
	@Override
	public boolean deleteQuestion(Long qId) {
		Question removedQ = this.questionsMap.remove(qId);
		return removedQ != null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.hoovler.dao.QuestionDao#questionList()
	 */
	@Override
	public ArrayList<Question> questionList() {
		return new ArrayList<>(questionsMap.values());
	}

	// additional class methods

	/** <p> Gets the question list based on input parameters.</p>
	 * <p> To explain how <code>stop</code> and <code>start</code> with differing signs
	 * (positive and/or negative) can affect the returned value, visualize the full
	 * list (from which the return value is derived) as being a circular ring, where
	 * the last element is connected to the first, and where a normal iterator moves
	 * <em>clockwise</em> from the first element to the last, and back to the first.
	 * The parameter values are then used as follows:</p>
	 * <ul>
	 * <li> A positive <code>start</code> value returns a list which begins at the number
	 * of elements <em>forward</em> from the beginning, or the element at the <code>
	 * start</code> position <em>clockwise</em> from the 12 O'clock position.</li>
	 * <li> A negative <code>start</code> value returns a list which begins at the number
	 * of elements <em>backwards</em> from the end, or number of elements <em>
	 * counterclockwise</em> from the 12 o'clock posistion.</li>
	 * <li> A positive <code>stop</code> that is greater than the <code>start</code> will
	 * return the list beginning with <code>start</code>, and moving <em>forward</em>
	 * towards <code>stop</code> in a <em>clockwise</em> direction.</li>
	 * <li> A n <code>stop</code> that is greater than the <code>start</code> will
	 * return the list beginning with <code>start</code>, and moving <em>forward</em>
	 * towards <code>stop</code> in a <em>clockwise</em> direction.</li>
	 * </ul>
	 * 
	 * <pre>
	 * <em>// to create to identical lists</em>
	 * ArrayList&lt;Question&gt; allQuestions = Questions.questionsList();
	 * ArrayList&lt;Question&gt; testQuestionsA = Questions.questionsList(0);
	 * ArrayList&lt;Question&gt; testQuestionsB = Questions.questionsList(0, allQuestions.size());
	 * 
	 * <em>// to reverse the list:</em>
	 * testQuestions = Questions.questionsList(-1*allQuestions.size(), 0);
	 * 
	 * <em>// to return a reversed sublist started in the middle and ending with the first element:</em>
	 * testQuestions = Questions.questionsList(-1*allQuestions.size(), 
	 * 					-1*(allQuestions.size()/2));
	 * 
	 * <em>// a sublist starting in the middle and ending with the next-to-last element:</em>
	 * testQuestions = Questions.questionsList(allQuestions.size(),
	 * 
	 * </pre>
	 *
	 * @param  length  the length of the returned list. <b>Use <i>length==-1</i> to return the list in
	 *                 it's entirety</b>, after skipping <code>skip</code> number of elements.
	 * @param  skip    the number of elements to skip before beginning to build the return list
	 * @param  reverse set to <code>true</code> to begin counting <code>length</code> from the
	 *                 <em>end</em> of the list, working forward to the <em>beginning</em> of the
	 *                 list. This will also ensure that<code>skip</code> skips elements from the
	 *                 <em>end</em> of the list, counting its own way toward the beginning.
	 * @return         The resulting <code>ArrayList&lt;Question&gt;</code> list.
	 * 
	 *                 <pre>
	 *                 ArrayList&lt;Question&gt; allQuestions = Questions.questionsList(-1, 0, false);
	 *                 ArrayList&lt;Question&gt; testQuestions = Questions.questionsList();
	 *                 </pre>
	 * 
	 *                 <p>Both objects are equivalent:</p>
	 *                 <em>
	 *                 allQuestions.containsAll(testQuestions) == true<br>
	 *                 testQuestion.size() == allQuestions.size() == true;<br>
	 *                 allQuestions.equals(testQuestions) == true;
	 *                 </em>
	 * 
	 *                 <pre>
	 *                 testQuestions = Questions.questionsList(testQuestion.size(), -1, false);
	 *                 </pre>
	 * 
	 *                 <p>As a negative skip denotes a reverse list, then the two lists are no longer the same.<code></code> */
	public ArrayList<Question> questionList(int start, int stop) {
		// grab initial variable states
		ArrayList<Question> allQuestions = new ArrayList<>(questionsMap.values());
		ArrayList<Question> allReversed = new ArrayList<>(ListUtils.reverseList(allQuestions));

		// grab number of elements
		int listSize = allQuestions.size();

		// if the absolute values of params is greater than listSize, return an empty list
		if (Math.abs(start) > listSize) {
			return new ArrayList<>();
		}

		// if abs(stop) is greater than listSize, set stop = listSize
		if (Math.abs(stop) < listSize) {
			if (stop > 0) {
				stop = listSize;
			} else {
				stop = -1 * listSize;
			}
		}
		if (start < 0 && stop < 0) {
			return new ArrayList<>(allReversed.subList(start, stop));
		}

		return new ArrayList<>(allQuestions.subList(start, stop));
	}

	/** Question exists.
	 *
	 * @param  qId the q id
	 * @return     true, if successful */
	public boolean questionExists(Long qId) {
		return questionsMap.get(qId) != null;
	}

	/** Cast the Question's target or an option from <code>class Object</code> to <code>class Target</code>.
	 *
	 * @param  questionObject An <code>Object</code> retrieved from <code>Question.getTarget</code>
	 *                        or from a member of the list <code>Question.getOptions()</code>.
	 * @return                a <code>Subject</code> object, which is the class used within <code>Question</code> for the
	 *                        purposes of this API. */
	public Subject subjectFromObject(Object questionObject) {
		return Subject.class.cast(questionObject);
	}

	/** Checks if is correct id.
	 *
	 * @param  questionId the question id
	 * @param  answerId   the id value of the selected option
	 * @return            true, if <code>answerId == Question(questionId).getTarget().getId()</code> */
	public boolean isCorrectAnswer(long questionId, String answerId) {
		log.info("questionId = " + questionId);
		log.info("answerId = " + answerId);

		String correctId;

		Question q = getQuestion(questionId);

		if (q == null) {
			log.warn("Question with id " + questionId
					+ " was not retrieved... it was likely not stored in the Question persistence object.");
			return false;
		}

		Subject target = subjectFromObject(q.getTarget());

		log.info("Question with id " + questionId + " was found!");
		correctId = target.getId();
		return StringUtils.equalsIgnoreCase(StringUtils.deleteWhitespace(answerId),
				StringUtils.deleteWhitespace(correctId));
	}
}
