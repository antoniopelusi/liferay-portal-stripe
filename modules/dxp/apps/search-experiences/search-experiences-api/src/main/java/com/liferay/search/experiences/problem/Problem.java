/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.problem;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Petteri Karttunen
 * @author Brian Wing Shun Chan
 */
public class Problem {

	public Problem(Problem problem) {
		_className = problem._className;
		_elementKey = problem._elementKey;
		_languageKey = problem._languageKey;
		_message = problem._message;
		_throwable = problem._throwable;
		_rootConfiguration = problem._rootConfiguration;
		_rootJSONObject = problem._rootJSONObject;
		_rootJSONObjectPropertyKey = problem._rootJSONObjectPropertyKey;
		_rootJSONObjectValue = problem._rootJSONObjectValue;
		_severity = problem._severity;
	}

	public String getClassName() {
		return _className;
	}

	public String getElementKey() {
		return _elementKey;
	}

	public String getLanguageKey() {
		return _languageKey;
	}

	public String getMessage() {
		return _message;
	}

	public String getRootConfiguration() {

		// TODO Remove this

		return _rootConfiguration;
	}

	public JSONObject getRootJSONObject() {
		return _rootJSONObject;
	}

	public String getRootJSONObjectPropertyKey() {
		return _rootJSONObjectPropertyKey;
	}

	public String getRootJSONObjectValue() {
		return _rootJSONObjectValue;
	}

	public Severity getSeverity() {
		return _severity;
	}

	public Throwable getThrowable() {
		return _throwable;
	}

	public void setElementKey(String elementKey) {
		_elementKey = elementKey;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{_className=", _className, ", _elementKey=", _elementKey,
			", _languageKey=", _languageKey, ", _message=", _message,
			", _rootConfiguration=", _rootConfiguration, ", _rootJSONObject=",
			_rootJSONObject, ", _rootJSONObjectPropertyKey=",
			_rootJSONObjectPropertyKey, ", _rootJSONObjectValue=",
			_rootJSONObjectValue, ", _severity=", _severity, ", _throwable=",
			_throwable, "}");
	}

	public static class Builder {

		public Builder() {
			_problem = new Problem();
		}

		public Builder(Problem problem) {
			_problem = problem;
		}

		public Problem build() {
			return new Problem(_problem);
		}

		public Builder className(String className) {
			_problem._className = className;

			return this;
		}

		public Builder elementKey(String elementKey) {
			_problem._elementKey = elementKey;

			return this;
		}

		public Builder languageKey(String languageKey) {
			_problem._languageKey = languageKey;

			return this;
		}

		public Builder message(String message) {
			_problem._message = message;

			return this;
		}

		public Builder rootConfiguration(String rootConfiguration) {
			_problem._rootConfiguration = rootConfiguration;

			return this;
		}

		public Builder rootJSONObject(JSONObject rootJSONObject) {
			_problem._rootJSONObject = rootJSONObject;

			return this;
		}

		public Builder rootJSONObjectPropertyKey(
			String rootJSONObjectPropertyKey) {

			_problem._rootJSONObjectPropertyKey = rootJSONObjectPropertyKey;

			return this;
		}

		public Builder rootJSONObjectValue(String rootJSONObjectValue) {
			_problem._rootJSONObjectValue = rootJSONObjectValue;

			return this;
		}

		public Builder severity(Severity severity) {
			_problem._severity = severity;

			return this;
		}

		public Builder throwable(Throwable throwable) {
			_problem._throwable = throwable;

			return this;
		}

		private final Problem _problem;

	}

	private Problem() {
	}

	private String _className;
	private String _elementKey;
	private String _languageKey;
	private String _message;
	private String _rootConfiguration;
	private JSONObject _rootJSONObject;
	private String _rootJSONObjectPropertyKey;
	private String _rootJSONObjectValue;
	private Severity _severity;
	private Throwable _throwable;

}