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

package com.liferay.portal.kernel.util;

import java.text.Format;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class FastDateFormatFactoryUtil {

	public static Format getDate(int style, Locale locale, TimeZone timeZone) {
		return _fastDateFormatFactory.getDate(style, locale, timeZone);
	}

	public static Format getDate(Locale locale) {
		return _fastDateFormatFactory.getDate(locale);
	}

	public static Format getDate(Locale locale, TimeZone timeZone) {
		return _fastDateFormatFactory.getDate(locale, timeZone);
	}

	public static Format getDate(TimeZone timeZone) {
		return _fastDateFormatFactory.getDate(timeZone);
	}

	public static Format getDateTime(
		int dateStyle, int timeStyle, Locale locale, TimeZone timeZone) {

		return _fastDateFormatFactory.getDateTime(
			dateStyle, timeStyle, locale, timeZone);
	}

	public static Format getDateTime(Locale locale) {
		return _fastDateFormatFactory.getDateTime(locale);
	}

	public static Format getDateTime(Locale locale, TimeZone timeZone) {
		return _fastDateFormatFactory.getDateTime(locale, timeZone);
	}

	public static Format getDateTime(TimeZone timeZone) {
		return _fastDateFormatFactory.getDateTime(timeZone);
	}

	public static FastDateFormatFactory getFastDateFormatFactory() {
		return _fastDateFormatFactory;
	}

	public static Format getSimpleDateFormat(String pattern) {
		return _fastDateFormatFactory.getSimpleDateFormat(pattern);
	}

	public static Format getSimpleDateFormat(String pattern, Locale locale) {
		return _fastDateFormatFactory.getSimpleDateFormat(pattern, locale);
	}

	public static Format getSimpleDateFormat(
		String pattern, Locale locale, TimeZone timeZone) {

		return _fastDateFormatFactory.getSimpleDateFormat(
			pattern, locale, timeZone);
	}

	public static Format getSimpleDateFormat(
		String pattern, TimeZone timeZone) {

		return _fastDateFormatFactory.getSimpleDateFormat(pattern, timeZone);
	}

	public static Format getTime(int style, Locale locale, TimeZone timeZone) {
		return _fastDateFormatFactory.getTime(style, locale, timeZone);
	}

	public static Format getTime(Locale locale) {
		return _fastDateFormatFactory.getTime(locale);
	}

	public static Format getTime(Locale locale, TimeZone timeZone) {
		return _fastDateFormatFactory.getTime(locale, timeZone);
	}

	public static Format getTime(TimeZone timeZone) {
		return _fastDateFormatFactory.getTime(timeZone);
	}

	public void setFastDateFormatFactory(
		FastDateFormatFactory fastDateFormatFactory) {

		_fastDateFormatFactory = fastDateFormatFactory;
	}

	private static FastDateFormatFactory _fastDateFormatFactory;

}