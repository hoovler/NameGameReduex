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

/** <p> <h3>Headshot</h3> </p> <p> <b><u>Purpose</u></b> </p> This Class contains all portrait-related information for a Profile. <p> <b><u>Information</u></b> </p> <p> The <code>Headshot</code> object is nested within <code>Profile</code> as a member variable, and must be accessed as such. </p> */
public class Headshot {
	/** The type. */
	private String type;

	/** The mime type. */
	private String mimeType;

	/** The id. */
	private String id;

	/** The url. */
	private String url;

	/** The alt. */
	private String alt;

	/** The height. */
	private Integer height;

	/** The width. */
	private Integer width;

	/** Gets the type.
	 *
	 * @return the type */
	public String getType() {
		return this.type;
	}

	/** Gets the mime type.
	 *
	 * @return the mime type */
	public String getMimeType() {
		return this.mimeType;
	}

	/** Gets the id.
	 *
	 * @return the id */
	public String getId() {
		return this.id;
	}

	/** Gets the url.
	 *
	 * @return the url */
	public String getUrl() {
		return this.url;
	}

	/** Gets the alt.
	 *
	 * @return the alt */
	public String getAlt() {
		return this.alt;
	}

	/** Gets the height.
	 *
	 * @return the height */
	public Integer getHeight() {
		return this.height;
	}

	/** Gets the width.
	 *
	 * @return the width */
	public Integer getWidth() {
		return this.width;
	}

	/** Sets the type.
	 *
	 * @param type the new type */
	public void setType(String type) {
		this.type = type;
	}

	/** Sets the mime type.
	 *
	 * @param mimeType the new mime type */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/** Sets the id.
	 *
	 * @param id the new id */
	public void setId(String id) {
		this.id = id;
	}

	/** Ensures the given url begins with the given protocol.
	 * <p>This method assumes the <code>url</code> is passed to the method in either of the following states:</p>
	 * <ul><li>Starting with a set of double forward slashes: <code>//some.domain.com</code></li><li>Starting with the passed protocol: <code>http://some.domain.com</code></li></ul>
	 * <p>If the former, the <code>protocol</code> is added as the prefix to the <code>url</code>, while the latter results in no change.</p>
	 * <p><b>Note:</b><em> This method will not validate the </em><code>url</code>.</p>
	 * <p>If the <code>url</code> is already prefixed a value <b>other than</b> "//" <b>or</b> the <code>protocol</code> passed in, then the result will be a malformed Headshot.url</p>
	 *
	 * @param url      the url prefixed by "//" or the <code>protocol</code>
	 * @param protocol the protocol */
	public void setUrl(String url, String protocol) {
		// ensure the url begins with the proper protocol
		if (!StringUtils.startsWithIgnoreCase(url, protocol)) {
			url = protocol + url;
		}
		setUrl(url);
	}

	/** Sets Headshot.url
	 *
	 * @param url the new url */
	public void setUrl(String url) {
		this.url = url;
	}

	/** Sets the alt.
	 *
	 * @param alt the new alt */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/** Sets the height.
	 *
	 * @param height the new height */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/** Sets the width.
	 *
	 * @param width the new width */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/** Instantiates a new headshot. */
	public Headshot() {
	}

	/** Instantiates a new headshot.
	 *
	 * @param id the id */
	public Headshot(String id) {
		this.id = id;
	}

	/** Instantiates a new headshot.
	 *
	 * @param type     the type
	 * @param mimeType the mime type
	 * @param id       the id
	 * @param url      the url
	 * @param alt      the alt
	 * @param height   the height
	 * @param width    the width */
	public Headshot(String type, String mimeType, String id, String url, String alt, Integer height, Integer width) {
		this.type = type;
		this.mimeType = mimeType;
		this.id = id;
		this.url = url;
		this.alt = alt;
		this.height = height;
		this.width = width;
	}
}
