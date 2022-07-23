<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String servletPath = GetterUtil.getString(request.getServletPath());

PortletURL baseURL = PortletURLBuilder.createRenderURL(
	liferayPortletResponse
).setParameter(
	"p_u_i_d", selectedUser.getUserId()
).buildPortletURL();
%>

<clay:navigation-bar
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						boolean active = servletPath.equals("/review_uad_data.jsp") || servletPath.equals("/view_uad_hierarchy.jsp");

						PortletURL reviewDataURL = null;

						try {
							reviewDataURL = PortletURLBuilder.create(
								PortletURLUtil.clone(baseURL, renderResponse)
							).setMVCRenderCommandName(
								"/user_associated_data/review_uad_data"
							).buildPortletURL();
						}
						catch (PortletException e) {
							reviewDataURL = baseURL;
						}

						navigationItem.setActive(active);
						navigationItem.setHref(reviewDataURL.toString());
						navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "review-data"));
					});
				add(
					navigationItem -> {
						boolean active = servletPath.equals("/anonymize_nonreviewable_uad_data.jsp");

						PortletURL nonreviewableDataURL = null;

						try {
							nonreviewableDataURL = PortletURLBuilder.create(
								PortletURLUtil.clone(baseURL, renderResponse)
							).setMVCRenderCommandName(
								"/user_associated_data/anonymize_nonreviewable_uad_data"
							).buildPortletURL();
						}
						catch (PortletException e) {
							nonreviewableDataURL = baseURL;
						}

						navigationItem.setActive(active);
						navigationItem.setHref(nonreviewableDataURL.toString());
						navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "auto-anonymize-data"));
					});
			}
		}
	%>'
/>