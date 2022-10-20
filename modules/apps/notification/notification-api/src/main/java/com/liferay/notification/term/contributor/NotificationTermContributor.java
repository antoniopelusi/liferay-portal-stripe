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

package com.liferay.notification.term.contributor;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Gustavo Lima
 */
public interface NotificationTermContributor {

	public List<String> getTermNames();

	public String getTermValue(Locale locale, Object object, String termName)
		throws PortalException;

	public String getTermValue(String termName, Locale locale);

	public default Map<String, String> getTermValues(Locale locale) {
		Map<String, String> termValues = new HashMap<>();

		List<String> termNames = getTermNames();

		for (String termName : termNames) {
			termValues.put(termName, getTermValue(termName, locale));
		}

		return termValues;
	}

}