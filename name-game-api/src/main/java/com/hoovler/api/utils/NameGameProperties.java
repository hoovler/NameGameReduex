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
package com.hoovler.api.utils;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * <p><h3>NameGameProperties</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>NameGameProperties</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * An example:
 * <pre>some code</pre>
 * Another example:
 * <pre>some more code;</pre>
 */
public class NameGameProperties {
	
	public static final String[] paramNames = new String[] 
			{"param.email.name", "param.mode.name", "param.matts.name"};
	
	public static final String[] paramDefauls = new String[]
			{"param.email.default","param.mode.default","param.matts.default"};
	
	/** <p><i>BUNDLE_NAME</i> = <u>{@value}</u></p> <p>The case-sensitive name of the project's configuration file.</p>
	 * <p><u><b>Requirements</b></u></p>
	 * <ul>
	 * 	<li>The file <em>must</em> be within the folder <em>/${projectRoot}/resources</li>
	 * 	<li>The file <em>must</em> have the filetype suffix of <em>.properties</em></li>
	 * 	<li>The file <em>must not</em> have the same name as the Spring Boot configuration file, 
	 * 		<em>application.properties</em>, unless that is the file to which access is required.
	 *  */
	private static final String BUNDLE_NAME = "namegame"; //$NON-NLS-1$

	/** <p><i>RESOURCE_BUNDLE</i> = <u>{@value}</u></p> <p>The [value description]</p> <pre>some example use</pre>. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * Gets the string.
	 *
	 * @param key the key
	 * @return the string
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public static HashMap<String, String> getProperties() {
		// new map that will contain the key/value pairs from the properties file
		HashMap<String, String> properties = new HashMap<>();

		for (String key : RESOURCE_BUNDLE.keySet()) {
			properties.put(key, RESOURCE_BUNDLE.getString(key));
		}
		return properties;
	}
	
	/**
	 * Instantiates a new name game properties.
	 */
	private NameGameProperties() {}
}
