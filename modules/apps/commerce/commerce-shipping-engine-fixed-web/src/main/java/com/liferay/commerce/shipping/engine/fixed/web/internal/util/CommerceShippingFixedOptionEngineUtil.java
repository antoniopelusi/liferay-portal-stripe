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

package com.liferay.commerce.shipping.engine.fixed.web.internal.util;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionQualifier;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionQualifierLocalService;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShippingFixedOptionEngineUtil {

	public static List<CommerceShippingFixedOption>
		getEligibleCommerceShippingFixedOptions(
			long commerceOrderTypeId,
			CommerceShippingFixedOptionQualifierLocalService
				commerceShippingFixedOptionQualifierLocalService,
			List<CommerceShippingFixedOption> commerceShippingFixedOptions) {

		List<CommerceShippingFixedOption> eligibleCommerceShippingFixedOptions =
			new ArrayList<>();

		for (CommerceShippingFixedOption commerceShippingFixedOption :
				commerceShippingFixedOptions) {

			List<CommerceShippingFixedOptionQualifier>
				commerceShippingFixedOptionQualifiers =
					commerceShippingFixedOptionQualifierLocalService.
						getCommerceShippingFixedOptionQualifiers(
							CommerceOrderType.class.getName(),
							commerceShippingFixedOption.
								getCommerceShippingFixedOptionId());

			if ((commerceOrderTypeId > 0) &&
				ListUtil.isNotEmpty(commerceShippingFixedOptionQualifiers) &&
				!ListUtil.exists(
					commerceShippingFixedOptionQualifiers,
					commercePaymentMethodGroupRelQualifier -> {
						long classPK =
							commercePaymentMethodGroupRelQualifier.getClassPK();

						return classPK == commerceOrderTypeId;
					})) {

				continue;
			}

			eligibleCommerceShippingFixedOptions.add(
				commerceShippingFixedOption);
		}

		return eligibleCommerceShippingFixedOptions;
	}

}