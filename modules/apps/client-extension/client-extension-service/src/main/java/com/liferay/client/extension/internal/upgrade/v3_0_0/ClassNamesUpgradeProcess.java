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

package com.liferay.client.extension.internal.upgrade.v3_0_0;

import com.liferay.portal.kernel.dao.orm.WildcardMode;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.upgrade.v7_0_0.UpgradeKernelPackage;

/**
 * @author Dante Wang
 */
public class ClassNamesUpgradeProcess extends UpgradeKernelPackage {

	@Override
	protected void doUpgrade() throws UpgradeException {
		super.doUpgrade();

		try {
			upgradeTable(
				"ResourcePermission", "primKey", getClassNames(),
				WildcardMode.LEADING);
			upgradeTable(
				"ResourcePermission", "primKey", getResourceNames(),
				WildcardMode.LEADING);
		}
		catch (Exception exception) {
			throw new UpgradeException(exception);
		}
	}

	@Override
	protected String[][] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String[][] getResourceNames() {
		return _RESOURCE_NAMES;
	}

	private static final String[][] _CLASS_NAMES = {
		{
			"com.liferay.remote.app.model.RemoteAppEntry",
			"com.liferay.client.extension.model.ClientExtensionEntry"
		}
	};

	private static final String[][] _RESOURCE_NAMES = {
		{"com.liferay.remote.app", "com.liferay.client.extension"}
	};

}