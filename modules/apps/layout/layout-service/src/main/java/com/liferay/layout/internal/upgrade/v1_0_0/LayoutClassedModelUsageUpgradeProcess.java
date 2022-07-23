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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.model.AssetEntryUsage;
import com.liferay.asset.service.AssetEntryUsageLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class LayoutClassedModelUsageUpgradeProcess extends UpgradeProcess {

	public LayoutClassedModelUsageUpgradeProcess(
		AssetEntryLocalService assetEntryLocalService,
		AssetEntryUsageLocalService assetEntryUsageLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
		_assetEntryUsageLocalService = assetEntryUsageLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeSchema();

		_upgradeLayoutClassedModelUsage();
	}

	private void _upgradeLayoutClassedModelUsage() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement preparedStatement =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"insert into LayoutClassedModelUsage (mvccVersion, ",
						"uuid_, layoutClassedModelUsageId, groupId, ",
						"createDate, modifiedDate, classNameId, classPK, ",
						"containerKey, containerType, plid, type_ ) values ",
						"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"))) {

			List<AssetEntryUsage> assetEntryUsages =
				_assetEntryUsageLocalService.getAssetEntryUsages(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (AssetEntryUsage assetEntryUsage : assetEntryUsages) {
				AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
					assetEntryUsage.getAssetEntryId());

				if ((assetEntry == null) || (assetEntryUsage.getPlid() <= 0)) {
					continue;
				}

				preparedStatement.setLong(1, 0);
				preparedStatement.setString(2, PortalUUIDUtil.generate());
				preparedStatement.setLong(3, increment());
				preparedStatement.setLong(4, assetEntryUsage.getGroupId());
				preparedStatement.setDate(
					5, new Date(System.currentTimeMillis()));
				preparedStatement.setDate(
					6, new Date(System.currentTimeMillis()));
				preparedStatement.setLong(7, assetEntry.getClassNameId());
				preparedStatement.setLong(8, assetEntry.getClassPK());
				preparedStatement.setString(
					9, assetEntryUsage.getContainerKey());
				preparedStatement.setLong(
					10, assetEntryUsage.getContainerType());
				preparedStatement.setLong(11, assetEntryUsage.getPlid());
				preparedStatement.setLong(12, assetEntryUsage.getType());

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		}
	}

	private void _upgradeSchema() throws Exception {
		String template = StringUtil.read(
			LayoutClassedModelUsageUpgradeProcess.class.getResourceAsStream(
				"dependencies/update.sql"));

		runSQLTemplateString(template, false);
	}

	private final AssetEntryLocalService _assetEntryLocalService;
	private final AssetEntryUsageLocalService _assetEntryUsageLocalService;

}