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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Sergio González
 */
public class UpgradeLayoutFriendlyURL extends UpgradeProcess {

	protected void addLayoutFriendlyURL() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement preparedStatement = connection.prepareStatement(
				"select plid, groupId, companyId, userId, userName, " +
					"createDate, modifiedDate, privateLayout, friendlyURL " +
						"from Layout");
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long plid = resultSet.getLong("plid");
				long groupId = resultSet.getLong("groupId");
				long companyId = resultSet.getLong("companyId");
				long userId = resultSet.getLong("userId");
				String userName = resultSet.getString("userName");
				Timestamp createDate = resultSet.getTimestamp("createDate");
				Timestamp modifiedDate = resultSet.getTimestamp("modifiedDate");
				boolean privateLayout = resultSet.getBoolean("privateLayout");
				String friendlyURL = resultSet.getString("friendlyURL");

				addLayoutFriendlyURL(
					groupId, companyId, userId, userName, createDate,
					modifiedDate, plid, privateLayout, friendlyURL);
			}
		}
	}

	protected void addLayoutFriendlyURL(
			long groupId, long companyId, long userId, String userName,
			Timestamp createDate, Timestamp modifiedDate, long plid,
			boolean privateLayout, String friendlyURL)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("insert into LayoutFriendlyURL (uuid_, ");
		sb.append("layoutFriendlyURLId, groupId, companyId, userId, ");
		sb.append("userName, createDate, modifiedDate, plid, privateLayout, ");
		sb.append("friendlyURL, languageId) values (?, ?, ?, ?, ?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?)");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString())) {

			preparedStatement.setString(1, PortalUUIDUtil.generate());
			preparedStatement.setLong(2, increment());
			preparedStatement.setLong(3, groupId);
			preparedStatement.setLong(4, companyId);
			preparedStatement.setLong(5, userId);
			preparedStatement.setString(6, userName);
			preparedStatement.setTimestamp(7, createDate);
			preparedStatement.setTimestamp(8, modifiedDate);
			preparedStatement.setLong(9, plid);
			preparedStatement.setBoolean(10, privateLayout);
			preparedStatement.setString(11, friendlyURL);
			preparedStatement.setString(
				12, UpgradeProcessUtil.getDefaultLanguageId(companyId));

			preparedStatement.executeUpdate();
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		addLayoutFriendlyURL();
	}

}