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

package com.liferay.object.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;ObjectState&quot; database table.
 *
 * @author Marco Leo
 * @see ObjectState
 * @generated
 */
public class ObjectStateTable extends BaseTable<ObjectStateTable> {

	public static final ObjectStateTable INSTANCE = new ObjectStateTable();

	public final Column<ObjectStateTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<ObjectStateTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTable, Long> objectStateId = createColumn(
		"objectStateId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ObjectStateTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTable, Long> listTypeEntryId = createColumn(
		"listTypeEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTable, Long> objectStateFlowId =
		createColumn(
			"objectStateFlowId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private ObjectStateTable() {
		super("ObjectState", ObjectStateTable::new);
	}

}