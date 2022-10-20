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

package com.liferay.object.model.impl;

import com.liferay.object.model.ObjectStateTransition;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ObjectStateTransition in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class ObjectStateTransitionCacheModel
	implements CacheModel<ObjectStateTransition>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ObjectStateTransitionCacheModel)) {
			return false;
		}

		ObjectStateTransitionCacheModel objectStateTransitionCacheModel =
			(ObjectStateTransitionCacheModel)object;

		if ((objectStateTransitionId ==
				objectStateTransitionCacheModel.objectStateTransitionId) &&
			(mvccVersion == objectStateTransitionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, objectStateTransitionId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", objectStateTransitionId=");
		sb.append(objectStateTransitionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", objectStateFlowId=");
		sb.append(objectStateFlowId);
		sb.append(", sourceObjectStateId=");
		sb.append(sourceObjectStateId);
		sb.append(", targetObjectStateId=");
		sb.append(targetObjectStateId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ObjectStateTransition toEntityModel() {
		ObjectStateTransitionImpl objectStateTransitionImpl =
			new ObjectStateTransitionImpl();

		objectStateTransitionImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			objectStateTransitionImpl.setUuid("");
		}
		else {
			objectStateTransitionImpl.setUuid(uuid);
		}

		objectStateTransitionImpl.setObjectStateTransitionId(
			objectStateTransitionId);
		objectStateTransitionImpl.setCompanyId(companyId);
		objectStateTransitionImpl.setUserId(userId);

		if (userName == null) {
			objectStateTransitionImpl.setUserName("");
		}
		else {
			objectStateTransitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			objectStateTransitionImpl.setCreateDate(null);
		}
		else {
			objectStateTransitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			objectStateTransitionImpl.setModifiedDate(null);
		}
		else {
			objectStateTransitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		objectStateTransitionImpl.setObjectStateFlowId(objectStateFlowId);
		objectStateTransitionImpl.setSourceObjectStateId(sourceObjectStateId);
		objectStateTransitionImpl.setTargetObjectStateId(targetObjectStateId);

		objectStateTransitionImpl.resetOriginalValues();

		return objectStateTransitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		objectStateTransitionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		objectStateFlowId = objectInput.readLong();

		sourceObjectStateId = objectInput.readLong();

		targetObjectStateId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(objectStateTransitionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(objectStateFlowId);

		objectOutput.writeLong(sourceObjectStateId);

		objectOutput.writeLong(targetObjectStateId);
	}

	public long mvccVersion;
	public String uuid;
	public long objectStateTransitionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long objectStateFlowId;
	public long sourceObjectStateId;
	public long targetObjectStateId;

}