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

	protected static ArrayList<String> knownTypes;
	
	private static final String tBool = "boolean";
	private static final String tShort = "short";
	private static final String tInt = "integer";
	private static final String tDoub = "double";
	private static final String tLong = "long";
	private static final String tBigInt = "biginteger";
	private static final String tBigDec = "bigdecimal";
	private static final String tChar = "character";
	private static final String tString = "string";

	/** Any value at or below this point is interpreted as FALSE, while any value above it is TRUE */
	public static final int MAX_FALSE_BOOL = 0;

	/** Parses the boolean.
	 *
	 * @param        <T> the generic type
	 * @param  value the value
	 * @return       true, if successful */
	public static <T> boolean parseBool(T value) {
		String type = value.getClass().getSimpleName();

		log.info("Parsing boolean...");
		log.info("... type = " + type);
		log.info("... value = " + value.toString());

		return isTrue(value);
	}
	
	public static <T> Integer parseInt(T object) {
		if (isCommonType(object) && isNumeric(object)) {
			String classType = object.getClass().getSimpleName().toLowerCase();
			
			switch (classType) {
			case tBool:
				return (Boolean) object ? 1 : 0;
			case tShort:
				
			case tInt:
			case tDoub:
			case tLong:
			case tBigInt:
			case tBigDec:
			case tChar:
			case tString:
			default:
				return null;
			} // end case
		}
		return null;
	}
	
	public static <T> Double parseDouble(T object) {
		if (isCommonType(object) && isNumeric(object)) {
			return 1.0;
		}
		return null;
	}
	
	public static <T> Long parseLong(T object) {
		if (isCommonType(object) && isNumeric(object)) {
			return Long.parseLong("1");
		}
		
		return null;
	}

	/** Checks if is true.
	 *
	 * @param        <T> the generic type
	 * @param  value the value
	 * @return       true, if is true */
	private static <T> boolean isTrue(T value) {

		if (isCommonType(value)) {
			String classType = value.getClass().getSimpleName().toLowerCase();

			switch (classType) {
			case tBool:
				return (Boolean) value;
			case tShort:
				return Math.signum((Short) value) == 1;
			case tInt:
				return ((Integer) value > 0);
			case tDoub:
				return ((Double) value > 0);
			case tLong:
				return (Long) value > 0;
			case tBigInt:
				BigInteger biVal = (BigInteger) value;
				return biVal.signum() == 1;
			case tBigDec:
				BigDecimal bdVal = (BigDecimal) value;
				return bdVal.signum() == 1;
			case tChar:
				Character cVal = (Character) value;
				cVal = Character.toLowerCase(cVal);
				if(isNumeric(cVal)) {
					return parseInt(tChar) > 0;
				}
				return cVal.equals('t') || cVal.equals('y') || cVal.equals('k') || cVal.equals('+');
			case tString:
				String strVal = (String) value;
				if (StringUtils.isNumeric(strVal)) {
					return isTrue(Integer.parseInt(strVal));
				} else {
					String unsigned = StringUtils.removeStart(StringUtils.removeStart(strVal, "+"), "-");
					String checkDouble;
					if (StringUtils.isNumericSpace(unsigned)) {
						return isTrue(Integer.parseInt(strVal));
					}  else {
						checkDouble = StringUtils.remove(unsigned, '.');
						if (StringUtils.isNumericSpace(checkDouble)) {
							return isTrue(Double.parseDouble(strVal));	
						}
					}
				}
				strVal = StringUtils.deleteWhitespace(strVal.toLowerCase());
				return StringUtils.equalsAnyIgnoreCase(strVal, "1", "t", "true", "y", "yes", "ya", "sure", "ok",
						"affirmative");

			default:
				return false;
			} // end case
		} // end if
		return false;
	}
	

	/** Checks if is number.
	 *
	 * @param        <T> the generic type
	 * @param  value the value
	 * @return       true, if is number */
	private static <T> boolean isNumber(T value) {
		if (isCommonType(value)) {
			String classType = value.getClass().getSimpleName().toLowerCase();
			
			int minNum = '0';
			int maxNum = '9';
			
			switch (classType) {
			case tBool:
			case tShort:
			case tInt:
			case tDoub:
			case tLong:
			case tBigInt:
			case tBigDec:
				return true;
			case tChar:
				int cVal = (Character) value;
				return cVal >= minNum && cVal <= maxNum;
			case tString:
				String sVal = (String) value;
				if (StringUtils.isNumeric(sVal)) {
					return true;
				} else {
					String stripped = StringUtils.deleteWhitespace(StringUtils.replaceChars(sVal, "-.", ""));
					return StringUtils.isNumeric(stripped);
				}
			default:
				return false;
			} // end case
		} // end if
		return false;
	}
	
	/** Checks if is numeric.
	 *
	 * @param        <T> the generic type
	 * @param  value the value
	 * @return       true, if is numeric */
	public static <T> boolean isNumeric(T value) {
		String type = value.getClass().getSimpleName();

		log.info("Checking numeric...");
		log.info("... type = " + type);
		log.info("... value = " + value.toString());
		
		return isNumber(value);
	}

	/** Checks if is boolable.
	 *
	 * @param        <T> the generic type
	 * @param  value the value
	 * @return       true, if is boolable */
	public static <T> boolean isCommonType(T value) {
		return boolable().contains(value.getClass().getSimpleName());
	}
	
	/** Get a string list of types which are easily cast to a boolean.
	 *
	 * @return An <code>ArrayList&lt;String&gt;</code> of simple class names for types which can be easily parsed into a boolean value */
	private static ArrayList<String> boolable() {

		ArrayList<String> boolable = new ArrayList<>();

		boolable.add(Boolean.class.getSimpleName());
		boolable.add(Short.class.getSimpleName());
		boolable.add(Integer.class.getSimpleName());
		boolable.add(Double.class.getSimpleName());
		boolable.add(Long.class.getSimpleName());
		boolable.add(Character.class.getSimpleName());
		boolable.add(String.class.getSimpleName());
		boolable.add(BigInteger.class.getSimpleName());
		boolable.add(BigDecimal.class.getSimpleName());

		return boolable;
	}
}
