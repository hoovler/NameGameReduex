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
package com.hoovler.utils.impl;

import org.apache.commons.lang3.StringUtils;

import com.hoovler.utils.enums.SimpleType;

// TODO: Auto-generated Javadoc
/** The Class TypeUtils. */
public class TypeUtils {

	/** Checks if is boolean.
	 *
	 * @param      <T> the generic type
	 * @param  obj the obj
	 * @return     true, if is boolean */
	public static <T> boolean isBoolean(T obj) {
		return StringUtils.equals(obj.getClass().getSimpleName(), SimpleType.BOOLEAN.val());
	}

	/** Checks if is short.
	 *
	 * @param      <T> the generic type
	 * @param  obj the obj
	 * @return     true, if is short */
	public static <T> boolean isShort(T obj) {
		return StringUtils.equals(obj.getClass().getSimpleName(), SimpleType.SHORT.val());
	}

	/** Checks if is int.
	 *
	 * @param      <T> the generic type
	 * @param  obj the obj
	 * @return     true, if is int */
	public static <T> boolean isInt(T obj) {
		return StringUtils.equals(obj.getClass().getSimpleName(), SimpleType.INTEGER.val());
	}

	/** Checks if is double.
	 *
	 * @param      <T> the generic type
	 * @param  obj the obj
	 * @return     true, if is double */
	public static <T> boolean isDouble(T obj) {
		return StringUtils.equals(obj.getClass().getSimpleName(), SimpleType.DOUBLE.val());
	}

	/** Checks if is long.
	 *
	 * @param      <T> the generic type
	 * @param  obj the obj
	 * @return     true, if is long */
	public static <T> boolean isLong(T obj) {
		return StringUtils.equals(obj.getClass().getSimpleName(), SimpleType.LONG.val());
	}

	/** Checks if is char.
	 *
	 * @param      <T> the generic type
	 * @param  obj the obj
	 * @return     true, if is char */
	public static <T> boolean isChar(T obj) {
		return StringUtils.equals(obj.getClass().getSimpleName(), SimpleType.CHARACTER.val());
	}

	/** Checks if is string.
	 *
	 * @param      <T> the generic type
	 * @param  obj the obj
	 * @return     true, if is string */
	public static <T> boolean isString(T obj) {
		return StringUtils.equals(obj.getClass().getSimpleName(), SimpleType.STRING.val());
	}

	/** Checks if is type.
	 *
	 * @param       <T> the generic type
	 * @param  obj  the obj
	 * @param  type the type
	 * @return      true, if is type */
	public static <T> boolean isType(T obj, String type) {
		return StringUtils.equalsAnyIgnoreCase(obj.getClass().getSimpleName(), type);
	}

	/** Checks if is type.
	 *
	 * @param        <T> the generic type
	 * @param        <C> the generic type
	 * @param  obj   the obj
	 * @param  clazz the clazz
	 * @return       true, if is type */
	public static <T, C> boolean isType(T obj, C clazz) {
		return StringUtils.equalsIgnoreCase(clazz.getClass().getSimpleName(), obj.getClass().getSimpleName());
	}

	private TypeUtils() {
	}
}
