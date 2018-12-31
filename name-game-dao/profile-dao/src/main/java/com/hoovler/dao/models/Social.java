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
package com.hoovler.dao.models;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/** The Class Social. */
public class Social {

	/** The type. */
	private String type;

	/** The action call. */
	private String actionCall;

	/** The url. */
	private String url;

	/** Gets Social.type
	 *
	 * @return the type */
	public String getType() {
		return type;
	}

	/** Gets Social.actionCall
	 *
	 * @return the action call */
	public String getActionCall() {
		return actionCall;
	}

	/** Gets Social.url
	 *
	 * @return the url */
	public String getUrl() {
		return url;
	}

	/** Sets Social.type
	 *
	 * @param type the new type */
	public void setType(String type) {
		this.type = type;
	}

	/** Sets Social.actionCall
	 *
	 * @param actionCall the new action call */
	public void setActionCall(String actionCall) {
		this.actionCall = actionCall;
	}

	/** Sets Social.url
	 *
	 * @param url the new url */
	public void setUrl(String url) {
		this.url = url;
	}

	/** Instantiates a new social. */
	public Social() {
		this.type = StringUtils.EMPTY;
		this.actionCall = StringUtils.EMPTY;
		this.url = StringUtils.EMPTY;
	}

	/** Instantiates a new social.
	 *
	 * @param type       the type
	 * @param actionCall the action call
	 * @param url        the url */
	public Social(String type, String actionCall, String url) {
		this.type = type;
		this.actionCall = actionCall;
		this.url = url;
	}

}
