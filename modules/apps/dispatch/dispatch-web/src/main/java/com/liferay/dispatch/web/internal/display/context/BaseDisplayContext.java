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

package com.liferay.dispatch.web.internal.display.context;

import com.liferay.dispatch.web.internal.display.context.helper.DispatchRequestHelper;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Matija Petanjek
 */
public abstract class BaseDisplayContext {

	public BaseDisplayContext(RenderRequest renderRequest) {
		dispatchRequestHelper = new DispatchRequestHelper(renderRequest);
	}

	public List<NavigationItem> getNavigationItems() {
		HttpServletRequest httpServletRequest =
			dispatchRequestHelper.getRequest();

		LiferayPortletResponse liferayPortletResponse =
			dispatchRequestHelper.getLiferayPortletResponse();

		String tabs1 = ParamUtil.getString(
			httpServletRequest, "tabs1", "dispatch-trigger");

		return NavigationItemList.of(
			NavigationItemBuilder.setActive(
				tabs1.equals("dispatch-trigger")
			).setHref(
				liferayPortletResponse.createRenderURL(), "tabs1",
				"dispatch-trigger", "mvcRenderCommandName",
				"/dispatch/view_dispatch_trigger"
			).setLabel(
				LanguageUtil.get(httpServletRequest, "dispatch-triggers")
			).build(),
			NavigationItemBuilder.setActive(
				tabs1.equals("scheduler-response")
			).setHref(
				liferayPortletResponse.createRenderURL(), "tabs1",
				"scheduler-response", "mvcRenderCommandName",
				"/dispatch/edit_scheduler_response"
			).setLabel(
				LanguageUtil.get(httpServletRequest, "scheduled-jobs")
			).build());
	}

	protected final DispatchRequestHelper dispatchRequestHelper;

}