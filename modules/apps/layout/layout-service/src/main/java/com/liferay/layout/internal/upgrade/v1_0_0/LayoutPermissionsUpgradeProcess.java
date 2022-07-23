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

package com.liferay.layout.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Michael Bowerman
 */
public class LayoutPermissionsUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		String sql = SQLTransformer.transform(
			StringBundler.concat(
				"select Layout.companyId, Layout.plid, Layout.privateLayout",
				", Layout.groupId, Layout.userId from Layout left join ",
				"ResourcePermission on (ResourcePermission.companyId = ",
				"Layout.companyId and ResourcePermission.name = '",
				Layout.class.getName(), "' and ResourcePermission.scope = ",
				ResourceConstants.SCOPE_INDIVIDUAL,
				" and ResourcePermission.primKeyId = Layout.plid) where ",
				"ResourcePermission.resourcePermissionId is null"));

		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sql);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long companyId = resultSet.getLong("companyId");
				long groupId = resultSet.getLong("groupId");
				long plid = resultSet.getLong("plid");
				boolean privateLayout = resultSet.getBoolean("privateLayout");
				long userId = resultSet.getLong("userId");

				boolean addGroupPermission = true;
				boolean addGuestPermission = true;

				if (privateLayout) {
					addGuestPermission = false;

					Group group = GroupLocalServiceUtil.getGroup(groupId);

					if (group.isUser() || group.isUserGroup()) {
						addGroupPermission = false;
					}
				}

				ResourceLocalServiceUtil.addResources(
					companyId, groupId, userId, Layout.class.getName(), plid,
					false, addGroupPermission, addGuestPermission);
			}
		}
	}

}