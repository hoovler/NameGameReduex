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
package com.hoovler.dao.generic;

import java.util.ArrayList;

import com.hoovler.dao.models.Profile;

/**
 * <p><h3>ProfileDao</h3>
 * <p><b><u>Purpose</u></b>
 * This interface provides a basic outline for operations performed on a collection of {@code Profile} objects.
 * <p><b><u>Examples</u></b>
 * Instantiation using the {@code DefaultProfileDao} implementation:
 * <pre>ProfileDao profileDao = new DefaultProfileDao();</pre>
 * Using a custom URI data source for the {@code DefaultProfileDao} implementation:
 * <pre>
 * String dataSource = "http://your.custom.domain/apirofiles";
 * ProfileDao profileDao = new DefaultProfileDao(dataSource);
 * </pre>
 */
public interface ProfileDao {
		/**
	 * Gets a {@code Profile} object.
	 *
	 * @param id the id of the {@code Profile} object. 
	 * @return the corresponding {@code Profile} object.
	 */
	Profile getProfile(String id);
		/**
	 * Adds a {@code Profile</code> object to the <code>Profile} object collection.
	 *
	 * @param profile the {@code Profile} object to add to the collection.
	 * @return the {@code Profile} object added.
	 */
	Profile addProfile(Profile profile);
		/**
	 * Update a {@code Profile</code> object within the <code>Profile} object collection.
	 *
	 * @param id the id of the {@code Profile} object to update.
	 * @param profile the {@code Profile} object with changed attribute values.
	 * @return 
	 * <ul>		
	 * <li>the original {@code Profile</code> object that was changed if a {@code Profile} object with the associated {@code id} is found.</li>  
	 * <li>{@code NULL} otherwise</li>
	 * </ul>
	 */
	Profile updateProfile(String id, Profile profile);
		/**
	 * Delete a {@code Profile</code> object from the <code>Profile} object collection.
	 *
	 * @param id the id of the {@code Profile</code> object to remove from the <code>Profile} object collection.
	 * @return true, if the {@code Profile</code> object was successfully removed from the <code>Profile} object collection.
	 */
	boolean deleteProfile(String id);
		/**
	 * Gets the entire {@code Profile} object collection.
	 *
	 * @return the list of {@code Profile</code> objects in the <code>Profile} object collection.
	 */
	ArrayList<Profile> profileList();
}
