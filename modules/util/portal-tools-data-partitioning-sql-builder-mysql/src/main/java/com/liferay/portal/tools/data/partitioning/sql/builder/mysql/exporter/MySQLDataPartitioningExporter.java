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

package com.liferay.portal.tools.data.partitioning.sql.builder.mysql.exporter;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.BaseDataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.InsertSQLBuilder;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.serializer.DefaultFieldSerializer;

import java.text.SimpleDateFormat;

/**
 * @author Manuel de la Peña
 */
public class MySQLDataPartitioningExporter
	extends BaseDataPartitioningExporter {

	public MySQLDataPartitioningExporter() {
		super(
			new InsertSQLBuilder(
				new DefaultFieldSerializer(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));
	}

	@Override
	public String getControlTableNamesSQL(ExportContext exportContext) {
		return StringBundler.concat(
			"select c1.", getTableNameFieldName(),
			" from information_schema.columns c1 where c1.table_schema = '",
			exportContext.getSchemaName(), "' and c1.", getTableNameFieldName(),
			" not in (", getPartitionedTableNamesSQL(exportContext),
			") group by c1.", getTableNameFieldName(), " order by c1.",
			getTableNameFieldName());
	}

	@Override
	public int getFetchSize() {
		return Integer.MIN_VALUE;
	}

	@Override
	public String getPartitionedTableNamesSQL(ExportContext exportContext) {
		return StringBundler.concat(
			"select c2.", getTableNameFieldName(),
			" from information_schema.columns c2 where c2.table_schema = '",
			exportContext.getSchemaName(),
			"' and c2.column_name = 'companyId' group by c2.",
			getTableNameFieldName(), " order by c2.", getTableNameFieldName());
	}

	@Override
	public String getTableNameFieldName() {
		return "table_name";
	}

}