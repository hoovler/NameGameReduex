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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoovler.utils.impl.BoolUtils;

class BoolUtilsTests {
	private static Logger log = LogManager.getLogger(BoolUtilsTests.class.getName());

	// testing primitive values
	private static short[] shortPrimitives = { 1, 100, 1000 };
	private static int[] intPrimitives = { 1, 100, 1000 };
	private static double[] doublePrimitives = { 1.00, 100.00, 1000.00 };
	private static char[] charPrimitives = { '1', 'y', 'Y', 't', 'T' };

	// and non-primitive values
	private static Short[] shortObjects;
	private static Integer[] intObjects;
	private static Double[] doubleObjects;
	private static Character[] charObjects;

	// all of the primitive array values are cast as strings and added here, as well
	private static String[] trueStringStandards = { "yes", "true", "ok" };

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		buildArrays();
	}

	@BeforeEach
	void setUp() throws Exception {
		buildArrays();
	}

	@Test
	void testTypes() {
		
		short sVal = 1;
		assertTrue(BoolUtils.parseBool(sVal));
		Short SVal = sVal;
		assertTrue(BoolUtils.parseBool(SVal));
		
		int iVal = 1;
		assertTrue(BoolUtils.parseBool(iVal));
		Integer IVal = iVal;
		assertTrue(BoolUtils.parseBool(IVal));

		double dVal = 1;
		assertTrue(BoolUtils.parseBool(dVal));
		Double DVal = dVal;
		assertTrue(BoolUtils.parseBool(DVal));
		
		long lVal = 1;
		assertTrue(BoolUtils.parseBool(lVal));
		Long LVal = lVal;
		assertTrue(BoolUtils.parseBool(LVal));
		
		char cVal = 'y';
		assertTrue(BoolUtils.parseBool(cVal));
		Character CVal = cVal;
		assertTrue(BoolUtils.parseBool(CVal));

		String stVal = "yes";
		assertTrue(BoolUtils.parseBool(stVal));
	}
	
	@Test
	void testTrueStrings() {
		for (String string : trueStringStandards) {
			assertTrue(BoolUtils.parseBool(string));
		}
	}

	private static void buildArrays() {

		// set all non-primitive arrays
		shortObjects = ArrayUtils.toObject(shortPrimitives);
		intObjects = ArrayUtils.toObject(intPrimitives);
		doubleObjects = ArrayUtils.toObject(doublePrimitives);
		charObjects = ArrayUtils.toObject(charPrimitives);

		// get temp string values for each array
		String[] shortTrueString = ArrayUtils.toStringArray(shortObjects);
		String[] intTrueString = ArrayUtils.toStringArray(intObjects);
		String[] doubTrueString = ArrayUtils.toStringArray(doubleObjects);
		String[] charTrueString = ArrayUtils.toStringArray(charObjects);

		// add all other values to the string array
		trueStringStandards = ArrayUtils.addAll(trueStringStandards, shortTrueString);
		trueStringStandards = ArrayUtils.addAll(trueStringStandards, intTrueString);
		trueStringStandards = ArrayUtils.addAll(trueStringStandards, doubTrueString);
		trueStringStandards = ArrayUtils.addAll(trueStringStandards, charTrueString);

		for (String string : trueStringStandards) {
			log.info("True value: " + string);
		}
	}
}
