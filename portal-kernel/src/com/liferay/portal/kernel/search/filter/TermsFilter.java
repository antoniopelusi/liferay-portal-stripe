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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class TermsFilter extends BaseFilter {

	public TermsFilter(String field) {
		_field = field;
	}

	@Override
	public <T> T accept(FilterVisitor<T> filterVisitor) {
		return filterVisitor.visit(this);
	}

	public void addValue(String value) {
		_values.add(value);
	}

	public void addValues(String... values) {
		Collections.addAll(_values, values);
	}

	public String getField() {
		return _field;
	}

	@Override
	public int getSortOrder() {
		return 4;
	}

	public String[] getValues() {
		return _values.toArray(new String[0]);
	}

	public boolean isEmpty() {
		return _values.isEmpty();
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{(", _field, "=", _values, "), ", super.toString(), "}");
	}

	private final String _field;
	private final Set<String> _values = new HashSet<>();

}