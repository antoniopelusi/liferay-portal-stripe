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

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pavel Savinov
 */
public class LayoutUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement preparedStatement1 = connection.prepareStatement(
				"select plid, typeSettings from Layout where classNameId = ? " +
					"and classPK > 0 and type_ = ? and system_ = ?");
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update Layout set status = ? where plid = ?")) {

			preparedStatement1.setLong(
				1, PortalUtil.getClassNameId(Layout.class));
			preparedStatement1.setString(2, LayoutConstants.TYPE_CONTENT);
			preparedStatement1.setBoolean(3, true);

			try (ResultSet resultSet = preparedStatement1.executeQuery()) {
				while (resultSet.next()) {
					long plid = resultSet.getLong("plid");

					String typeSettings = resultSet.getString("typeSettings");

					UnicodeProperties unicodeProperties =
						UnicodePropertiesBuilder.load(
							typeSettings
						).build();

					boolean published = GetterUtil.getBoolean(
						unicodeProperties.getProperty("published"));

					if (published) {
						preparedStatement2.setInt(
							1, WorkflowConstants.STATUS_APPROVED);
					}
					else {
						preparedStatement2.setInt(
							1, WorkflowConstants.STATUS_DRAFT);
					}

					preparedStatement2.setLong(2, plid);

					preparedStatement2.addBatch();
				}

				preparedStatement2.executeBatch();
			}
		}
	}

}