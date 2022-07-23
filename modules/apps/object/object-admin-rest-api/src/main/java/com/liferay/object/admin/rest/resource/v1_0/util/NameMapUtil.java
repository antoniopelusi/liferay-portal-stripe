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

package com.liferay.object.admin.rest.resource.v1_0.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Feliphe Marinho
 */
public class NameMapUtil {

	public static <K, V> Map<K, V> copy(
		Map<? extends K, ? extends V> sourceNameMap) {

		Map<K, V> targetNameMap = new HashMap<>();

		for (Map.Entry<? extends K, ? extends V> entry :
				sourceNameMap.entrySet()) {

			Locale locale = null;

			if (entry.getKey() instanceof Locale) {
				locale = (Locale)entry.getKey();
			}
			else {
				locale = LocaleUtil.fromLanguageId((String)entry.getKey());
			}

			targetNameMap.put(
				(K)entry.getKey(),
				(V)StringUtil.appendParentheticalSuffix(
					(String)entry.getValue(),
					LanguageUtil.get(locale, "copy")));
		}

		return targetNameMap;
	}

}