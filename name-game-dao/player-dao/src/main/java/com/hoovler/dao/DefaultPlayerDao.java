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
package com.hoovler.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.models.Player;

public class DefaultPlayerDao implements PlayerDao {
	private static Logger log = LogManager.getLogger(DefaultPlayerDao.class.getName());

	private HashMap<String, Player> playerMap = new HashMap<>();

	public DefaultPlayerDao() {
		// start off with a dummy player...
		addPlayer(randomPlayer());
	}

	@Override
	public Player getPlayer(String email) {
		return playerMap.get(email);
	}

	@Override
	public Player addPlayer(Player player) {
		if (player.getEmail() == null || StringUtils.isBlank(player.getEmail())) {
			log.warn("No email found...");
		} else {
			playerMap.put(player.getEmail(), player);
		}
		return player;
	}

	/* (non-Javadoc)
	 * @see com.hoovler.dao.PlayerDao#updatePlayer(java.lang.String, com.hoovler.dao.models.Player)
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

	@Override
	public boolean deletePlayer(String email) {
		Player removedPlayer = this.playerMap.remove(email);
		return removedPlayer != null;
	}

	@Override
	public ArrayList<Player> playerList() {
		return new ArrayList<>(playerMap.values());
	}

	/**
	 * Random player.
	 *
	 * @return the player
	 */
	private static Player randomPlayer() {
		log.info("randomPlayer() ...");
		return randomPlayer(null);
	}

	/**
	 * Random player.
	 *
	 * @param email the email
	 * @return the player
	 */
	private static Player randomPlayer(String email) {
		log.info("Generating random player...");
		
		log.info("randomPlayer(String email)");
		
		Player player = new Player();
		player.setEmail(StringUtils.defaultIfBlank(email, PlayerDaoHelper.getRandomEmail()));
		
		log.info("email = " + player.getEmail());

		return player;
	}

}
