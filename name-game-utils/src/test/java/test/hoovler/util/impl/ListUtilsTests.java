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
package test.hoovler.util.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.hoovler.utils.impl.ListUtils;

class ListUtilsTests {
	private static Logger log = LogManager.getLogger(ListUtilsTests.class.getName());

	static ArrayList<String> arrayList;
	static String[] stringArray;
	
	int count;
	ArrayList<String> testList;
	int start;
	int velocity;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		arrayList = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			arrayList.add("arrayList_" + (i + 1));
		}
		stringArray = new String[] { "stringArray_1", "stringArray_2", "stringArray_3" };
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
		printResults(reversedList);
		assertEquals(arrayList, new ArrayList<>(ListUtils.reverseList(reversedList)));
	}

	@Test
	void testSubsetListCase1() {
		start = 0;
		velocity = 0;

		printSubsetTestsHeader(1, "First Item Only");
		testList = new ArrayList<>(ListUtils.subset(arrayList, start, velocity));
		printResults(testList);
		
		assertTrue(!testList.isEmpty());
	}
	
	@Test
	void testSubsetListCase2() {
		start = -1;
		velocity = -arrayList.size();
		
		printSubsetTestsHeader(2, "Full List in Reverse");
		testList = new ArrayList<>(ListUtils.subset(arrayList, start, velocity));
		printResults(testList);
		
		assertTrue(!testList.isEmpty());
	}
	
	@Test
	void testSubsetListCase3() {
		start = -6;
		velocity = 0;

		printSubsetTestsHeader(3, "Single, non-First Item");
		testList = new ArrayList<>(ListUtils.subset(arrayList, start, velocity));
		printResults(testList);
		
		assertTrue(!testList.isEmpty());
	}
	
	@Test
	void testSubsetListCase4() {
		start = 9;
		velocity = 5;

		printSubsetTestsHeader(4, "Positive start, non-Looped Subset");
		testList = new ArrayList<>(ListUtils.subset(arrayList, start, velocity));
		printResults(testList);
		
		assertTrue(!testList.isEmpty());
	}
	
	@Test
	void testSubsetListCase5() {
		start = 24;
		velocity = 10;

		printSubsetTestsHeader(5, "Positive start, Looped Subset");
		testList = new ArrayList<>(ListUtils.subset(arrayList, start, velocity));
		printResults(testList);
		
		assertTrue(!testList.isEmpty());
	}
	
	@Test
	void testSubsetListCase6() {
		start = 24;
		velocity = -10;

		printSubsetTestsHeader(6, "Positive start, Backwards, non-Looped Subset");
		testList = new ArrayList<>(ListUtils.subset(arrayList, start, velocity));
		printResults(testList);
		
		assertTrue(!testList.isEmpty());
	}
	
	@Test
	void testSubsetListCase7() {
		start = 5;
		velocity = -15;

		printSubsetTestsHeader(7, "Positive start, Backwards, Looped Subset");
		testList = new ArrayList<>(ListUtils.subset(arrayList, start, velocity));
		printResults(testList);
		
		assertTrue(!testList.isEmpty());
	}
	
	private void printSubsetTestsHeader(int caseNumber, String caseDescription) {
		log.info("Use Case " + caseNumber + ": " + caseDescription);
		log.info(" start=" + start + "; velocity=" + velocity);		
	}
	
	private void printResults(ArrayList<String> testList){
		count = 1;
		log.info("Number of elements = " + testList.size());
		for (String item : testList) {
			log.info("item #" + count + " [index " + (count-1) + "] = " + item);
			count++;
		}		
	}
}
