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

package com.liferay.portal.search.tuning.synonyms.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Wade Cao
 */
public class SynonymSetDisplayContextTest {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_synonymSetDisplayContext = new SynonymSetDisplayContext();
	}

	@Test
	public void testGetterSetter() {
		_synonymSetDisplayContext.setDisplayedSynonymSet("displayedSynonymSet");
		_synonymSetDisplayContext.setDropDownItems(
			new ArrayList<DropdownItem>());
		_synonymSetDisplayContext.setEditRenderURL("editRenderURL");
		_synonymSetDisplayContext.setSynonyms("synonyms");
		_synonymSetDisplayContext.setSynonymSetId("synonymSetId");

		Assert.assertEquals(
			"displayedSynonymSet",
			_synonymSetDisplayContext.getDisplayedSynonymSet());
		Assert.assertNotNull(_synonymSetDisplayContext.getDropdownItems());
		Assert.assertEquals(
			"editRenderURL", _synonymSetDisplayContext.getEditRenderURL());
		Assert.assertEquals(
			"synonyms", _synonymSetDisplayContext.getSynonymSet());
		Assert.assertEquals(
			"synonymSetId", _synonymSetDisplayContext.getSynonymSetId());
	}

	private SynonymSetDisplayContext _synonymSetDisplayContext;

}