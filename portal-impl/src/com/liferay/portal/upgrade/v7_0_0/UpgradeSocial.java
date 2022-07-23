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

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Adolfo Pérez
 */
public class UpgradeSocial extends UpgradeProcess {

	protected void addSocialActivitySets(long delta) throws Exception {
		try (Statement s = connection.createStatement()) {
			s.execute(
				StringBundler.concat(
					"insert into SocialActivitySet select (activityId + ",
					delta, ") as activitySetId, groupId, companyId, userId, ",
					"createDate, createDate AS modifiedDate, classNameId, ",
					"classPK, type_, extraData, 1 as activityCount from ",
					"SocialActivity where mirrorActivityId = 0"));
		}
	}

	protected void deleteOrphanedSocialRequests() throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"delete from SocialRequest where classNameId = ? and classPK " +
					"not in (select groupId from Group_)")) {

			preparedStatement.setLong(
				1,
				PortalUtil.getClassNameId(
					"com.liferay.portal.kernel.model.Group"));

			preparedStatement.execute();
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (getSocialActivitySetsCount() > 0) {
			return;
		}

		long increment = increment();

		long delta = getDelta(increment);

		addSocialActivitySets(delta);

		deleteOrphanedSocialRequests();

		updateSocialActivities(delta);

		long counterIncrement = _getCounterIncrement();

		while (counterIncrement > Integer.MAX_VALUE) {
			increment(Counter.class.getName(), Integer.MAX_VALUE);

			counterIncrement -= Integer.MAX_VALUE;
		}

		if (counterIncrement > 0) {
			increment(Counter.class.getName(), (int)counterIncrement);
		}
	}

	protected long getDelta(long increment) throws Exception {
		try (Statement s = connection.createStatement()) {
			try (ResultSet resultSet = s.executeQuery(
					"select min(activityId) from SocialActivity")) {

				if (resultSet.next()) {
					long minActivityId = resultSet.getLong(1);

					return increment - minActivityId;
				}

				return 0;
			}
		}
	}

	protected int getSocialActivitySetsCount() throws Exception {
		try (Statement s = connection.createStatement()) {
			String query = "select count(activitySetId) from SocialActivitySet";

			try (ResultSet resultSet = s.executeQuery(query)) {
				if (resultSet.next()) {
					return resultSet.getInt(1);
				}

				return 0;
			}
		}
	}

	protected void updateSocialActivities(long delta) throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"update SocialActivity set activitySetId = (activityId + ?) " +
					"where mirrorActivityId = 0")) {

			preparedStatement.setLong(1, delta);

			preparedStatement.execute();
		}
	}

	private long _getCounterIncrement() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				"select currentId from Counter where name = ?")) {

			preparedStatement1.setString(1, Counter.class.getName());

			long counter = 0;

			try (ResultSet resultSet = preparedStatement1.executeQuery()) {
				if (resultSet.next()) {
					counter = resultSet.getLong("currentId");
				}
			}

			PreparedStatement preparedStatement2 = connection.prepareStatement(
				"select max(activitySetId) from SocialActivitySet");

			try (ResultSet resultSet = preparedStatement2.executeQuery()) {
				if (resultSet.next()) {
					return Math.max(0, resultSet.getLong(1) - counter);
				}

				return 0;
			}
		}
	}

}