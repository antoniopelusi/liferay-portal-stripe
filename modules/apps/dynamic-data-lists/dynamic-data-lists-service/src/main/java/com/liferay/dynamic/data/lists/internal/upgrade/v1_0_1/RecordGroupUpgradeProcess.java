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

package com.liferay.dynamic.data.lists.internal.upgrade.v1_0_1;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pedro Queiroz
 */
public class RecordGroupUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select DDLRecordSet.groupId, DDLRecord.recordId from ",
					"DDLRecord inner join DDLRecordSet on ",
					"DDLRecord.recordSetId = DDLRecordSet.recordSetId where ",
					"DDLRecord.groupId != DDLRecordSet.groupId"));
			ResultSet resultSet = preparedStatement1.executeQuery();
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDLRecord set groupId = ? where recordId = ?")) {

			while (resultSet.next()) {
				long groupId = resultSet.getLong("groupId");
				long recordId = resultSet.getLong("recordId");

				preparedStatement2.setLong(1, groupId);
				preparedStatement2.setLong(2, recordId);

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

}