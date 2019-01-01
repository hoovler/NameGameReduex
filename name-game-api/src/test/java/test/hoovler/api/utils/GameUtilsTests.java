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
package test.hoovler.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.hoovler.api.utils.NameGameHelper;

class GameUtilsTests {
	private static Logger log = LogManager.getLogger(GameUtilsTests.class.getName());

	private static String playerEmail;
	private static String questionId;
		@BeforeAll
	static void setUpBeforeClass() throws Exception {
		playerEmail = "foo@bar.com";
		questionId = "12345678987654321";
	}

	/**
	 * <p>Testing GameUtils.encodeToHexWithSalt() and GameUtils.decodeFromHexWithSalt()</p>
	 * 
	 * <p><b><u>Functionality Tested</u></b></p>
	 * <p>When a player recieves a question, they are given a <code>questionId</code>
	 * (not to be confused with <code>Question.qId</code>, but a derivative thereof)
	 * to be used as the ending directory in <b>POST</b> URI when the same player
	 * responds with an answer.</p>
	 * <p>This <code>questionId</code> is built using the <code>playerEmail</code>
	 * and the <code>Question.qId</code>, and is unique to those two values; however,
	 * it can be deconstructed into its two constituent values, which is exactly how
	 * the two algorithms called in this test are supposed to do.</p>
	 * <p>The functionality is similar to that of password-based / salted encryption & 
	 * decryption algorithms for requirements involving a reversible hash; however, 
	 * security is not a requirement here. Rather than using a standard Java cipher 
	 * suite the methods herein use a simple String-to-ASCII-to-Hex process.
	 * The only notable downsides are longer and less secure "encrypted" values.
	 * To mitigate these concerns: 
	 * <ul>
	 * <li>The "encrypted" hex values are treated as Strings</li>
	 * <li>Critical information <b>cannot</b> be exposed or otherwise compromised 
	 * should a user determine the methods used.</li>
	 * <li>Nearly all PBE algorithms are no more than "security by obscurity"</li>
	 * <li><b>The specific algorithms being tested are not used for 
	 * security, but for validation.</b></li></ul></p>
	 * <p><b><u>Purpose</u></b></p>
	 * <p>To ensure the application <b>successfully generates an ID which is unique to the 
	 * player, <em>and</em> with which the application can decompose in order to validate 
	 * that the player providing an answer is the player to whom the question was 
	 * asked.</b></p>
	 */
	@Test
	void testEncodeDecode() {
		String encoded = NameGameHelper.encodeToHexWithSalt(questionId, playerEmail);
		log.debug("encoded = " + encoded);
		String decoded = NameGameHelper.decodeFromHexWithSalt(encoded, playerEmail);
		log.debug("decoded = " + decoded);
		assertEquals(decoded, questionId);
	}
}
