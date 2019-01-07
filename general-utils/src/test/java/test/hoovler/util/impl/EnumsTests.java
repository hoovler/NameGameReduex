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

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoovler.utils.enums.SimpleType;

class EnumsTests {
	private static Logger log = LogManager.getLogger(EnumsTests.class.getName());

	private static final boolean dummyBool = true;
	private static final short dummyShort = 1;
	private static final int dummyInt = 1;
	private static final double dummyDoub = 1.0;
	private static final long dummyLong = 1;
	private static final char dummyChar = 'c';
	private static final String dummyString = "String";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	void test() {
		assertAllSame(dummyBool, SimpleType.BOOLEAN);
		assertAllSame(dummyShort, SimpleType.SHORT);
		assertAllSame(dummyInt, SimpleType.INTEGER);
		assertAllSame(dummyDoub, SimpleType.DOUBLE);
		assertAllSame(dummyLong, SimpleType.LONG);
		assertAllSame(dummyChar, SimpleType.CHARACTER);
		assertAllSame(dummyString, SimpleType.STRING);
	}
	
	/**
	 * Assert all same.
	 *
	 * @param <T> the generic type
	 * @param dummy the dummy
	 * @param type the type
	 */
	private <T> void assertAllSame(T dummy, SimpleType type){
		String simpleName = dummy.getClass().getSimpleName();
		String typeSimpleName = type.val();
		
		String canonicalName = dummy.getClass().getCanonicalName();
		String typeCanonicalName = type.canonical();
		
		log.info("simpleName = " + simpleName + "; canonicalName = " + canonicalName);
		log.info("typeSimpleName = " + typeSimpleName + "; typeCanonicalName = " + typeCanonicalName);
		
		assertTrue(StringUtils.equals(simpleName, typeSimpleName));
		assertTrue(StringUtils.equals(canonicalName, typeCanonicalName));
	}
}
