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

package com.liferay.portal.search.tuning.synonyms.web.internal.index;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.document.DocumentBuilder;
import com.liferay.portal.search.document.DocumentBuilderFactory;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Wade Cao
 */
public class SynonymSetToDocumentTranslatorImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		_synonymSetToDocumentTranslatorImpl =
			new SynonymSetToDocumentTranslatorImpl();

		ReflectionTestUtil.setFieldValue(
			_synonymSetToDocumentTranslatorImpl, "_documentBuilderFactory",
			_documentBuilderFactory);
	}

	@Test
	public void testTranslate() {
		Document document = Mockito.mock(Document.class);

		DocumentBuilder documentBuilder = Mockito.mock(DocumentBuilder.class);

		Mockito.doReturn(
			document
		).when(
			documentBuilder
		).build();

		Mockito.doReturn(
			documentBuilder
		).when(
			documentBuilder
		).setString(
			Mockito.anyString(), Mockito.anyString()
		);

		Mockito.doReturn(
			documentBuilder
		).when(
			_documentBuilderFactory
		).builder();

		_synonymSetToDocumentTranslatorImpl.translate(
			Mockito.mock(SynonymSet.class));

		Assert.assertEquals(
			document,
			_synonymSetToDocumentTranslatorImpl.translate(
				Mockito.mock(SynonymSet.class)));
	}

	@Mock
	private DocumentBuilderFactory _documentBuilderFactory;

	private SynonymSetToDocumentTranslatorImpl
		_synonymSetToDocumentTranslatorImpl;

}