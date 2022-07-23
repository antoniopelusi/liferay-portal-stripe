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

package com.liferay.portal.rules.engine.drools.test;

import com.liferay.petra.string.StringBundler;

/**
 * @author Michael C. Han
 */
public class UserProfile {

	public int getAge() {
		return _age;
	}

	public String getAgeGroup() {
		return _ageGroup;
	}

	public void setAge(int age) {
		_age = age;
	}

	public void setAgeGroup(String ageGroup) {
		_ageGroup = ageGroup;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{age=", _age, ", _ageGroup=", _ageGroup, "}");
	}

	private int _age;
	private String _ageGroup;

}