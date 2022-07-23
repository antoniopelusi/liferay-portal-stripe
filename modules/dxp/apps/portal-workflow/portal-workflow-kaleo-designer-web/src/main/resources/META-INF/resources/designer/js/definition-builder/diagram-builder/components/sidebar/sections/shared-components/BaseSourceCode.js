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

import {Editor} from 'frontend-editor-ckeditor-web';
import React, {useRef} from 'react';

import {editorConfig} from '../../../../../constants';

const BaseSourceCode = ({scriptSourceCode, updateSelectedItem}) => {
	const editorRef = useRef();

	return (
		<Editor
			config={editorConfig}
			onInstanceReady={({editor}) => {
				editor.setMode('source');

				if (scriptSourceCode) {
					editor.setData(scriptSourceCode[0]);
				}

				document
					.querySelector('div.sidebar-body')
					.addEventListener('keyup', () => {
						updateSelectedItem(editor);
					});

				return () => {
					document
						.querySelector('div.sidebar-body')
						.removeEventListener(
							'keyup',
							updateSelectedItem(editor)
						);
				};
			}}
			ref={editorRef}
		/>
	);
};

export default BaseSourceCode;
