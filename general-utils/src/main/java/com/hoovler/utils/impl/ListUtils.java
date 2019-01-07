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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoovler.utils.models.Velocity;

// TODO: Auto-generated Javadoc
/** The Class ListUtils. */
public class ListUtils {
	private static Logger log = LogManager.getLogger(ListUtils.class.getName());

	/** Return a reversed copy a given list, without affecting the original list.
	 * 
	 * @author      Michael Hoovler
	 * @param       <T> A generic type. Accepts a list of any type with elements of any type.
	 * @param  list Any non-empty list which implements <code>java.util.List</code>, containing elements of any type.
	 * @return      A reversed copy of the original list. */
	public static <T> List<T> reverseList(List<T> list) {
		log.debug("Reversing list...");

		return list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), lst -> {
			Collections.reverse(lst);
			return lst.stream();
		})).collect(Collectors.toCollection(ArrayList::new));
	}

	/** A semi-Python-like, but not fully Pythonic, list subsetting utility method.
	 * 
	 * <p>This method calls {@link #subset(List, int, Velocity)} with:</p><pre>return subset(list, start, new Velocity(velocity));</pre>
	 * <p>The values of <code>start</code> and <code>stop</code> are not treated in the same manner as the generic <code>sublist(int start, int stop)</code> values, but treated as a python subset command, such as <code>lst[-1:-len(lst)]</code>, which would return the entire list in reverse.</p>
	 * 
	 * <p>A positive <code>start</code> value results in the returned list starting with the element <b>start</b> number of elements from the beginning of the list passed in, while a negative means the returned list starts on the <b>abs(start) - 1</b> number of elements from the end of the passed-in list.</p>
	 * 
	 * <p>The <code>velocity</code> parameter is treated as a non-scalar value, from which is derived both direction and magnitude (number of elements) of the returned sublist. Its sign determines the direction, and its absolute value indicates the number of elements in the return value. An abs(velocity) > list.size() results in the full list, starting with <code>start</code>, ending with <code>list.get((start + velocity) % list.size())</code>, moving forward or backward along the original list - as determined by the sign of the velocity.</p>
	 * 
	 * 
	 * @author          Michael Hoovler
	 * @param           <T> the generic type
	 * @param  list     the list from which to derive the subset
	 * @param  start    the starting index. If <code>start &lt; 0</code>,
	 * @param  velocity the velocity
	 * @return          The desired list subset:
	 * 
	 *                  <p><u><b>Examples</b></u></p>
	 *                  <pre>
	 *                  // returns a size=1 list with the first element
	 *                  subset = ListUtils.subset(list, 0, 0);
	 * 
	 *                  // returns a size=1 list with the nth element
	 *                  subset = ListUtils.subset(list, n, 0);
	 * 
	 *                  // create list and populate with 10 elements
	 *                  List lst = new List();
	 *                  for (i = 0:9) { lst.add("elem_"+i) }
	 * 
	 *                  // normal order, full list
	 *                  subset = ListUtils.subset(list, 0, list.size());
	 *                  result: elem_0, elem_1, ... elem_9
	 * 
	 *                  // start with last index, move right three elements
	 *                  subset = ListUtils.subset(list, -1, 3);
	 *                  result: elem_9, elem_0, elem_1, elem_2
	 * 
	 *                  // start with last index, move left three elements
	 *                  subset = ListUtils.subset(list, -1, -3);
	 *                  result: elem_9, elem_8, elem_7, elem_6
	 * 
	 *                  // full list in reverse, starting with index 2 and ending with index 3
	 *                  subset = ListUtils.subset(list, 2, -123);
	 *                  result: elem_2, elem_1, elem_0, elem_9, elem_8, ... elem_3
	 *                  </pre>
	 * 
	 * @see             com.hoovler.utils.impl.ListUtils#subset(List, int, Velocity) */
	public static <T> List<T> subset(List<T> list, int start, int velocity) {
		return subset(list, start, new Velocity(velocity));
	}

	/** A semi-Python-like, but not fully Pythonic, list subsetting utility method.
	 * 
	 * <p>See {@link #subset(List, int, int)} for a detailed description.</p>
	 * 
	 *
	 * @author          Michael Hoovler
	 * @param           <T> the generic type
	 * @param  list     the list
	 * @param  start    the start
	 * @param  velocity the velocity
	 * @return          the list */
	public static <T> List<T> subset(List<T> list, int start, Velocity velocity) {
		// diagnostic: method entry
		log.debug("subsetting list...");

		// extract velocity vector data
		int startIndex = start < 0 ? list.size() - Math.abs(start) : start;
		int mag = velocity.magnitude();
		List<T> subset = new ArrayList<>();
		final int max = list.size();

		switch (velocity.direction()) {

		case Velocity.DEFAULT_POS_DIR:
			log.debug("Iterate forward along list...");
			for (int i = 0; i < mag; i++) {
				subset.add(list.get((startIndex + i) % max));
			}
			return subset;

		case Velocity.DEFAULT_NEG_DIR:
			log.debug("Iterate backwards along list...");
			int index = startIndex;
			for (int i = 0; i < mag; i++) {
				subset.add(list.get(index));
				index = index - 1 < 0 ? max - 1 : index - 1;
			}
			return subset;

		case Velocity.DEFAULT_ZER_DIR:
		default:
			log.debug("No iteration; returned list has one value...");
			subset.add(list.get(startIndex));
			return subset;
		}

	}

	private ListUtils() {
		// no-op
	}
}
