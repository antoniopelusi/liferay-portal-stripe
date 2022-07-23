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

<%@ include file="/html/common/init.jsp" %>

<%@ page isErrorPage="true" %>

<%
String message = null;

if (exception instanceof PrincipalException) {
	_log.warn("User ID " + request.getRemoteUser());
	_log.warn("Current URL " + PortalUtil.getCurrentURL(request));
	_log.warn("Referer " + request.getHeader("Referer"));
	_log.warn("Remote address " + request.getRemoteAddr());

	if (exception != null) {
		_log.warn(exception, exception);

		message = exception.getMessage();
	}
	else {
		_log.warn("Exception is null");
	}
}
else {
	_log.error("User ID " + request.getRemoteUser());
	_log.error("Current URL " + PortalUtil.getCurrentURL(request));
	_log.error("Referer " + request.getHeader("Referer"));
	_log.error("Remote address " + request.getRemoteAddr());

	if (exception != null) {
		_log.error(exception, exception);

		message = exception.getMessage();
	}
	else {
		_log.error("Exception is null");
	}
}
%>

<center>
	<br />

	<table border="0" cellpadding="0" cellspacing="0" width="95%">
		<tr>
			<td>
				<font color="#FF0000" face="Verdana, Tahoma, Arial" size="2">
					<c:choose>
						<c:when test="<%= exception instanceof PrincipalException %>">
							<liferay-ui:message key="you-do-not-have-permission-to-view-this-page" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="an-unexpected-system-error-occurred" />
						</c:otherwise>
					</c:choose>

					<br />
				</font>

				<c:if test="<%= message != null %>">
					<br />

					<%= HtmlUtil.escape(message) %>
				</c:if>
			</td>
		</tr>
	</table>

	<br />
</center>

<%!
private static final Log _log = LogFactoryUtil.getLog("portal_web.docroot.html.common.error_jsp");
%>