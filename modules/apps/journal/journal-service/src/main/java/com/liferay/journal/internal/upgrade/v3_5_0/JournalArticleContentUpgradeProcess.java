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

package com.liferay.journal.internal.upgrade.v3_5_0;

import com.liferay.journal.content.compatibility.converter.JournalContentCompatibilityConverter;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Eudaldo Alonso
 */
public class JournalArticleContentUpgradeProcess extends UpgradeProcess {

	public JournalArticleContentUpgradeProcess(
		JournalContentCompatibilityConverter
			journalContentCompatibilityConverter) {

		_journalContentCompatibilityConverter =
			journalContentCompatibilityConverter;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				"select id_, content from JournalArticle");
			ResultSet resultSet = preparedStatement1.executeQuery();
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update JournalArticle set content = ? where id_ = ?")) {

			while (resultSet.next()) {
				preparedStatement2.setString(
					1,
					_journalContentCompatibilityConverter.convert(
						resultSet.getString("content")));
				preparedStatement2.setLong(2, resultSet.getLong("id_"));

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

	private final JournalContentCompatibilityConverter
		_journalContentCompatibilityConverter;

}