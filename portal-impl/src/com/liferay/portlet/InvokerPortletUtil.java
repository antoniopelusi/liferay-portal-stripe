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

package com.liferay.portlet;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.internal.PortletSessionImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletSession;

import javax.servlet.http.HttpSession;

/**
 * @author Neil Griffin
 */
public class InvokerPortletUtil {

	public static void clearResponse(
		HttpSession httpSession, long plid, String portletId,
		String languageId) {

		String sesResponseId = encodeResponseKey(plid, portletId, languageId);

		Map<String, InvokerPortletResponse> responses = getResponses(
			httpSession);

		responses.remove(sesResponseId);
	}

	public static void clearResponses(PortletSession session) {
		Map<String, InvokerPortletResponse> responses = getResponses(session);

		responses.clear();
	}

	public static String encodeResponseKey(
		long plid, String portletId, String languageId) {

		return StringBundler.concat(
			StringUtil.toHexString(plid), StringPool.UNDERLINE, portletId,
			StringPool.UNDERLINE, languageId);
	}

	public static Map<String, InvokerPortletResponse> getResponses(
		HttpSession httpSession) {

		Map<String, InvokerPortletResponse> responses =
			(Map<String, InvokerPortletResponse>)httpSession.getAttribute(
				WebKeys.CACHE_PORTLET_RESPONSES);

		if (responses == null) {
			responses = new ConcurrentHashMap<>();

			httpSession.setAttribute(
				WebKeys.CACHE_PORTLET_RESPONSES, responses);
		}

		return responses;
	}

	public static Map<String, InvokerPortletResponse> getResponses(
		PortletSession portletSession) {

		PortletSessionImpl portletSessionImpl =
			(PortletSessionImpl)portletSession;

		return getResponses(portletSessionImpl.getHttpSession());
	}

}