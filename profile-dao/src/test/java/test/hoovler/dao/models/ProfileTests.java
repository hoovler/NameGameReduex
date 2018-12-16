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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.hoovler.dao.models.Profile;

class ProfileTests {
	private static Logger log = LogManager.getLogger(ProfileTests.class.getName());

	@Test
	void testProfileAttributes() {
		log.traceEntry();
		
		Profile profile = ObjectsTestsHelper.makeProfileFromStaticData();
		
		assertEquals(profile.getId(), ObjectsTestsHelper.pId);
		assertEquals(profile.getType(), ObjectsTestsHelper.pType);
		assertEquals(profile.getSlug(), ObjectsTestsHelper.pSlug);
		assertEquals(profile.getJobTitle(), ObjectsTestsHelper.pJobTitle);
		assertEquals(profile.getFirstName(), ObjectsTestsHelper.pFirstName);
		assertEquals(profile.getLastName(), ObjectsTestsHelper.pLastName);
		assertEquals(profile.getBio(), ObjectsTestsHelper.pBio);
	}
}
