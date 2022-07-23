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

package com.liferay.commerce.product.internal.upgrade.v1_8_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 */
public class CPAttachmentFileEntryUpgradeProcess extends UpgradeProcess {

	public CPAttachmentFileEntryUpgradeProcess(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		long classNameId = _classNameLocalService.getClassNameId(
			CPDefinition.class);

		String updateCPAttachmentFileEntrySQL =
			"update CPAttachmentFileEntry set groupId = ? where classNameId " +
				"= ? and classPK = ?";

		String selectCPDefinitionSQL =
			"select CPDefinition.groupId, CPDefinitionId from CPDefinition " +
				"inner join CPAttachmentFileEntry on CPDefinitionId = classPK";

		try (PreparedStatement preparedStatement =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPAttachmentFileEntrySQL);
			Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(selectCPDefinitionSQL)) {

			while (resultSet.next()) {
				preparedStatement.setLong(1, resultSet.getLong("groupId"));
				preparedStatement.setLong(2, classNameId);
				preparedStatement.setLong(
					3, resultSet.getLong("CPDefinitionId"));

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		}
	}

	private final ClassNameLocalService _classNameLocalService;

}