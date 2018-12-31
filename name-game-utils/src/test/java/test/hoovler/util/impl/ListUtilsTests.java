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
package test.hoovler.util.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoovler.utils.impl.ListUtils;

class ListUtilsTests {
	private static Logger log = LogManager.getLogger(ListUtilsTests.class.getName());
	
	static ArrayList<String> arrayList;
	static String[] stringArray;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		arrayList = new ArrayList<>();
		for (int i=0; i<3; i++) {
			arrayList.add("arrayList_" + (i+1));
		}
		stringArray = new String[]{"stringArray_1", "stringArray_2", "stringArray_3"};
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testReverseList() {
		log.info("==== ArrayList ====");
		int count = 1;
		for (String item : arrayList) {
			log.info("item #" + count + " = " + item);
			count++;
		}
		
		log.info("==== ArrayList.Reversed ====");
		ArrayList<String> reversedList = new ArrayList<>(ListUtils.reverseList(arrayList)); 
		count = 1;
		for (String meti : reversedList) {
			log.info("reversed item #" + count + " = " + meti);
			count++;
		}
		assertEquals(arrayList, new ArrayList<>(ListUtils.reverseList(reversedList)));
	}
}
