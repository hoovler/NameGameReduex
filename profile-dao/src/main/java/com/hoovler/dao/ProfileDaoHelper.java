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
package com.hoovler.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

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
import com.hoovler.resource.ProfileDaoGlobals;

/**
 * <p><h3>Profiles</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>Profiles</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * An example:
 * <pre>some code</pre>
 * Another example:
 * <pre>some more code;</pre>
 */
public class ProfileDaoHelper {

	/** The Constant log. */
	private static Logger log = LogManager.getLogger(ProfileDaoHelper.class.getName());

	public static HashMap<String, Profile> getProfilesMap() {
		return getProfilesMap(null);
	}

	public static HashMap<String, Profile> getProfilesMap(String sourceUrl) {
		// make sure URL isn't null; first check sourceUrl param, then properties file, then hard-wired global
		String url = checkUrl(sourceUrl);
		log.info("url=" + url);

		// set profile array
		HashMap<String, Profile> profiles = new HashMap<>();

		try {
			for (JsonElement jsonProfile : getAllElements(url)) {

				JsonObject profileJson = jsonProfile.getAsJsonObject();

				// create Social object
				JsonArray socialLinksJson = profileJson.get("socialLinks").getAsJsonArray();
				ArrayList<Social> socials = new ArrayList<>();
				for (JsonElement link : socialLinksJson) {
					JsonObject social = link.getAsJsonObject().getAsJsonObject();
					socials.add(new Social(getStrVal(social, "type"), getStrVal(social, "callToAction"),
							getStrVal(social, "url")));
				}

				// create Headshot object
				JsonObject headshotJson = profileJson.get("headshot").getAsJsonObject();
				Headshot headshot = new Headshot(
						getStrVal(headshotJson, "type"), 
						getStrVal(headshotJson, "mimeType"),
						getStrVal(headshotJson, "id"), 
						getStrVal(headshotJson, "url"), 
						getStrVal(headshotJson, "alt"),
						getIntVal(headshotJson, "height"), 
						getIntVal(headshotJson, "width"));

				// create Profile object
				Profile profile = new Profile(
						getStrVal(profileJson, "id"),
						getStrVal(profileJson, "type"),
						getStrVal(profileJson, "slug"),
						getStrVal(profileJson, "jobTitle"),
						getStrVal(profileJson, "firstName"),
						getStrVal(profileJson, "lastName"),
						getStrVal(profileJson, "bio"),
						headshot,
						socials);


				// and add to list!
				profiles.put(profile.getId(), profile);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profiles;
	}

	/**
	 * Source url.
	 *
	 * @param sourceUrl the source url
	 * @return the string
	 */
	private static String checkUrl(String sourceUrl) {
		if (StringUtils.isNotBlank(sourceUrl)) {
			return sourceUrl;
		} else {
			String tmpUrl = ProfileDaoGlobals.getPropString(ProfileDaoGlobals.PROP_sourceUrlKey);
			if (StringUtils.isNotBlank(tmpUrl)) {
				return tmpUrl;
			} else {
				return ProfileDaoGlobals.DEFAULT_SOURCE;
			}
		}
	}
	
	/**
	 * Gets the val.
	 *
	 * @param profile the profile
	 * @param key the key
	 * @return the val
	 */
	private static String getStrVal(JsonObject obj, String key) {
		// log.traceEntry("key="+key);
		String value = "";
		if (obj.has(key)) {
			value = StringUtils.defaultIfBlank(obj.get(key).getAsString(), StringUtils.EMPTY);
		}
		log.debug("returning: " + key + " = " + value);
		return value;
	}

	/**
	 * Gets the int val.
	 *
	 * @param obj the obj
	 * @param key the key
	 * @return the int val
	 */
	private static Integer getIntVal(JsonObject obj, String key) {
		// log.traceEntry("key="+key);
		Integer value = -1;
		if (obj.has(key)) {
			value = obj.get(key).getAsInt();
		}
		log.debug("returning: " + key + " = " + value);
		return value;
	}

	/**
	 * Gets the all elements.
	 *
	 * @param uri the uri
	 * @return the all elements
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

}
