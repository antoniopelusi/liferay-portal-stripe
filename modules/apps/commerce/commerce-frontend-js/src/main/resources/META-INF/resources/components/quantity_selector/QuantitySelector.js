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

import classnames from 'classnames';
import PropTypes from 'prop-types';
import React, {forwardRef, useRef} from 'react';

import InputQuantitySelector from './InputQuantitySelector';
import ListQuantitySelector from './ListQuantitySelector';

const QuantitySelector = forwardRef(
	(
		{
			alignment,
			allowedQuantities,
			disabled,
			max,
			min,
			name,
			onUpdate,
			quantity,
			size,
			step,
		},
		providedRef
	) => {
		const inputRef = useRef();

		const Selector =
			allowedQuantities?.length > 0
				? ListQuantitySelector
				: InputQuantitySelector;

		return (
			<Selector
				alignment={alignment}
				allowedQuantities={allowedQuantities}
				className={classnames({
					[`form-control-${size}`]: size,
					'quantity-selector': true,
				})}
				disabled={disabled}
				max={max}
				min={min}
				name={name}
				onUpdate={onUpdate}
				quantity={quantity}
				ref={providedRef || inputRef}
				step={step}
			/>
		);
	}
);

QuantitySelector.defaultProps = {
	disabled: false,
};

QuantitySelector.propTypes = {
	alignment: PropTypes.oneOf(['top', 'bottom']),
	disabled: PropTypes.bool,
	name: PropTypes.string,
	onUpdate: PropTypes.func.isRequired,
	quantity: PropTypes.number,
	size: PropTypes.oneOf(['lg', 'md', 'sm']),
};

export default QuantitySelector;
