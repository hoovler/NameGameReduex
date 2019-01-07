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
package com.hoovler.utils.enums;

import org.apache.commons.lang3.StringUtils;

public enum SimpleType {
	BOOLEAN("Boolean"),
	SHORT("Short"),
	INTEGER("Integer"),
	DOUBLE("Double"),
	LONG("Long"),
	CHARACTER("Character"),
	STRING("String");
	
	private String val;
	private static final String pkg = "java.lang";
	
	private SimpleType(String val) {
		this.val = val;
	}

	public String val() {
		return this.val;
	}
	
	public String pkg() {
		return pkg;
	}
	
	public String canonical() {
		return StringUtils.joinWith(".", pkg, this.val);
	}
	
	public String primitive() {
		String primitive = this.val.toLowerCase();
		switch(primitive) {
		case "integer":
			return "int";
		case "character":
			return "char";
		case "string":
			return "String";
		default:
			return primitive;
		}
	}
}
