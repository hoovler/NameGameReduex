/* 
 * Copyright (c) ${author} 2018 
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
package com.hoovler.resource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ProfileDaoGlobals {
	private static Logger log = LogManager.getLogger(ProfileDaoGlobals.class.getName());
	
	/** <p><i>STR_TYPE</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre> */
	public static final String STR_TYPE = "String";

	/** <p><i>INT_TYPE</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre> */
	public static final String INT_TYPE = "Integer";

	/** <p><i>DEFAULT_SOURCE</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre> */
	public static final String DEFAULT_SOURCE = "https://www.willowtreeapps.com/api/v1.0/profiles";

	/** <p><i>PROPERTIES_FILENAME</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre> */
	public static final String PROPERTIES_FILENAME = "profile_dao";

	/** <p><i>PROP_jsonUrlDefault</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre> */
	public static final String PROP_jsonUrlDefault = "json.url.default";

	/** <p><i>PROP_jsonAttributesRoot</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>*/
	public static final String PROP_jsonAttributesRoot = "json.attributes.root";
	
	/** <p><i>PROP_sourceUrlKey</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	public static final String PROP_sourceUrlKey = "json.url.default";

	// properties file special characters

	/** <p><i>PROP_KEY_SEP</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	public static final String PROP_KEY_SEP = ".";

	/** <p> {@value} </p> <p> The separation character for .properties file string values which should be treated as lists, such as: 
	 * </p><pre> prop.string.list = "item1,item2,item3" </pre>
	 * 
	 * Additionally, all whitespace is removed before any operations are performed on the string, so valid property values may also 
	 * contain spaces:
	 * <pre> prop.string.list = "item1 , item2 , item3" </pre> */
	public static final String PROP_LISTVAL_SEP = ",";
	
	public static void objectHash(Object obj) {
		log.info("Starting objHash: " + obj.hashCode());
		Method[] objMethods = obj.getClass().getMethods();
		
		for (Method method: objMethods) {
			if (StringUtils.startsWithIgnoreCase(method.getName(), "get")) {
				if (StringUtils.equalsIgnoreCase(method.getReturnType().getName(), "String")) {
					log.info("Found accessor that returns a string: " + method.getName());
				}
			}
		}
	}
	
	/**
	 * Reads the properties file, and gets all properties.
	 *
	 * @return The set of properties as a <code>ResourceBundle</code> object.
	 */
	public static ResourceBundle getPropertiesBundle() {
		log.traceEntry();
		return ResourceBundle.getBundle(PROPERTIES_FILENAME);
	}

	/**
	 * Reads the properties file, and gets all properties.
	 *
	 * @return The set of properties as a <code>HashMap&lt;String, String&gt;</code> object.
	 */
	public static HashMap<String, String> getPropertiesMap() {
		log.traceEntry();
		HashMap<String, String> propertiesMap = new HashMap<String, String>();
		ResourceBundle rb = getPropertiesBundle();
		Enumeration<String> keys = rb.getKeys();
		String key = "";
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			propertiesMap.put(key, rb.getString(key));
		}
		return propertiesMap;
	}

	/**
	 * Reads the properties file, and gets only <code>json.attributes.*</code> properties.
	 *
	 * @return An <code>ArrayList</code> of the <code>json.attributes.*</code> values.
	 */
	public static HashMap<String, String> getAttributesProperties() {
		log.traceEntry();
		return getAttributesProperties(
				StringUtils.substringBeforeLast(PROP_jsonAttributesRoot, PROP_KEY_SEP));
	} // end getAttributeProperties{}

	/**
	 * Reads the properties file, and gets only <code>json.attributes.*</code> properties.
	 *
	 * @return An <code>ArrayList</code> of the <code>json.attributes.*</code> values.
	 */
	public static HashMap<String, String> getAttributesProperties(String propertyNamePrefix) {
		log.traceEntry();
		ResourceBundle rb = getPropertiesBundle();

		// use propPrefix to pull values from bundle
		HashMap<String, String> attributeProps = new HashMap<String, String>();
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

	public static ArrayList<String> attributeObjectNames(String propertyNamePrefix) {
		log.traceEntry();
		ResourceBundle rb = getPropertiesBundle();

		ArrayList<String> attrObjects = new ArrayList<String>();
		Enumeration<String> keys = rb.getKeys();
		String key = "";
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			if (StringUtils.startsWith(key, propertyNamePrefix)) {
				attrObjects.add(StringUtils.substringAfterLast(key, PROP_KEY_SEP));
			} // end if
		} // end while()
		return attrObjects;
	}

	/**
	 * Converts a comma-delimited property value into a list of values.
	 *
	 * @param propName The key/name of the comma-delimited property value
	 * @return An <code>ArrayList&lt;String&gt;</code> containing the values of the <code>propName</code> property.
	 */
	public static ArrayList<String> propertyAsList(String propName) {
		log.traceEntry();
		String value = StringUtils.deleteWhitespace(getPropertiesBundle().getString(propName));
		return valueAsList(value);
	}

	/**
	 * Converts a comma-delimited String into a list of values.
	 *
	 * @param value the comma-delimited String to convert to list.
	 * @return an <code>ArrayList&lt;String&gt;</code> containing the values of the <code>propName</code> property.
	 */
	public static ArrayList<String> valueAsList(String value) {
		log.traceEntry();
		ArrayList<String> listOfVals = new ArrayList<String>();
		String[] arrayOfVals = StringUtils.split(value, PROP_LISTVAL_SEP);
		//(ArrayList<String>) Arrays.asList(StringUtils.split(value, Global.PROP_LISTVAL_SEP));
		for (String val : arrayOfVals) {
			listOfVals.add(val);
		}
		return listOfVals;
	}
	
	/**
	 * Gets the prop string.
	 *
	 * @param key the key
	 * @return the prop string
	 */
	public static String getPropString(String key) {
		return getPropertiesBundle().getString(key);
	}
	
	/**
	 * Gets the prop int.
	 *
	 * @param key the key
	 * @return the prop int
	 */
	public static Integer getPropInt(String key) {
		return Integer.getInteger(getPropertiesBundle().getString(key));
	}
}
