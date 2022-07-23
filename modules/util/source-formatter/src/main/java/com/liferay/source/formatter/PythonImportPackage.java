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

package com.liferay.source.formatter;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.tools.ImportPackage;

/**
 * @author Alan Huang
 */
public class PythonImportPackage extends ImportPackage {

	public PythonImportPackage(String importString, String line) {
		super(importString, false, line);

		_importString = importString;
	}

	@Override
	public String getPackageLevel() {
		int pos = _importString.indexOf(StringPool.PERIOD);

		if (pos == -1) {
			return _importString;
		}

		return _importString.substring(0, pos);
	}

	private final String _importString;

}