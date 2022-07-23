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

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 * @author Brian Wing Shun Chan
 */
public class HttpSessionWrapper implements HttpSession {

	public HttpSessionWrapper(HttpSession httpSession) {
		_httpSession = httpSession;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof HttpSessionWrapper) {
			HttpSessionWrapper sessionWrapper = (HttpSessionWrapper)object;

			object = sessionWrapper.getWrappedSession();
		}

		return _httpSession.equals(object);
	}

	@Override
	public Object getAttribute(String name) {
		return _httpSession.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return _httpSession.getAttributeNames();
	}

	@Override
	public long getCreationTime() {
		return _httpSession.getCreationTime();
	}

	@Override
	public String getId() {
		return _httpSession.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return _httpSession.getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {
		return _httpSession.getMaxInactiveInterval();
	}

	@Override
	public ServletContext getServletContext() {
		return _httpSession.getServletContext();
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public HttpSessionContext getSessionContext() {
		return _httpSession.getSessionContext();
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public Object getValue(String name) {
		return _httpSession.getValue(name);
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public String[] getValueNames() {
		return _httpSession.getValueNames();
	}

	public HttpSession getWrappedSession() {
		return _httpSession;
	}

	@Override
	public int hashCode() {
		return _httpSession.hashCode();
	}

	@Override
	public void invalidate() {
		_httpSession.invalidate();
	}

	@Override
	public boolean isNew() {
		return _httpSession.isNew();
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public void putValue(String name, Object value) {
		_httpSession.putValue(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		_httpSession.removeAttribute(name);
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public void removeValue(String name) {
		_httpSession.removeValue(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		_httpSession.setAttribute(name, value);
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		_httpSession.setMaxInactiveInterval(interval);
	}

	private final HttpSession _httpSession;

}