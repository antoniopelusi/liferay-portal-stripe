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

package com.liferay.portal.kernel.dao.orm;

/**
 * @author Brian Wing Shun Chan
 */
public class ProjectionFactoryUtil {

	public static Projection alias(Projection projection, String alias) {
		return _projectionFactory.alias(projection, alias);
	}

	public static Projection avg(String propertyName) {
		return _projectionFactory.avg(propertyName);
	}

	public static Projection count(String propertyName) {
		return _projectionFactory.count(propertyName);
	}

	public static Projection countDistinct(String propertyName) {
		return _projectionFactory.countDistinct(propertyName);
	}

	public static Projection distinct(Projection projection) {
		return _projectionFactory.distinct(projection);
	}

	public static ProjectionFactory getProjectionFactory() {
		return _projectionFactory;
	}

	public static Projection groupProperty(String propertyName) {
		return _projectionFactory.groupProperty(propertyName);
	}

	public static Projection max(String propertyName) {
		return _projectionFactory.max(propertyName);
	}

	public static Projection min(String propertyName) {
		return _projectionFactory.min(propertyName);
	}

	public static ProjectionList projectionList() {
		return _projectionFactory.projectionList();
	}

	public static Projection property(String propertyName) {
		return _projectionFactory.property(propertyName);
	}

	public static Projection rowCount() {
		return _projectionFactory.rowCount();
	}

	public static Projection sqlGroupProjection(
		String sql, String groupBy, String[] columnAliases, Type[] types) {

		return _projectionFactory.sqlGroupProjection(
			sql, groupBy, columnAliases, types);
	}

	public static Projection sqlProjection(
		String sql, String[] columnAliases, Type[] types) {

		return _projectionFactory.sqlProjection(sql, columnAliases, types);
	}

	public static Projection sum(String propertyName) {
		return _projectionFactory.sum(propertyName);
	}

	public void setProjectionFactory(ProjectionFactory projectionFactory) {
		_projectionFactory = projectionFactory;
	}

	private static ProjectionFactory _projectionFactory;

}