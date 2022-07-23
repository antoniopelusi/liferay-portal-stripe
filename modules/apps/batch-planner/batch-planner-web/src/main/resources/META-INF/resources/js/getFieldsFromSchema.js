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

const getFieldsFromSchema = (schema) => {
	const dbFields = {
		optional: [],
		required: [],
	};

	if (!schema) {
		return dbFields;
	}

	for (const [label, property] of Object.entries(schema)) {
		if (property.writeOnly || property.readOnly || label.startsWith('x-')) {
			continue;
		}

		let name = label;

		if (property.extensions && property.extensions['x-parent-map']) {
			name = property.extensions['x-parent-map'] + '_' + label;
		}

		const field = {
			description: property.description,
			label,
			name,
		};

		dbFields[property.required ? 'required' : 'optional'].push(field);
	}

	return dbFields;
};

export default getFieldsFromSchema;
