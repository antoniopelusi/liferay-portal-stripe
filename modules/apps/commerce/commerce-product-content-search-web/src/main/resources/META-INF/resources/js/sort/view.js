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

import {createPortletURL, navigate} from 'frontend-js-web';

function changeOrderBy(currentURL, portletDisplayId, label) {
	const newUrl = createPortletURL(currentURL, {
		orderByCol: label,
		p_p_id: portletDisplayId,
	});

	navigate(newUrl.toString());
}

export default function ({currentURL, namespace, portletDisplayId}) {
	const optionLinks = document.querySelectorAll(
		`.sortWidgetOptions[id^=${namespace}]`
	);

	const commerceSortByButton = document.getElementById('commerce-order-by');

	const dropdownSortByButton = document.getElementById(
		`${namespace}commerce-dropdown-order-by`
	);

	function handleOptionLinks(event) {
		event.preventDefault();

		changeOrderBy(
			currentURL,
			portletDisplayId,
			event.target.getAttribute('data-label')
		);
	}

	optionLinks.forEach((link) => {
		link.addEventListener('click', handleOptionLinks);
	});

	const closeDropDownOnClickOutside = (event) => {
		if (
			event.target !== commerceSortByButton &&
			dropdownSortByButton.classList.contains('show') &&
			event.target.parentNode !== dropdownSortByButton
		) {
			dropdownSortByButton.classList.remove('show');
		}
	};

	window.addEventListener('mouseup', closeDropDownOnClickOutside);

	const clickOnSortButton = () => {
		if (dropdownSortByButton) {
			dropdownSortByButton.classList.toggle('show');
		}
	};

	commerceSortByButton.addEventListener('click', clickOnSortButton);

	return {
		dispose() {
			window.removeEventListener('mouseup', closeDropDownOnClickOutside);
			commerceSortByButton.removeEventListener(
				'click',
				clickOnSortButton
			);
			optionLinks?.forEach((link) => {
				link.removeEventListener('click', handleOptionLinks);
			});
		},
	};
}
