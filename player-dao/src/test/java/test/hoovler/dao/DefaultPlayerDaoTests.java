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
package test.hoovler.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.hoovler.dao.DefaultPlayerDao;
import com.hoovler.dao.PlayerDao;
import com.hoovler.dao.models.Player;

class DefaultPlayerDaoTests {
	private static Logger log = LogManager.getLogger(DefaultPlayerDaoTests.class.getName());

	@Test
	void testDefaultPlayerDaoConstructor() {
		log.info("testPlayerDaoList");
		
		ArrayList<Player> playerList = new DefaultPlayerDao().playerList();
		assertNotNull(playerList);
		
		// the list should have one random player, by default...
		log.info("playerList.size() = " + playerList.size());
		assertEquals(playerList.size(), 1);
		
		log.info(playerList.get(0).getEmail());
		assertNotNull(playerList.get(0));
	}
	
	@Test
	void testAddPlayer() {
		PlayerDao playerDao = new DefaultPlayerDao();
		
		// one object to start
		assertEquals(playerDao.playerList().size(), 1);
		
		// now to add and test
		Player nuPlayer = new Player();
		
		String email = "testemail@email.com";
		Long numGuesses = new Long(100);
		Long numCorrect = numGuesses / 2;
		
		nuPlayer.setEmail(email);
		nuPlayer.setNumberGuesses(numGuesses);
		nuPlayer.setNumberCorrect(numCorrect);
		
		// add
		playerDao.addPlayer(nuPlayer);
		
		// retrieve
		Player oldPlayer = playerDao.getPlayer(email);
		log.info("oldPlayer.getEmail() = " + oldPlayer.getEmail());
	}

}
