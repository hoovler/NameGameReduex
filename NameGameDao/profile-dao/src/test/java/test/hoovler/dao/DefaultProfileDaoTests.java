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
package test.hoovler.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.ProfileDao;
import com.hoovler.dao.models.Profile;

class DefaultProfileDaoTests {
	private static Logger log = LogManager.getLogger(DefaultProfileDaoTests.class.getName());

	/**
	 * Test profile dao constructors.
	 */
	@Test
	void testProfileDaoConstructors() {
		
		ProfileDao profileDao_a = new DefaultProfileDao();
		ProfileDao profileDao_b = new DefaultProfileDao("https://www.willowtreeapps.com/api/v1.0/profiles");
		
		// Profile list object
		ArrayList<Profile> profileList_a = profileDao_a.profileList();
		ArrayList<Profile> profileList_b = profileDao_b.profileList();
		
		log.info("profileList_a.size() = " + profileList_a.size());
		log.info("profileList_b.size() = " + profileList_b.size());
		
		// ensure each profile in list_a is accessible from dao_b
		for (Profile profile : profileList_a) {
			log.info("Asserting Not Null: " + profileDao_b.getProfile(profile.getId()));
			assertNotNull(profileDao_b.getProfile(profile.getId()));
		}
	}
	

	/**
	 * Test get profile.
	 */
	@Test
	void testGetProfile() {
		ProfileDao profileDao = new DefaultProfileDao();
		ArrayList<Profile> profileList = profileDao.profileList();
		
		// generate a random index
		int index_a = new Random().nextInt(profileList.size());
		
		// grab test profile from corresponding index
		Profile profile_a = profileList.get(index_a);		
		
		// now grab the object with the same ID from DefaultProfileDao.getProfile
		Profile profile_b = profileDao.getProfile(profile_a.getId());
		log.info("Asserting Not Null: " + profile_b);
		assertNotNull(profile_b);
		
		// now, we would expect the two objects to be the same
		log.info("Asserting Equals: " + profile_a.hashCode() + " == " + profile_b.hashCode());
		assertEquals(profile_a.hashCode(), profile_b.hashCode());
		
		// and finally, we just make sure any other random profile has a different hashCode()
		int index_b = new Random().nextInt(profileList.size());
		while(index_b == index_a) {
			index_b = new Random().nextInt(profileList.size());
		}
		Profile profile_c = profileDao.getProfile(profileList.get(index_b).getId());
		log.info("Asserting Not Equals: " + profile_b.hashCode() + " != " + profile_c.hashCode());
		assertNotEquals(profile_b.hashCode(), profile_c.hashCode());
	}
}
