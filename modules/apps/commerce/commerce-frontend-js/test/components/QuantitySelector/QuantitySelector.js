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
import {render} from '@testing-library/react';
import React from 'react';

import QuantitySelector from '../../../src/main/resources/META-INF/resources/components/quantity_selector/QuantitySelector';

const defaultProps = {
	alignment: 'top',
	allowedQuantities: null,
	disabled: false,
	max: 9999,
	min: 1,
	name: 'test-name',
	onUpdate: () => {},
	quantity: 1,
	size: 'md',
	step: 1,
};

describe('Quantity Selector', () => {
	it('must render an input when there are no allowed quantities', async () => {
		const quantitySelector = render(<QuantitySelector {...defaultProps} />);

		expect(
			quantitySelector.container.querySelector('input')
		).toBeInTheDocument();
	});

	it('must render a select box when there are allowed quantities', async () => {
		const quantitySelector = render(
			<QuantitySelector
				{...defaultProps}
				allowedQuantities={[1, 5, 10]}
			/>
		);

		expect(
			quantitySelector.container.querySelector('select')
		).toBeInTheDocument();
	});

	it("must render an input when allowed quantities is an array that doesn't contain elements", () => {
		const quantitySelector = render(
			<QuantitySelector {...defaultProps} allowedQuantities={[]} />
		);

		expect(
			quantitySelector.container.querySelector('select')
		).not.toBeInTheDocument();
	});
});
