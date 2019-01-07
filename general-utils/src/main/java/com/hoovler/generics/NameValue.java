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
package com.hoovler.generics;

// TODO: Auto-generated Javadoc
/**
 * The Class NameValue.
 *
 * @author Michael
 */
/**
 * @author Michael
 *
 */
public class NameValue implements ItemPair<String, String> {

	private String name;
	private String value;

	/* (non-Javadoc)
	 * @see com.hoovler.generics.ItemPair#getLeft()
	 */
	@Override
	public String getLeft() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.hoovler.generics.ItemPair#getRight()
	 */
	@Override
	public String getRight() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.hoovler.generics.ItemPair#setLeft(java.lang.Object)
	 */
	@Override
	public void setLeft(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.hoovler.generics.ItemPair#setRight(java.lang.Object)
	 */
	@Override
	public void setRight(String value) {
		this.value = value;
	}

	/**
	 * Sets NameValue.name
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		setLeft(name);
	}

	/**
	 * Sets NameValue.value
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		setRight(value);
	}

	/**
	 * Instantiates a new name value.
	 */
	public NameValue() {
	}

	/**
	 * Instantiates a new name value.
	 *
	 * @param name the name
	 */
	public NameValue(String name) {
		this.name = name;
	}
	
	/**
	 * Instantiates a new name value.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public NameValue(String name, String value) {
		this.name = name;
		this.value = value;
	}

}
