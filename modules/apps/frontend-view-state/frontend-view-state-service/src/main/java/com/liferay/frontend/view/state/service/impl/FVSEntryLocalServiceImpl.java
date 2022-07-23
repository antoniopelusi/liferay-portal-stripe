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

package com.liferay.frontend.view.state.service.impl;

import com.liferay.frontend.view.state.model.FVSEntry;
import com.liferay.frontend.view.state.service.base.FVSEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.frontend.view.state.model.FVSEntry",
	service = AopService.class
)
public class FVSEntryLocalServiceImpl extends FVSEntryLocalServiceBaseImpl {

	@Override
	public FVSEntry addFVSEntry(long userId, String viewState)
		throws PortalException {

		FVSEntry fvsEntry = fvsEntryPersistence.create(
			counterLocalService.increment());

		User user = _userLocalService.getUserById(userId);

		fvsEntry.setCompanyId(user.getCompanyId());
		fvsEntry.setUserId(user.getUserId());
		fvsEntry.setUserName(user.getFullName());

		fvsEntry.setViewState(viewState);

		return fvsEntryPersistence.update(fvsEntry);
	}

	@Reference
	private UserLocalService _userLocalService;

}