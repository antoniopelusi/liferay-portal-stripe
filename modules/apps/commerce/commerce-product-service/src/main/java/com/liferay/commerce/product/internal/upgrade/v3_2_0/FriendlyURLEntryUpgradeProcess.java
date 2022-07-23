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

package com.liferay.commerce.product.internal.upgrade.v3_2_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Danny Situ
 */
public class FriendlyURLEntryUpgradeProcess extends UpgradeProcess {

	public FriendlyURLEntryUpgradeProcess(
		ClassNameLocalService classNameLocalService,
		GroupLocalService groupLocalService) {

		_classNameLocalService = classNameLocalService;
		_groupLocalService = groupLocalService;
	}

	@Override
	public void doUpgrade() throws Exception {
		if (!hasTable("FriendlyURLEntry")) {
			return;
		}

		long assetCategoryClassNameId = _classNameLocalService.getClassNameId(
			AssetCategory.class);
		long cProductClassNameId = _classNameLocalService.getClassNameId(
			CProduct.class);

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select * from FriendlyURLEntry where classNameId in (",
					assetCategoryClassNameId, ",", cProductClassNameId, ")"));
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update FriendlyURLEntry set groupId = ? where " +
						"friendlyURLEntryId = ?");
			PreparedStatement preparedStatement3 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update FriendlyURLEntryLocalization set groupId = ? " +
						"where friendlyURLEntryId = ?");
			ResultSet resultSet = preparedStatement1.executeQuery()) {

			while (resultSet.next()) {
				long companyId = resultSet.getLong("companyId");
				long groupId = resultSet.getLong("groupId");

				List<Long> groupIds = _groupLocalService.getGroupIds(
					companyId, true);

				if (groupIds.contains(groupId)) {
					continue;
				}

				groupIds = _groupLocalService.getGroupIds(companyId, false);

				if (groupIds.contains(groupId)) {
					continue;
				}

				Group group = _groupLocalService.getCompanyGroup(companyId);

				if (groupId == group.getGroupId()) {
					continue;
				}

				long friendlyURLEntryId = resultSet.getLong(
					"friendlyURLEntryId");

				preparedStatement2.setLong(1, group.getGroupId());
				preparedStatement2.setLong(2, friendlyURLEntryId);

				preparedStatement2.execute();

				preparedStatement3.setLong(1, group.getGroupId());
				preparedStatement3.setLong(2, friendlyURLEntryId);

				preparedStatement3.execute();
			}
		}
	}

	private final ClassNameLocalService _classNameLocalService;
	private final GroupLocalService _groupLocalService;

}