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
package com.hoovler.dao;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** The Class ProfileDaoSingleton. <p>This class provides all constant values and static methods required for populating an implementation of ProfileDao.</p> */
public class ProfileDaoSingleton {
	/** The log. */
	private static Logger log = LogManager.getLogger(ProfileDaoSingleton.class.getName());

	/** The Constant DEFAULT_SOURCE. */
	public static final String DEFAULT_SOURCE = "https://www.willowtreeapps.com/api/v1.0/profiles";

	/** The Constant PROPERTIES_FILENAME. */
	public static final String PROPERTIES_FILENAME = "profile_dao";

	/** The Constant PROP_jsonUrlDefault. */
	public static final String PROP_DEFAULT_URL_KEY = "json.url.default";

	/** The Constant PROP_sourceUrlKey. */
	public static final String PROP_sourceUrlKey = "json.url.default";

	/** The Constant PROP_KEY_SEP. */
	public static final String PROP_KEY_SEP = ".";

	/** The Constant PROP_LISTVAL_SEP. */
	public static final String PROP_LISTVAL_SEP = ",";
		/** The Constant URL_PROTOCOL. */
	public static final String URL_PROTOCOL = "http:";
		/** The Constant URL_PREFIX. */
	public static final String URL_PREFIX = "//";

	/** Reads the properties file, and gets all properties.
	 *
	 * @return The set of properties as a <code>ResourceBundle</code> object. */
	public static ResourceBundle getPropertiesBundle() {
		log.traceEntry();
		return ResourceBundle.getBundle(PROPERTIES_FILENAME);
	}

	/** Reads the properties file, and gets all properties.
	 *
	 * @return The set of properties as a <code>HashMap&lt;String, String&gt;</code> object. */
	public static HashMap<String, String> getPropertiesMap() {
		log.traceEntry();
		HashMap<String, String> propertiesMap = new HashMap<>();
		ResourceBundle rb = getPropertiesBundle();
		Enumeration<String> keys = rb.getKeys();
		String key = "";
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			propertiesMap.put(key, rb.getString(key));
		}
		return propertiesMap;
	}

	/** Reads the properties file, and gets only <code>json.attributes.*</code> properties.
	 *
	 * @param  propertyNamePrefix the property name prefix
	 * @return                    An <code>ArrayList</code> of the <code>json.attributes.*</code> values. */
	public static HashMap<String, String> getAttributesProperties(String propertyNamePrefix) {
		log.traceEntry();
		ResourceBundle rb = getPropertiesBundle();

		// use propPrefix to pull values from bundle
		HashMap<String, String> attributeProps = new HashMap<>();
		Enumeration<String> keys = rb.getKeys();
		String key = "";
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			if (StringUtils.startsWith(key, propertyNamePrefix)) {
				attributeProps.put(key, rb.getString(key));
			} // end if
		} // end while()
		return attributeProps;
	} // end getAttributeProperties{}

	/** Gets the prop string.
	 *
	 * @param  key the key
	 * @return     the prop string */
	public static String getPropString(String key) {
		return getPropertiesBundle().getString(key);
	}

	/** Instantiates a new profile dao singleton. */
	private ProfileDaoSingleton() {
		// no-op
	}
}
