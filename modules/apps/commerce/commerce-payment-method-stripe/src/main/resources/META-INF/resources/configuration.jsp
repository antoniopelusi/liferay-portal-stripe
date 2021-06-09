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
StripeGroupServiceConfiguration stripeCommercePaymentEngineGroupServiceConfiguration = (StripeGroupServiceConfiguration)request.getAttribute(StripeGroupServiceConfiguration.class.getName());
%>

<portlet:actionURL name="/commerce_payment_methods/edit_stripe_commerce_payment_method_configuration" var="editCommercePaymentMethodActionURL" />

<aui:form action="<%= editCommercePaymentMethodActionURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="commerceChannelId" type="hidden" value='<%= ParamUtil.getLong(request, "commerceChannelId") %>' />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<commerce-ui:panel>
		<commerce-ui:info-box
			title="authentication"
		>
			<aui:input label="public-key" name="public-key" value="<%= stripeCommercePaymentEngineGroupServiceConfiguration.publicKey() %>" />

			<aui:input label="secret-key" name="secret-key" value="<%= stripeCommercePaymentEngineGroupServiceConfiguration.secretKey() %>" />
		</commerce-ui:info-box>
	</commerce-ui:panel>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>