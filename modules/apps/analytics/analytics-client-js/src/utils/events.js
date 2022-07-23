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

import {getClosestAssetElement} from '../utils/assets';
import {convertUTCDateToLocalDate} from './date';

const onReady = (fn) => {
	if (
		document.readyState === 'interactive' ||
		document.readyState === 'complete' ||
		document.readyState === 'loaded'
	) {
		fn();
	}
	else {
		document.addEventListener('DOMContentLoaded', fn);
	}

	return () => document.removeEventListener('DOMContentLoaded', fn);
};

/**
 * Creates an event listener for all event types in events array.
 *
 * @param {Array.string} events Array of event type.
 * @param {Function} fn The event listener callback.
 * @returns {Function} The function to remove all the event listers that were added from events param.
 */
const onEvents = (events, fn) => {
	if (events) {
		events.forEach((eventName) => document.addEventListener(eventName, fn));

		return () => {
			events.forEach((eventName) => {
				document.removeEventListener(eventName, fn);
			});
		};
	}
};

const clickEvent = ({
	analytics,
	applicationId,
	eventType,
	getPayload,
	isTrackable,
	type,
}) => {
	const onClick = ({target}) => {
		const element = getClosestAssetElement(target, type);

		if (!isTrackable(element) || target.control) {
			return;
		}

		const tagName = target.tagName.toLowerCase();

		const payload = getPayload(element);
		Object.assign(payload, {tagName});

		if (tagName === 'a') {
			Object.assign(payload, {
				href: target.href,
				text: target.innerText,
			});
		}
		else if (tagName === 'img') {
			Object.assign(payload, {
				src: target.src,
			});
		}

		analytics.send(eventType, applicationId, payload);
	};

	document.addEventListener('click', onClick);

	return () => document.removeEventListener('click', onClick);
};

/**
 * Serializes data and returns the result appending a timestamp
 * to the returned data as well.
 *
 * @param {string} eventId The event Id
 * @param {string} applicationId The application Id
 * @param {Object} properties Additional properties to serialize
 * @protected
 * @returns {Object}
 */
export function normalizeEvent(
	eventId,
	applicationId,
	properties,
	contextHash
) {
	const date = new Date();
	const eventDate = date.toISOString();
	const eventLocalDate = convertUTCDateToLocalDate(date).toISOString();

	return {
		applicationId,
		contextHash,
		eventDate,
		eventId,
		eventLocalDate,
		properties,
	};
}

/**
 * Sort comparator for ISO 8601 eventDates in ascending order.
 *
 * @param {Object} a - First event to compare.
 * @param {Object} b - Second event to compare.
 * @returns {Number}    Comparison result.
 */
const sortByEventDate = (a, b) => {
	if (a.eventDate < b.eventDate) {
		return -1;
	}

	if (a.eventDate > b.eventDate) {
		return 1;
	}

	return 0;
};

const removeDups = (results, items) => {
	const events = results.flatMap(({value}) => value.events);

	return items.filter(
		({contextHash, eventDate, eventId}) =>
			!events.some(
				({
					contextHash: resultContextHash,
					eventDate: resultEventDate,
					eventId: resultEventId,
				}) =>
					contextHash === resultContextHash &&
					eventId === resultEventId &&
					eventDate === resultEventDate
			)
	);
};

export {clickEvent, onEvents, onReady, removeDups, sortByEventDate};
