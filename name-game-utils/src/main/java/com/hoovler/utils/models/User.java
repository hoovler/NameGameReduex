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
package com.hoovler.utils.models;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {

	/** The email. */
	private String email;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The roles. */
	private ArrayList<Role> roles;

	/**
	 * Gets User.email
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets User.username
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets User.password
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets User.roles
	 *
	 * @return the roles
	 */
	public ArrayList<Role> getRoles() {
		return roles;
	}

	/**
	 * Sets User.email
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets User.username
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets User.password
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets User.roles
	 *
	 * @param roles the new roles
	 */
	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param email the email
	 * @param username the username
	 * @param password the password
	 * @param roles the roles
	 */
	public User(String email, String username, String password, ArrayList<Role> roles) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param email the email
	 * @param roles the roles
	 */
	public User(String email, ArrayList<Role> roles) {
		this.email = email;
		this.roles = roles;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param email the email
	 */
	public User(String email) {
		this.email = email;
	}

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

}
