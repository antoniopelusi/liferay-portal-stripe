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

package com.liferay.portal.kernel.search.filter;

import com.liferay.petra.string.StringBundler;

/**
 * @author Michael C. Han
 */
public class MissingFilter extends BaseFilter {

	public MissingFilter(String field) {
		_field = field;
	}

	@Override
	public <T> T accept(FilterVisitor<T> filterVisitor) {
		return filterVisitor.visit(this);
	}

	public String getField() {
		return _field;
	}

	@Override
	public int getSortOrder() {
		return 2;
	}

	public Boolean isExists() {
		return _exists;
	}

	public Boolean isNullValue() {
		return _nullValue;
	}

	public void setExists(boolean exists) {
		_exists = exists;
	}

	public void setNullValue(boolean nullValue) {
		_nullValue = nullValue;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{(", _field, ", _exists=", _exists, ", _nullValue=", _nullValue,
			"), ", super.toString(), "}");
	}

	private Boolean _exists;
	private final String _field;
	private Boolean _nullValue;

}