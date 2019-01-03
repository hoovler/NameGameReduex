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
package com.hoovler.api.utils;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.models.QuestionOption;
import com.hoovler.api.models.Subject;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Profile;
import com.hoovler.dao.models.Question;

public class QuestionsHelper extends NameGameHelper {
	private static Logger log = LogManager.getLogger(QuestionsHelper.class.getName());

	/** Ask response.
	 *
	 * @param  reverse the reverse
	 * @param  q       the q
	 * @return         the question options */
	public static ArrayList<QuestionOption> getQuestionOptions(boolean reverse, Question q) {
		return reverse ? getReverseOptions(q.getOptions()) : getNormalOptions(q.getOptions());
	}

	/** Gets QuestionsHelper.questionTarget
	 *
	 * @param  reverse the reverse
	 * @param  q       the q
	 * @return         the question target */
	public static String getQuestionTarget(boolean reverse, Question q) {
		return reverse ? getReverseTarget(q.getTarget())
				: getNormalTarget(q.getTarget());
	}

	/** Gets a normal option (or target), where question.options = List<imageUrl>
	 *
	 * @param  object the object
	 * @return        the normal option */
	public static String getNormalTarget(Object object) {
		Subject subject = Subject.class.cast(object);
		return subject.getImageUrl();
	}

	/** Gets a list of normal options, where question.options = List<imageUrl>
	 *
	 * @param  subjects the subjects
	 * @return          the normal options */
	public static ArrayList<QuestionOption> getNormalOptions(ArrayList<Object> subjects) {
		ArrayList<QuestionOption> formattedOptions = new ArrayList<>();
		log.info("getNormalOptions(ArrayList<Object> subjects)");
		for (Object object : subjects) {
			Subject subject = Subject.class.cast(object);
			QuestionOption option = new QuestionOption(subject.getId(), subject.getName());
			log.info("    Adding new AskOption: optionId = " + option.getOptionId() + ", optionValue"
					+ option.getOptionValue());
			formattedOptions.add(option);
		}
		return formattedOptions;
	}

	/** Gets a reverse option (or target), where question.options = List<name>
	 *
	 * @param  object the object
	 * @return        the reverse option */
	public static String getReverseTarget(Object object) {
		Subject subject = Subject.class.cast(object);
		return subject.getName();
	}

	/** Gets a list of reverse options, where question.options = List<name>
	 *
	 * @param  optionsFromQ the subjects
	 * @return              the reverse options */
	public static ArrayList<QuestionOption> getReverseOptions(ArrayList<Object> optionsFromQ) {
		ArrayList<QuestionOption> formattedOptions = new ArrayList<>();
		log.info("getReverseOptions(ArrayList<Object> subjects)");
		for (Object object : optionsFromQ) {
			Subject subject = Subject.class.cast(object);
			QuestionOption option = new QuestionOption(subject.getId(), subject.getImageUrl());
			log.info("    Adding new AskOption: optionId = " + option.getOptionId() + ", optionValue"
					+ option.getOptionValue());
			formattedOptions.add(option);
		}
		return formattedOptions;
	}

	/** Generates a valid Question object.
	 * 
	 * @author            Michael Hoovler
	 * @param  data       - the <code>Data</code> persistence object, containing the session states (and wrapper methods) of each included DAO
	 * @param  namePrefix - a case-insensitive string with which each Subject's name must begin.
	 * @return            <code>Question</code> q; contains options, a target, a dateCreated, and an id. Also contains timeAsked, if:
	 *                    <code>setTimeAsked == true;</code>
	 *                    <p>Since the class type for Question.target (and those of the list Question.options) is Object (for generic reuse
	 *                    of the Question class and its QuestionDao persistence handler), this method iterates through a generated list
	 *                    of <code>Subject</code> objects, converting each to an <code>Object</code> object before being set as the Question.options attribute.</p>
	 *                    <p>The Question.qId value is set immediately upon the initialization of the Question object, allowing any return from this
	 *                    method to be immediately added to a QuestionDao persistence instance.</p>
	 * @see               com.hoovler.dao.models.Question#generateId() Question.generateId() */
	public static Question generateQuestion(DefaultProfileDao profileService, String namePrefix) {
		ArrayList<Profile> questionObjects = profileService.profileList();
		Question q = new Question();

		// use a random index (0 : Data.numOptions-1) to select the 'target' from the
		// list of 'options'
		int index = new Random().nextInt(NameGameHelper.NUM_OPTIONS);
		ArrayList<Object> optionObjects = new ArrayList<>();

		// since Question.options is a list of Object types, rather than Subject types,
		// we must
		// loop through the list of Subjects and convert them to Objects
		int itr = 0;
		for (Subject option : getSubjects(questionObjects, NameGameHelper.NUM_OPTIONS, namePrefix)) {
			optionObjects.add(option);

			// set random option as the matching target
			if (itr == index) {
				q.setTarget(new Subject(option.getId(), option.getName(), option.getImageUrl()));
			}
			itr++;
		}
		q.setOptions(optionObjects);
		return q;
	}

	protected static ArrayList<Subject> getSubjects(ArrayList<Profile> from, int numSubjects) {
		return getSubjects(from, numSubjects, null);
	}

	/** Gets the subjects.
	 *
	 * @param  from        the from
	 * @param  numSubjects the num subjects
	 * @param  namePrefix  the name prefix
	 * @return             the subjects */
	protected static ArrayList<Subject> getSubjects(ArrayList<Profile> from, int numSubjects, String namePrefix) {
		ArrayList<Subject> subjects = new ArrayList<>();

		for (int i = 0; i < numSubjects; i++) {
			int index = -1;
			Profile candidate = null;

			// continue to loop through origin profiles list until one matches our criteria
			while (!isViable(candidate, namePrefix)) {
				// randomly choose a profile from the origin list
				index = new Random().nextInt(from.size());
				candidate = from.get(index);
			}

			// determined to match criteria, profile will be added as a subject
			subjects.add(new Subject(candidate.getId(), candidate.getFirstName() + " " + candidate.getLastName(),
					candidate.getHeadshot().getUrl()));

			// remove candidate to eliminate duplicates
			from.remove(index);
		}

		return subjects;
	}

	/** Gets the target.
	 *
	 * @param  from the from
	 * @return      the target */
	protected static Subject getTarget(ArrayList<Subject> from) {
		if (!from.isEmpty()) {
			int index = new Random().nextInt(from.size());
			return new Subject(from.get(index).getId(), from.get(index).getName(), from.get(index).getImageUrl());
		} else {
			log.error("Unable to extract target from an empty list of subjects.");
			return null;
		}
	}

	/** Checks if a given profile should be used as a Subject within a Question.
	 *
	 * @param  profile the <code>Profile</code> object to test.
	 * @return         true, if the <code>profile</code> has a valid first name, last name, and Headshot.Url. */
	private static boolean isViable(Profile candidate, String namePrefix) {
		return candidate != null && StringUtils.isNoneBlank(
				new String[] { candidate.getFirstName(), candidate.getLastName(), candidate.getHeadshot().getUrl() })
				&& StringUtils.startsWithIgnoreCase(candidate.getFirstName(), namePrefix);
	}
}
