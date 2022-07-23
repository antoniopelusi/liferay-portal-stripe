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
public class MultiselectItemBuilder {

	public static AfterLabelStep setLabel(String label) {
		MultiselectItemStep multiselectItemStep = new MultiselectItemStep();

		return multiselectItemStep.setLabel(label);
	}

	public static AfterLabelStep setLabel(
		UnsafeSupplier<String, Exception> labelUnsafeSupplier) {

		MultiselectItemStep multiselectItemStep = new MultiselectItemStep();

		return multiselectItemStep.setLabel(labelUnsafeSupplier);
	}

	public static AfterValueStep setValue(String value) {
		MultiselectItemStep multiselectItemStep = new MultiselectItemStep();

		return multiselectItemStep.setValue(value);
	}

	public static AfterValueStep setValue(
		UnsafeSupplier<String, Exception> valueUnsafeSupplier) {

		MultiselectItemStep multiselectItemStep = new MultiselectItemStep();

		return multiselectItemStep.setValue(valueUnsafeSupplier);
	}

	public static class MultiselectItemStep
		implements AfterLabelStep, AfterValueStep, BuildStep, LabelStep,
				   ValueStep {

		@Override
		public MultiselectItem build() {
			return _multiselectItem;
		}

		@Override
		public AfterLabelStep setLabel(String label) {
			_multiselectItem.setLabel(label);

			return this;
		}

		@Override
		public AfterLabelStep setLabel(
			UnsafeSupplier<String, Exception> labelUnsafeSupplier) {

			try {
				String label = labelUnsafeSupplier.get();

				if (label != null) {
					_multiselectItem.setLabel(label);
				}

				return this;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		@Override
		public AfterValueStep setValue(String value) {
			_multiselectItem.setValue(value);

			return this;
		}

		@Override
		public AfterValueStep setValue(
			UnsafeSupplier<String, Exception> valueUnsafeSupplier) {

			try {
				String value = valueUnsafeSupplier.get();

				if (value != null) {
					_multiselectItem.setValue(value);
				}

				return this;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		private final MultiselectItem _multiselectItem = new MultiselectItem();

	}

	public interface AfterLabelStep extends BuildStep, ValueStep {
	}

	public interface AfterValueStep extends BuildStep {
	}

	public interface BuildStep {

		public MultiselectItem build();

	}

	public interface LabelStep {

		public AfterLabelStep setLabel(String label);

		public AfterLabelStep setLabel(
			UnsafeSupplier<String, Exception> labelUnsafeSupplier);

	}

	public interface ValueStep {

		public AfterValueStep setValue(String value);

		public AfterValueStep setValue(
			UnsafeSupplier<String, Exception> valueUnsafeSupplier);

	}

}