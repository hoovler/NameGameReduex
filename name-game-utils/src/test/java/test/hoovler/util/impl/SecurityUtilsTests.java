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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoovler.utils.impl.SecurityUtils;

class SecurityUtilsTests {
	private static Logger log = LogManager.getLogger(SecurityUtilsTests.class.getName());

	private static String rawValueString;
	private static String rawValueLong;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		rawValueString = "TestingString";
		rawValueLong = "12345678987654321";
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testStringHex() {
		String hexString = SecurityUtils.toHex(rawValueString);
		log.info("hexString = " + hexString);
		String rawString = SecurityUtils.fromHex(hexString);
		log.info("rawString = " + rawString);
		assertEquals(rawString, rawValueString);
	}
	
	@Test
	void testStringLong() {
		String hexString = SecurityUtils.toHex(rawValueLong);
		log.info("hexString = " + hexString);
		String rawString = SecurityUtils.fromHex(hexString);
		log.info("rawString = " + rawString);
		assertEquals(rawString, rawValueLong);
	}
}
