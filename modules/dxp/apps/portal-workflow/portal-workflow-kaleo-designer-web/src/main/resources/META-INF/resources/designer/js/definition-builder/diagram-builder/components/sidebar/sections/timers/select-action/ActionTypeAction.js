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

// import ClayButton, {ClayButtonWithIcon} from '@clayui/button';

import PropTypes from 'prop-types';
import React, {useContext, useState} from 'react';

import {DiagramBuilderContext} from '../../../../../DiagramBuilderContext';
import BaseActionsInfo from '../../shared-components/BaseActionsInfo';

const ActionTypeAction = ({
	actionData,
	actionSectionsIndex,
	actionType,
	setActionSections,
}) => {
	const {selectedItem} = useContext(DiagramBuilderContext);
	const validActionData =
		actionData.actionType === 'timerActions' ? actionData : null;
	const [script, setScript] = useState(validActionData?.script || '');
	const [description, setDescription] = useState(
		validActionData?.description || ''
	);
	const [name, setName] = useState(validActionData?.name || '');
	const [priority, setPriority] = useState(validActionData?.priority || 1);

	const updateActionInfo = (item) => {
		if (item.name && item.script) {
			setActionSections((previousSections) => {
				const updatedSections = [...previousSections];

				updatedSections[actionSectionsIndex] = {
					...previousSections[actionSectionsIndex],
					...item,
					actionType,
				};

				return updatedSections;
			});
		}
	};

	return (
		<BaseActionsInfo
			description={description}
			name={name}
			placeholderName={Liferay.Language.get('my-action')}
			placeholderScript="${userName} sent you a ${entryType} for review in the workflow."
			priority={priority}
			script={script}
			scriptLabel={Liferay.Language.get('script')}
			scriptLabelSecondary={Liferay.Language.get('groovy')}
			selectedItem={selectedItem}
			setDescription={setDescription}
			setName={setName}
			setPriority={setPriority}
			setScript={setScript}
			updateActionInfo={updateActionInfo}
		/>
	);
};

ActionTypeAction.propTypes = {
	actionSectionsIndex: PropTypes.number.isRequired,
	actionSubSectionsIndex: PropTypes.number.isRequired,
	timersIndex: PropTypes.number.isRequired,
	updateSelectedItem: PropTypes.func.isRequired,
};

export default ActionTypeAction;
