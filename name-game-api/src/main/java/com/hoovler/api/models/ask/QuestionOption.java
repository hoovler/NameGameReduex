/*
 * Copyright (c) Michael Hoovler (hoovlermichael@gmail.com) 2018
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.hoovler.api.models.ask;

// TODO: Auto-generated Javadoc
/** The Class AskOption.
 * <p>This class represents a single option within the list of options presented to the player</p> */
public class QuestionOption {

	/** The option id, same as the question's option IDs.
	 * <p><code>id == Question.getOptions().getId() == Profile.getId()</code></p> */
	private String optionId;

	/** The option value.
	 * <p>Could be either a name string or an image URL string, depending on <code>Mode</code>.</p> */
	private String optionValue;

	/** Gets QuestionOption.optionId
	 *
	 * @return the option id */
	public String getOptionId() {
		return optionId;
	}

	/** Gets QuestionOption.optionValue
	 *
	 * @return the option value */
	public String getOptionValue() {
		return optionValue;
	}

	/** Sets QuestionOption.optionId
	 *
	 * @param optionId the new option id */
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	/** Sets QuestionOption.optionValue
	 *
	 * @param optionValue the new option value */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	/** Instantiates a new ask option. */
	public QuestionOption() {

	}

	/** Instantiates a new ask option.
	 *
	 * @param optionId the option id */
	public QuestionOption(String optionId) {
		this.optionId = optionId;
	}

	/** Instantiates a new ask option.
	 *
	 * @param optionId    the option id
	 * @param optionValue the option value */
	public QuestionOption(String optionId, String optionValue) {
		this.optionId = optionId;
		this.optionValue = optionValue;
	}

}
