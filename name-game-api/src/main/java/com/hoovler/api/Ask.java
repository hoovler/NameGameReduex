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
package com.hoovler.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.api.data.Data;
import com.hoovler.api.data.Meta;
import com.hoovler.api.models.Mode;
import com.hoovler.dao.models.Question;

public class Ask {
	private static Logger log = LogManager.getLogger(Ask.class.getName());
	
	private Meta meta;
	private Data data;
	
	public Ask(String playerEmail, Mode mode, Data data) {
		this.meta = new Meta();
		meta.setGameMode(mode.ordinal());
		meta.setPlayerEmail(playerEmail);
		
		Question question = formulateQuestion(this.meta.getGameMode()); 
	}
	
	private Question formulateQuestion(Mode mode) {
		switch(mode) {
		case Mode.NORMAL:
			log.info("NORMAL");
			
		}
	}
}
