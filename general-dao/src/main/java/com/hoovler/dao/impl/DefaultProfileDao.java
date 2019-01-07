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
package com.hoovler.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.generic.ProfileDao;
import com.hoovler.dao.models.Profile;
import com.hoovler.dao.resources.ProfileDaoHelper;

/** The default implementation of the <code>ProfileDao</code> interface.
 * <p><b>Examples</b>
 * <p>Instantiate a new, empty DAO object:<pre>ProfileDao profileDao = new DefaultProfileDao();<p>ProfileDao profileDao = new DefaultProfileDao(false);</pre>
 * <p>Instantiate a new DAO object auto-populated with the default data source:<pre>ProfileDao profileDao = new DefaultProfileDao(true);</pre>
 * <p>Instantiate a new DAO object using a custom URI data source:<pre>String dataSource = "http://your.custom.domain/api/profiles";<p>ProfileDao profileDao = new DefaultProfileDao(dataSource);</pre>
 * 
 * @see com.hoovler.dao.ProfileDao() */
public class DefaultProfileDao implements ProfileDao {
	private static Logger log = LogManager.getLogger(DefaultProfileDao.class.getName());

	private HashMap<String, Profile> profileMap = new HashMap<>();

	/** Instantiates a new {@code DefaultProfileDao} object. */
	public DefaultProfileDao() {
		this.profileMap = new HashMap<>();
	}

	/** Instantiates a new {@code DefaultProfileDao} object based on the {@code autoPopulate} boolean value.
	 * 
	 * <p>If {@code autoPopulate} is true, this constructor will:
	 * <ol><li>First checks for the URL in the value of the {@code json.url.default} within {@code /resources/profile_dao.properties}<li>If unavailable, it the used the hard-coded value of {@code https://www.willowtreeapps.com/api/v1.0/profiles}</ol>
	 * <p>If {@code autoPopulate} is false, this acts like the default constructor.
	 * <p> To manually set a data source URL, use the following constructor instead: <pre>DefaultProfileDao profileDao = new DefaultProfileDao(jsonUrl)</pre> */
	public DefaultProfileDao(boolean autoPopulate) {
		if (autoPopulate) {
			this.profileMap = initProfileMap(null);
		} else {
			// generate all profiles from source
			this.profileMap = new HashMap<>();
		}
	}

	/** Instantiates a new {@code DefaultProfileDao} object.
	 *
	 * @param jsonUrl the URL of the site / resource containing the JSON array of Profile objects. */
	public DefaultProfileDao(String jsonUrl) {
		// generate all profiles from source
		this.profileMap = initProfileMap(jsonUrl);
	}

	/** Gets the profile.
	 *
	 * @param  id the id
	 * @return    the profile
	 * 
	 * @see       com.hoovler.dao.ProfileDao#getProfile(java.lang.String) */
	@Override
	public Profile getProfile(String id) {
		return profileMap.get(id);
	}

	/** Adds the profile.
	 *
	 * @param  profile the profile
	 * @return         the profile */
	@Override
	public Profile addProfile(Profile profile) {
		if (profile.getId() == null || StringUtils.isBlank(profile.getId())) {
			log.warn("No profile.id has been set... not adding profile.");
		} else {
			profileMap.put(profile.getId(), profile);
		}
		return profile;
	}

	/** Update profile.
	 *
	 * @param  id      the id
	 * @param  profile the profile
	 * @return         the profile */
	@Override
	public Profile updateProfile(String id, Profile profile) {
		if (!profileMap.containsKey(id)) {
			log.warn("No profile with id=" + id + " found... not updating profile.");
			return null;
		} else {
			return profileMap.put(id, profile);
		}
	}

	/** Delete profile.
	 *
	 * @param  id the id
	 * @return    true, if successful */
	@Override
	public boolean deleteProfile(String id) {
		return profileMap.remove(id) != null;
	}

	/** Profile list.
	 *
	 * @return the array list */
	@Override
	public ArrayList<Profile> profileList() {
		return new ArrayList<>(profileMap.values());
	}

	/** Inits the profile map.
	 *
	 * @param  sourceUrl the source url
	 * @return           the hash map */
	private static HashMap<String, Profile> initProfileMap(String sourceUrl) {
		return ProfileDaoHelper.getProfilesMap(sourceUrl);
	}
}
