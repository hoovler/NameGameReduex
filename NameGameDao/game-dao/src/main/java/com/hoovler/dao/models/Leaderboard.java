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
package com.hoovler.dao.models;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.DefaultPlayerDao;

/**
 * <p><h3>Leaderboard</h3>
 * <p><b><u>Purpose</u></b></p>
 * This Class ...</p>
 * <p><b><u>Information</u></b><br />
 * The <code>Leaderboard</code> object is...</p>
 * <p><b><u>Examples</u></b></p>
 * An example:
 * <pre>some code</pre>
 * Another example:
 * <pre>some more code;</pre>
 */
public class Leaderboard extends DefaultPlayerDao{
	private static Logger log = LogManager.getLogger(Leaderboard.class.getName());
	
	private TreeMap<Float, Player> leaders;

	@Override
	public Player getPlayer(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player addPlayer(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player updatePlayer(String email, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePlayer(String email) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	/* 
	 * Return the list of players, sorted by score
	 * 
	 */
	public ArrayList<Player> playerList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
		
}
