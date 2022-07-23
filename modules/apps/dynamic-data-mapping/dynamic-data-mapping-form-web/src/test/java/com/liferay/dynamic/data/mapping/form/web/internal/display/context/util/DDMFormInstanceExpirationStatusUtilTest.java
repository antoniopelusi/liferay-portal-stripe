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

package com.liferay.dynamic.data.mapping.form.web.internal.display.context.util;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceSettings;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.TimeZone;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Rodrigo Paulino
 */
public class DDMFormInstanceExpirationStatusUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testFormExpired() throws Exception {
		DDMFormInstanceSettings ddmFormInstanceSettings =
			_mockDDMFormInstanceSettings("1987-09-22", false);

		Assert.assertTrue(
			DDMFormInstanceExpirationStatusUtil.isFormExpired(
				_mockDDMFormInstance(ddmFormInstanceSettings), null));
		Assert.assertTrue(
			DDMFormInstanceExpirationStatusUtil.isFormExpired(
				_mockDDMFormInstance(ddmFormInstanceSettings),
				TimeZone.getDefault()));
	}

	@Test
	public void testFormNotExpiredWithInexistentForm() throws Exception {
		Assert.assertFalse(
			DDMFormInstanceExpirationStatusUtil.isFormExpired(null, null));
	}

	@Test
	public void testFormNotExpiredWithNeverExpireSetting() throws Exception {
		DDMFormInstanceSettings ddmFormInstanceSettings =
			_mockDDMFormInstanceSettings(null, true);

		Assert.assertFalse(
			DDMFormInstanceExpirationStatusUtil.isFormExpired(
				_mockDDMFormInstance(ddmFormInstanceSettings), null));
	}

	private DDMFormInstance _mockDDMFormInstance(
			DDMFormInstanceSettings ddmFormInstanceSettings)
		throws Exception {

		DDMFormInstance ddmFormInstance = Mockito.mock(DDMFormInstance.class);

		Mockito.when(
			ddmFormInstance.getSettingsModel()
		).thenReturn(
			ddmFormInstanceSettings
		);

		return ddmFormInstance;
	}

	private DDMFormInstanceSettings _mockDDMFormInstanceSettings(
		String expirationDate, boolean neverExpire) {

		DDMFormInstanceSettings ddmFormInstanceSettings = Mockito.mock(
			DDMFormInstanceSettings.class);

		if (expirationDate != null) {
			Mockito.when(
				ddmFormInstanceSettings.expirationDate()
			).thenReturn(
				expirationDate
			);
		}

		Mockito.when(
			ddmFormInstanceSettings.neverExpire()
		).thenReturn(
			neverExpire
		);

		return ddmFormInstanceSettings;
	}

}