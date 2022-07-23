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

import ClayButton, {ClayButtonWithIcon} from '@clayui/button';
import React, {useState} from 'react';

const ActionTypeNotification = () => {
	const [notificationSections, setNotificationSections] = useState([
		{identifier: `${Date.now()}-0`},
	]);

	const deleteSection = (identifier) => {
		setNotificationSections((prevSections) => {
			const newSections = prevSections.filter(
				(prevSection) => prevSection.identifier !== identifier
			);

			return newSections;
		});
	};

	return notificationSections.map(({identifier}) => {
		return (
			<div key={`section-${identifier}`}>
				<div>Notification Placeholder {identifier}</div>

				<div className="section-buttons-area">
					<ClayButton
						className="mr-3"
						displayType="secondary"
						onClick={() =>
							setNotificationSections((prev) => {
								return [
									...prev,
									{
										identifier: `${Date.now()}-${
											prev.length
										}`,
									},
								];
							})
						}
					>
						Add Button Placeholder
					</ClayButton>

					{notificationSections.length > 1 && (
						<ClayButtonWithIcon
							className="delete-button"
							displayType="unstyled"
							onClick={() => deleteSection(identifier)}
							symbol="trash"
						/>
					)}
				</div>
			</div>
		);
	});
};

export default ActionTypeNotification;
