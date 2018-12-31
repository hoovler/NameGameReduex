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
/** The Class SecurityConfig. */
public class SecurityConfig {

	/** The roles. */
	private ArrayList<Role> roles;

	/** The users. */
	private ArrayList<User> users;

	/** Gets SecurityConfig.roles
	 *
	 * @return the roles */
	public ArrayList<Role> getRoles() {
		return roles;
	}

	/** Gets SecurityConfig.users
	 *
	 * @return the users */
	public ArrayList<User> getUsers() {
		return users;
	}

	/** Sets SecurityConfig.roles
	 *
	 * @param roles the new roles */
	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

	/** Sets SecurityConfig.users
	 *
	 * @param users the new users */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/** Instantiates a new security config.
	 *
	 * @param roles the roles
	 * @param users the users */
	public SecurityConfig(ArrayList<Role> roles, ArrayList<User> users) {
		this.roles = roles;
		this.users = users;
	}

	/** Instantiates a new security config.
	 *
	 * @param users the users */
	public SecurityConfig(ArrayList<User> users) {
		this.users = users;
		this.roles = new ArrayList<>();
	}

	/** Instantiates a new security config. */
	public SecurityConfig() {
		this.users = new ArrayList<>();
		this.roles = new ArrayList<>();
	}

}
