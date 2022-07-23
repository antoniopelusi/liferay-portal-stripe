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

package com.liferay.portal.search.tuning.synonyms.web.internal.filter.name;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Wade Cao
 */
public class SynonymSetFilterNameHolderImplTest {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_synonymSetFilterNameHolderImpl = new SynonymSetFilterNameHolderImpl();
	}

	@Test
	public void testActivate() {
		_synonymSetFilterNameHolderImpl.activate(Collections.emptyMap());

		Assert.assertArrayEquals(
			new String[] {
				"liferay_filter_synonym_en", "liferay_filter_synonym_es"
			},
			_synonymSetFilterNameHolderImpl.getFilterNames());
	}

	private SynonymSetFilterNameHolderImpl _synonymSetFilterNameHolderImpl;

}