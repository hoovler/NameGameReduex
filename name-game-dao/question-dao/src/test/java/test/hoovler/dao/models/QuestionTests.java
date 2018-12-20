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
package test.hoovler.dao.models;

import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoovler.dao.models.Question;


class QuestionTests {
	private static Logger log = LogManager.getLogger(QuestionTests.class.getName());

	private static int numOptions;
	private static String preId;
	private static String preName;
	private static String preUrl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		numOptions = 6;
		preId = "SID00";
		preName = "Subject_";
		preUrl = "http://images.org/subject";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		log.info("Starting JUnit Test...");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testOptions() {
		log.info("void testOptions()");
		
		Question q = new Question();
		log.info("" + q.getCreated().getTime());
		
		ArrayList<Object> options = new ArrayList<>();
		
		for (int i = 0; i < numOptions; i++) {
			options.set(i, new QObject(preId+i, preName+i, preUrl+i));
		}
		
		Object target = options.get(new Random().nextInt(options.size()));
		
		
		examineQ(q);
	}
	
	private static void examineQ(Question q) {
		log.info("================================");
		log.info("Examining Question: " + q.getId());
		log.info("================================");
	}
}
