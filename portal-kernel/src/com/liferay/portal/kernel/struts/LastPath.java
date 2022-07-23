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

package com.liferay.portal.kernel.struts;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class LastPath implements Serializable {

	public LastPath(String contextPath, String path) {
		this(contextPath, path, StringPool.BLANK);
	}

	public LastPath(String contextPath, String path, String parameters) {
		_contextPath = contextPath;
		_path = path;
		_parameters = parameters;
	}

	public String getContextPath() {
		return _contextPath;
	}

	public String getParameters() {
		return _parameters;
	}

	public String getPath() {
		return _path;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{contextPath=", _contextPath, ", path=", _path, "}");
	}

	private final String _contextPath;
	private final String _parameters;
	private final String _path;

}