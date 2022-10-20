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

/// <reference types="react" />

import './FormCustomSelect.scss';
export declare function FormCustomSelect<
	T extends CustomItem<number | string> = CustomItem
>({
	className,
	disabled,
	error,
	feedbackMessage,
	id,
	label,
	multipleChoice,
	onChange,
	options,
	placeholder,
	required,
	selectAllOption,
	setOptions,
	value,
}: IProps<T>): JSX.Element;
export interface CustomItem<T = string> {
	checked?: boolean;
	description?: string;
	label: string;
	value?: T;
}
interface IProps<T extends CustomItem<number | string> = CustomItem> {
	className?: string;
	disabled?: boolean;
	error?: string;
	feedbackMessage?: string;
	id?: string;
	label?: string;
	multipleChoice?: boolean;
	onChange?: (selected: T) => void;
	options: T[];
	placeholder?: string;
	required?: boolean;
	selectAllOption?: boolean;
	setOptions?: (options: T[]) => void;
	value?: string | number | string[];
}
export {};
