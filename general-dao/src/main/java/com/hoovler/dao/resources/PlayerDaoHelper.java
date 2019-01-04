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
package com.hoovler.dao.resources;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.DomainValidator.ArrayType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PlayerDaoHelper {
	private static Logger log = LogManager.getLogger(PlayerDaoHelper.class.getName());
		public static String getRandomEmail() {
		log.info("PlayerDaoHelper.getRandomEmail()");
		// build handle and domain with random alpha & alphanumeric strings
		String lastInitial = RandomStringUtils.randomAlphabetic(1);
		String firstFive = RandomStringUtils.randomAlphabetic(5);

		// get valid generic top-level domains
		String[] domainPrefixes = DomainValidator.getTLDEntries(ArrayType.GENERIC_RO);

		// essentially, this removes the potentially-naughty domain names
		final ArrayList<String> cleanPrefixes = new ArrayList<>();
		for (final String domain : domainPrefixes) {
			if (StringUtils.containsNone(domain, "--")) {
				cleanPrefixes.add(domain);
			}
		}

		// and make our random selection
		final String domain = cleanPrefixes.get(new Random().nextInt(cleanPrefixes.size()));

		// set a few top-level domain codes and grab a random one
		final String[] codes = { "com", "org", "gov", "us", "tech", "edu" };
		//final String code = codes[(int) (Math.random() * codes.length)];
		final String code = codes[new Random().nextInt(codes.length)];

		// put it all together
		return StringUtils.lowerCase(String.format("%s.%s@%s.%s", lastInitial, firstFive, domain, code));
	}
}
