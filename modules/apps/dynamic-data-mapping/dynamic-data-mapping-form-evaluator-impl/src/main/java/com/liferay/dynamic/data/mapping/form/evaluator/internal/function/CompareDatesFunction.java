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
import com.liferay.dynamic.data.mapping.form.validation.util.DateParameterUtil;

import java.time.LocalDate;

import java.util.Objects;

/**
 * @author Selton Guedes
 */
public class CompareDatesFunction
	implements DDMExpressionFunction.Function2<Object, Object, Boolean> {

	public static final String NAME = "compareDates";

	@Override
	public Boolean apply(Object date1, Object date2) {
		LocalDate localDate1 = DateParameterUtil.getLocalDate(date1.toString());

		LocalDate localDate2 = DateParameterUtil.getLocalDate(date2.toString());

		if (Objects.isNull(localDate1) || Objects.isNull(localDate2)) {
			return false;
		}

		return localDate1.isEqual(localDate2);
	}

	@Override
	public String getName() {
		return NAME;
	}

}