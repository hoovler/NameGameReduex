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

import com.hoovler.dao.models.Headshot;

class HeadshotTests {
	private static Logger log = LogManager.getLogger(HeadshotTests.class.getName());

	@Test
	void testHeadshotAttributes() {
		log.traceEntry();
				Headshot headshot = ObjectsTestsHelper.makeProfileFromStaticData().getHeadshot();
		assertEquals(headshot.getId(), ObjectsTestsHelper.hsId);
		assertEquals(headshot.getType(), ObjectsTestsHelper.hsType);
		assertEquals(headshot.getMimeType(), ObjectsTestsHelper.hsMimeType);
		assertEquals(headshot.getUrl(), ObjectsTestsHelper.hsUrl);
		assertEquals(headshot.getAlt(), ObjectsTestsHelper.hsAlt);		
		assertEquals(headshot.getHeight(), ObjectsTestsHelper.hsHeight);
		assertEquals(headshot.getWidth(), ObjectsTestsHelper.hsWidth);
	}
}
