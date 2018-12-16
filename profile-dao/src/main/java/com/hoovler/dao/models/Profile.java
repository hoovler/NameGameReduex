/* 
 * Copyright (c) Michael Hoovler 2018 
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

import java.util.ArrayList;

/**
 * <p>
 * <h1>The Profile Class</h1><br />
 * <em>Short description of Profile...</em>
 * </p>
 * <p>
 * <b><u>Purpose</u></b><br />
 * Purpose text
 * </p>
 * <p>
 * <b><u>Usage</u></b><br />
 * Usage scenarios...
 * </p>
 * <p>
 * <b><u>Examples</u></b><br />
 * An example:<br />
 * <code>some code</code><br />
 * Another example:<br />
 * <code>some more code;</code>
 * </p>
 * <br />
 */
public class Profile {
	/** The id attribute of the ProfileEntry object. */
	private String id;

	/**
	 * The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>type</i></b>
	 * </pre>
	 */
	private String type;

	/**
	 * The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>slug</i></b>
	 * </pre>
	 */
	private String slug;

	/**
	 * The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>jobTitle</i></b>
	 * </pre>
	 */
	private String jobTitle;

	/**
	 * The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>firstName</i></b>
	 * </pre>
	 */
	private String firstName;

	/**
	 * The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>lastName</i></b>
	 * </pre>
	 */
	private String lastName;

	/**
	 * The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>bio</i></b>
	 * </pre>
	 */
	private String bio;

	/**
	 * The object containing portrait information, found in:
	 * 
	 * <pre>
	 * rootnode.<b><i>headshot</i></b>
	 * </pre>
	 */
	private Headshot headshot;

	/**
	 * A list of tuples grouped by social media type, found in:
	 * 
	 * <pre>
	 * rootnode.<b><i>socialLinks</i></b>
	 * </pre>
	 */
	private ArrayList<Social> social;

	/**
	 * Gets the id attribute of the ProfileEntry object.
	 *
	 * @return the id attribute of the ProfileEntry object
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Gets the value of:
	 * 
	 * <pre>
	 * rootnode.
	 *
	 * @return the value of:
	 * 
	 *         <pre>
	 *         rootnode
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Gets the value of:
	 * 
	 * <pre>
	 * rootnode.
	 *
	 * @return the value of:
	 * 
	 *         <pre>
	 *         rootnode
	 */
	public String getSlug() {
		return this.slug;
	}

	/**
	 * Gets the value of:
	 * 
	 * <pre>
	 * rootnode.
	 *
	 * @return the value of:
	 * 
	 *         <pre>
	 *         rootnode
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * Gets the value of:
	 * 
	 * <pre>
	 * rootnode.
	 *
	 * @return the value of:
	 * 
	 *         <pre>
	 *         rootnode
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Gets the value of:
	 * 
	 * <pre>
	 * rootnode.
	 *
	 * @return the value of:
	 * 
	 *         <pre>
	 *         rootnode
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Gets the value of:
	 * 
	 * <pre>
	 * rootnode.
	 *
	 * @return the value of:
	 * 
	 *         <pre>
	 *         rootnode
	 */
	public String getBio() {
		return this.bio;
	}

	/**
	 * Gets the object containing portrait information, found in:
	 * 
	 * <pre>
	 * rootnode.
	 *
	 * @return the object containing portrait information, found in:
	 * 
	 *         <pre>
	 *         rootnode
	 */
	public Headshot getHeadshot() {
		return this.headshot;
	}

	/**
	 * Gets the a list of tuples grouped by social media type, found in:
	 * 
	 * <pre>
	 * rootnode.
	 *
	 * @return the a list of tuples grouped by social media type, found in:
	 * 
	 *         <pre>
	 *         rootnode
	 */
	public ArrayList<Social> getSocial() {
		return this.social;
	}
	

	/**
	 * Sets the id attribute of the ProfileEntry object.
	 *
	 * @param id the new id attribute of the ProfileEntry object
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the value of:  <pre> rootnode.
	 *
	 * @param type the new value of:  <pre> rootnode
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Sets the value of:  <pre> rootnode.
	 *
	 * @param slug the new value of:  <pre> rootnode
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * Sets the value of:  <pre> rootnode.
	 *
	 * @param jobTitle the new value of:  <pre> rootnode
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Sets the value of:  <pre> rootnode.
	 *
	 * @param firstName the new value of:  <pre> rootnode
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the value of:  <pre> rootnode.
	 *
	 * @param lastName the new value of:  <pre> rootnode
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the value of:  <pre> rootnode.
	 *
	 * @param bio the new value of:  <pre> rootnode
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * Sets the object containing portrait information, found in:  <pre> rootnode.
	 *
	 * @param headshot the new object containing portrait information, found in:  <pre> rootnode
	 */
	public void setHeadshot(Headshot headshot) {
		this.headshot = headshot;
	}

	/**
	 * Sets the a list of tuples grouped by social media type, found in:  <pre> rootnode.
	 *
	 * @param social the new a list of tuples grouped by social media type, found in:  <pre> rootnode
	 */
	public void setSocial(ArrayList<Social> social) {
		this.social = social;
	}

	/**
	 * Instantiates a new profile.  Not bothering with an internal Builder() class 
	 * for this application -- but would normally use one.
	 *
	 * @param id the id
	 * @param type the type
	 * @param slug the slug
	 * @param jobTitle the job title
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param bio the bio
	 * @param headshot the headshot
	 * @param social the social
	 */
	public Profile(String id, String type, String slug, String jobTitle, String firstName, String lastName, String bio,
			Headshot headshot, ArrayList<Social> social) {
		this.id = id;
		this.type = type;
		this.slug = slug;
		this.jobTitle = jobTitle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
		this.headshot = headshot;
		this.social = social;
	}

}
