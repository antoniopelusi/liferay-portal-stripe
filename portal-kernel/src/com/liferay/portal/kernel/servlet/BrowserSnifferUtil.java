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

import javax.servlet.http.HttpServletRequest;

/**
 * See http://www.zytrax.com/tech/web/browser_ids.htm for examples.
 *
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class BrowserSnifferUtil {

	public static boolean acceptsGzip(HttpServletRequest httpServletRequest) {
		return _browserSniffer.acceptsGzip(httpServletRequest);
	}

	public static String getBrowserId(HttpServletRequest httpServletRequest) {
		return _browserSniffer.getBrowserId(httpServletRequest);
	}

	public static BrowserMetadata getBrowserMetadata(
		HttpServletRequest httpServletRequest) {

		return _browserSniffer.getBrowserMetadata(httpServletRequest);
	}

	public static BrowserSniffer getBrowserSniffer() {
		return _browserSniffer;
	}

	public static float getMajorVersion(HttpServletRequest httpServletRequest) {
		return _browserSniffer.getMajorVersion(httpServletRequest);
	}

	public static String getRevision(HttpServletRequest httpServletRequest) {
		return _browserSniffer.getRevision(httpServletRequest);
	}

	public static String getVersion(HttpServletRequest httpServletRequest) {
		return _browserSniffer.getVersion(httpServletRequest);
	}

	public static boolean isAir(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isAir(httpServletRequest);
	}

	public static boolean isAndroid(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isAndroid(httpServletRequest);
	}

	public static boolean isChrome(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isChrome(httpServletRequest);
	}

	public static boolean isEdge(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isEdge(httpServletRequest);
	}

	public static boolean isFirefox(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isFirefox(httpServletRequest);
	}

	public static boolean isGecko(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isGecko(httpServletRequest);
	}

	public static boolean isIe(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isIe(httpServletRequest);
	}

	public static boolean isIeOnWin32(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isIeOnWin32(httpServletRequest);
	}

	public static boolean isIeOnWin64(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isIeOnWin64(httpServletRequest);
	}

	public static boolean isIphone(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isIphone(httpServletRequest);
	}

	public static boolean isLinux(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isLinux(httpServletRequest);
	}

	public static boolean isMac(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isMac(httpServletRequest);
	}

	public static boolean isMobile(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isMobile(httpServletRequest);
	}

	public static boolean isMozilla(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isMozilla(httpServletRequest);
	}

	public static boolean isOpera(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isOpera(httpServletRequest);
	}

	public static boolean isRtf(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isRtf(httpServletRequest);
	}

	public static boolean isSafari(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isSafari(httpServletRequest);
	}

	public static boolean isSun(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isSun(httpServletRequest);
	}

	public static boolean isWebKit(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isWebKit(httpServletRequest);
	}

	public static boolean isWindows(HttpServletRequest httpServletRequest) {
		return _browserSniffer.isWindows(httpServletRequest);
	}

	public void setBrowserSniffer(BrowserSniffer browserSniffer) {
		_browserSniffer = browserSniffer;
	}

	private static BrowserSniffer _browserSniffer;

}