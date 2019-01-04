/*
 * Copyright (c) Michael Hoovler <hoovlermichael@gmail.com> 2019
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.DefaultPlayerDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Stats;
import com.hoovler.utils.impl.ListUtils;

// TODO: Auto-generated Javadoc
/** The Class Players. */
public class Players extends DefaultPlayerDao {
	private static Logger log = LogManager.getLogger(Players.class.getName());

	// TODO: Use hibernate to store persistence objects; javax.persistence.EntityManager
	/** Player exists.
	 *
	 * @param  email the email
	 * @return       true, if successful */
	public boolean playerExists(String email) {
		return super.getPlayer(email) != null;
	}

	/** Retrieve player stats.
	 *
	 * @param  email the email
	 * @return       the stats */
	public Stats retrievePlayerStats(String email) {
		if (playerExists(email)) {
			return super.getPlayer(email).getStats();
		} else {
			return new Stats();
		}
	}

	/** Adds the player if the player doesn't yet exist. Otherwise, don't add anything.
	 *
	 * @param  email the email
	 * @return       the newly-added Player if player was new; otherwise, return existing player object */
	public Player getOrAddPlayer(String email) {
		if (!playerExists(email)) {
			log.info("Player not found; Returning a new player object initilized with 'email' set to " + email);
			return super.addPlayer(new Player(email, new Stats()));
		}
		log.info("Player with 'email'=" + email + " found!");
		return super.getPlayer(email);
	}

	/** Retrieve the subset of the playerlist, sorted by rank (score), and to the specifications of the endpoint params
	 * <p>In this case, having <code>start</code> and <code>velocity</code> at 0 is unlikely the users' intention. As such, default param values for BOTH will result in the entire list.</p>
	 *
	 * @param  start    the start
	 * @param  velocity the velocity
	 * @return          the array list */
	public ArrayList<Player> playerList(int start, int velocity) {
		if (start == 0 && velocity == 0) {
			return orderByScore();
		}
		return orderByScore(start, velocity);
	}

	/** Order by score.
	 *
	 * @return the array list */
	public ArrayList<Player> orderByScore() {
		return orderByScore(0, playerList().size());
	}

	/** Order by score.
	 *
	 * @param  start    the start
	 * @param  velocity the velocity
	 * @return          the array list */
	public ArrayList<Player> orderByScore(int start, int velocity) {
		List<Player> ranked = playerList().stream().sorted(Comparator.comparingDouble(Player::scoreFromPlayerStats))
				.collect(Collectors.toList());
		return (ArrayList<Player>) ListUtils.subset(ranked, start, velocity);

		// playerList().sort(Comparator.comparing(Player::getScore));
	}
}
