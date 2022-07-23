/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.json;

import com.liferay.petra.string.StringBundler;

/**
 * @author Miguel Pastor
 */
public class FooBean5 {

	public double getDoubleValue() {
		return _doubleValue;
	}

	public int getIntegerValue() {
		return _integerValue;
	}

	public long getLongValue() {
		return _longValue;
	}

	public void setDoubleValue(double doubleValue) {
		_doubleValue = doubleValue;
	}

	public void setIntegerValue(int integerValue) {
		_integerValue = integerValue;
	}

	public void setLongValue(long longValue) {
		_longValue = longValue;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{doubleValue=", _doubleValue, ", integerValue=", _integerValue,
			", longValue=", _longValue, "}");
	}

	private double _doubleValue;
	private int _integerValue;
	private long _longValue;

}