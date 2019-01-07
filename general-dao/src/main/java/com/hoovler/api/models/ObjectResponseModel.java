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
package com.hoovler.api.models;

/** A generic class to serve as the parent object for a response model of an API's, single-object returning endpoint. 
 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>*/
public class ObjectResponseModel {

	/** A placeholder for any message that should be sent with the reponse. */
	protected String message;

	/** Gets the message that was stored with this response.
	 * 
	 * <p><b>NOTE</b>: Not intended for use as the response content. An example use would be for returning a note to an endpoint consumer regarding why the expected object content is empty.
	 * 
	 * @return the message */
	public String getMessage() {
		return this.message;
	}

	/** Sets a message that should be sent with the response.
	 * 
	 * <p><b>NOTE</b>: Not intended for use as the response content. An example use would be for returning a note to an endpoint consumer regarding why the expected object content is empty.
	 *
	 * @param message the new message */
	public void setMessage(String message) {
		this.message = message;
	}
}
