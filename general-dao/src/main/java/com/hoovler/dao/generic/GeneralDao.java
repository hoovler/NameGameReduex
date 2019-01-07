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

import java.util.HashMap;
import java.util.List;

public abstract class GeneralDao {
	protected HashMap<Object, Object> items;
	
	public abstract boolean exists(Object key);
	
	/**
	 * Adds an object / record to the DAO
	 *
	 * @param key the unique identifier for the added value
	 * @param value the value to add
	 * @return If the object exists, <code>null</code>; otherwise, the object already associated with the key. 
	 */
	public Object add(Object key, Object value) {
		return this.items.put(key, value);
	}

	public boolean delete(Object key) {
		return this.items.remove(key) != null;
	}
	
	public abstract List<Object> asList();
	public abstract HashMap<Object, Object> asMap();
	public abstract Object add(Object value);
	public abstract Object update(Object key, Object value);
	public abstract List<Object> endpointHandler();
}
