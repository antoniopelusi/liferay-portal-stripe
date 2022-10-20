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

package com.liferay.object.constants;

import com.liferay.petra.string.StringPool;

/**
 * @author Carolina Barbosa
 */
public class ObjectActionConstants {

	public static final int STATUS_FAILED = 2;

	public static final int STATUS_NEVER_RAN = 0;

	public static final int STATUS_SUCCESS = 1;

	public static String getStatusLabel(int status) {
		if (status == STATUS_FAILED) {
			return "failed";
		}
		else if (status == STATUS_NEVER_RAN) {
			return "never-ran";
		}
		else if (status == STATUS_SUCCESS) {
			return "success";
		}

		return StringPool.BLANK;
	}

}