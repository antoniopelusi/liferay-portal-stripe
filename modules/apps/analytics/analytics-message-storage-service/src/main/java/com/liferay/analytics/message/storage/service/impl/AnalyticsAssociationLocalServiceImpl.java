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

package com.liferay.analytics.message.storage.service.impl;

import com.liferay.analytics.message.storage.model.AnalyticsAssociation;
import com.liferay.analytics.message.storage.service.base.AnalyticsAssociationLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.analytics.message.storage.model.AnalyticsAssociation",
	service = AopService.class
)
public class AnalyticsAssociationLocalServiceImpl
	extends AnalyticsAssociationLocalServiceBaseImpl {

	public AnalyticsAssociation addAnalyticsAssociation(
		long companyId, Date createDate, long userId,
		String associationClassName, long associationClassPK, String className,
		long classPK) {

		AnalyticsAssociation analyticsAssociation =
			analyticsAssociationPersistence.create(
				counterLocalService.increment());

		analyticsAssociation.setCompanyId(companyId);
		analyticsAssociation.setCreateDate(createDate);
		analyticsAssociation.setModifiedDate(createDate);
		analyticsAssociation.setUserId(userId);
		analyticsAssociation.setAssociationClassName(associationClassName);
		analyticsAssociation.setAssociationClassPK(associationClassPK);
		analyticsAssociation.setClassName(className);
		analyticsAssociation.setClassPK(classPK);

		return analyticsAssociationPersistence.update(analyticsAssociation);
	}

	@Override
	public void deleteAnalyticsAssociations(
		long companyId, String associationClassName, long associationClassPK) {

		analyticsAssociationPersistence.removeByC_A_A(
			companyId, associationClassName, associationClassPK);
	}

	@Override
	public List<AnalyticsAssociation> getAnalyticsAssociations(
		long companyId, Date modifiedDate, String associationClassName,
		int start, int end) {

		return analyticsAssociationPersistence.findByC_GtM_A(
			companyId, modifiedDate, associationClassName, start, end);
	}

	@Override
	public List<AnalyticsAssociation> getAnalyticsAssociations(
		long companyId, String associationClassName, int start, int end) {

		return analyticsAssociationPersistence.findByC_A(
			companyId, associationClassName, start, end);
	}

	@Override
	public int getAnalyticsAssociationsCount(
		long companyId, Date modifiedDate, String associationClassName) {

		return analyticsAssociationPersistence.countByC_GtM_A(
			companyId, modifiedDate, associationClassName);
	}

	@Override
	public int getAnalyticsAssociationsCount(
		long companyId, String associationClassName) {

		return analyticsAssociationPersistence.countByC_A(
			companyId, associationClassName);
	}

}