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

package com.liferay.document.library.repository.cmis.search;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

/**
 * @author Mika Koivisto
 */
public class CMISSimpleExpression implements CMISCriterion {

	public CMISSimpleExpression(
		String field, String value,
		CMISSimpleExpressionOperator cmisSimpleExpressionOperator) {

		_field = field;
		_value = value;
		_cmisSimpleExpressionOperator = cmisSimpleExpressionOperator;
	}

	@Override
	public String toQueryFragment() {
		return StringBundler.concat(
			_field, StringPool.SPACE, _cmisSimpleExpressionOperator,
			StringPool.SPACE, StringPool.APOSTROPHE, _value,
			StringPool.APOSTROPHE);
	}

	private final CMISSimpleExpressionOperator _cmisSimpleExpressionOperator;
	private final String _field;
	private final String _value;

}