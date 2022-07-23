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

package com.liferay.journal.web.internal.item.selector.helper;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.journal.constants.JournalWebKeys;
import com.liferay.journal.item.selector.criterion.JournalItemSelectorCriterion;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.portal.kernel.portlet.LiferayRenderRequest;
import com.liferay.portal.kernel.portlet.LiferayRenderResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portlet.LiferayPortletUtil;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Roberto Díaz
 */
public class JournalItemSelectorHelper {

	public JournalItemSelectorHelper(
		JournalArticle article, JournalFolder folder,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_article = article;
		_folder = folder;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_itemSelector = (ItemSelector)renderRequest.getAttribute(
			JournalWebKeys.ITEM_SELECTOR);
	}

	public PortletURL getDocumentLibrarySelectorURL() {
		LiferayRenderRequest liferayRenderRequest =
			(LiferayRenderRequest)LiferayPortletUtil.getLiferayPortletRequest(
				_renderRequest);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(liferayRenderRequest);

		LiferayRenderResponse liferayRenderResponse =
			(LiferayRenderResponse)LiferayPortletUtil.getLiferayPortletResponse(
				_renderResponse);

		ItemSelectorCriterion itemSelectorCriterion =
			new FileItemSelectorCriterion();

		itemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new FileEntryItemSelectorReturnType());

		return _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory,
			liferayRenderResponse.getNamespace() + "selectDocumentLibrary",
			itemSelectorCriterion);
	}

	public PortletURL getImageSelectorURL() {
		LiferayRenderRequest liferayRenderRequest =
			(LiferayRenderRequest)LiferayPortletUtil.getLiferayPortletRequest(
				_renderRequest);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(liferayRenderRequest);

		LiferayRenderResponse liferayRenderResponse =
			(LiferayRenderResponse)LiferayPortletUtil.getLiferayPortletResponse(
				_renderResponse);

		JournalItemSelectorCriterion journalItemSelectorCriterion =
			new JournalItemSelectorCriterion();

		journalItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new FileEntryItemSelectorReturnType());

		if (_article != null) {
			journalItemSelectorCriterion.setFolderId(_article.getFolderId());
			journalItemSelectorCriterion.setResourcePrimKey(
				_article.getResourcePrimKey());
		}
		else if (_folder != null) {
			journalItemSelectorCriterion.setFolderId(_folder.getFolderId());
		}

		ItemSelectorCriterion fileItemSelectorCriterion =
			new ImageItemSelectorCriterion();

		fileItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new FileEntryItemSelectorReturnType());

		return _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory,
			liferayRenderResponse.getNamespace() + "selectDocumentLibrary",
			journalItemSelectorCriterion, fileItemSelectorCriterion);
	}

	private final JournalArticle _article;
	private final JournalFolder _folder;
	private final ItemSelector _itemSelector;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}