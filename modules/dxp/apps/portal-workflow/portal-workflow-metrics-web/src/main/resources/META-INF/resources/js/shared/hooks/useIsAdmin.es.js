/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {fetch} from 'frontend-js-web';
import {useCallback, useState} from 'react';

import {headers} from '../rest/fetch.es';

const useIsAdmin = () => {
	const [isAdmin, setAdmin] = useState();

	const fetchData = useCallback(async () => {
		const fetchURL = new URL(
			'/o/headless-admin-user/v1.0/my-user-account',
			Liferay.ThemeDisplay.getPortalURL()
		);

		const response = await fetch(fetchURL, {
			headers,
			method: 'GET',
		});

		const data = await response.json();

		if (response.ok) {
			setAdmin(data);

			const callback = (currentUserAccount) => {
				setAdmin(
					currentUserAccount?.roleBriefs?.some(
						(role) => role.name === 'Administrator'
					)
				);
			};

			return callback(data);
		}

		throw data;
	}, []);

	return {
		fetchData,
		isAdmin,
	};
};

export {useIsAdmin};
