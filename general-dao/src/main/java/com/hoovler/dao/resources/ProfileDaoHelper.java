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
package com.hoovler.dao.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hoovler.dao.models.Headshot;
import com.hoovler.dao.models.Profile;
import com.hoovler.dao.models.Social;


/**A singleton object that assists in the creation of the {@code Profile} object, and is reponsible for auto-populating the {@code DefaultProfileDao} repository. */
public class ProfileDaoHelper {
	/** The Constant log. */
	private static Logger log = LogManager.getLogger(ProfileDaoHelper.class.getName());

	/** The Constant DEFAULT_SOURCE. */
	public static final String DEFAULT_SOURCE = "https://www.willowtreeapps.com/api/v1.0/profiles";

	/** The Constant PROPERTIES_FILENAME. */
	public static final String PROPERTIES_FILENAME = "profile_dao";

	/** The Constant PROP_jsonUrlDefault. */
	public static final String PROP_DEFAULT_URL_KEY = "json.url.default";

	/** The Constant PROP_sourceUrlKey. */
	public static final String PROP_sourceUrlKey = "json.url.default";

	/** The Constant PROP_KEY_SEP. */
	public static final String PROP_KEY_SEP = ".";

	/** The Constant PROP_LISTVAL_SEP. */
	public static final String PROP_LISTVAL_SEP = ",";
		/** The Constant URL_PROTOCOL. */
	public static final String URL_PROTOCOL = "http:";
		/** The Constant URL_PREFIX. */
	public static final String URL_PREFIX = "//";
	
	// ************************************************************
	
	
	private static final String JKEY_TYPE = "type";
	private static final String JKEY_ID = "id";
	private static final String JKEY_URL = "url";
	
	private static final String JKEY_SLUG = "slug";
	private static final String JKEY_JOB_TITLE = "jobTitle";
	private static final String JKEY_FIRST_NAME = "firstName";
	private static final String JKEY_LAST_NAME = "lastName";
	private static final String JKEY_BIO = "bio";
	
	private static final String JKEY_HS = "headshot";
	private static final String JKEY_HS_MIME = "mimeType";
	private static final String JKEY_HS_ALT = "alt";
	private static final String JKEY_HS_HEIGHT = "height";
	private static final String JKEY_HS_WIDTH = "width";
	
	private static final String JKEY_SOCIAL = "socialLinks";
	private static final String JKEY_SOCIAL_ACTION = "callToAction";

	/** Reads the properties file, and gets all properties.
	 *
	 * @return The set of properties as a <code>ResourceBundle</code> object. */
	public static ResourceBundle getPropertiesBundle() {
		log.traceEntry();
		return ResourceBundle.getBundle(PROPERTIES_FILENAME);
	}

	/** Reads the properties file, and gets all properties.
	 *
	 * @return The set of properties as a <code>HashMap&lt;String, String&gt;</code> object. */
	public static HashMap<String, String> getPropertiesMap() {
		log.traceEntry();
		HashMap<String, String> propertiesMap = new HashMap<>();
		ResourceBundle rb = getPropertiesBundle();
		Enumeration<String> keys = rb.getKeys();
		String key = "";
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			propertiesMap.put(key, rb.getString(key));
		}
		return propertiesMap;
	}

	/** Reads the properties file, and gets only <code>json.attributes.*</code> properties.
	 *
	 * @param  propertyNamePrefix the property name prefix
	 * @return                    An <code>ArrayList</code> of the <code>json.attributes.*</code> values. */
	public static HashMap<String, String> getAttributesProperties(String propertyNamePrefix) {
		log.traceEntry();
		ResourceBundle rb = getPropertiesBundle();

		// use propPrefix to pull values from bundle
		HashMap<String, String> attributeProps = new HashMap<>();
		Enumeration<String> keys = rb.getKeys();
		String key = "";
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			if (StringUtils.startsWith(key, propertyNamePrefix)) {
				attributeProps.put(key, rb.getString(key));
			} // end if
		} // end while()
		return attributeProps;
	} // end getAttributeProperties{}

	/** Gets the prop string.
	 *
	 * @param  key the key
	 * @return     the prop string */
	public static String getPropString(String key) {
		return getPropertiesBundle().getString(key);
	}
	
	/**
	 * Gets ProfileDaoHelper.profilesMap
	 *
	 * @return the profiles map
	 */
	public static HashMap<String, Profile> getProfilesMap() {
		return getProfilesMap(null);
	}

	/**
	 * Parses the source JSON for the profiles list, and build the {@code Profile} object, included the nested {@code Headshot} and {@code Social[]} objects.
	 * 
	 * <p>For the profiles list specifically, this method is preferable to the standard parsing methods within most common JSON packages because there none of those parsing utilities offer the option to create empty object members when, given an array of JSON objects, some objects do not have those members.
	 *
	 * @param sourceUrl the URL of the source JSON profiles list.
	 * @return a {@code HashMap<String, Profile>} map wherein the {@code Profile.getId()} is the key, and the {@code Profile} object is the value.
	 */
	public static HashMap<String, Profile> getProfilesMap(String sourceUrl) {
		// make sure URL isn't null; first check sourceUrl param, then properties file, then hard-wired global
		String url = checkUrl(sourceUrl);
		log.debug("url=" + url);

		// set profile array
		HashMap<String, Profile> profiles = new HashMap<>();

		try {
			for (JsonElement jsonProfile : getAllElements(url)) {
				JsonObject profileJson = jsonProfile.getAsJsonObject();

				// create Social object
				JsonArray socialLinksJson = profileJson.get(JKEY_SOCIAL).getAsJsonArray();
				ArrayList<Social> socials = new ArrayList<>();

				for (JsonElement link : socialLinksJson) {
					JsonObject social = link.getAsJsonObject().getAsJsonObject();
					// create new Social each loop
					Social socialObj = new Social();
					socialObj.setType(getStrVal(social, JKEY_TYPE));
					socialObj.setActionCall(getStrVal(social, JKEY_SOCIAL_ACTION));
					socialObj.setUrl(getStrVal(social, JKEY_URL));
					// and add it to the list of all social networking accounts the profile might have
					socials.add(socialObj);
				}

				// create Headshot object
				JsonObject headshotJson = profileJson.get(JKEY_HS).getAsJsonObject();

				Headshot headshot = new Headshot();
				headshot.setType(getStrVal(headshotJson, JKEY_TYPE));
				headshot.setMimeType(getStrVal(headshotJson, JKEY_HS_MIME));
				headshot.setId(getStrVal(headshotJson, JKEY_ID));
				headshot.setUrl(getStrVal(headshotJson, JKEY_URL), URL_PROTOCOL);
				headshot.setAlt(getStrVal(headshotJson, JKEY_HS_ALT));
				headshot.setHeight(getIntVal(headshotJson, JKEY_HS_HEIGHT));
				headshot.setWidth(getIntVal(headshotJson, JKEY_HS_WIDTH));

				// create Profile object
				Profile profile = new Profile();
				profile.setId(getStrVal(profileJson, JKEY_ID));
				profile.setType(getStrVal(profileJson, JKEY_TYPE));
				profile.setSlug(getStrVal(profileJson, JKEY_SLUG));
				profile.setJobTitle(getStrVal(profileJson, JKEY_JOB_TITLE));
				profile.setFirstName(getStrVal(profileJson, JKEY_FIRST_NAME));
				profile.setLastName(getStrVal(profileJson, JKEY_LAST_NAME));
				profile.setBio(getStrVal(profileJson, JKEY_BIO));
				profile.setHeadshot(headshot);
				profile.setSocial(socials);

				// and add to list!
				profiles.put(profile.getId(), profile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return profiles;
	}

	/** Check the URL for which the profiles should be within the response body in JSON format.
	 * <p>This method will not validate the <code>sourceUrl</code>, but it will return a valid value should the <code>sourceUrl</code> happen to be blank or null.</p>
	 * <ol><li></li>If the passed value is blank or null, the method will grab the value of the <code>json.url.default</code> key within the <em>/resources/profile_dao.properties</em> file.<li>If the value of the <code>json.url.default</code> key is blank or null, then a default, hard-coded value is used: <code>ProfileDaoSingleton.DEFAULT_SOURCE = "https://www.willowtreeapps.com/api/v1.0/profiles"</code></li></ol>
	 *
	 * @param  sourceUrl the source url to check for empty and null.
	 * @return           A non-empty and non-null <code>sourceUrl</url> */
	private static String checkUrl(String sourceUrl) {
		if (StringUtils.isNotBlank(sourceUrl)) {
			return sourceUrl;
		} else {
			String tmpUrl = getPropString(PROP_sourceUrlKey);
			if (StringUtils.isNotBlank(tmpUrl)) {
				return tmpUrl;
			} else {
				return DEFAULT_SOURCE;
			}
		}
	}

	/** Gets the value with the given <code>key</code> label from the JsonObject <code>obj</code>.
	 *
	 * @param  profile the profile
	 * @param  key     the key
	 * @return         the val */
	private static String getStrVal(JsonObject obj, String key) {
		// log.traceEntry("key="+key);
		String value = "";
		if (obj.has(key)) {
			value = StringUtils.defaultIfBlank(obj.get(key).getAsString(), StringUtils.EMPTY);
		}
		log.debug("returning: " + key + " = " + value);
		return value;
	}

	/** Gets the int val.
	 *
	 * @param  obj the obj
	 * @param  key the key
	 * @return     the int val */
	private static Integer getIntVal(JsonObject obj, String key) {
		// log.traceEntry("key="+key);
		Integer value = -1;
		if (obj.has(key)) {
			value = obj.get(key).getAsInt();
		}
		log.debug("returning: " + key + " = " + value);
		return value;
	}

	/** Gets the all elements.
	 *
	 * @param  uri         the uri
	 * @return             the all elements
	 * @throws IOException Signals that an I/O exception has occurred. */
	private static JsonArray getAllElements(String uri) throws IOException {
		// connect to the datasource...
		URL url = new URL(uri);
		URLConnection req = url.openConnection();
		req.connect();

		// parse the root element out of the string...
		JsonParser jParse = new JsonParser();
		JsonElement root = jParse.parse(new InputStreamReader((InputStream) req.getContent()));

		// convert to an array of objects...
		return root.getAsJsonArray();
	}
		private ProfileDaoHelper() {
		// no-op
	}
}
