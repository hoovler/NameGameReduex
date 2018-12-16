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
package test.hoovler.dao.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.hoovler.dao.models.Profile;
import com.hoovler.dao.models.Social;

class SocialTests {
	private static Logger log = LogManager.getLogger(SocialTests.class.getName());

	@Test
	void testSocialsAttributes() {
		log.traceEntry();
		
		Profile profile = ObjectsTestsHelper.makeProfileFromStaticData();
		String fn = profile.getFirstName();
		String ln = profile.getLastName();
		
		ArrayList<Social> socials = profile.getSocial();
		String[] socTypes = ObjectsTestsHelper.socTypes;
		String[] socCalls = ObjectsTestsHelper.makeSocCalls(socTypes, fn, ln);
		String[] socUrls = ObjectsTestsHelper.makeSocUrls(socTypes, fn, ln);
		
		int itr = 0;
		for (Social social : socials) {
			assertEquals(social.getType(), socTypes[itr]);
			assertEquals(social.getActionCall(), socCalls[itr]);
			assertEquals(social.getUrl(), socUrls[itr]);
			itr++;
		}
	}
}
