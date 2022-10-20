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

package com.liferay.gradle.plugins.workspace.internal.client.extension;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gregory Amerson
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientExtension {

	@JsonAnySetter
	public void ignored(String name, Object value) {
		if (value instanceof List) {
			List<?> listValue = (List<?>)value;

			value = StringUtil.merge(listValue, StringPool.NEW_LINE);
		}

		_typeSettings.add(StringBundler.concat(name, "=", value));
	}

	public String toJSON() throws Exception {
		Map<String, Object> config = new HashMap<>();

		config.put("baseURL", _BASE_URL_PREFIX + projectName);
		config.put("description", description);
		config.put("dxp.lxc.liferay.com.virtualInstanceId", "default");
		config.put("name", name);
		config.put("sourceCodeURL", sourceCodeURL);
		config.put("type", type);
		config.put("typeSettings", _typeSettings);

		Map<String, Object> jsonMap = new HashMap<>();

		jsonMap.put(_CLIENT_EXTENSION_FACTORY_PREFIX + id, config);

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(
			SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

		ObjectWriter objectWriter =
			objectMapper.writerWithDefaultPrettyPrinter();

		return objectWriter.writeValueAsString(jsonMap);
	}

	public String description = "";
	public String id;
	public String name = "";
	public String projectName;
	public String sourceCodeURL = "";
	public String type;

	private static final String _BASE_URL_PREFIX = "${portalURL}/o/";

	private static final String _CLIENT_EXTENSION_FACTORY_PREFIX =
		"com.liferay.client.extension.type.configuration.CETConfiguration~";

	private final List<String> _typeSettings = new ArrayList<>();

}