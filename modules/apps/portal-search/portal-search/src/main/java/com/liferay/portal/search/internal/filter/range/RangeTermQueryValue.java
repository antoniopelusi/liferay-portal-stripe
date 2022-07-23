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

package com.liferay.portal.search.internal.filter.range;

/**
 * @author Adam Brandizzi
 */
public class RangeTermQueryValue {

	public RangeTermQueryValue() {
	}

	public RangeTermQueryValue(RangeTermQueryValue rangeTermQueryValue) {
		_includesLower = rangeTermQueryValue._includesLower;
		_includesUpper = rangeTermQueryValue._includesUpper;
		_lowerBound = rangeTermQueryValue._lowerBound;
		_upperBound = rangeTermQueryValue._upperBound;
	}

	public String getLowerBound() {
		return _lowerBound;
	}

	public String getUpperBound() {
		return _upperBound;
	}

	public boolean isIncludesLower() {
		return _includesLower;
	}

	public boolean isIncludesUpper() {
		return _includesUpper;
	}

	public static class Builder {

		public RangeTermQueryValue build() {
			return new RangeTermQueryValue(_rangeTermQueryValue);
		}

		public void includesLower(boolean includesLower) {
			_rangeTermQueryValue._includesLower = includesLower;
		}

		public void includesUpper(boolean includesUpper) {
			_rangeTermQueryValue._includesUpper = includesUpper;
		}

		public void lowerBound(String lowerBound) {
			_rangeTermQueryValue._lowerBound = lowerBound;
		}

		public void upperBound(String upperBound) {
			_rangeTermQueryValue._upperBound = upperBound;
		}

		private final RangeTermQueryValue _rangeTermQueryValue =
			new RangeTermQueryValue();

	}

	private boolean _includesLower;
	private boolean _includesUpper;
	private String _lowerBound;
	private String _upperBound;

}