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

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoTestsHelper {
	private static Logger log = LogManager.getLogger(DaoTestsHelper.class.getName());
	
	/**
	 * Checks for given value in given list.  
	 * 
	 * Operation is case-sensitive; if case-insensitive operation is required, 
	 * ensure all given parameters are converted before calling method.
	 *
	 * @param strList the str list
	 * @param key the key
	 * @return true, if successful
	 */
	protected static boolean hasValue(String[] strList, String key) {
		log.traceEntry();
		for(String val : strList) {
			if(StringUtils.equals(val, key)) return true;
		}
		return false;
	}
	
	/**
	 * Checks for partial value within a given list.
	 * 
	 * Operation is case-sensitive; if case-insensitive operation is required, 
	 * ensure all given parameters are converted before calling method.
	 * 
	 * @param strList the str list
	 * @param partialKey the partial key
	 * @return true, if successful
	 */
	protected static boolean hasPartialValue(String[] strList, String partialKey) {
		log.traceEntry();
		for(String val : strList) {
			if(StringUtils.contains(val, partialKey)) return true;
		}
		return false;
	}
	
	/**
	 * Checks for a string ending with a given value within a given list.
	 * 
	 * Operation is case-sensitive; if case-insensitive operation is required, 
	 * ensure all given parameters are converted before calling method.
	 * 
	 * @param strList the str list
	 * @param keyEnding the key ending
	 * @return true, if successful
	 */
	protected static boolean hasValueEnding(String[] strList, String keyEnding) {
		log.traceEntry();
		for(String val : strList) {
			if(StringUtils.endsWith(val, keyEnding)) return true;
		}
		return false;
	}
}
