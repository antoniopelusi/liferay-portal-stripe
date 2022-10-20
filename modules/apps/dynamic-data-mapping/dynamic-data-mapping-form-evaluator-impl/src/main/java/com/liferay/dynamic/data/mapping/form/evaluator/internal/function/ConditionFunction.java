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

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;

/**
 * @author Selton Guedes
 */
public class ConditionFunction
	implements DDMExpressionFunction.Function3
		<Boolean, Object, Object, Object> {

	public static final String NAME = "condition";

	@Override
	public Object apply(Boolean condition, Object object1, Object object2) {
		if (condition) {
			return object1;
		}

		return object2;
	}

	@Override
	public String getName() {
		return NAME;
	}

}