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

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.StringPool;

import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Georgel Pop
 */
public class MapUtilTest {

	@Test
	public void testGetWithFallback() {
		MapUtil mapUtil = new MapUtil();

		Map<Locale, String> localizedValueMap = HashMapBuilder.put(
			LocaleUtil.BRAZIL, "lápis"
		).put(
			LocaleUtil.CHINA, "鉛筆"
		).put(
			LocaleUtil.SPAIN, StringPool.BLANK
		).put(
			LocaleUtil.getDefault(), "pencil"
		).getMap();

		Assert.assertEquals(
			"lápis",
			mapUtil.getWithFallbackKey(
				localizedValueMap, LocaleUtil.BRAZIL, LocaleUtil.getDefault()));
		Assert.assertEquals(
			"鉛筆",
			mapUtil.getWithFallbackKey(
				localizedValueMap, LocaleUtil.CHINA, LocaleUtil.getDefault()));
		Assert.assertEquals(
			"pencil",
			mapUtil.getWithFallbackKey(
				localizedValueMap, LocaleUtil.SPAIN, LocaleUtil.getDefault()));
		Assert.assertEquals(
			"pencil",
			mapUtil.getWithFallbackKey(
				localizedValueMap, LocaleUtil.getDefault(),
				LocaleUtil.getDefault()));
		Assert.assertEquals(
			"pencil",
			mapUtil.getWithFallbackKey(
				localizedValueMap, null, LocaleUtil.getDefault()));
	}

}