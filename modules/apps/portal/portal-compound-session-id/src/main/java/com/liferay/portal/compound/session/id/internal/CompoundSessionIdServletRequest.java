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

package com.liferay.portal.compound.session.id.internal;

import com.liferay.portal.kernel.servlet.PersistentHttpServletRequestWrapper;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdHttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Michael C. Han
 */
public class CompoundSessionIdServletRequest
	extends PersistentHttpServletRequestWrapper {

	public CompoundSessionIdServletRequest(
		HttpServletRequest httpServletRequest) {

		super(httpServletRequest);
	}

	@Override
	public HttpSession getSession() {
		return _getCompoundSessionIdHttpSession(super.getSession());
	}

	@Override
	public HttpSession getSession(boolean create) {
		HttpSession httpSession = super.getSession(create);

		if (httpSession == null) {
			return httpSession;
		}

		return _getCompoundSessionIdHttpSession(httpSession);
	}

	private CompoundSessionIdHttpSession _getCompoundSessionIdHttpSession(
		HttpSession httpSession) {

		if ((_compoundSessionIdHttpSession != null) &&
			(httpSession ==
				_compoundSessionIdHttpSession.getWrappedSession())) {

			return _compoundSessionIdHttpSession;
		}

		_compoundSessionIdHttpSession = new CompoundSessionIdHttpSession(
			httpSession);

		return _compoundSessionIdHttpSession;
	}

	private CompoundSessionIdHttpSession _compoundSessionIdHttpSession;

}