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

/**
 * @author Michael C. Han
 */
public abstract class BaseFilter implements Filter {

	@Override
	public String getExecutionOption() {
		return _executionOption;
	}

	@Override
	public Boolean isCached() {
		return true;
	}

	@Override
	public void setCached(Boolean cached) {
		_cached = cached;
	}

	@Override
	public void setExecutionOption(String executionOption) {
		_executionOption = executionOption;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"(cached=", _cached, ", executionOption=", _executionOption, ")");
	}

	private Boolean _cached;
	private String _executionOption;

}