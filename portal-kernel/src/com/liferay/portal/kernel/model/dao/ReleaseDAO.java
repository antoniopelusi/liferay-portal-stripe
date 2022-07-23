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

package com.liferay.portal.kernel.model.dao;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.ReleaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Adolfo Pérez
 */
public class ReleaseDAO {

	public void addRelease(Connection connection, String bundleSymbolicName)
		throws SQLException {

		if (hasRelease(connection, bundleSymbolicName)) {
			return;
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				StringBundler.concat(
					"insert into Release_ (mvccVersion, releaseId, ",
					"createDate, modifiedDate, servletContextName, ",
					"schemaVersion, buildNumber, buildDate, verified, state_, ",
					"testString) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"))) {

			preparedStatement.setLong(1, 0);
			preparedStatement.setLong(2, increment());
			preparedStatement.setTimestamp(3, timestamp);
			preparedStatement.setTimestamp(4, timestamp);
			preparedStatement.setString(5, bundleSymbolicName);
			preparedStatement.setString(6, "0.0.1");
			preparedStatement.setInt(7, 001);
			preparedStatement.setTimestamp(8, timestamp);
			preparedStatement.setBoolean(9, false);
			preparedStatement.setInt(10, 0);
			preparedStatement.setString(11, ReleaseConstants.TEST_STRING);

			preparedStatement.execute();
		}
	}

	protected boolean hasRelease(
			Connection connection, String bundleSymbolicName)
		throws SQLException {

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"select * from Release_ where servletContextName = ?")) {

			preparedStatement.setString(1, bundleSymbolicName);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}
	}

	protected long increment() {
		return CounterLocalServiceUtil.increment();
	}

}