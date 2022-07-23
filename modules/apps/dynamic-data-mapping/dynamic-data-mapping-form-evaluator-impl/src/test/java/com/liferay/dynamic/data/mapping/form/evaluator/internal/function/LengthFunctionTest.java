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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Rodrigo Paulino
 */
public class LengthFunctionTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testApplyWithoutText() {
		LengthFunction lengthFunction = new LengthFunction();

		Assert.assertEquals(Integer.valueOf(0), lengthFunction.apply(null));
		Assert.assertEquals(
			Integer.valueOf(0), lengthFunction.apply(StringPool.BLANK));
	}

	@Test
	public void testApplyWithText() {
		LengthFunction lengthFunction = new LengthFunction();

		Assert.assertEquals(
			Integer.valueOf(8), lengthFunction.apply("12345678"));
	}

}