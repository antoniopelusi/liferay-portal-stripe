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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.QName;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletQNameUtil {

	public static String getKey(QName qName) {
		return _portletQName.getKey(qName);
	}

	public static String getKey(String uri, String localPart) {
		return _portletQName.getKey(uri, localPart);
	}

	public static PortletQName getPortletQName() {
		return _portletQName;
	}

	public static String getPublicRenderParameterIdentifier(
		String publicRenderParameterName) {

		return _portletQName.getPublicRenderParameterIdentifier(
			publicRenderParameterName);
	}

	public static String getPublicRenderParameterName(QName qName) {
		return _portletQName.getPublicRenderParameterName(qName);
	}

	public static QName getQName(
		Element qNameEl, Element nameEl, String defaultNamespace) {

		return _portletQName.getQName(qNameEl, nameEl, defaultNamespace);
	}

	public static QName getQName(String publicRenderParameterName) {
		return _portletQName.getQName(publicRenderParameterName);
	}

	public static String getRemovePublicRenderParameterName(QName qName) {
		return _portletQName.getRemovePublicRenderParameterName(qName);
	}

	public static void setPublicRenderParameterIdentifier(
		String publicRenderParameterName, String identifier) {

		_portletQName.setPublicRenderParameterIdentifier(
			publicRenderParameterName, identifier);
	}

	public void setPortletQName(PortletQName portletQName) {
		_portletQName = portletQName;
	}

	private static PortletQName _portletQName;

}