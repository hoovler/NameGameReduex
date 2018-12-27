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
package com.hoovler.api.models;

import java.util.ArrayList;

import com.hoovler.dao.models.Player;

public class Leaderboard {
	private ArrayList<Player> players;

	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * <p>Instantiates a new leaderboard, and populates the list of players based on the
	 * input parameters.</p>
	 *
	 * @param skip <i>integer; skip over x players</i>
	 * <p>		
	 *		<td>Integer value representing how many Player elements to skip over in the sorted list.
	 * 		If <code>top == true</code>, then <code>skip</code> can also be understood as the element
	 * 		in an ordered list of players to begin pulling those which will be added to the list within
	 * 		this object.  As indices in Java begin at 0, a <skip>skip</skip> value of 1 (skip 1 player)
	 * 		begins the new list with the Player object in the second element, element 1.
	 * </p>
	 * <p>	If <code>skip < 1</code>, then no elements are excluded from the beginning of the list.</p>
	 * <p>	If <code>skip > PlayerDao.playerList().size</code>, then the <code>ArrayList<Player> players</code>
	 * 		of this object will be an empty list.</p>
	 * 
	 * @param top <i>boolean, start at the top?</i>
	 * 
	 * <p>	<td>The boolean indicating whether to skip elements from the top of the list, or the bottom.
	 *      If <code>top == true</code>, then elements will be skipped from the top of the list - starting
	 *      with the Player with the highest score, then the next highest, and so on.  If <code>top == true</code>
	 *      and <code>skip == 0</code>, then the values in the <code>ArrayList<Player> players</code> will 
	 *      start with the highest-ranking player in element 0, the next-highest in element 1, and so on.
	 * <p>	If <code>top == false</code>, however, then the elements will be skipped from the <b>bottom</b>
	 *      of the list - that is, starting with the Player with the <em>lowest</em> score, then the next
	 *      to lowest, and so on.  It also affects the direction that <code>length</code> will pull from
	 *      the list, regardless of whether <code>skip</code> is given at all.  If <code>top == false</code>,
	 *      the values in the <code>ArrayList<Player> players</code> will start with the <em>lowest-ranking</em>
	 *      player in element 0, the next-to-lowest in element 1, and so on...</p></td>
	 * </tr>
	 * </table>
	 * 
	 * @param length the length
	 * <table border=1>
	 * <tr>
	 * 		<td><code>length</code></td>
	 * 
	 *		<td>The integer value that dictates how many players should be in the <code>ArrayList<Player> players</code>.
	 *		<p>If <code>length < 1</code>, this object's player list will be empty.</p>
	 *		<p>If <code>length > PlayerDao.playerList().size</code>, this object's player list will have ALL the
	 *		Player objects available.</p></td>
	 * </tr>
	 * </table>
	 * 
	 */
	public Leaderboard(int skip, boolean top, int length) {
		// a simple list of all players, sorted by score in descending order
	}
}
