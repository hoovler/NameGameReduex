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
package com.hoovler.utils;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/** The Class Messages. */
public class Messages {
	private static final String BUNDLE_NAME = "messages"; // $NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/** * CONFIRMATIONS. */

	public static final String MSG_ADD_QUESTION = "info.add.question";

	// ********************** WARNINGS

	/** The Constant MSG_INVALID_ID. */
	public static final String MSG_ID_NOT_FOUND = "warn.id.not.found";

	public static final String MSG_ID_BLANK = "warn.id.blank";

	/** The Constant MSG_NONMATCHING_ID. */
	public static final String MSG_IDS_NON_MATCHING = "warn.id.matching.prefix";

	/** The Constant MSG_NOT_UPDATING. */
	public static final String MSG_NOT_UPDATING = "warn.update.suffix";

	// ********************** METHODS

	/** A simplified method for calling Messages.getString(String key)
	 *
	 * @param  key a key within the message bundle properties file for which the value is desired
	 * @return     the property value denoted by the key */
	public static String get(String key) {
		return getString(key);
	}

	/** Gets the string value of the given key from the properties bundle.
	 *
	 * @param  key a key within the message bundle properties file for which the value is desired
	 * @return     the property value denoted by the key */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/** Gets the properties.
	 *
	 * @return the properties */
	public static HashMap<String, String> getProperties() {
		// new map that will contain the key/value pairs from the properties file
		HashMap<String, String> properties = new HashMap<>();

		for (String key : RESOURCE_BUNDLE.keySet()) {
			properties.put(key, RESOURCE_BUNDLE.getString(key));
		}
		return properties;
	}

	private Messages() {
		// no-op
	}
}
