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

package com.liferay.asset.display.page.upgrade;

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Jürgen Kappler
 */
public abstract class BaseUpgradeAssetDisplayPageEntry extends UpgradeProcess {

	protected void upgradeAssetDisplayPageTypes(
			String tableName, String pkColumnName, String modelClassName)
		throws Exception {

		long modelClassNameId = PortalUtil.getClassNameId(modelClassName);

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select distinct groupId, companyId, ", pkColumnName,
					" from ", tableName, " where ", pkColumnName,
					" not in (select classPK from AssetDisplayPageEntry where ",
					"classNameId in (", modelClassNameId, "))"));
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"insert into AssetDisplayPageEntry (uuid_, ",
						"assetDisplayPageEntryId, groupId, companyId, userId, ",
						"userName, createDate, modifiedDate, classNameId, ",
						"classPK, layoutPageTemplateEntryId, type_, plid) ",
						"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"))) {

			try (ResultSet resultSet = preparedStatement1.executeQuery()) {
				while (resultSet.next()) {
					Timestamp now = new Timestamp(System.currentTimeMillis());

					preparedStatement2.setString(1, PortalUUIDUtil.generate());
					preparedStatement2.setLong(2, increment());
					preparedStatement2.setLong(3, resultSet.getLong("groupId"));
					preparedStatement2.setLong(
						4, resultSet.getLong("companyId"));
					preparedStatement2.setLong(5, 0);
					preparedStatement2.setString(6, null);
					preparedStatement2.setTimestamp(7, now);
					preparedStatement2.setTimestamp(8, now);
					preparedStatement2.setLong(9, modelClassNameId);
					preparedStatement2.setLong(
						10, resultSet.getLong(pkColumnName));
					preparedStatement2.setLong(11, 0);
					preparedStatement2.setLong(
						12, AssetDisplayPageConstants.TYPE_NONE);
					preparedStatement2.setLong(13, 0);

					preparedStatement2.addBatch();
				}

				preparedStatement2.executeBatch();
			}
		}

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"delete from AssetDisplayPageEntry where classNameId = ? and " +
					"type_ = ?")) {

			preparedStatement.setLong(1, modelClassNameId);
			preparedStatement.setLong(
				2, AssetDisplayPageConstants.TYPE_DEFAULT);

			preparedStatement.executeUpdate();
		}
	}

}