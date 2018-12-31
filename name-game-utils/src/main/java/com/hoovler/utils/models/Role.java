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

import com.hoovler.utils.enums.RestVerb;

// TODO: Auto-generated Javadoc
/** The Class Role. */
public class Role {

	/** The name. */
	private String name;

	/** The access. */
	private ArrayList<RestVerb> access;

	/** Gets Role.name
	 *
	 * @return the name */
	public String getName() {
		return name;
	}

	/** Gets Role.access
	 *
	 * @return the access */
	public ArrayList<RestVerb> getAccess() {
		return access;
	}

	/** Sets Role.name
	 *
	 * @param name the new name */
	public void setName(String name) {
		this.name = name;
	}

	/** Sets Role.access
	 *
	 * @param access the new access */
	public void setAccess(ArrayList<RestVerb> access) {
		this.access = access;
	}

	/** Instantiates a new role. */
	public Role() {
		// no-op
	}

	/** Instantiates a new role.
	 *
	 * @param name the name */
	public Role(String name) {
		this.name = name;
	}

	/** Instantiates a new role.
	 *
	 * @param name   the name
	 * @param access the access */
	public Role(String name, ArrayList<RestVerb> access) {
		this.name = name;
		this.access = access;
	}

}
