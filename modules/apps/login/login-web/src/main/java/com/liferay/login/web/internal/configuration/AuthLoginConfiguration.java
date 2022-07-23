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

package com.liferay.login.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Erick Monteiro
 */
@ExtendedObjectClassDefinition(
	category = "login", scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.login.web.internal.configuration.AuthLoginConfiguration",
	localization = "content/Language", name = "login-configuration-name"
)
public interface AuthLoginConfiguration {

	@Meta.AD(
		deflt = "false", description = "prompt-enabled-description",
		name = "prompt-enabled-name", required = false
	)
	public boolean promptEnabled();

}