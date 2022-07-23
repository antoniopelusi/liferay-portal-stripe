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

package com.liferay.sync.internal.upgrade.v1_0_2;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.sync.service.DLSyncEventLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.sync.constants.SyncDLObjectConstants;
import com.liferay.sync.internal.configuration.SyncServiceConfigurationValues;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Dennis Ju
 */
public class UpgradeSyncDLObject extends UpgradeProcess {

	public UpgradeSyncDLObject(
		CounterLocalService counterLocalService,
		DLSyncEventLocalService dlSyncEventLocalService,
		GroupLocalService groupLocalService) {

		_counterLocalService = counterLocalService;
		_dlSyncEventLocalService = dlSyncEventLocalService;
		_groupLocalService = groupLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			ActionableDynamicQuery actionableDynamicQuery =
				_groupLocalService.getActionableDynamicQuery();

			actionableDynamicQuery.setAddCriteriaMethod(
				dynamicQuery -> {
					Property classNameId = PropertyFactoryUtil.forName(
						"classNameId");
					Property siteProperty = PropertyFactoryUtil.forName("site");

					dynamicQuery.add(
						RestrictionsFactoryUtil.or(
							classNameId.eq(
								PortalUtil.getClassNameId(User.class)),
							siteProperty.eq(true)));
				});
			actionableDynamicQuery.setPerformActionMethod(
				(Group group) -> {
					if (group.isStaged()) {
						return;
					}

					try {
						_verifyDLFileEntriesAndFolders(group.getGroupId());

						_verifyLocks(group.getGroupId());
						_verifyMacPackages(group.getGroupId());
					}
					catch (Exception exception) {
						throw new PortalException(exception);
					}
				});

			actionableDynamicQuery.performActions();

			_dlSyncEventLocalService.deleteDLSyncEvents();
		}
	}

	private void _verifyDLFileEntriesAndFolders(long groupId) throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select DLFolder.companyId, DLFolder.userId, ",
					"DLFolder.userName, DLFolder.createDate, ",
					"DLFolder.modifiedDate, DLFolder.repositoryId, ",
					"DLFolder.parentFolderId as parentFolderId, ",
					"DLFolder.treePath, DLFolder.name, '' as extension, '' as ",
					"mimeType, DLFolder.description, '' as changeLog, '' as ",
					"version, 0 as versionId, 0 as size_, '",
					SyncDLObjectConstants.TYPE_FOLDER,
					"' as type, DLFolder.folderId as typePK, DLFolder.uuid_ ",
					"as typeUuid, DLFolder.status from DLFolder where ",
					"DLFolder.repositoryId = ", groupId,
					" union all select DLFileVersion.companyId, ",
					"DLFileVersion.userId, DLFileVersion.userName, ",
					"DLFileVersion.createDate, DLFileVersion.modifiedDate, ",
					"DLFileVersion.repositoryId, DLFileVersion.folderId as ",
					"parentFolderId, DLFileVersion.treePath, ",
					"DLFileVersion.title as name, DLFileVersion.extension, ",
					"DLFileVersion.mimeType, DLFileVersion.description, ",
					"DLFileVersion.changeLog, DLFileVersion.version, ",
					"DLFileVersion.fileVersionId as versionId, ",
					"DLFileVersion.size_ as size_, '",
					SyncDLObjectConstants.TYPE_FILE,
					"' as type, DLFileVersion.fileEntryId as typePK, ",
					"DLFileEntry.uuid_ as typeUuid, DLFileVersion.status from ",
					"DLFileEntry, DLFileVersion where ",
					"DLFileEntry.repositoryId = ", groupId,
					" and DLFileEntry.fileEntryId = DLFileVersion.fileEntryId ",
					"and DLFileEntry.version = DLFileVersion.version union ",
					"all select DLFileVersion.companyId, ",
					"DLFileVersion.userId, DLFileVersion.userName, ",
					"DLFileVersion.createDate, DLFileVersion.modifiedDate, ",
					"DLFileVersion.repositoryId, DLFileVersion.folderId as ",
					"parentFolderId, DLFileVersion.treePath, ",
					"DLFileVersion.title as name, DLFileVersion.extension, ",
					"DLFileVersion.mimeType, DLFileVersion.description, ",
					"DLFileVersion.changeLog, DLFileVersion.version, ",
					"DLFileVersion.fileVersionId as versionId, ",
					"DLFileVersion.size_ as size_, '",
					SyncDLObjectConstants.TYPE_PRIVATE_WORKING_COPY,
					"' as type, DLFileVersion.fileEntryId as typePK, ",
					"DLFileEntry.uuid_ as typeUuid, DLFileVersion.status from ",
					"DLFileEntry, DLFileVersion where ",
					"DLFileEntry.repositoryId = ", groupId,
					" and DLFileEntry.fileEntryId = DLFileVersion.fileEntryId ",
					"and DLFileVersion.version = '",
					DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION, "'"));
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"insert into SyncDLObject (syncDLObjectId, companyId, ",
						"userId, userName, createTime, modifiedTime, ",
						"repositoryId, parentFolderId, treePath, name, ",
						"extension, mimeType, description, changeLog, ",
						"version, versionId, size_, event, type_, typePK, ",
						"typeUuid) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ",
						"?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
			ResultSet resultSet = preparedStatement1.executeQuery()) {

			while (resultSet.next()) {
				Timestamp createDate = resultSet.getTimestamp("createDate");
				Timestamp modifiedDate = resultSet.getTimestamp("modifiedDate");
				int status = resultSet.getInt("status");

				String event = StringPool.BLANK;

				if (status == WorkflowConstants.STATUS_IN_TRASH) {
					event = SyncDLObjectConstants.EVENT_TRASH;
				}
				else {
					event = SyncDLObjectConstants.EVENT_ADD;
				}

				preparedStatement2.setLong(1, _counterLocalService.increment());
				preparedStatement2.setLong(2, resultSet.getLong("companyId"));
				preparedStatement2.setLong(3, resultSet.getLong("userId"));
				preparedStatement2.setString(
					4, resultSet.getString("userName"));
				preparedStatement2.setLong(5, createDate.getTime());
				preparedStatement2.setLong(6, modifiedDate.getTime());
				preparedStatement2.setLong(7, groupId);
				preparedStatement2.setLong(
					8, resultSet.getLong("parentFolderId"));
				preparedStatement2.setString(
					9, resultSet.getString("treePath"));
				preparedStatement2.setString(10, resultSet.getString("name"));
				preparedStatement2.setString(
					11, resultSet.getString("extension"));
				preparedStatement2.setString(
					12, resultSet.getString("mimeType"));
				preparedStatement2.setString(
					13, resultSet.getString("description"));
				preparedStatement2.setString(
					14, resultSet.getString("changeLog"));
				preparedStatement2.setString(
					15, resultSet.getString("version"));
				preparedStatement2.setLong(16, resultSet.getLong("versionId"));
				preparedStatement2.setLong(17, resultSet.getLong("size_"));
				preparedStatement2.setString(18, event);
				preparedStatement2.setString(19, resultSet.getString("type"));
				preparedStatement2.setLong(20, resultSet.getLong("typePK"));
				preparedStatement2.setString(
					21, resultSet.getString("typeUuid"));

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

	private void _verifyLocks(long groupId) throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				SQLTransformer.transform(
					StringBundler.concat(
						"select Lock_.expirationDate, Lock_.userId, ",
						"Lock_.userName, DLFileVersion.fileEntryId from ",
						"DLFileVersion, Lock_ where DLFileVersion.version = '",
						DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION,
						"' and CAST_TEXT(DLFileVersion.fileEntryId) = ",
						"Lock_.key_")));
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"update SyncDLObject set lockExpirationDate = ?, ",
						"lockUserId = ?, lockUserName = ? where typePK = ? ",
						"and repositoryId = ", groupId));
			ResultSet resultSet = preparedStatement1.executeQuery()) {

			while (resultSet.next()) {
				preparedStatement2.setTimestamp(
					1, resultSet.getTimestamp("expirationDate"));
				preparedStatement2.setLong(2, resultSet.getLong("userId"));
				preparedStatement2.setString(
					3, resultSet.getString("userName"));
				preparedStatement2.setLong(4, resultSet.getLong("fileEntryId"));

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

	private void _verifyMacPackages(long groupId) throws Exception {
		String[] fileNames =
			SyncServiceConfigurationValues.SYNC_MAC_PACKAGE_METADATA_FILE_NAMES;

		StringBundler sb = new StringBundler((fileNames.length * 4) + 5);

		sb.append("select DLFolder.folderId, DLFolder.name from DLFolder, ");
		sb.append("DLFileEntry where DLFolder.repositoryId = ");
		sb.append(groupId);
		sb.append(" and DLFileEntry.folderId = DLFolder.folderId and ");
		sb.append("DLFileEntry.title in (");

		for (int i = 0; i < fileNames.length; i++) {
			sb.append(StringPool.APOSTROPHE);
			sb.append(fileNames[i]);
			sb.append(StringPool.APOSTROPHE);

			if (i != (fileNames.length - 1)) {
				sb.append(CharPool.COMMA);
			}
		}

		sb.append(")");

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				sb.toString());
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update SyncDLObject set extraSettings = ? where typePK " +
						"= ?");
			ResultSet resultSet = preparedStatement1.executeQuery()) {

			while (resultSet.next()) {
				String name = resultSet.getString("name");

				if (!ArrayUtil.contains(
						SyncServiceConfigurationValues.
							SYNC_MAC_PACKAGE_FOLDER_EXTENSIONS,
						FileUtil.getExtension(name))) {

					continue;
				}

				JSONObject extraSettingsJSONObject = JSONUtil.put(
					"macPackage", true);

				preparedStatement2.setString(
					1, extraSettingsJSONObject.toString());

				preparedStatement2.setLong(2, resultSet.getLong("folderId"));

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

	private final CounterLocalService _counterLocalService;
	private final DLSyncEventLocalService _dlSyncEventLocalService;
	private final GroupLocalService _groupLocalService;

}