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
package com.hoovler.dao.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Stats;
import com.hoovler.utils.impl.BoolUtils;
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

	/** Order by score.
	 *
	 * @param  start    the start
	 * @param  velocity the velocity
	 * @return          the array list */
	public ArrayList<Player> rankedList() {
		return (ArrayList<Player>) ListUtils.reverseList(playerList().stream()
				.sorted(Comparator.comparingDouble(Player::scoreFromPlayerStats)).collect(Collectors.toList()));

		// playerList().sort(Comparator.comparing(Player::getScore));
	}

	/** Endpoint Handler: Return a subset of player objects that are, if applicable, ordered by rank.
	 *
	 * @param  startVal the start val
	 * @param  stopVal  the stop val
	 * @param  velVal   the vel val
	 * @param  ranked   the ranked
	 * @return          the array list */
	public ArrayList<Player> endpointHandler(String startVal, String stopVal, String velVal, boolean ranked) {
		// com.hoovler.utils.impl.BoolUtils has a more robust check for numeric values than StringUtils...
		int start = BoolUtils.isNumeric(startVal) ? Integer.parseInt(startVal) : 0;
		int stop = BoolUtils.isNumeric(stopVal) ? Integer.parseInt(stopVal) : 0;
		int velocity = BoolUtils.isNumeric(velVal) ? Integer.parseInt(velVal) : 0;

		// determine which subsetting, if any, to use
		if (intNotDefault(startVal)) {
			log.info("startVal is not default.");
			if (intNotDefault(stopVal)) {
				// return an index-based subset
				log.info("returning an index-based subset: start=" + start + " | stop=" + stop);
				return ranked ? new ArrayList<>( rankedList().subList(start, stop) )
						: new ArrayList<>( playerList().subList(start, stop) );
			}

			if (intNotDefault(velVal)) {
				// return a directional subset
				log.info("returning a directional subset: start=" + start + " | velocity=" + velocity);
				return ranked ? new ArrayList<>( ListUtils.subset(rankedList(), start, velocity) )
						: new ArrayList<>( ListUtils.subset(playerList(), start, velocity) );
			}
		}
		// if none of the above, or input errors, just return the whole list
		log.info("returning the entire list, size = " + playerList().size());
		return ranked ? rankedList() : playerList();
	}

	/**
	 * Determine whether or not an expected integer value was passed in from the endpoint
	 *
	 * @param val the value to test
	 * @return true if the value isn't blank.
	 */
	private boolean intNotDefault(String val) {
		return StringUtils.isNotBlank(val);
	}

	/** Endpoint Handler: Update a player object with new information from a well-formatted request body payload string.
	 *
	 * @param  email   the email
	 * @param  payload the payload
	 * @return         the player */
	public Player endpointHandler(String email, String payload) {
		// TODO: add payload parser
		return new Player();
	}
}
