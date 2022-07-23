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

package com.liferay.social.kernel.util;

import java.util.List;

/**
 * @author Adolfo Pérez
 */
public class SocialRelationTypesUtil {

	public static List<Integer> getAllSocialRelationTypes() {
		return _socialRelationTypes.getAllSocialRelationTypes();
	}

	public static SocialRelationTypes getSocialRelationTypes() {
		return _socialRelationTypes;
	}

	public static String getTypeLabel(int type) {
		return _socialRelationTypes.getTypeLabel(type);
	}

	public static boolean isTypeBi(int type) {
		return _socialRelationTypes.isTypeBi(type);
	}

	public static boolean isTypeUni(int type) {
		return _socialRelationTypes.isTypeUni(type);
	}

	public void setSocialRelationTypes(
		SocialRelationTypes socialRelationTypes) {

		_socialRelationTypes = socialRelationTypes;
	}

	private static SocialRelationTypes _socialRelationTypes;

}