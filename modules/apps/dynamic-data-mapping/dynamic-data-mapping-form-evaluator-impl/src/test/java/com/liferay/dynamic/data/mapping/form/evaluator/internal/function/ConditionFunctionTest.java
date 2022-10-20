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

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Selton Guedes
 */
@RunWith(Parameterized.class)
public class ConditionFunctionTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Parameterized.Parameters(
		name = "condition={0}, object1={1}, object2={2}, expectedObject={3}"
	)
	public static List<Object[]> data() {
		return Arrays.asList(
			new Object[][] {
				{true, "expected", StringUtil.randomString(), "expected"},
				{false, StringUtil.randomString(), "expected", "expected"},
				{true, 2, 7, 2}, {false, 2, 7, 7},
				{false, new BigDecimal(1), new BigDecimal(4), new BigDecimal(4)}
			});
	}

	@Test
	public void testApply() {
		ConditionFunction conditionalFunction = new ConditionFunction();

		Object actualResult = conditionalFunction.apply(
			condition, object1, object2);

		Assert.assertEquals(expectedObject, actualResult);
	}

	@Parameterized.Parameter
	public boolean condition;

	@Parameterized.Parameter(3)
	public Object expectedObject;

	@Parameterized.Parameter(1)
	public Object object1;

	@Parameterized.Parameter(2)
	public Object object2;

}