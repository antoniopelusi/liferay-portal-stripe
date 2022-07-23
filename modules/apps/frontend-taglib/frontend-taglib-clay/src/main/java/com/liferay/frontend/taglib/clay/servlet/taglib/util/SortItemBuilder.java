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

package com.liferay.frontend.taglib.clay.servlet.taglib.util;

import com.liferay.petra.function.UnsafeSupplier;

/**
 * @author Hugo Huijser
 */
public class SortItemBuilder {

	public static AfterDirectionStep setDirection(String direction) {
		SortItemStep sortItemStep = new SortItemStep();

		return sortItemStep.setDirection(direction);
	}

	public static AfterDirectionStep setDirection(
		UnsafeSupplier<String, Exception> directionUnsafeSupplier) {

		SortItemStep sortItemStep = new SortItemStep();

		return sortItemStep.setDirection(directionUnsafeSupplier);
	}

	public static AfterKeyStep setKey(String key) {
		SortItemStep sortItemStep = new SortItemStep();

		return sortItemStep.setKey(key);
	}

	public static AfterKeyStep setKey(
		UnsafeSupplier<String, Exception> keyUnsafeSupplier) {

		SortItemStep sortItemStep = new SortItemStep();

		return sortItemStep.setKey(keyUnsafeSupplier);
	}

	public static class SortItemStep
		implements AfterDirectionStep, AfterKeyStep, BuildStep, DirectionStep,
				   KeyStep {

		@Override
		public SortItem build() {
			return _sortItem;
		}

		@Override
		public AfterDirectionStep setDirection(String direction) {
			_sortItem.setDirection(direction);

			return this;
		}

		@Override
		public AfterDirectionStep setDirection(
			UnsafeSupplier<String, Exception> directionUnsafeSupplier) {

			try {
				String direction = directionUnsafeSupplier.get();

				if (direction != null) {
					_sortItem.setDirection(direction);
				}

				return this;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		@Override
		public AfterKeyStep setKey(String key) {
			_sortItem.setKey(key);

			return this;
		}

		@Override
		public AfterKeyStep setKey(
			UnsafeSupplier<String, Exception> keyUnsafeSupplier) {

			try {
				String key = keyUnsafeSupplier.get();

				if (key != null) {
					_sortItem.setKey(key);
				}

				return this;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		private final SortItem _sortItem = new SortItem();

	}

	public interface AfterDirectionStep extends BuildStep, KeyStep {
	}

	public interface AfterKeyStep extends BuildStep {
	}

	public interface BuildStep {

		public SortItem build();

	}

	public interface DirectionStep {

		public AfterDirectionStep setDirection(String direction);

		public AfterDirectionStep setDirection(
			UnsafeSupplier<String, Exception> directionUnsafeSupplier);

	}

	public interface KeyStep {

		public AfterKeyStep setKey(String key);

		public AfterKeyStep setKey(
			UnsafeSupplier<String, Exception> keyUnsafeSupplier);

	}

}