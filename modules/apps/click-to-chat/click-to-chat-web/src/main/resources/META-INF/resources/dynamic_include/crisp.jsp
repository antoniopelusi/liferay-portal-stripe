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
	window.$crisp = [];
	window.CRISP_WEBSITE_ID = '<%= clickToChatChatProviderAccountId %>';

	(function () {
		function loadCrispScript() {
			function setCrispUserInfo() {
				if ('<%= themeDisplay.isSignedIn() %>' === 'true') {
					$crisp.push([
						'set',
						'user:email',
						'<%= user.getEmailAddress() %>',
					]);
					$crisp.push([
						'set',
						'user:nickname',
						'<%= user.getScreenName() %>',
					]);
				}
			}

			if (!document.getElementById('crisp-script-chat')) {
				var scriptElement = document.createElement('script');

				scriptElement.setAttribute('async', true);
				scriptElement.setAttribute('id', 'crisp-script-chat');
				scriptElement.setAttribute('src', 'https://client.crisp.chat/l.js');
				scriptElement.setAttribute('type', 'text/javascript');
				scriptElement.onload = function () {
					setCrispUserInfo();
				};

				var bodyElement = document.getElementsByTagName('body').item(0);

				bodyElement.appendChild(scriptElement);
			}
			else {
				setCrispUserInfo();
			}
		}

		window.onload = function () {
			loadCrispScript();
		};

		if (document.readyState === 'complete') {
			loadCrispScript();
		}
	})();
</script>