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

package com.liferay.petra.content;

import com.liferay.petra.string.StringUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Peter Fellwock
 * @see    com.liferay.util.ContentUtil
 */
public class ContentUtil {

	public static String get(ClassLoader classLoader, String location) {
		return _contentUtil._get(classLoader, location, false);
	}

	public static String get(
		ClassLoader classLoader, String location, boolean all) {

		return _contentUtil._get(classLoader, location, all);
	}

	private ContentUtil() {
	}

	private String _get(ClassLoader classLoader, String location, boolean all) {
		String content = _contentPool.get(location);

		if (content == null) {
			try {
				content = StringUtil.read(classLoader, location, all);

				_put(location, content);
			}
			catch (IOException ioException) {
				_logger.log(
					Level.SEVERE, ioException.getMessage(), ioException);
			}
		}

		return content;
	}

	private void _put(String location, String content) {
		_contentPool.put(location, content);
	}

	private static final Logger _logger = Logger.getLogger(
		ContentUtil.class.getName());

	private static final ContentUtil _contentUtil = new ContentUtil();

	private final Map<String, String> _contentPool = new HashMap<>();

}