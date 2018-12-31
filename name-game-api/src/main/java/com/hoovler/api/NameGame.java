/* 
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2018
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hoovler.api.resources.Players;
import com.hoovler.api.resources.Questions;
import com.hoovler.dao.DefaultProfileDao;

/**
 * The Class NameGame.
 */
@SpringBootApplication
public class NameGame {
	private static Logger log = LogManager.getLogger(NameGame.class.getName());
	
	/**
	 * Profile service.
	 *
	 * @return the default profile dao
	 */
	@Bean
	protected DefaultProfileDao profileService() {
		return new DefaultProfileDao();
	}
	
	/**
	 * Player service.
	 *
	 * @return the players
	 */
	@Bean
	protected Players playerService() {
		return new Players();
	}
	
	/**
	 * Question service.
	 *
	 * @return the questions
	 */
	@Bean
	protected Questions questionService() {
		return new Questions();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		log.info("========== APPLICATION INITIALIZED ============");
		SpringApplication.run(NameGame.class, args);
	}
}
