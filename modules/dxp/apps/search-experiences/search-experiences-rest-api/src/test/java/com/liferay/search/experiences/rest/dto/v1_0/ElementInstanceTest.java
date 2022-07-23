/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.rest.dto.v1_0;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class ElementInstanceTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testEscape() throws Exception {
		_testEscapeElementInstance(
			"\"es\\\\ca\\\"pe\": \"{\\\"match\\\":4}\"", "es\\ca\"pe",
			"{\"match\":4}");
		_testEscapeElementInstance(
			"\"es\\\\\\\\ca\\\\\\\"pe\": \"{\\\"ma\\\\tch\\\":4}\"",
			"es\\\\ca\\\"pe", "{\"ma\\tch\":4}");
	}

	private void _testEscapeElementInstance(
		String escaped, String key, String value) {

		ElementInstance elementInstance1 = ElementInstance.unsafeToDTO(
			"{\"uiConfigurationValues\": {}}");

		Map<String, Object> uiConfigurationValues1 =
			elementInstance1.getUiConfigurationValues();

		uiConfigurationValues1.put(key, value);

		Assert.assertEquals(
			"{\"uiConfigurationValues\": {" + escaped + "}}",
			elementInstance1.toString());

		ElementInstance elementInstance2 = ElementInstance.unsafeToDTO(
			elementInstance1.toString());

		Map<String, Object> uiConfigurationValues2 =
			elementInstance2.getUiConfigurationValues();

		Assert.assertEquals(value, uiConfigurationValues2.get(key));

		Assert.assertEquals(
			elementInstance1.toString(), elementInstance2.toString());
	}

}