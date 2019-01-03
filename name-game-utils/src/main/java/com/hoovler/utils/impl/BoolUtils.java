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
package com.hoovler.utils.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** PrimitiveUtils provides utilities for primitive types - and their non-primitive wrappers (such as int & Integer) - which are not otherwise provided in the wrapper classes. */
public class BoolUtils {
	private static Logger log = LogManager.getLogger(BoolUtils.class.getName());

	/** <code>String[] BOOL_TRUE = { "1", "yes", "y", "true", "t" }</code>
	 * <p>The values representing a strict TRUE boolean. As used in this class, the alpha-only strings are treated as case-insensitive, and the numeric-only are treated as any type of the same value.</p>
	 * For example: <pre>"1" && "1.0" && "Yes" && "T" == true;</pre> */
	public static final String[] BOOL_TRUE = new String[] { "1", "yes", "y", "true", "t" };

	/** <code>String[] BOOL_FALSE = { "0", "no", "n", "false", "f"}</code>
	 * <p>The values representing a strict FALSE boolean. As used in this class, the alpha-only strings are treated as case-insensitive, and the numeric-only are treated as any type of the same value.</p>
	 * For example: <pre>"0" && "0.0" && "No" && "T" == true;</pre> */
	//public static final String[] BOOL_FALSE = new String[] { "0", "no", "n", "false", "f" };

	
	protected static ArrayList<String> knownTypes;

	
	/** Any value at or below this point is interpreted as FALSE, while any value above it is TRUE */
	public static final int MAX_FALSE_BOOL = 0;

	/**
	 * Parses the boolean.
	 *
	 * @param <T> the generic type
	 * @param value the value
	 * @return true, if successful
	 */
	public static <T> boolean parseBool(T value) {
		
		
		
		String type = value.getClass().getSimpleName();
		
		log.info("parsing boolean from a " + type);
		log.info("... type = " + type);
		log.info("... value = " + value.toString());

		boolean isTrue = isTrue(value);
		
		log.info("... boolean = " + isTrue);

		return isTrue;
	}
	
	/**
	 * Checks if is boolable.
	 *
	 * @param <T> the generic type
	 * @param value the value
	 * @return true, if is boolable
	 */
	public static <T> boolean isBoolable(T value) {
		return boolable().contains(value.getClass().getSimpleName());
	}
	
	/**
	 * Get a string list of types which are easily cast to a boolean.
	 *
	 * @return An <code>ArrayList&lt;String&gt;</code> of simple class names for types which can be easily parsed into a boolean value 
	 */
	public static ArrayList<String> boolable(){
		
		ArrayList<String> boolable = new ArrayList<>();

		boolable.add(Boolean.class.getSimpleName());
		boolable.add(Integer.class.getSimpleName());
		boolable.add(Double.class.getSimpleName());
		boolable.add(Long.class.getSimpleName());
		boolable.add(Character.class.getSimpleName());
		boolable.add(String.class.getSimpleName());
		boolable.add(BigInteger.class.getSimpleName());
		boolable.add(BigDecimal.class.getSimpleName());
		
		return boolable;
	}
	
	/**
	 * Checks if is true.
	 *
	 * @param <T> the generic type
	 * @param value the value
	 * @return true, if is true
	 */
	public static <T> boolean isTrue(T value) {
		
		if (isBoolable(value)) {
			String classType = value.getClass().getSimpleName().toLowerCase();
			
			switch (classType) {
			case "boolean":
				boolean bVal = (Boolean) value;
				return bVal;
			case "integer":
				int iVal = (Integer) value;
				return (iVal > 0);
			case "double":
				double dVal = (Double) value;
				return (dVal > 0);
			case "long":
				long lVal = (Long) value;
				return lVal > 0;
			case "character":
				Character cVal = (Character) value;
				cVal = Character.toLowerCase(cVal);
				return cVal.equals('t') || cVal.equals('y');
			case "string":
				String sVal = (String) value;
				sVal = StringUtils.deleteWhitespace(sVal.toLowerCase());
				return StringUtils.equalsAnyIgnoreCase(sVal, "1", "t", "true", "y", "yes", "ya", "sure", "ok", "affirmative");
			case "biginteger":
				BigInteger biVal = (BigInteger) value;
				return biVal.signum() == 1;
			case "bigdecimal":
				BigDecimal bdVal = (BigDecimal) value;
				return bdVal.signum() == 1;
			default:
				return false;
			} // end case
		} // end if
		return false;
	}
}
