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
package com.hoovler.api.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.DefaultPlayerDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Stats;

public class Players extends DefaultPlayerDao {
		private static Logger log = LogManager.getLogger(Players.class.getName());
		// TODO: Use hibernate to store persistence objects; javax.persistence.EntityManager
	/**
	 * Player exists.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean playerExists(String email) {
		return super.getPlayer(email) != null;
	}

	/**
	 * Retrieve player stats.
	 *
	 * @param email the email
	 * @return the stats
	 */
	public Stats retrievePlayerStats(String email) {
		if (playerExists(email)) {
			return super.getPlayer(email).getStats();
		} else {
			return new Stats();
		}
	}

	/**
	 * Adds the player if the player doesn't yet exist.  Otherwise, don't add anything.
	 *
	 * @param email - the player's email
	 * @return the newly-added Player if player was new; otherwise, return existing player object
	 */
	public Player getOrAddPlayer(String email) {
		if (!playerExists(email)) {
			log.info("Player not found; Returning a new player object initilized with 'email' set to " + email);
			return super.addPlayer(new Player(email, new Stats()));
		}
		log.info("Player with 'email'=" + email + " found!");
		return super.getPlayer(email);
	}
}
