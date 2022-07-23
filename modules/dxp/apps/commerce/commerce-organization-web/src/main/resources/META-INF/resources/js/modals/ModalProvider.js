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

import {useModal} from '@clayui/modal';
import React, {useMemo} from 'react';

import AddAccountsModal from './AddAccountsModal';
import AddOrganizationsModal from './AddOrganizationsModal';
import InviteUsersModal from './InviteUsersModal';

const modals = {
	account: AddAccountsModal,
	organization: AddOrganizationsModal,
	user: InviteUsersModal,
};

export default function ModalProvider({active, closeModal, parentData, type}) {
	const {observer, onClose} = useModal({
		onClose: closeModal,
	});

	const Modal = useMemo(() => parentData && modals[type], [parentData, type]);

	return (
		active && (
			<Modal
				closeModal={onClose}
				observer={observer}
				parentData={parentData}
			/>
		)
	);
}
