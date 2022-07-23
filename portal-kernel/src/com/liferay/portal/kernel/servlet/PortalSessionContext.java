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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.Validator;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalSessionContext {

	public static int count() {
		return _sessionPool.size();
	}

	public static HttpSession get(String sessionId) {
		if (Validator.isNull(sessionId)) {
			return null;
		}

		return _sessionPool.get(sessionId);
	}

	public static void put(String sessionId, HttpSession httpSession) {
		_sessionPool.put(sessionId, httpSession);
	}

	public static HttpSession remove(String sessionId) {
		return _sessionPool.remove(sessionId);
	}

	public static Collection<HttpSession> values() {
		return _sessionPool.values();
	}

	protected PortalSessionContext() {
	}

	private static final Map<String, HttpSession> _sessionPool =
		new ConcurrentHashMap<>();

}