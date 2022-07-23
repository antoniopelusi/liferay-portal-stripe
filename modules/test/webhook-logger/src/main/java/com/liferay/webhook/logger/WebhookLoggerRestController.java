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

package com.liferay.webhook.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Inácio Nery
 * @author Brian Wing Shun Chan
 */
@RequestMapping("/")
@RestController
public class WebhookLoggerRestController {

	@GetMapping("{value}")
	public String getValue(@PathVariable(required = false) String value) {
		return value;
	}

	@PostMapping("{value}")
	public String postValue(@RequestBody String value) {
		try {
			JSONObject jsonObject = new JSONObject(value);

			_log.info("\n\n" + jsonObject.toString(4) + "\n");
		}
		catch (Exception exception) {
			_log.info("Value: " + value);
		}

		return value;
	}

	private static final Log _log = LogFactory.getLog(
		WebhookLoggerRestController.class);

}