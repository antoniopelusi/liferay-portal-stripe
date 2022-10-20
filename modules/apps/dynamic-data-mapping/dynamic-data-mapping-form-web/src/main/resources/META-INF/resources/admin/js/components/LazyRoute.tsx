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

import React, {lazy, useMemo} from 'react';
import {Route} from 'react-router-dom';

export default function LazyRoute({
	importPath,
	...otherProps
}: {
	exact?: boolean;
	importPath: string;
	path: string;
}) {
	const Component = useMemo(
		() =>
			lazy(
				() =>
					new Promise((resolve, reject) => {

						// @ts-ignore

						Liferay.Loader.require(
							[importPath],

							// @ts-ignore

							(Component) => resolve(Component),

							// @ts-ignore

							(error) => reject(error as Error)
						);
					})
			),
		[importPath]
	);

	return (
		<Route
			{...otherProps}
			render={(props: any) => <Component {...props} />}
		/>
	);
}
