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

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.dao.DefaultPlayerDao;
import com.hoovler.dao.PlayerDao;

public class Game {
	private static Logger log = LogManager.getLogger(Game.class.getName());

	private PlayerDao playerDao;
	
	private boolean gameStarted = false;
	
	public Game(String playerEmail, int gameMode) {
		if(!gameStarted) {
			startGame();
		}
		
		//return new Ask(playerEmail, gameMode, this.playerDao);
	}
	
	protected void startGame() {
		this.playerDao = new DefaultPlayerDao();
	}


	public class Ask {
		
		private Question question;
		private int mode;
		
		public Ask(String playerEmail, int gameMode, PlayerDao playerDao) {
			if(StringUtils.isNotBlank(playerDao.getPlayer(playerEmail).getEmail())) {
				
			} else {
				// add new player
			}
		}
	}

	public class Answer {
		private boolean correct;
		private String answerId;
		
		public Answer(String answerId) {
			this.answerId = answerId;
		}
	}
}
