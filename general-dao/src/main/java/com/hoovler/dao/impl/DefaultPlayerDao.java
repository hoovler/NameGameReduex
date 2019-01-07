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
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.generic.PlayerDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Stats;

/**
 * The Class DefaultPlayerDao.
 */
public class DefaultPlayerDao implements PlayerDao {
	private static Logger log = LogManager.getLogger(DefaultPlayerDao.class.getName());

	private HashMap<String, Player> playerMap = new HashMap<>();

	/**
	 * Instantiates a new default player dao.
	 */
	public DefaultPlayerDao() {
		this.playerMap = new HashMap<>();
	}

	/* (non-Javadoc)
	 * @see com.hoovler.dao.generic.PlayerDao#getPlayer(java.lang.String)
	 */
	@Override
	public Player getPlayer(String email) {
		return playerMap.get(email);
	}

	/* (non-Javadoc)
	 * @see com.hoovler.dao.generic.PlayerDao#addPlayer(com.hoovler.dao.models.Player)
	 */
	@Override
	public Player addPlayer(Player player) {
		if (player.getEmail() == null || StringUtils.isBlank(player.getEmail())) {
			log.warn("ID required: player object must have an email.");
		} else {
			playerMap.put(player.getEmail(), player);
		}
		return player;
	}
		
		/**
		 * Adds the player.
		 *
		 * @param email the email
		 * @return the player
		 */
		public Player addPlayer(String email) {
		if (StringUtils.isBlank(email)) {
			log.warn("ID required: player object must have an email.");
		} else {
			playerMap.put(email, new Player(email, new Stats()));
		}
		return playerMap.get(email);
	}

	/* (non-Javadoc)
	 * @see com.hoovler.dao.generic.PlayerDao#updatePlayer(java.lang.String, com.hoovler.dao.models.Player)
	 */
	@Override
	public Player updatePlayer(String email, Player player) {
		if (!playerMap.containsKey(email)) {
			log.warn("No player with email=" + email + " found... not updating player.");
			return null;
		} else {
			return playerMap.put(email, player);
		}
	}

	/* (non-Javadoc)
	 * @see com.hoovler.dao.generic.PlayerDao#deletePlayer(java.lang.String)
	 */
	@Override
	public boolean deletePlayer(String email) {
		Player removedPlayer = this.playerMap.remove(email);
		return removedPlayer != null;
	}

	/* (non-Javadoc)
	 * @see com.hoovler.dao.generic.PlayerDao#playerList()
	 */
	@Override
	public ArrayList<Player> playerList() {
		return new ArrayList<>(playerMap.values());
	}
}
