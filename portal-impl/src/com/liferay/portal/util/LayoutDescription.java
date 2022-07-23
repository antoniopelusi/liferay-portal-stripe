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

package com.liferay.portal.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class LayoutDescription implements Serializable {

	public LayoutDescription(long plid, String name, int depth) {
		_plid = plid;
		_name = name;
		_depth = depth;
	}

	public int getDepth() {
		return _depth;
	}

	public String getDisplayName() {
		StringBundler sb = new StringBundler(_depth + 1);

		for (int i = 0; i < _depth; i++) {
			sb.append("-&nbsp;");
		}

		sb.append(HtmlUtil.escape(_name));

		return sb.toString();
	}

	public String getName() {
		return _name;
	}

	public long getPlid() {
		return _plid;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{depth=", _depth, ", name=", _name, ", plid=", _plid, "}");
	}

	private final int _depth;
	private final String _name;
	private final long _plid;

}