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

package com.liferay.object.model.impl;

import com.liferay.object.model.ObjectViewColumn;
import com.liferay.object.model.ObjectViewFilterColumn;
import com.liferay.object.model.ObjectViewSortColumn;

import java.util.List;

/**
 * @author Gabriel Albuquerque
 */
public class ObjectViewImpl extends ObjectViewBaseImpl {

	@Override
	public List<ObjectViewColumn> getObjectViewColumns() {
		return _objectViewColumns;
	}

	public List<ObjectViewFilterColumn> getObjectViewFilterColumns() {
		return _objectViewFilterColumns;
	}

	public List<ObjectViewSortColumn> getObjectViewSortColumns() {
		return _objectViewSortColumns;
	}

	@Override
	public void setObjectViewColumns(List<ObjectViewColumn> objectViewColumns) {
		_objectViewColumns = objectViewColumns;
	}

	public void setObjectViewFilterColumns(
		List<ObjectViewFilterColumn> objectViewFilterColumns) {

		_objectViewFilterColumns = objectViewFilterColumns;
	}

	public void setObjectViewSortColumns(
		List<ObjectViewSortColumn> objectViewSortColumns) {

		_objectViewSortColumns = objectViewSortColumns;
	}

	private List<ObjectViewColumn> _objectViewColumns;
	private List<ObjectViewFilterColumn> _objectViewFilterColumns;
	private List<ObjectViewSortColumn> _objectViewSortColumns;

}