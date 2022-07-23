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

package com.liferay.portal.kernel.search;

import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class Stats implements Serializable {

	public static final Stats STANDARD_STATS = new Stats(
		true, true, true, true, true);

	public Stats() {
	}

	public Stats(
		boolean min, boolean max, boolean sum, boolean count, boolean missing) {

		_min = min;
		_max = max;
		_sum = sum;
		_count = count;
		_missing = missing;
	}

	public String getField() {
		return _field;
	}

	public boolean isCount() {
		return _count;
	}

	public boolean isEnabled() {
		if (_count || _max || _mean || _min || _missing || _standardDeviation ||
			_sum || _sumOfSquares) {

			return true;
		}

		return false;
	}

	public boolean isMax() {
		return _max;
	}

	public boolean isMean() {
		return _mean;
	}

	public boolean isMin() {
		return _min;
	}

	public boolean isMissing() {
		return _missing;
	}

	public boolean isStandardDeviation() {
		return _standardDeviation;
	}

	public boolean isSum() {
		return _sum;
	}

	public boolean isSumOfSquares() {
		return _sumOfSquares;
	}

	public void setCount(boolean count) {
		_count = count;
	}

	public void setField(String field) {
		_field = field;
	}

	public void setMax(boolean max) {
		_max = max;
	}

	public void setMean(boolean mean) {
		_mean = mean;
	}

	public void setMin(boolean min) {
		_min = min;
	}

	public void setMissing(boolean missing) {
		_missing = missing;
	}

	public void setStandardDeviation(boolean standardDeviation) {
		_standardDeviation = standardDeviation;
	}

	public void setSum(boolean sum) {
		_sum = sum;
	}

	public void setSumOfSquares(boolean sumOfSquares) {
		_sumOfSquares = sumOfSquares;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{count=", _count, ", field=", _field, ", max=", _max, ", mean=",
			_mean, ", min=", _min, ", missing=", _missing,
			", standardDeviation=", _standardDeviation, ", sum=", _sum,
			", sumOfSquares=", _sumOfSquares, "}");
	}

	private boolean _count;
	private String _field;
	private boolean _max;
	private boolean _mean;
	private boolean _min;
	private boolean _missing;
	private boolean _standardDeviation;
	private boolean _sum;
	private boolean _sumOfSquares;

}