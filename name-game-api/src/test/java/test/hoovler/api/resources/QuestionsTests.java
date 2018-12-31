/*
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2018
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
package test.hoovler.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoovler.api.resources.Questions;
import com.hoovler.api.utils.QuestionsHelper;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.models.Question;

// TODO: Auto-generated Javadoc
/** The Class QuestionsTests. */
class QuestionsTests {

	private static Logger log = LogManager.getLogger(QuestionsTests.class.getName());

	// declared constants for use in all tests
	private static final int numQuestions = 50;
	private static final String normalPrefix = StringUtils.EMPTY;
	private static final String mattsOnlyPrefix = "mat";

	// undeclared members initialized for all tests
	private static Questions questions;
	private static Questions mattsOnly;
	private static DefaultProfileDao profiles;
	
	// undeclared members initialized for EACH test
	private static Question normalQ;
	private static long normalId;
	private static Question mattsQ;
	private static long mattsId;
	

	/** Sets the up before class.
	 *
	 * @throws Exception the exception */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("************* Starting up tests...");
		questions = new Questions();
		mattsOnly = new Questions();
		profiles = new DefaultProfileDao();

		// populate lists
		for (int i = 0; i < numQuestions; i++) {
			questions.addQuestion(QuestionsHelper.generateQuestion(profiles, normalPrefix));
			mattsOnly.addQuestion(QuestionsHelper.generateQuestion(profiles, mattsOnlyPrefix));
		}
	}
	
	@BeforeEach
	static void setUpBeforeTest() throws Exception {
		normalQ = QuestionsHelper.generateQuestion(profiles, normalPrefix);
		normalId = normalQ.getId();
		mattsQ = QuestionsHelper.generateQuestion(profiles, mattsOnlyPrefix);
		mattsId = mattsQ.getId();
		log.info("Setting up test... normalId=" + normalId + "; mattsId=" + mattsId);
	}



	/** Test get question. */
	@Test
	void testGetQuestion() {
		log.info("... testGetQuestion() ...");
		int index = new Random().nextInt(numQuestions);
		long qId = questions.questionList().get(index).getId();
		
		assertNotNull(questions.getQuestion(qId));
		assertTrue(questions.getQuestion(qId).getId() == qId);
	}

	/** Test adding a question */
	@Test
	void testAddQuestion() {
		log.info("... testAddQuestion() ...");
		
		// normal
		questions.addQuestion(normalQ);
		assertNotNull(questions.getQuestion(normalId));
		
		// matts-only
		mattsOnly.addQuestion(mattsQ);
		assertNotNull(mattsOnly.getQuestion(mattsId));
	}

	/** Test update question. */
	@Test
	void testUpdateQuestion() {
		int index = new Random().nextInt(numQuestions);
		log.info("... testUpdateQuestion() ... index=" + index);
		
		// normal
		Question oldQ = questions.questionList().get(index);
		assertEquals(questions.updateQuestion(oldQ.getId(), normalQ).getId(), oldQ.getId());
		
		
		// matts
	}

	/** Test delete question. */
	@Test
	void testDeleteQuestion() {
		log.info("... testDeleteQuestion() ...");
		assertTrue(1 == 1);
		Question q;
		
		// normal
		
		// matts

	}

	/** Test question list. */
	@Test
	void testQuestionList() {
		log.info("... testQuestionsList() ...");
		Question q;
		
		// normal
		
		// matts

	}

	/** Test question list int int. */
	@Test
	void testQuestionListIntInt() {
		log.info("... testQuestionListIntInt() ...");
		Question q;
		
		// normal
		
		// matts

	}

	/** Test question exists. */
	@Test
	void testQuestionExists() {
		log.info("... testQuestionExists() ...");
		Question q;
		
		// normal
		
		// matts

	}
	
	/** Tear down after all tests.
	 *
	 * @throws Exception the exception */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		log.info("... Tearing down tests *************");
	}
}
