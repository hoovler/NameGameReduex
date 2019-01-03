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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoovler.utils.impl.BoolUtils;


class BoolUtilsTests {
	private static Logger log = LogManager.getLogger(BoolUtilsTests.class.getName());

	private static boolean booltype;
	private static Boolean boolType;
	
	private static short shorttype;
	private static Short shortType;
	
	private static int inttype;
	private static Integer intType;
	
	private static double doubtype;
	private static Double doubType;
	
	private static long longtype;
	private static Long longType;
	
	private static char chartype;
	private static Character charType;
	
	private static String stringType;
	
	private static BigInteger bigInt;
	
	private static BigDecimal bigDec;

	private static int[] trueInts = {1, 100, 1000};
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		booltype = true;
		boolType = booltype;
		
		inttype = 1;
		intType = inttype;
		doubtype = 1.0;
		doubType = doubtype;
		longtype = 1;
		longType = longtype;
		
		chartype = 'y';
		charType = chartype;
		stringType = "TRUE";
		
		bigInt = BigInteger.ONE;
		bigDec = BigDecimal.ONE;
		
		Field[] fields = BoolUtilsTests.class.getDeclaredFields();
		for (Field field : fields) {
			log.info("field = " + field.getName() + "; type = " + field.getType());
		}
		
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
	void testNumericStandardsTrue() {
		shorttype = 1;
		assertTrue(BoolUtils.parseBool(shorttype));
		
		shortType = 1;
		assertTrue(BoolUtils.parseBool(shortType));
		
		inttype = 1;
		assertTrue(BoolUtils.parseBool(inttype));
		
		intType = inttype;
		assertTrue(BoolUtils.parseBool(intType));
		
		doubtype = 1.0;
		assertTrue(BoolUtils.parseBool(doubtype));
		
		doubType = doubtype;
		assertTrue(BoolUtils.parseBool(doubType));
		
		longtype = 1;
		assertTrue(BoolUtils.parseBool(longtype));
		
		longType = longtype;
		assertTrue(BoolUtils.parseBool(longType));
	}
	
	@Test
	void testNumericStandardsFalse() {
		shorttype = -1;
		assertTrue(!BoolUtils.parseBool(shorttype));
		
		shortType = -1;
		assertTrue(!BoolUtils.parseBool(shortType));
		
		inttype = -1;
		assertTrue(!BoolUtils.parseBool(inttype));
		
		intType = inttype;
		assertTrue(!BoolUtils.parseBool(intType));
		
		doubtype = -1.0;
		assertTrue(!BoolUtils.parseBool(doubtype));
		
		doubType = doubtype;
		assertTrue(!BoolUtils.parseBool(doubType));
		
		longtype = -1;
		assertTrue(!BoolUtils.parseBool(longtype));
		
		longType = longtype;
		assertTrue(!BoolUtils.parseBool(longType));
	}

	@Test
	void testAlphaStandardsTrue() {
		
		// common true characters
		chartype = 'y';
		assertTrue(!BoolUtils.parseBool(longType));
		
		charType = 'Y';
		
		charType = 't';
		
		charType = 'T';
		
		charType = '1';
	
		// common true strings
		stringType = "y";
		
		stringType = "Y";
		
		stringType = "t";
		
		stringType = "T";
		
		stringType = "1";
		
		stringType = "1.0";
		
		stringType = "yes";
		
		stringType = "YES";
		
		
		
		
		
		
	}
	
	
	
	@Test
	void testNumericTrue() {
		shorttype = 30;
		BoolUtils.parseBool(inttype);
		
		shortType = 31;
		BoolUtils.parseBool(shortType);
		
		inttype = 32;
		BoolUtils.parseBool(inttype);
		
		intType = inttype;
		BoolUtils.parseBool(intType);
		
		doubtype = 33.3223667;
		BoolUtils.parseBool(doubtype);
		
		doubType = doubtype;
		BoolUtils.parseBool(doubType);
		
		longtype = 343434343;
		BoolUtils.parseBool(longtype);
		
		longType = longtype;
		BoolUtils.parseBool(longType);
	}
	
	@Test
	void testNumericFalse() {
		inttype = 1;
		BoolUtils.parseBool(inttype);
		
		doubtype = 1.0;
	}
	

	
	@Test
	void testAlphaFalse() {
		inttype = 1;
		BoolUtils.parseBool(inttype);
		
		doubtype = 1.0;
	}
	
	@Test
	void test() {
		BoolUtils.parseBool(booltype);
		BoolUtils.parseBool(boolType);
		
		BoolUtils.parseBool(inttype);
		BoolUtils.parseBool(intType);
		
		BoolUtils.parseBool(doubtype);
		BoolUtils.parseBool(doubType);
		
		BoolUtils.parseBool(longtype);
		BoolUtils.parseBool(longType);
		
		BoolUtils.parseBool(chartype);
		BoolUtils.parseBool(charType);
		BoolUtils.parseBool(stringType);
		
		BoolUtils.parseBool(bigInt);
		BoolUtils.parseBool(bigDec);
		
		booltype = false;
		boolType = booltype;
		inttype = -44;
		intType = inttype;
		doubtype = -2.99217711233;
		doubType = doubtype;
		longtype = 23445;
		longType = longtype;
		
		chartype = '%';
		charType = chartype;
		stringType = "This is anything other than a boolean value.";
		
		bigInt = BigInteger.valueOf(longtype);
		bigDec = BigDecimal.valueOf(doubtype);

		BoolUtils.parseBool(booltype);
		BoolUtils.parseBool(boolType);
		
		BoolUtils.parseBool(inttype);
		BoolUtils.parseBool(intType);
		
		BoolUtils.parseBool(doubtype);
		BoolUtils.parseBool(doubType);
		
		BoolUtils.parseBool(longtype);
		BoolUtils.parseBool(longType);
		
		BoolUtils.parseBool(chartype);
		BoolUtils.parseBool(charType);
		BoolUtils.parseBool(stringType);
		
		BoolUtils.parseBool(bigInt);
		BoolUtils.parseBool(bigDec);
	}
}
