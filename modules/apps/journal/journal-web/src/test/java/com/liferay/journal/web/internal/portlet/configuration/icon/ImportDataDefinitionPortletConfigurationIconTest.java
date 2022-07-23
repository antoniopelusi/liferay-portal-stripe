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

package com.liferay.journal.web.internal.portlet.configuration.icon;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Marcela Cunha
 */
@PrepareForTest({ResourceBundleUtil.class, LanguageUtil.class})
@RunWith(PowerMockRunner.class)
public class ImportDataDefinitionPortletConfigurationIconTest {

	@Test
	public void testGetJspPath() {
		Assert.assertEquals(
			"/configuration/icon/import_data_definition.jsp",
			_importDataDefinitionPortletConfigurationIcon.getJspPath());
	}

	@Test
	public void testIsShow() {
		Assert.assertTrue(
			_importDataDefinitionPortletConfigurationIcon.isShow(null));
	}

	private final ImportDataDefinitionPortletConfigurationIcon
		_importDataDefinitionPortletConfigurationIcon =
			new ImportDataDefinitionPortletConfigurationIcon();

}