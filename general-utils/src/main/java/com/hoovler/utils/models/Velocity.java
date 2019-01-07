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
package com.hoovler.utils.models;

// TODO: Auto-generated Javadoc
/** An object assisting in the operations of list subsetting.
 * 
 * <p><code>com.hoovler.utils.impl.ListUtils.subset()</code> requires a <code>velocity</code> argument, which is, similar to a velocity in classical physics, a non-scalar value which carries more than one discrete observation.</p>
 * <p>In this case, the <em>direction</em> and <em>magnitude</em> are derived from the <code>vector</code> argument. This class allows the <code>int vector</code> to be transformed into an object within which these two attributes explicitly retained.</p>
 * <p>The <code>magnitude</code> is always the absolute value of the <code>vector</code>. Operating over the two-dimensional space of a list renders <code>direction</code> simple to compute:<ul><li><code>vector &lt; 0</code> ? <code>direction = LEFT</code></li><li><code>vector &gt; 0</code> ? <code>direction = RIGHT</code></li><li><code>vector == 0</code> ? <code>direction = NONE</code></li></ul></p>
 * <p>This class also contains <code>static</code> methods, in lieu of creating a seperate singleton companion.  This allows a developer to invoke the following methods without having to instantiate a new <code>Velocity</code> object:</p>
 * <ul><li><code>Vector.direction(rawVelocity)</code><ul>Returns the string direction associated with the vector, which will be one of the aforementioned direction values.</ul></li><li><code>Vector.dir(rawVelocity)</code><ul>Returns the first character of the direction string associated with the vector: <em>R</em>, <em>L</em>, or <em>N</em></ul></li><li><code>Vector.magnitude(rawVelocity)</code><ul>Returns the magnitude of the given vector, calculated as: <code>Math.abs(vector)</code></ul></li></ul> 
 * */
public class Velocity {

	/** The Constant DEFAULT_NEG_DIR. */
	public static final String DEFAULT_NEG_DIR = "LEFT";

	/** The Constant DEFAULT_POS_DIR. */
	public static final String DEFAULT_POS_DIR = "RIGHT";

	/** The Constant DEFAULT_ZER_DIR. */
	public static final String DEFAULT_ZER_DIR = "NONE";

	// member variables
	private int value;
	private String direction;
	private char dir;
	private int magnitude;

	/** The raw value of the integer with which this object was initialized.
	 *
	 * @return the int */
	public int value() {
		return this.value;
	}

	/** Return this object's full-string <code>direction</code> value.
	 *
	 * @return the string */
	public String direction() {
		return direction;
	}

	/** Return the value of this object's single-character <code>dir</code> attribute.
	 *
	 * @return The first character of the string value of this object's <code>direction</code> attribute */
	public char dir() {
		return this.dir;
	}

	/** Return the value of this object's <code>magnitude</code> attribute.
	 *
	 * @return the int */
	public int magnitude() {
		return this.magnitude;
	}

	/** Set this object's raw value
	 * <p><b>Note:</b> This is the only setter method for this class.</p>
	 *
	 * @param value the value */
	public void setValue(int value) {
		initVelocity(value);
	}

	/** Instantiates a new Velocity object.
	 * <p>This is the only constructor because all other properties are determined by the raw velocity <code>value</code>, and not having a default constructor with no params was a design decision to avoid complexity.  <code>Velocity</code> <em><b>must</b></em> be initialized with a raw velocity value.</p>
	 *
	 * @param value The raw velocity value */
	public Velocity(int value) {
		initVelocity(value);
	}

	/** Inits the velocity.
	 *
	 * @param raw the raw value of velocity */
	private void initVelocity(int raw) {
		// set simple / one-line derivation values
		this.value = raw;
		this.magnitude = Math.abs(raw);
		this.dir = deriveDir(raw);
		this.direction = deriveDirection(raw);
	}

	/* PROTECTED SETTERS: for possible future inheritence, although a simple call to super.initVelocity(velocity) would cover it. */

	/** Sets Velocity.direction
	 *
	 * @param direction the new direction */
	protected void setDirection(String direction) {
		this.direction = direction;
	}

	/** Sets Velocity.dir
	 *
	 * @param dir the new dir */
	protected void setDir(char dir) {
		this.dir = dir;
	}

	/** Sets Velocity.magnitude
	 *
	 * @param magnitude the new magnitude */
	protected void setMagnitude(int magnitude) {
		this.magnitude = magnitude;
	}

	/* STATIC METHODS: To derive values related to a vector without creating a new instance of the Velocity class */

	/** Determine full-string direction of given velocity in a static manner, without creating an instance of the <code>Velocity</code> object.
	 * <p><b>NOTE:</b> Uses default direction values.</p>
	 *
	 * @param  velocity the velocity
	 * @return          The full-string direction associated with this velocity. */
	public static String direction(int velocity) {
		return deriveDirection(velocity);
	}

	/** Determine single-character direction of given velocity in a static manner, without creating an instance of the <code>Velocity</code> object.
	 * <p><b>NOTE:</b> Uses default direction values.</p>
	 *
	 * @param  velocity the velocity
	 * @return          The single-character direction associated with the velocity. */
	public static char dir(int velocity) {
		return deriveDir(velocity);
	}

	/** Determine the magnitude of given velocity in a static manner, without creating an instance of the <code>Velocity</code> object.
	 *
	 * @param  velocity the velocity
	 * @return          the int */
	public static int magnitude(int velocity) {
		return Math.abs(velocity);
	}

	/** Derive the full <code>direction</code> string associated with the given raw <code>velocity</code> value.
	 *
	 * @param  velocity the raw velocity value
	 * @return          The appropriate directional string */
	protected static String deriveDirection(int velocity) {
		// return default values for direction
		switch (Integer.signum(velocity)) {
		case -1:
			return DEFAULT_NEG_DIR;
		case 1:
			return DEFAULT_POS_DIR;
		case 0:
		default:
			return DEFAULT_ZER_DIR;
		}
	}

	/** Derive the single-character <code>dir</code> value associated with the raw <code>velocity</code>.
	 *
	 * @param  velocity the raw velocity value
	 * @return          The appropriate directional character */
	protected static char deriveDir(int velocity) {
		// return default values for direction
		switch (Integer.signum(velocity)) {
		case -1:
			return DEFAULT_NEG_DIR.charAt(0);
		case 1:
			return DEFAULT_POS_DIR.charAt(0);
		case 0:
		default:
			return DEFAULT_ZER_DIR.charAt(0);
		}

	}

}
