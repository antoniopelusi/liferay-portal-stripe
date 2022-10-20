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

import ClayButton from '@clayui/button';
import React from 'react';

import {findRuleByFieldName} from '../../utils/rulesSupport';
import {PagesVisitor} from '../../utils/visitors.es';

export default function pageReset({
	action,
	modalDispatch,
	onClose,
	pages,
	rules,
}) {
	const currentPage = pages[action.payload.pageIndex];
	const visitor = new PagesVisitor([currentPage]);

	return (dispatch) => {
		let ruleFound = false;
		visitor.mapFields((field) => {
			if (findRuleByFieldName(field.fieldName, null, rules)) {
				ruleFound = true;

				return;
			}
		});

		if (ruleFound) {
			modalDispatch({
				payload: {
					body: Liferay.Language.get(
						'a-rule-is-applied-to-fields-of-this-page'
					),
					footer: [
						null,
						null,
						<ClayButton.Group key={3} spaced>
							<ClayButton
								displayType="secondary"
								onClick={onClose}
							>
								{Liferay.Language.get('cancel')}
							</ClayButton>

							<ClayButton
								displayType="danger"
								onClick={() => {
									onClose();
									dispatch(action);
								}}
							>
								{Liferay.Language.get('confirm')}
							</ClayButton>
						</ClayButton.Group>,
					],
					header: Liferay.Language.get(
						'reset-page-with-rule-applied-to-field'
					),
					size: 'md',
				},
				type: 1,
			});
		}
		else {
			dispatch(action);
		}
	};
}
