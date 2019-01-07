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
package com.hoovler.utils.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** The SecurityUtils class contains security-related and statically-available methods and constant
 * values. */
public class SecurityUtils {
	private static Logger log = LogManager.getLogger(SecurityUtils.class.getName());

	/** Convert a string from its raw, decimal ascii form into a hexadecimal string representation.
	 *
	 * @param  raw the raw string value in decimal ascii
	 * @return     the hexadecimal string representation of the passed-in string */
	public static String toHex(String raw) {
		// return an empty string if raw is empty
		if (StringUtils.isBlank(raw)) return StringUtils.EMPTY;
		
		char[] chars = raw.toCharArray();
		StringBuilder hex = new StringBuilder(StringUtils.EMPTY);
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString(chars[i]));
		}
		log.debug("Returning hex value of " + raw + ": " + hex.toString());
		return hex.toString();
	}

	/** Convert a hexadecimal string to its raw, decimal ascii form.
	 *
	 * @param  hex the hexadecimal string
	 * @return     the String in its original, raw, decimal ascii form */
	public static String fromHex(String hex) {
		// return an empty string if hex is empty
		if (StringUtils.isBlank(hex)) return StringUtils.EMPTY;
		
		StringBuilder raw = new StringBuilder(StringUtils.EMPTY);
		for (int i = 0; i < hex.length(); i += 2) {
			String str = hex.substring(i, i + 2);
			raw.append((char) Integer.parseInt(str, 16));
		}
		log.debug("Returning raw value of " + hex + ": " + raw.toString());
		return raw.toString();
	}

	private SecurityUtils() {
		// no-op
	}
}
