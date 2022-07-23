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

import React, {useContext} from 'react';

import {DiagramBuilderContext} from '../../../../../DiagramBuilderContext';
import SidebarPanel from '../../../SidebarPanel';
import BaseUser from '../../shared-components/BaseUser';

const User = (props) => {
	const {setSelectedItem} = useContext(DiagramBuilderContext);

	const updateSelectedItem = (values) => {
		setSelectedItem((previousItem) => ({
			...previousItem,
			data: {
				...previousItem.data,
				assignments: {
					assignmentType: ['user'],
					emailAddress: values.map(({emailAddress}) => emailAddress),
					sectionsData: values.map((values) => values),
				},
			},
		}));
	};

	return (
		<SidebarPanel panelTitle={Liferay.Language.get('section')}>
			<BaseUser updateSelectedItem={updateSelectedItem} {...props} />
		</SidebarPanel>
	);
};

export default User;
