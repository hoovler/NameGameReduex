/*
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2018
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.ProfileDao;
import com.hoovler.dao.ProfileDaoSingleton;
import com.hoovler.dao.models.Profile;

/** JUnit 5 / Jupiter Testing: <b>ProfileDaoTests</b>
 * <p>This test class tests the DefaultProfileDao use cases, and includes implied testing of all classes in <code>com.hoovler.dao</code> and <code>com.hoovler.dao.models</code> for the entire <code>profile-dao</code> maven project.</p> */
class ProfileDaoTests {
	private static Logger log = LogManager.getLogger(ProfileDaoTests.class.getName());

	private static ProfileDao profileDao_a;
	private static ProfileDao profileDao_a1;
	private static ProfileDao profileDao_b;
	private static ProfileDao profileDao_b1;
	private static ProfileDao profileDao_c;
	private static ProfileDao profileDao_c1;

	private static ArrayList<Profile> profileList_a;
	private static ArrayList<Profile> profileList_a1;
	private static ArrayList<Profile> profileList_b;
	private static ArrayList<Profile> profileList_b1;
	private static ArrayList<Profile> profileList_c;
	private static ArrayList<Profile> profileList_c1;

	/** Sets the up before all test methods.
	 * 
	 * <p>The methodology employed is to create a DefaultProfileDao instance - and corresponding List of Profile objects - for each of the three possible sources of the <code>sourceUrl</code>, and to then create <em>another</em> instance of each, which should ostensibly be equivalent.</p>
	 *
	 * @throws Exception the exception */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// profile_a will use value from the properties file for the profile URL
		profileDao_a = new DefaultProfileDao();
		profileDao_a1 = new DefaultProfileDao();

		// profile_b will use hard-coded constant value for the profile URL
		profileDao_b = new DefaultProfileDao(ProfileDaoSingleton.DEFAULT_SOURCE);
		profileDao_b1 = new DefaultProfileDao(ProfileDaoSingleton.DEFAULT_SOURCE);

		// and profile_c will use locally-given value for the profile URL
		profileDao_c = new DefaultProfileDao("https://www.willowtreeapps.com/api/v1.0/profiles");
		profileDao_c1 = new DefaultProfileDao("https://www.willowtreeapps.com/api/v1.0/profiles");

		// list of profileDao_a profile objects
		profileList_a = new ArrayList<>(profileDao_a.profileList());
		profileList_a1 = new ArrayList<>(profileDao_a1.profileList());

		// list of profileDao_b profile objects
		profileList_b = new ArrayList<>(profileDao_b.profileList());
		profileList_b1 = new ArrayList<>(profileDao_b1.profileList());

		// list of profileDao_c profile objects
		profileList_c = new ArrayList<>(profileDao_c.profileList());
		profileList_c1 = new ArrayList<>(profileDao_c1.profileList());

		// log startup
		log.info("=========" + log.getName() + " Setup =========");
		log.info("profileList_a.size() = " + profileList_a.size());
		log.info("profileList_a1.size() = " + profileList_a1.size());
		log.info("profileList_b.size() = " + profileList_b.size());
		log.info("profileList_b1.size() = " + profileList_b1.size());
		log.info("profileList_c.size() = " + profileList_c.size());
		log.info("profileList_c1.size() = " + profileList_c1.size());
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		log.info("=========" + log.getName() + " Teardown =========");
	}

	@Test
	void testProfilesA() {
		log.info("Entering testProfilesA()...");
		int idx = 0;
		// ensure each profile in list_a is accessible from dao_a1
		for (Profile profile : profileList_a) {
			log.info("profileList_a.get(" + idx + ").getId() = " + profileList_a.get(idx).getId());
			log.info("profileList_a1.get(" + idx + ").getId() = " + profileList_a1.get(idx).getId());
			assertNotNull(profileDao_a1.getProfile(profile.getId()));
			assertEquals(profileList_a.get(idx).getId(), profileList_a1.get(idx).getId());
			idx++;
		}
	}

	@Test
	void testProfilesB() {
		log.info("Entering testProfilesB()...");
		int idx = 0;
		// ensure each profile in list_b is accessible from dao_b1
		for (Profile profile : profileList_b) {
			log.info("profileList_b.get(" + idx + ").getId() = " + profileList_b.get(idx).getId());
			log.info("profileList_b1.get(" + idx + ") = " + profileList_b1.get(idx).getId());
			assertNotNull(profileDao_b1.getProfile(profile.getId()));
			assertEquals(profileList_b.get(idx).getId(), profileList_b1.get(idx).getId());
			idx++;
		}
	}

	@Test
	void testProfilesC() {
		log.info("Entering testProfilesC()...");
		int idx = 0;
		// ensure each profile in list_c is accessible from dao_c1
		for (Profile profile : profileList_c) {
			log.info("profileList_c.get(" + idx + ").getId() = " + profileList_c.get(idx).getId());
			log.info("profileList_c1.get(" + idx + ").getId() = " + profileList_c1.get(idx).getId());
			assertNotNull(profileDao_c1.getProfile(profile.getId()));
			assertEquals(profileList_c.get(idx).getId(), profileList_c1.get(idx).getId());
			idx++;
		}
	}
}
