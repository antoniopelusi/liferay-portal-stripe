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

package com.liferay.object.internal.upgrade.v3_19_3;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Selton Guedes
 */
public class ObjectFieldUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		String selectSQL = SQLTransformer.transform(
			StringBundler.concat(
				"select ObjectDefinition.dbTableName, ",
				"ObjectDefinition.system_, ObjectField.objectFieldId from ",
				"ObjectField inner join ObjectDefinition on ",
				"ObjectDefinition.objectDefinitionId = ",
				"ObjectField.objectDefinitionId where ",
				"(ObjectDefinition.dbTableName != ObjectField.dbTableName and ",
				"ObjectDefinition.system_ = [$TRUE$] and ObjectField.system_ ",
				"= [$FALSE$]) or (ObjectDefinition.dbTableName = ",
				"ObjectField.dbTableName and ObjectDefinition.system_ = ",
				"[$FALSE$] and ObjectField.system_ = [$TRUE$])"));

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				selectSQL);
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update ObjectField set dbTableName = ? where " +
						"objectFieldId = ?");
			ResultSet resultSet = preparedStatement1.executeQuery()) {

			while (resultSet.next()) {
				if (resultSet.getBoolean("system_")) {
					preparedStatement2.setString(
						1, resultSet.getString("dbTableName"));
				}
				else {
					preparedStatement2.setString(1, "ObjectEntry");
				}

				preparedStatement2.setLong(
					2, resultSet.getLong("objectFieldId"));

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

}