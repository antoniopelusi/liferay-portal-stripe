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

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Adam Brandizzi
 * @author Wade Cao
 */
public class SynonymsDisplayContextTest {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_synonymsDisplayContext = new SynonymsDisplayContext();
	}

	@Test
	public void testGetterSetter() {
		CreationMenu creationMenu = Mockito.mock(CreationMenu.class);
		List<DropdownItem> dropdownItems = Arrays.asList(
			Mockito.mock(DropdownItem.class));

		_synonymsDisplayContext.setCreationMenu(creationMenu);
		_synonymsDisplayContext.setDisabledManagementBar(false);
		_synonymsDisplayContext.setDropdownItems(dropdownItems);
		_synonymsDisplayContext.setItemsTotal(1);
		_synonymsDisplayContext.setSearchContainer(
			Mockito.mock(SearchContainer.class));

		Assert.assertEquals(1, _synonymsDisplayContext.getItemsTotal());
		Assert.assertEquals(
			creationMenu, _synonymsDisplayContext.getCreationMenu());
		Assert.assertEquals(
			dropdownItems,
			_synonymsDisplayContext.getActionDropdownMultipleItems());

		Assert.assertNotNull(_synonymsDisplayContext.getSearchContainer());
	}

	private SynonymsDisplayContext _synonymsDisplayContext;

}