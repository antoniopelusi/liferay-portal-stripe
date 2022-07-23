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

package com.liferay.commerce.product.internal.upgrade.v1_10_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Marco Leo
 */
public class CPInstanceUpgradeProcess extends UpgradeProcess {

	public CPInstanceUpgradeProcess(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	@Override
	protected void doUpgrade() throws Exception {
		String selectCPInstanceSQL =
			"select CPInstanceId, json from CPInstance where json <> ''";
		String updateCPInstanceSQL =
			"update CPInstance set json = ? WHERE CPInstanceId = ?";

		try (PreparedStatement preparedStatement =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPInstanceSQL);
			Statement s1 = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			Statement s2 = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			Statement s3 = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet1 = s1.executeQuery(selectCPInstanceSQL)) {

			while (resultSet1.next()) {
				JSONArray outputJSONArray = _jsonFactory.createJSONArray();

				JSONArray inputJSONArray = _jsonFactory.createJSONArray(
					resultSet1.getString("json"));

				for (int i = 0; i < inputJSONArray.length(); i++) {
					JSONObject inputJSONObject = inputJSONArray.getJSONObject(
						i);

					ResultSet resultSet2 = s2.executeQuery(
						"select key_ from CPDefinitionOptionRel where " +
							"CPDefinitionOptionRelId = " +
								inputJSONObject.getLong("key"));

					if (!resultSet2.next()) {
						continue;
					}

					JSONArray valueOutputJSONArray =
						_jsonFactory.createJSONArray();

					JSONArray valueInputJSONArray =
						inputJSONObject.getJSONArray("value");

					for (int j = 0; j < valueInputJSONArray.length(); j++) {
						ResultSet resultSet3 = s3.executeQuery(
							"select key_ from CPDefinitionOptionValueRel " +
								"where CPDefinitionOptionValueRelId = " +
									valueInputJSONArray.getLong(j));

						if (!resultSet3.next()) {
							continue;
						}

						valueOutputJSONArray.put(resultSet3.getString("key_"));
					}

					JSONObject outputJSONObject =
						_jsonFactory.createJSONObject();

					outputJSONObject.put(
						"key", resultSet2.getString("key_")
					).put(
						"value", valueOutputJSONArray
					);

					outputJSONArray.put(outputJSONObject);
				}

				preparedStatement.setString(1, outputJSONArray.toString());
				preparedStatement.setLong(
					2, resultSet1.getLong("CPInstanceId"));

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		}
	}

	private final JSONFactory _jsonFactory;

}