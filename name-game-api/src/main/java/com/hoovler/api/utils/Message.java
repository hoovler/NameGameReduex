/*
 * Copyright (c) ${author} 2019
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

import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/** Enum Messages
 * 
 * <p>This class acts as a quick-access utility to /resources/messages.properties. More specifically, it's an API that allows other classes to access the values within that properties file's name/value pairs by invoking the property's name. */
public enum Message {

	/** The info app init. */
	INFO_APP_INIT,
	/** The info msg set. */
	INFO_ASK_PARAMS,
	INFO_ANSWER_PARAMS;

	private String value;

	private String bundleName = "messages"; // $NON-NLS-1$
	private ResourceBundle messageBundle;

	/** Gets Messages.value
	 *
	 * @return the value */
	public String getValue() {
		if (this.value == null) {
			init();
		}

		return this.value;
	}

	/** Inits the. */
	private void init() {
		if (this.messageBundle == null) {
			this.messageBundle = ResourceBundle.getBundle(bundleName);
		}
		this.value = messageBundle.getString(this.toString());
	}

}
