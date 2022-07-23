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

import {useEffect} from 'react';

export function useEscapeKeyHandler(
	isFullscreen,
	isTooltipOpen,
	compress,
	closeTooltip
) {
	useEffect(() => {
		function handleEscapeKeyPress(event) {
			if (event.key === 'Escape') {
				if (isTooltipOpen) {
					return closeTooltip();
				}

				if (isFullscreen) {
					return compress();
				}
			}
		}

		if (isFullscreen || isTooltipOpen) {
			document.addEventListener('keyup', handleEscapeKeyPress, {
				once: true,
			});
		}
		else {
			document.removeEventListener('keyup', handleEscapeKeyPress);
		}

		return () => {
			document.removeEventListener('keyup', handleEscapeKeyPress);
		};
	}, [closeTooltip, compress, isFullscreen, isTooltipOpen]);
}
