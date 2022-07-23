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

package com.liferay.portal.kernel.license.util;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.license.LicenseInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class LicenseManagerUtil {

	public static void checkLicense(String productId) {
		_licenseManager.checkLicense(productId);
	}

	public static List<Map<String, String>> getClusterLicenseProperties(
		String clusterNodeId) {

		return _licenseManager.getClusterLicenseProperties(clusterNodeId);
	}

	public static String getHostName() {
		return _licenseManager.getHostName();
	}

	public static Set<String> getIpAddresses() {
		return _licenseManager.getIpAddresses();
	}

	public static LicenseInfo getLicenseInfo(String productId) {
		return _licenseManager.getLicenseInfo(productId);
	}

	public static LicenseManager getLicenseManager() {
		return _licenseManager;
	}

	public static List<Map<String, String>> getLicenseProperties() {
		return _licenseManager.getLicenseProperties();
	}

	public static Map<String, String> getLicenseProperties(String productId) {
		return _licenseManager.getLicenseProperties(productId);
	}

	public static int getLicenseState(Map<String, String> licenseProperties) {
		return _licenseManager.getLicenseState(licenseProperties);
	}

	public static int getLicenseState(String productId) {
		return _licenseManager.getLicenseState(productId);
	}

	public static Set<String> getMacAddresses() {
		return _licenseManager.getMacAddresses();
	}

	public static void registerLicense(JSONObject jsonObject) throws Exception {
		_licenseManager.registerLicense(jsonObject);
	}

	public void setLicenseManager(LicenseManager licenseManager) {
		_licenseManager = licenseManager;
	}

	private static LicenseManager _licenseManager;

}