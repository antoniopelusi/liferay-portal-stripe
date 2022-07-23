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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.Order;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderImpl implements Order {

	public OrderImpl(org.hibernate.criterion.Order order) {
		_order = order;
	}

	public org.hibernate.criterion.Order getWrappedOrder() {
		return _order;
	}

	@Override
	public String toString() {
		return StringBundler.concat("{_order=", _order, "}");
	}

	private final org.hibernate.criterion.Order _order;

}