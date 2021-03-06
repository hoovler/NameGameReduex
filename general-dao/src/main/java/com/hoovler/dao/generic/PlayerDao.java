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
package com.hoovler.dao.generic;

import java.util.List;

import com.hoovler.dao.models.Player;

/**
 * The Interface PlayerDao.
 * @author Michael Hoovler &lt;hoovlermichael@gmail.com&gt;
 */
public interface PlayerDao {
		/**
	 * Gets the player.
	 *
	 * @param email the email
	 * @return the player
	 */
	Player getPlayer(String email);
		
		/**
		 * Adds the player.
		 *
		 * @param player the player
		 * @return the player
		 */
		Player addPlayer(Player player);
		
		/**
		 * Update player.
		 * 
		 * @author <a href="https://github.com/hoovler">github.com/hoovler</a>
		 * @param email the email
		 * @param player the player
		 * @return <ul>		
		 * <li>the original <code>Player</code> object that was changed if a <code>Player</code> object with the associated <code>email</code> is found.</li>  
		 * <li><code>NULL</code> otherwise</li>
		 * </ul>
		 */
	Player updatePlayer(String email, Player player);
		
		/**
		 * Delete player.
		 *
		 * @param email the email
		 * @return true, if successful
		 */
		boolean deletePlayer(String email);
		
		/**
		 * Player list.
		 *
		 * @return the list
		 */
		List<Player> playerList();
}
