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

package com.liferay.portal.upgrade.v7_4_x;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pei-Jung Lan
 */
public class UpgradeListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_addListType("phone-number", ListTypeConstants.ADDRESS_PHONE);
	}

	private void _addListType(String name, String type) throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				StringBundler.concat(
					"select * from ListType where name = ",
					StringUtil.quote(name), "and type_ = ",
					StringUtil.quote(type)))) {

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return;
			}
		}

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"insert into ListType (listTypeId, name, type_) values (?, " +
					"?, ?)")) {

			preparedStatement.setLong(1, increment(ListType.class.getName()));
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, type);

			preparedStatement.executeUpdate();
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to add list type", exception);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeListType.class);

}