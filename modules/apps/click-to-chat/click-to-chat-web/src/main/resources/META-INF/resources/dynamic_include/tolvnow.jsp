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

<%@ include file="/dynamic_include/init.jsp" %>

<script type="text/javascript">
	var _tn = _tn || [];

	_tn.push(['account', '<%= clickToChatChatProviderAccountId %>']);
	_tn.push(['action', 'track-view']);

	<c:if test="<%= themeDisplay.isSignedIn() %>">
		_tn.push(['_setEmail', '<%= user.getEmailAddress() %>']);
		_tn.push(['_setName', '<%= user.getScreenName() %>']);
	</c:if>

	(function () {
		function loadTolnowScript() {
			if (!document.getElementById('tolvnow-script-chat')) {
				var spanElement = document.createElement('span');

				spanElement.setAttribute('id', 'tolvnow');

				var scriptElement = document.createElement('script');

				scriptElement.setAttribute('async', true);
				scriptElement.setAttribute('id', 'tolvnow-script-chat');
				scriptElement.setAttribute('src', '//tracker.tolvnow.com/js/tn.js');
				scriptElement.setAttribute('type', 'text/javascript');

				var bodyElement = document.getElementsByTagName('body').item(0);

				bodyElement.appendChild(spanElement);
				bodyElement.appendChild(scriptElement);
			}
		}

		window.onload = function () {
			loadTolnowScript();
		};

		if (document.readyState === 'complete') {
			loadTolnowScript();
		}
	})();
</script>