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

import '../../tests_utilities/polyfills';

import '@testing-library/jest-dom/extend-expect';
import {cleanup, fireEvent, render, screen} from '@testing-library/react';
import React from 'react';
import {act} from 'react-dom/test-utils';

import InputQuantitySelector from '../../../src/main/resources/META-INF/resources/components/quantity_selector/InputQuantitySelector';

const props = {
	allowedQuantities: [],
	disabled: false,
	max: 9999,
	min: 1,
	name: 'test-name',
	quantity: 1,
	size: 'md',
	step: 1,
};

describe('Quantity Selector - Popover', () => {
	let inputQuantitySelector;
	let input;
	let onUpdate;
	const defaultProps = {...props};

	beforeEach(() => {
		onUpdate = jest.fn();

		defaultProps.onUpdate = onUpdate;

		inputQuantitySelector = render(
			<InputQuantitySelector {...defaultProps} ref={{current: null}} />
		);

		input = inputQuantitySelector.container.querySelector('input');
	});

	afterEach(() => {
		cleanup();
	});

	it('must NOT show a popover when the min quantity is 1 and the current value is < 1', () => {
		jest.useFakeTimers();

		inputQuantitySelector.rerender(
			<InputQuantitySelector min={1} quantity={-1} />
		);

		act(() => {
			fireEvent.focus(input);
			jest.runAllTimers();
		});

		const popover = document.querySelector('.popover');

		expect(popover).not.toBeInTheDocument();
	});

	it('must show a popover when the min quantity is > 1 and the current value is <= 1', () => {
		jest.useFakeTimers();

		inputQuantitySelector.rerender(
			<InputQuantitySelector min={4} quantity={1} />
		);

		fireEvent.focus(input);

		act(() => {
			jest.runAllTimers();
		});

		const popover = document.querySelector('.popover');

		expect(popover).toBeInTheDocument();

		expect(
			screen.getByText(/min-quantity-per-order-is/)
		).toBeInTheDocument();
	});

	it("must show a popover when the max quantity is defined and the current value doesn't apply", () => {
		jest.useFakeTimers();

		inputQuantitySelector.rerender(
			<InputQuantitySelector max={5} quantity={7} />
		);

		fireEvent.focus(input);

		act(() => {
			jest.runAllTimers();
		});

		const popover = document.querySelector('.popover');

		expect(popover).toBeInTheDocument();

		expect(
			screen.getByText(/max-quantity-per-order-is-/)
		).toBeInTheDocument();
	});

	it("must show a popover when the multiple quantity is > 1 and the current value doesn't apply", () => {
		jest.useFakeTimers();

		inputQuantitySelector.rerender(
			<InputQuantitySelector quantity={3} step={4} />
		);

		fireEvent.focus(input);

		act(() => {
			jest.runAllTimers();
		});

		const popover = document.querySelector('.popover');

		expect(popover).toBeInTheDocument();

		expect(
			screen.getByText(/quantity-must-be-a-multiple-of-/)
		).toBeInTheDocument();
	});

	it("must inform the user when multiple constrains don't apply to the current value - min, multiple", () => {
		jest.useFakeTimers();

		inputQuantitySelector.rerender(
			<InputQuantitySelector max={60} min={5} quantity={3} step={4} />
		);

		fireEvent.focus(input);

		act(() => {
			jest.runAllTimers();
		});

		const popover = document.querySelector('.popover');

		expect(popover).toBeInTheDocument();

		const maxMessage = screen.getByText(/max-quantity-per-order-is-/);
		const minMessage = screen.getByText(/min-quantity-per-order-is/);
		const multipleMessage = screen.getByText(
			/quantity-must-be-a-multiple-of-/
		);

		expect(maxMessage).toBeInTheDocument();
		expect(maxMessage.classList).not.toContain('text-danger');

		expect(multipleMessage).toBeInTheDocument();
		expect(multipleMessage.classList).toContain('text-danger');

		expect(minMessage).toBeInTheDocument();
		expect(minMessage.classList).toContain('text-danger');
	});

	it("must inform the user when multiple constrains don't apply to the current value - max, multiple", () => {
		jest.useFakeTimers();

		inputQuantitySelector.rerender(
			<InputQuantitySelector max={60} min={5} quantity={61} step={4} />
		);

		fireEvent.focus(input);

		act(() => {
			jest.runAllTimers();
		});

		const popover = document.querySelector('.popover');

		expect(popover).toBeInTheDocument();

		const maxMessage = screen.getByText(/max-quantity-per-order-is-/);
		const minMessage = screen.getByText(/min-quantity-per-order-is/);
		const multipleMessage = screen.getByText(
			/quantity-must-be-a-multiple-of-/
		);

		expect(maxMessage).toBeInTheDocument();
		expect(maxMessage.classList).toContain('text-danger');

		expect(multipleMessage).toBeInTheDocument();
		expect(multipleMessage.classList).toContain('text-danger');

		expect(minMessage).toBeInTheDocument();
		expect(minMessage.classList).not.toContain('text-danger');
	});
});
