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
CPSearchResultsDisplayContext cpSearchResultsDisplayContext = (CPSearchResultsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

List<CPContentListRenderer> cpContentListRenderers = cpSearchResultsDisplayContext.getCPContentListRenderers();
%>

<aui:fieldset markupView="lexicon">
	<aui:select id="cpContentListRendererKeySelect" label="product-list-renderer" name="preferences--cpContentListRendererKey--">

		<%
		for (CPContentListRenderer cpContentListRenderer : cpContentListRenderers) {
			String key = cpContentListRenderer.getKey();
		%>

			<aui:option label="<%= cpContentListRenderer.getLabel(locale) %>" selected="<%= key.equals(cpSearchResultsDisplayContext.getCPContentListRendererKey()) %>" value="<%= key %>" />

		<%
		}
		%>

	</aui:select>
</aui:fieldset>

<liferay-frontend:component
	module="js/configuration/product_list_renderer"
/>