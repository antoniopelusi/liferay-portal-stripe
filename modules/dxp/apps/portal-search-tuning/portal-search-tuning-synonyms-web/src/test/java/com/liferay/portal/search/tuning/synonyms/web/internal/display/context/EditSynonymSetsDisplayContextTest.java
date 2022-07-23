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

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Wade Cao
 */
public class EditSynonymSetsDisplayContextTest {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_editSynonymSetsDisplayContext = new EditSynonymSetsDisplayContext();
	}

	@Test
	public void testGetterSetter() {
		_editSynonymSetsDisplayContext.setBackURL("backURL");
		_editSynonymSetsDisplayContext.setData(new HashMap<String, Object>());
		_editSynonymSetsDisplayContext.setFormName("formName");
		_editSynonymSetsDisplayContext.setInputName("inputName");
		_editSynonymSetsDisplayContext.setOriginalInputName(
			"originalInputName");
		_editSynonymSetsDisplayContext.setRedirect("redirect");
		_editSynonymSetsDisplayContext.setSynonymSetId("synonymSetId");

		Assert.assertEquals(
			"backURL", _editSynonymSetsDisplayContext.getBackURL());
		Assert.assertNotNull(_editSynonymSetsDisplayContext.getData());
		Assert.assertEquals(
			"formName", _editSynonymSetsDisplayContext.getFormName());
		Assert.assertEquals(
			"inputName", _editSynonymSetsDisplayContext.getInputName());
		Assert.assertEquals(
			"originalInputName",
			_editSynonymSetsDisplayContext.getOriginalInputName());
		Assert.assertEquals(
			"redirect", _editSynonymSetsDisplayContext.getRedirect());
		Assert.assertEquals(
			"synonymSetId", _editSynonymSetsDisplayContext.getSynonymSetId());
	}

	private EditSynonymSetsDisplayContext _editSynonymSetsDisplayContext;

}