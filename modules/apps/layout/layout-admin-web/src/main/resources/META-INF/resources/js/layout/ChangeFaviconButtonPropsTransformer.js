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

import {openSelectionModal} from 'frontend-js-web';

export default function propsTransformer({
	additionalProps,
	portletNamespace,
	...props
}) {
	return {
		...props,
		onClick() {
			const {url} = additionalProps;

			openSelectionModal({
				onSelect(selectedItem) {
					if (selectedItem) {
						const faviconFileEntryId = document.getElementById(
							`${portletNamespace}faviconFileEntryId`
						);
						const faviconImage = document.getElementById(
							`${portletNamespace}faviconImage`
						);
						const faviconTitle = document.getElementById(
							`${portletNamespace}faviconTitle`
						);
						const themeFaviconCETExternalReferenceCode = document.getElementById(
							`${portletNamespace}themeFaviconCETExternalReferenceCode`
						);

						if (
							faviconFileEntryId &&
							faviconImage &&
							faviconTitle &&
							selectedItem &&
							selectedItem.value &&
							themeFaviconCETExternalReferenceCode
						) {
							const itemValue = JSON.parse(selectedItem.value);

							if (
								selectedItem.returnType ===
								'com.liferay.client.extension.type.item.selector.CETItemSelectorReturnType'
							) {
								faviconFileEntryId.value = 0;
								themeFaviconCETExternalReferenceCode.value =
									itemValue.cetExternalReferenceCode;
							}
							else {
								faviconFileEntryId.value =
									itemValue.fileEntryId;
								themeFaviconCETExternalReferenceCode.value = '';
							}

							if (itemValue.url) {
								faviconImage.src = itemValue.url;
							}
							else {
								faviconImage.classList.add('d-none');
							}

							faviconTitle.innerHTML =
								itemValue.title || itemValue.name;
						}
					}
				},
				selectEventName: `${portletNamespace}selectImage`,
				title: Liferay.Language.get('select-favicon'),
				url: url.toString(),
			});
		},
	};
}
