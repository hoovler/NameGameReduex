/*
 * Copyright (c) ${author} 2018
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
package com.hoovler.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.models.Profile;

/**
 * <p>
 * <h3>DefaultProfileDao</h3>
 * <p>
 * <b><u>Purpose</u></b>
 * </p>
 * This Class is the default implementation of the <code>ProfileDao</code> interface.
 * </p>
 * <p>
 * <b><u>Examples</u></b>
 * </p>
 * Instantiate a new DAO object using the default data source:
 * 
 * <pre>
 * ProfileDao profileDao = new DefaultProfileDao();
 * </pre>
 * 
 * Instantiate a new DAO object using a custom URI data source:
 * 
 * <pre>
 * String dataSource = "http://your.custom.domain/api/profiles";
 * ProfileDao profileDao = new DefaultProfileDao(dataSource);
 * </pre>
 * 
 * @see com.hoovler.dao.ProfileDao() */
public class DefaultProfileDao implements ProfileDao {
	private static Logger log = LogManager.getLogger(DefaultProfileDao.class.getName());

	/**
	 * <p>
	 * TODO: add desc...
	 * </p>
	 */
	private HashMap<String, Profile> profileMap = new HashMap<>();

	/** Instantiates a new <code>DefaultProfileDao</code> object. For the
	 * data source, this unparameterized constructor uses the following logic:
	 * <ol>
	 * <li><b>It first checks for the URL in the value of:</b><br/>
	 * <code><i>/resources/profile_dao.properties</i> <br/> > json.url.default</code>
	 * <li><b>If unavailable, it then uses the default, hard-coded value of:</b><br/>
	 * <code>"https://www.willowtreeapps.com/api/v1.0/profiles"</code><br/>
	 * <b>which is found in:</b><br/>
	 * <code>com.hoovler.dao.DEFAULT_SOURCE</code>
	 * </ol>
	 * 
	 * For more control over the data source, use the following constructor instead:
	 * 
	 * <pre>
	 * DefaultProfileDao profileDao = new DefaultProfileDao(jsonUrl)
	 * </pre>
	 */
	public DefaultProfileDao() {
		// generate all profiles from source
		this.profileMap = initProfileMap(null);
	}

	/** Instantiates a new <code>DefaultProfileDao</code> object.
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
