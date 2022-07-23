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

import '@testing-library/jest-dom/extend-expect';
import {act, cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import ImportMappingItem from '../../../src/main/resources/META-INF/resources/js/import/ImportMappingItem';

const BASE_PROPS = {
	dbField: {
		label: 'nameLabel',
		name: 'name',
	},
	fileFields: ['first name', 'last name', 'address'],
	formEvaluated: false,
	portletNamespace: 'test',
	required: true,
	selectedFileField: 'first name',
	updateFieldMapping: () => {},
};

describe('ImportMappingItem', () => {
	afterEach(cleanup);

	it('must render', () => {
		render(<ImportMappingItem {...BASE_PROPS} />);
	});

	it('must call the updateFieldMapping method when a user selects a value', () => {
		const onChangeMock = jest.fn();

		const {getByLabelText} = render(
			<ImportMappingItem
				{...BASE_PROPS}
				updateFieldMapping={onChangeMock}
			/>
		);

		act(() => {
			fireEvent.change(getByLabelText(/nameLabel/), {
				target: {value: 'address'},
			});
		});

		expect(onChangeMock).toBeCalledWith('address');
	});

	it('must have a error status when the form is evaluated, the field is required and no value is selected', () => {
		const {container} = render(
			<ImportMappingItem
				{...BASE_PROPS}
				formEvaluated={true}
				selectedFileField=""
			/>
		);

		expect(
			container.querySelector('.form-group.has-error')
		).toBeInTheDocument();
	});

	it('must have a success status when the form is evaluated and the field is not required', () => {
		const {container} = render(
			<ImportMappingItem
				{...BASE_PROPS}
				dbField={{
					label: 'nameLabel',
					name: 'name',
				}}
				formEvaluated={true}
				required={false}
				selectedFileField=""
			/>
		);

		expect(
			container.querySelector('.form-group.has-success')
		).toBeInTheDocument();
	});
});
