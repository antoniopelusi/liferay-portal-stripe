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

package com.liferay.portal.search.elasticsearch7.internal.helper;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.search.elasticsearch7.configuration.RESTClientLoggerLevel;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.bulk.BulkResponse;

/**
 * @author Michael C. Han
 */
public class SearchLogHelperUtil {

	public static void logActionResponse(
		Log log, ActionResponse actionResponse) {

		_searchLogHelper.logActionResponse(log, actionResponse);
	}

	public static void logActionResponse(Log log, BulkResponse bulkResponse) {
		_searchLogHelper.logActionResponse(log, bulkResponse);
	}

	public static void setRESTClientLoggerLevel(
		RESTClientLoggerLevel restClientLoggerLevel) {

		_searchLogHelper.setRESTClientLoggerLevel(restClientLoggerLevel);
	}

	public static void setSearchLogHelper(SearchLogHelper searchLogHelper) {
		_searchLogHelper = searchLogHelper;
	}

	private static SearchLogHelper _searchLogHelper = new SearchLogHelperImpl();

}