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
package test.hoovler.dao.models;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.hoovler.dao.models.Headshot;
import com.hoovler.dao.models.Profile;
import com.hoovler.dao.models.Social;

public class ObjectsTestsHelper {
	/** Static profile data. */
	protected static final String pId = "123";
	protected static final String pType = "people";
	protected static final String pSlug = "best person ever";
	protected static final String pJobTitle = "Senior Person";
	protected static final String pFirstName = "John";
	protected static final String pLastName = "Doe";
	protected static final String pBio = "Very well, where do I begin? ...";

	/** Static headshot data. */
	protected static final String hsId = "456";
	protected static final String hsType = "image";
	protected static final String hsMimeType = "image/png";
	protected static final String hsUrl = "//some.server.somewhere/images/456.png";
	protected static final String hsAlt = "A portrait of John Doe";
	protected static final Integer hsHeight = 50;
	protected static final Integer hsWidth = 60;

	/** Static social data. */
	protected static final String[] socTypes = { "google", "linkedin", "twitter" };

	/**
	 * Make soc calls.
	 *
	 * @param socTypes the soc types
	 * @param firstName the first name
	 * @param lastName the last name
	 * @return the string[]
	 */
	protected static String[] makeSocCalls(String[] socTypes, String firstName, String lastName) {
		String[] socCalls = new String[socTypes.length];
		int itr = 0;
		for (String type : socTypes) {
			socCalls[itr] = "Follow " + firstName + " " + lastName + " on " + type;
			itr++;
		}
		return socCalls;
	}

	/**
	 * Make soc urls.
	 *
	 * @param socTypes the soc types
	 * @param firstName the first name
	 * @param lastName the last name
	 * @return the string[]
	 */
	protected static String[] makeSocUrls(String[] socTypes, String firstName, String lastName) {
		String[] socUrls = new String[socTypes.length];
		int itr = 0;
		for (String type : socTypes) {
			socUrls[itr] = "https://www." + type + ".com/user/" + StringUtils.lowerCase(lastName + firstName);
			itr++;
		}
		return socUrls;
	}

	/**
	 * Make a <code>Profile</code> object from static data.
	 *
	 * @return Profile 
	 */
	protected static Profile makeProfileFromStaticData() {
		String[] socCalls = makeSocCalls(socTypes, pFirstName, pLastName);
		String[] socUrls = makeSocUrls(socTypes, pFirstName, pLastName);

		// now, create builder objects, and add values from the inside-out, bottom-up

		ArrayList<Social> social = new ArrayList<>(Arrays.asList(new Social(socTypes[0], socCalls[0], socUrls[0]),
				new Social(socTypes[1], socCalls[1], socUrls[1]), new Social(socTypes[2], socCalls[2], socUrls[2])));

		// populate and build headshot
		Headshot headshot = new Headshot(hsId, hsMimeType, hsType, hsUrl, hsAlt, hsHeight, hsWidth);

				// build profile - finished!
		return new Profile(pId, pType, pSlug, pJobTitle, pFirstName, pLastName, pBio, headshot, social);
	}
}
