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

import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Leonardo Barros
 */
public class IsURLFunctionTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testInvalidURL() {
		IsURLFunction isURLFunction = new IsURLFunction();

		Assert.assertFalse(isURLFunction.apply(null));
		Assert.assertFalse(isURLFunction.apply(Http.HTTP_WITH_SLASH));
		Assert.assertFalse(isURLFunction.apply(Http.HTTPS_WITH_SLASH));
		Assert.assertFalse(isURLFunction.apply(RandomTestUtil.randomString()));
	}

	@Test
	public void testValidURL() {
		IsURLFunction isURLFunction = new IsURLFunction();

		Assert.assertTrue(isURLFunction.apply("http://www.liferay.com"));
	}

}