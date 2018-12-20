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
package com.hoovler.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.data.Data;
import com.hoovler.api.models.Subject;
import com.hoovler.dao.models.Profile;
import com.hoovler.dao.models.Question;

public class QuestionsHelper {
	private static Logger log = LogManager.getLogger(QuestionsHelper.class.getName());


	/**
	 * Generates a valid Question object.
	 * 
	 * @author 	Michael Hoovler
	 * @param 	questionObjects - the list of profiles to use as the pool from which to draw target / options.
	 * @param 	setTimeAsked - if Question.timeAsked should be set to the current time; 
	 * 			should be set to true if this method is called as part of the API request processing
	 * 			pipeline.
	 * @return 	<code>Question</code> q; contains options, a target, a dateCreated, and an id.  Also contains timeAsked, if: 
	 * 			<code>setTimeAsked == true;</code>
	 * 			<p>Since the class type for Question.target (and those of the list Question.options) is Object (for generic reuse
	 * 			of the Question class and its QuestionDao persistence handler), this method iterates through a generated list
	 * 			of <code>Subject</code> objects, converting each to an <code>Object</code> object before being set as the Question.options attribute.</p>
	 * 			<p>The Question.qId value is set immediately upon the initialization of the Question object, allowing any return from this
	 * 			method to be immediately added to a QuestionDao persistence instance.</p>
	 * @see com.hoovler.dao.models.Question#generateId() Question.generateId()
	 */
	public static Question generateQuestion(ArrayList<Profile> questionObjects, boolean setTimeAsked) {
		Question q = new Question();
		
		// use a random index (0 : Data.numOptions-1) to select the 'target' from the list of 'options'
		int index = new Random().nextInt(Data.numOptions);
		ArrayList<Object> optionObjects = new ArrayList<>();
		
		// since Question.options is a list of Object types, rather than Subject types, we must
		// loop through the list of Subjects and convert them to Objects
		int itr = 0;
		for (Subject option : getSubjects(questionObjects, Data.numOptions)) {
			optionObjects.add(option);

			// set random option as the matching target
			if(itr==index) {
				q.setTarget(new Subject(option.getId(), option.getName(), option.getImageUrl()));
			}
			itr++;
		}
		q.setOptions(optionObjects);
		if (setTimeAsked) q.setAsked(new Date());
		return q;
	}
	
	private QuestionsHelper() {
		// hiding implicit constructor
	}

	/**
	 * Gets the options.
	 *
	 * @param profileDao the profile dao
	 * @param numSubjects the num subjects
	 * @return the options
	 */
	protected static ArrayList<Subject> getSubjects(ArrayList<Profile> from, int numSubjects) {

		ArrayList<Subject> subjects = new ArrayList<>();
		
		for (int i = 0; i < numSubjects; i++) {

			int index = -1;
			Profile candidate = null;

			while (!isViable(candidate)) {
				index = new Random().nextInt(from.size());
				candidate = from.get(index);
			}

			// set subject
			subjects.add(new Subject(candidate.getId(), candidate.getFirstName() + " " + candidate.getLastName(),
					candidate.getHeadshot().getUrl()));

			// remove candidate to eliminate duplicates
			from.remove(index);
		}

		return subjects;
	}

	/**
	 * Gets the target.
	 *
	 * @param from the from
	 * @return the target
	 */
	protected static Subject getTarget(ArrayList<Subject> from) {
		if (!from.isEmpty()) {
			int index = new Random().nextInt(from.size());
			return new Subject(
					from.get(index).getId(), 
					from.get(index).getName(), 
					from.get(index).getImageUrl());	
		} else {
			log.error("Unable to extract target from an empty list of subjects.");
			return null;
		}
		
	}

	/**
	 * Checks if a given profile should be used as a Subject within a Question.
	 *
	 * @param profile the <code>Profile</code> object to test.
	 * @return true, if the <code>profile</code> has a valid first name, last name, and Headshot.Url.
	 */
	private static boolean isViable(Profile candidate) {
		if (candidate == null)
			return false;
		return StringUtils.isNoneBlank(
				new String[] { candidate.getFirstName(), candidate.getLastName(), candidate.getHeadshot().getUrl() });
	}

}
