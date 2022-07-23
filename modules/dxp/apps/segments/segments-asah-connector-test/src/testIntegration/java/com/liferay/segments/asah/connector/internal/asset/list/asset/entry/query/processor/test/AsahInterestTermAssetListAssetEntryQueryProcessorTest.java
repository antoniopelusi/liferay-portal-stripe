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

package com.liferay.segments.asah.connector.internal.asset.list.asset.entry.query.processor.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.list.asset.entry.query.processor.AssetListAssetEntryQueryProcessor;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author David Arques
 */
@RunWith(Arquillian.class)
public class AsahInterestTermAssetListAssetEntryQueryProcessorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_company = CompanyTestUtil.addCompany();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		_companyLocalService.deleteCompany(_company);
	}

	@Test
	public void testProcessAssetEntryQueryWithCachedInterestTerms() {
		PortalCache<String, String[]> portalCache =
			(PortalCache<String, String[]>)_multiVMPool.getPortalCache(
				"com.liferay.segments.asah.connector.internal.cache." +
					"AsahInterestTermCache");

		String userId = RandomTestUtil.randomString();

		String[] interestTerms = {
			RandomTestUtil.randomString(), RandomTestUtil.randomString()
		};

		portalCache.put("segments-" + userId, interestTerms);

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		_assetListAssetEntryQueryProcessor.processAssetEntryQuery(
			_company.getCompanyId(), userId, _getUnicodeProperties(true),
			assetEntryQuery);

		Assert.assertArrayEquals(
			interestTerms, assetEntryQuery.getAnyKeywords());
	}

	@Test
	public void testProcessAssetEntryQueryWithDisabledContentRecommendation() {
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		_assetListAssetEntryQueryProcessor.processAssetEntryQuery(
			_company.getCompanyId(), RandomTestUtil.randomString(),
			_getUnicodeProperties(false), assetEntryQuery);

		Assert.assertArrayEquals(
			new String[0], assetEntryQuery.getAnyKeywords());
	}

	@Test
	public void testProcessAssetEntryQueryWithEmptyUserId() {
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		_assetListAssetEntryQueryProcessor.processAssetEntryQuery(
			_company.getCompanyId(), StringPool.BLANK,
			_getUnicodeProperties(true), assetEntryQuery);

		Assert.assertArrayEquals(
			new String[0], assetEntryQuery.getAnyKeywords());
	}

	@Test
	public void testProcessAssetEntryQueryWithNullUserId() {
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		_assetListAssetEntryQueryProcessor.processAssetEntryQuery(
			_company.getCompanyId(), null, _getUnicodeProperties(true),
			assetEntryQuery);

		Assert.assertArrayEquals(
			new String[0], assetEntryQuery.getAnyKeywords());
	}

	@Test
	public void testProcessAssetEntryQueryWithUncachedInterestTerms() {
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		_assetListAssetEntryQueryProcessor.processAssetEntryQuery(
			_company.getCompanyId(), RandomTestUtil.randomString(),
			_getUnicodeProperties(true), assetEntryQuery);

		Assert.assertArrayEquals(
			new String[0], assetEntryQuery.getAnyKeywords());
	}

	private UnicodeProperties _getUnicodeProperties(
		boolean enableContentRecommendation) {

		return UnicodePropertiesBuilder.put(
			"enableContentRecommendation",
			String.valueOf(enableContentRecommendation)
		).build();
	}

	private static Company _company;

	@Inject
	private static CompanyLocalService _companyLocalService;

	@Inject(
		filter = "component.name=com.liferay.segments.asah.connector.internal.asset.list.asset.entry.query.processor.AsahInterestTermAssetListAssetEntryQueryProcessor"
	)
	private AssetListAssetEntryQueryProcessor
		_assetListAssetEntryQueryProcessor;

	@Inject
	private MultiVMPool _multiVMPool;

}