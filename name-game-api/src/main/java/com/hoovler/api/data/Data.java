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
package com.hoovler.api.data;

import java.util.ArrayList;

import com.hoovler.dao.DefaultPlayerDao;
import com.hoovler.dao.DefaultProfileDao;
import com.hoovler.dao.DefaultQuestionDao;
import com.hoovler.dao.models.Player;
import com.hoovler.dao.models.Profile;
import com.hoovler.dao.models.Question;
import com.hoovler.dao.models.Stat;

public class Data {

	// static members

	public static final int numOptions = 6;

	// standard class members and methods...

	private DefaultPlayerDao playerDao;

	private DefaultProfileDao profileDao;

	private DefaultQuestionDao questionDao;

	public DefaultPlayerDao getPlayerPersistence() {
		return this.playerDao;
	}

	public DefaultProfileDao getProfilePersistence() {
		return this.profileDao;
	}

	public DefaultQuestionDao getQuestionPersistence() {
		return this.questionDao;
	}

	public Data() {
		this.playerDao = new DefaultPlayerDao();
		this.profileDao = new DefaultProfileDao();
		this.questionDao = new DefaultQuestionDao();
	}

	// special class methods

	// ******************************************** get lists

	public ArrayList<Player> playerPool() {
		return this.playerDao.playerList();
	}

	public ArrayList<Profile> profilePool() {
		return this.profileDao.profileList();
	}

	public ArrayList<Question> questionsAsked() {
		return this.questionDao.questionList();
	}

	// ******************************************** player list specials

	public boolean playerExists(String email) {
		return this.playerDao.getPlayer(email) != null;
	}

	public Player getPlayer(String email) {
		if (!playerExists(email)) {
			return this.playerDao.addPlayer(email);
		} else {
			return this.playerDao.getPlayer(email);
		}
	}
	
	public ArrayList<Stat> retrievePlayerStats(String email) {
		if (playerExists(email)) {
			return this.playerDao.getPlayer(email).getStats();
		} else {
			return new ArrayList<Stat>();
		}
	}
	
	/**
	 * Updates a <code>Player</code> object by adding a new <code>Stat</code> object to its stats list.
	 *
	 * @param email the player's email address; AKA, the unique ID used for a player record. 
	 * @param statistics the Stat object to add to the player's list of stats
	 * @return 	- if the player's email is found, returns the newly-updated <code>Player</code> <br/> 
	 * 			- if no corresponding player is found, returns a new instance of the player, with a new Stat in its List<Stat> stats list.
	 */
	public Player updatePlayer(String email, Stat statistics) {
		Player player;
		ArrayList<Stat> stats;
		if (playerExists(email)) {
			player = this.playerDao.getPlayer(email);
			stats = player.getStats();
			
			stats.add(statistics);
			player.setStats(stats);
			
			this.playerDao.updatePlayer(email, player);
			return this.playerDao.getPlayer(email);
		} else {
			player = getPlayer(email);
			stats = new ArrayList<>();
			
			stats.add(statistics);
			player.setStats(stats);
			
			return this.playerDao.addPlayer(player);
		}
	}
}