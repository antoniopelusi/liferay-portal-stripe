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

package com.liferay.portal.search.tuning.synonyms.web.internal.request;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;

/**
 * @author Wade Cao
 */
public class SearchSynonymSetResponseTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_searchSynonymSetResponse = new SearchSynonymSetResponse();
	}

	@Test
	public void testGetterSetter() {
		_searchSynonymSetResponse.setDocuments(_documents);
		_searchSynonymSetResponse.setKeywords("keywords");
		_searchSynonymSetResponse.setPaginationDelta(1);
		_searchSynonymSetResponse.setPaginationStart(0);
		_searchSynonymSetResponse.setSearchContainer(_searchContainer);
		_searchSynonymSetResponse.setSearchHits(_searchHits);
		_searchSynonymSetResponse.setSearchResponse(_searchResponse);
		_searchSynonymSetResponse.setTotalHits(10);

		Assert.assertEquals(
			_documents, _searchSynonymSetResponse.getDocuments());
		Assert.assertEquals(
			Optional.of("keywords"),
			_searchSynonymSetResponse.getKeywordsOptional());
		Assert.assertEquals(1, _searchSynonymSetResponse.getPaginationDelta());
		Assert.assertEquals(0, _searchSynonymSetResponse.getPaginationStart());
		Assert.assertEquals(
			_searchContainer, _searchSynonymSetResponse.getSearchContainer());
		Assert.assertEquals(
			_searchHits, _searchSynonymSetResponse.getSearchHits());
		Assert.assertEquals(
			_searchResponse, _searchSynonymSetResponse.getSearchResponse());
		Assert.assertEquals(10, _searchSynonymSetResponse.getTotalHits());
	}

	@Mock
	private List<Document> _documents;

	@Mock
	private SearchContainer<Document> _searchContainer;

	@Mock
	private SearchHits _searchHits;

	@Mock
	private SearchResponse _searchResponse;

	private SearchSynonymSetResponse _searchSynonymSetResponse;

}