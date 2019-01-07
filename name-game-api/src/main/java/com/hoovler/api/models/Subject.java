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

// TODO: Auto-generated Javadoc
/** <p><h3>Subject</h3> <p><b><u>Purpose</u></b></p> This class can be seen as a thin wrapper around <code>com.hoovler.dao.models.Profile, taking its own attributes from a subset of the Profile and Headshot objects.</code></p> <p><b><u>Information</u></b><br /> The <code>Subject</code> object is...</p> <p><b><u>Examples</u></b></p> An example: <pre>some code</pre> Another example: <pre>some more code;</pre> */
public class Subject {
	private String id;
	private String name;
	private String imageUrl;

	/** Gets Subject.id
	 *
	 * @return the id */
	public String getId() {
		return id;
	}

	/** Gets Subject.name
	 *
	 * @return the name */
	public String getName() {
		return name;
	}

	/** Gets Subject.imageUrl
	 *
	 * @return the image url */
	public String getImageUrl() {
		return imageUrl;
	}

	/** Sets Subject.id
	 *
	 * @param id the new id */
	public void setId(String id) {
		this.id = id;
	}

	/** Sets Subject.name
	 *
	 * @param name the new name */
	public void setName(String name) {
		this.name = name;
	}

	/** Sets Subject.imageUrl
	 *
	 * @param imageUrl the new image url */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/** Instantiates a new subject. */
	public Subject() {
		this.id = "";
		this.name = "";
		this.imageUrl = "";
	}

	/** Instantiates a new subject.
	 *
	 * @param id       the id
	 * @param name     the name
	 * @param imageUrl the image url */
	public Subject(String id, String name, String imageUrl) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}
}
