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

package com.liferay.portal.vulcan.graphql.dto;

/**
 * @author Javier de Arcos
 */
public class GraphQLDTOProperty {

	public static GraphQLDTOProperty of(
		String name, boolean readOnly, Class<?> typeClass) {

		return new GraphQLDTOProperty(name, readOnly, typeClass);
	}

	public static GraphQLDTOProperty of(String name, Class<?> typeClass) {
		return new GraphQLDTOProperty(name, false, typeClass);
	}

	public GraphQLDTOProperty(
		String name, boolean readOnly, Class<?> typeClass) {

		_name = name;
		_readOnly = readOnly;
		_typeClass = typeClass;
	}

	public GraphQLDTOProperty(String name, Class<?> typeClass) {
		this(name, false, typeClass);
	}

	public String getName() {
		return _name;
	}

	public Class<?> getTypeClass() {
		return _typeClass;
	}

	public boolean isReadOnly() {
		return _readOnly;
	}

	private final String _name;
	private final boolean _readOnly;
	private final Class<?> _typeClass;

}