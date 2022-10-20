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

package com.liferay.message.boards.internal.change.tracking.spi.reference;

import com.liferay.change.tracking.spi.reference.TableReferenceDefinition;
import com.liferay.change.tracking.spi.reference.builder.ChildTableReferenceInfoBuilder;
import com.liferay.change.tracking.spi.reference.builder.ParentTableReferenceInfoBuilder;
import com.liferay.message.boards.model.MBMessageTable;
import com.liferay.message.boards.model.MBSuspiciousActivityTable;
import com.liferay.message.boards.model.MBThreadTable;
import com.liferay.message.boards.service.persistence.MBSuspiciousActivityPersistence;
import com.liferay.portal.kernel.model.UserTable;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = TableReferenceDefinition.class)
public class MBSuspiciousActivityTableReferenceDefinition
	implements TableReferenceDefinition<MBSuspiciousActivityTable> {

	@Override
	public void defineChildTableReferences(
		ChildTableReferenceInfoBuilder<MBSuspiciousActivityTable>
			childTableReferenceInfoBuilder) {
	}

	@Override
	public void defineParentTableReferences(
		ParentTableReferenceInfoBuilder<MBSuspiciousActivityTable>
			parentTableReferenceInfoBuilder) {

		parentTableReferenceInfoBuilder.groupedModel(
			MBSuspiciousActivityTable.INSTANCE
		).singleColumnReference(
			MBSuspiciousActivityTable.INSTANCE.userId, UserTable.INSTANCE.userId
		).singleColumnReference(
			MBSuspiciousActivityTable.INSTANCE.messageId,
			MBMessageTable.INSTANCE.messageId
		).singleColumnReference(
			MBSuspiciousActivityTable.INSTANCE.threadId,
			MBThreadTable.INSTANCE.threadId
		);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _mbSuspiciousActivityPersistence;
	}

	@Override
	public MBSuspiciousActivityTable getTable() {
		return MBSuspiciousActivityTable.INSTANCE;
	}

	@Reference
	private MBSuspiciousActivityPersistence _mbSuspiciousActivityPersistence;

}