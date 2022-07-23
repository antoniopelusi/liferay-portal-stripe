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

package com.liferay.portal.search.internal.sort;

import com.liferay.portal.search.script.Script;
import com.liferay.portal.search.sort.NestedSort;
import com.liferay.portal.search.sort.ScriptSort;
import com.liferay.portal.search.sort.SortMode;
import com.liferay.portal.search.sort.SortVisitor;

/**
 * @author Michael C. Han
 */
public class ScriptSortImpl extends BaseSortImpl implements ScriptSort {

	public ScriptSortImpl(Script script, ScriptSortType scriptSortType) {
		_script = script;
		_scriptSortType = scriptSortType;
	}

	@Override
	public <T> T accept(SortVisitor<T> sortVisitor) {
		return sortVisitor.visit(this);
	}

	@Override
	public NestedSort getNestedSort() {
		return _nestedSort;
	}

	@Override
	public Script getScript() {
		return _script;
	}

	@Override
	public ScriptSortType getScriptSortType() {
		return _scriptSortType;
	}

	@Override
	public SortMode getSortMode() {
		return _sortMode;
	}

	@Override
	public void setNestedSort(NestedSort nestedSort) {
		_nestedSort = nestedSort;
	}

	@Override
	public void setSortMode(SortMode sortMode) {
		_sortMode = sortMode;
	}

	private NestedSort _nestedSort;
	private final Script _script;
	private final ScriptSortType _scriptSortType;
	private SortMode _sortMode;

}