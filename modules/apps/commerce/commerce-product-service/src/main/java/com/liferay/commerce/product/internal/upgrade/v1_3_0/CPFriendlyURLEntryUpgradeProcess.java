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

package com.liferay.commerce.product.internal.upgrade.v1_3_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
public class CPFriendlyURLEntryUpgradeProcess extends UpgradeProcess {

	public CPFriendlyURLEntryUpgradeProcess(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable("CPFriendlyURLEntry")) {
			return;
		}

		long cpDefinitionClassNameId = _classNameLocalService.getClassNameId(
			CPDefinition.class);
		long cProductClassNameId = _classNameLocalService.getClassNameId(
			CProduct.class);

		String updateCPFriendlyURLSQL =
			"update CPFriendlyURLEntry set classNameId = ?, classPK = ? " +
				"where classNameId = ? and classPK = ?";

		String selectCPFriendlyURLEntrySQL = StringBundler.concat(
			"select distinct classPK, CProductId from CPFriendlyURLEntry ",
			"inner join CPDefinition on CPDefinition.CPDefinitionId = ",
			"CPFriendlyURLEntry.classPK where classNameId = ",
			cpDefinitionClassNameId);

		try (PreparedStatement preparedStatement =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPFriendlyURLSQL);
			Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(selectCPFriendlyURLEntrySQL)) {

			while (resultSet.next()) {
				long classPK = resultSet.getLong("classPK");
				long cProductId = resultSet.getLong("CProductId");

				preparedStatement.setLong(1, cProductClassNameId);
				preparedStatement.setLong(2, cProductId);
				preparedStatement.setLong(3, cpDefinitionClassNameId);
				preparedStatement.setLong(4, classPK);

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		}
	}

	private final ClassNameLocalService _classNameLocalService;

}