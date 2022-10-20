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

import {openToast} from 'frontend-js-web';

const titlesMap = {
	danger: Liferay.Language.get('danger'),
	success: Liferay.Language.get('success'),
	warning: Liferay.Language.get('warning'),
};

export function showNotification(
	message,
	type = 'success',
	closeable = true,
	duration = 500,
	title
) {
	openToast({
		closeable,
		delay: {
			hide: 5000,
			show: 0,
		},
		duration,
		message,
		title: title || titlesMap[type],
		type,
	});
}

export function showErrorNotification(
	error = Liferay.Language.get('unexpected-error')
) {
	showNotification(error, 'danger');
}
