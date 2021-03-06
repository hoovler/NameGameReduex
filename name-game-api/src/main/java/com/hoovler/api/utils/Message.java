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
	INFO_ASK_PARAMS, INFO_ANSWER_PARAMS,

	MSG_ID_BLANK, MSG_ID_NOT_FOUND, MSG_IDS_NON_MATCHING, MSG_NOT_UPDATING, MSG_ADD_QUESTION,

	MSG_Q_ALREADY_ANSWERED, MSG_Q_NOT_EXISTS, MSG_Q_NOT_ASKED, MSG_CORRECT_ANSWER, MSG_INCORRECT_ANSWER,

	MSG_INVALID_ARG, MSG_NOT_IMPL, MSG_INVALID_ANSWER_BODY, MSG_ENCOURAGE,

	WARN_JSON_PARSE, WARN_JSON_SYNTAX, WARN_JSON_TYPE_UNEXPECTED, WARN_JSON_ERROR, WARN_JSON_SUFFIX;

	private String value;

	private String bundleName = GameHelper.MSG_RESOURCE_NAME; // $NON-NLS-1$
	private ResourceBundle messageBundle;
	

	/** Gets Messages.value
	 *
	 * @return the value */
	public String s() {
		if (this.value == null) {
			init();
		}

		return this.value;
	}

	/** Inits the. */
	private void init() {
		try {

			if (this.messageBundle == null) {
				this.messageBundle = ResourceBundle.getBundle(bundleName);
			}
			this.value = messageBundle.getString(this.toString());

		} catch (Exception e) {
			e.printStackTrace();
			this.value = GameHelper.WRN_MISSING_RESOURCE + GameHelper.MSG_RESOURCE_NAME;
		}
	}

}
