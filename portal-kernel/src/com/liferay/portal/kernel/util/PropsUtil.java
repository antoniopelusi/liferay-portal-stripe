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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.configuration.Filter;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class PropsUtil {

	public static boolean contains(String key) {
		return _props.contains(key);
	}

	public static String get(String key) {
		return _props.get(key);
	}

	public static String get(String key, Filter filter) {
		return _props.get(key, filter);
	}

	public static String[] getArray(String key) {
		return _props.getArray(key);
	}

	public static String[] getArray(String key, Filter filter) {
		return _props.getArray(key, filter);
	}

	public static Properties getProperties() {
		return _props.getProperties();
	}

	public static Properties getProperties(
		String prefix, boolean removePrefix) {

		return _props.getProperties(prefix, removePrefix);
	}

	public static Props getProps() {
		return _props;
	}

	public static void setProps(Props props) {
		_props = props;
	}

	private static Props _props;

}