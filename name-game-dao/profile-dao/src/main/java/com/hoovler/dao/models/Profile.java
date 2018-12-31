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

import java.util.ArrayList;

/**
 * The Class Profile.
 */
public class Profile {
	/** The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>id</i></b>
	 * </pre>
	 */
	private String id;

	/** The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>type</i></b>
	 * </pre>
	 */
	private String type;

	/** The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>slug</i></b>
	 * </pre>
	 */
	private String slug;

	/** The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>jobTitle</i></b>
	 * </pre>
	 */
	private String jobTitle;

	/** The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>firstName</i></b>
	 * </pre>
	 */
	private String firstName;

	/** The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>lastName</i></b>
	 * </pre>
	 */
	private String lastName;

	/** The value of:
	 * 
	 * <pre>
	 * rootnode.<b><i>bio</i></b>
	 * </pre>
	 */
	private String bio;

	/** The object containing portrait information, found in:
	 * 
	 * <pre>
	 * rootnode.<b><i>headshot</i></b>
	 * </pre>
	 */
	private Headshot headshot;

	/** A list of tuples grouped by social media type, found in:
	 * 
	 * <pre>
	 * rootnode.<b><i>socialLinks</i></b>
	 * </pre>
	 */
	private ArrayList<Social> social;

	/** Gets the id attribute of the ProfileEntry object.
	 *
	 * @return the id attribute of the ProfileEntry object */
	public String getId() {
		return this.id;
	}

	/** Gets the type.
	 *
	 * @return the type */
	public String getType() {
		return this.type;
	}

	/** Gets the slug.
	 *
	 * @return the slug */
	public String getSlug() {
		return this.slug;
	}

	/** Gets the job title.
	 *
	 * @return the job title */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/** Gets the first name.
	 *
	 * @return the first name */
	public String getFirstName() {
		return this.firstName;
	}

	/** Gets the last name.
	 *
	 * @return the last name */
	public String getLastName() {
		return this.lastName;
	}

	/** Gets the bio.
	 *
	 * @return the bio */
	public String getBio() {
		return this.bio;
	}

	/** Gets the headshot.
	 *
	 * @return the headshot */
	public Headshot getHeadshot() {
		return this.headshot;
	}

	/** Gets the social.
	 *
	 * @return the social */
	public ArrayList<Social> getSocial() {
		return this.social;
	}

	/** Sets the id attribute of the ProfileEntry object.
	 *
	 * @param id the new id attribute of the ProfileEntry object */
	public void setId(String id) {
		this.id = id;
	}

	/** Sets the type.
	 *
	 * @param type the new type */
	public void setType(String type) {
		this.type = type;
	}

	/** Sets the slug.
	 *
	 * @param slug the new slug */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/** Sets the job title.
	 *
	 * @param jobTitle the new job title */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/** Sets the first name.
	 *
	 * @param firstName the new first name */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** Sets the last name.
	 *
	 * @param lastName the new last name */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** Sets the bio.
	 *
	 * @param bio the new bio */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/** Sets the headshot.
	 *
	 * @param headshot the new headshot */
	public void setHeadshot(Headshot headshot) {
		this.headshot = headshot;
	}

	/** Sets the social.
	 *
	 * @param social the new social */
	public void setSocial(ArrayList<Social> social) {
		this.social = social;
	}

	/** Instantiates a new profile. */
	public Profile() {

	}

	/** Instantiates a new profile. Not bothering with an internal Builder() class
	 * for this application -- but would normally use one.
	 *
	 * @param id        the id
	 * @param type      the type
	 * @param slug      the slug
	 * @param jobTitle  the job title
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param bio       the bio
	 * @param headshot  the headshot
	 * @param social    the social */
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
