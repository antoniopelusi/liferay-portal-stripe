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

import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.DateFormatFactoryImpl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Selton Guedes
 */
@RunWith(Parameterized.class)
public class CompareDatesFunctionTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Parameterized.Parameters(name = "date1={0}, date2={1}, expectedResult={2}")
	public static List<Object[]> data() {
		return Arrays.asList(
			new Object[][] {
				{"2022-04-03", "2022-04-03", true},
				{"2021-04-03", "2022-04-03", false},
				{"2022-04-03 10:13:00", "2022-04-03", true},
				{"2022-04-03 10:13:00", "2022-04-03 10:13:00", true},
				{"2021-04-03 10:13:00", "2022-04-02", false},
				{"2021-04", "2022-04-02", false}
			});
	}

	@Before
	public void setUp() throws Exception {
		_setUpDateFormatFactoryUtil();
	}

	@Test
	public void testApply() {
		Assert.assertEquals(
			expectedResult, _compareDatesFunction.apply(date1, date2));
	}

	@Parameterized.Parameter
	public Object date1;

	@Parameterized.Parameter(1)
	public Object date2;

	@Parameterized.Parameter(2)
	public boolean expectedResult;

	private void _setUpDateFormatFactoryUtil() {
		DateFormatFactoryUtil dateFormatFactoryUtil =
			new DateFormatFactoryUtil();

		dateFormatFactoryUtil.setDateFormatFactory(new DateFormatFactoryImpl());
	}

	private final CompareDatesFunction _compareDatesFunction =
		new CompareDatesFunction();

}