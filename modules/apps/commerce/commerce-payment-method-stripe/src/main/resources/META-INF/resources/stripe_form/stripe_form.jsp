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

<%
String sessionId = (String)request.getAttribute("sessionId");
String publicKey = (String)request.getAttribute("publicKey");
%>

<script src="https://js.stripe.com/v3/"></script>

<script>
	var stripe = Stripe('<%= publicKey %>');
	stripe.redirectToCheckout({
		sessionId: '<%= sessionId %>'
	}).then(function (result) {
		log.info("Error msg:" + result.error.message);
	});
</script>