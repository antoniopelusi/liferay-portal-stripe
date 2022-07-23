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

import {ClayButtonWithIcon} from '@clayui/button';
import {ClayDropDownWithItems} from '@clayui/drop-down';
import React, {useContext} from 'react';

import ChartContext from '../ChartContext';
import {VIEWS} from '../utils/constants';

function ViewSelector() {
	const {currentView, updateCurrentView} = useContext(ChartContext);

	const viewTypes = VIEWS.map((view) => ({
		active: view.id === currentView.id,
		label: view.label,
		onClick: () => updateCurrentView(view),
		symbolLeft: view.symbol,
	}));

	return (
		<ClayDropDownWithItems
			items={viewTypes}
			trigger={
				<ClayButtonWithIcon
					className="nav-link nav-link-monospaced"
					displayType="unstyled"
					monospaced
					symbol={currentView.symbol}
				/>
			}
		/>
	);
}

export default ViewSelector;
