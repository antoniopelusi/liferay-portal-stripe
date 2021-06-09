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

package com.liferay.commerce.payment.method.stripe.internal;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.stripe.internal.configuration.StripeGroupServiceConfiguration;
import com.liferay.commerce.payment.method.stripe.internal.constants.StripeCommercePaymentMethodConstants;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Antonio Pelusi
 */
@Component(
	enabled = false, immediate = true,
	property = "commerce.payment.engine.method.key=" + StripeCommercePaymentMethod.KEY,
	service = CommercePaymentMethod.class
)
public class StripeCommercePaymentMethod implements CommercePaymentMethod {

	public static final String KEY = "stripe";

	@Override
	public CommercePaymentResult cancelPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		return new CommercePaymentResult(
			null, commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.ORDER_STATUS_CANCELLED, true, null, null,
			Collections.emptyList(), true);
	}

	@Override
	public CommercePaymentResult completePayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		return new CommercePaymentResult(
			null, commercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_PAID, true, null, null,
			Collections.emptyList(), true);
	}

	@Override
	public String getDescription(Locale locale) {
		return null;
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName(Locale locale) {
		return LanguageUtil.get(_getResourceBundle(locale), KEY);
	}

	@Override
	public int getPaymentType() {
		return CommercePaymentConstants.
			COMMERCE_PAYMENT_METHOD_TYPE_ONLINE_REDIRECT;
	}

	@Override
	public String getServletPath() {
		return StripeCommercePaymentMethodConstants.
			COMPLETE_PAYMENT_SERVLET_PATH;
	}

	@Override
	public boolean isCancelEnabled() {
		return true;
	}

	@Override
	public boolean isCompleteEnabled() {
		return true;
	}

	@Override
	public boolean isProcessPaymentEnabled() {
		return true;
	}

	@Override
	public CommercePaymentResult processPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		StripeCommercePaymentRequest stripeCommercePaymentRequest =
			(StripeCommercePaymentRequest)commercePaymentRequest;

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			stripeCommercePaymentRequest.getCommerceOrderId());

		StripeGroupServiceConfiguration configuration = _getConfiguration(
			commerceOrder.getGroupId());

		Stripe.apiKey = configuration.secretKey();

		SessionCreateParams params = SessionCreateParams.builder(
		).addPaymentMethodType(
			SessionCreateParams.PaymentMethodType.CARD
		).setMode(
			SessionCreateParams.Mode.PAYMENT
		).setSuccessUrl(
			commercePaymentRequest.getReturnUrl()
		).setCancelUrl(
			commercePaymentRequest.getCancelUrl()
		).addAllLineItem(
			_getLineItems(
				commerceOrder, stripeCommercePaymentRequest.getLocale())
		).build();

		Session session = Session.create(params);

		String url = StringBundler.concat(
			_getServletUrl(stripeCommercePaymentRequest), "?sessionId=",
			session.getId(), "&publicKey=", configuration.publicKey());

		return new CommercePaymentResult(
			session.getId(), stripeCommercePaymentRequest.getCommerceOrderId(),
			CommerceOrderConstants.PAYMENT_STATUS_PENDING, true, url, null,
			Collections.emptyList(), true);
	}

	private StripeGroupServiceConfiguration _getConfiguration(long groupId)
		throws Exception {

		return _configurationProvider.getConfiguration(
			StripeGroupServiceConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId, StripeCommercePaymentMethodConstants.SERVICE_NAME));
	}

	private List<SessionCreateParams.LineItem> _getLineItems(
			CommerceOrder commerceOrder, Locale locale)
		throws Exception {

		List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			BigDecimal finalPrice = commerceOrderItem.getFinalPrice();

			BigDecimal unitPrice = finalPrice.divide(
				new BigDecimal(commerceOrderItem.getQuantity()));

			BigDecimal unitAmount = unitPrice.multiply(new BigDecimal(100));

			CommerceCurrency commerceCurrency =
				commerceOrder.getCommerceCurrency();

			SessionCreateParams.LineItem lineItem =
				SessionCreateParams.LineItem.builder(
				).setQuantity(
					(long)commerceOrderItem.getQuantity()
				).setPriceData(
					SessionCreateParams.LineItem.PriceData.builder(
					).setCurrency(
						commerceCurrency.getCode()
					).setUnitAmount(
						unitAmount.longValue()
					).setProductData(
						SessionCreateParams.LineItem.PriceData.ProductData.
							builder(
							).setName(
								commerceOrderItem.getName(locale)
							).build()
					).build()
				).build();

			lineItems.add(lineItem);
		}

		return lineItems;
	}

	private ResourceBundle _getResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	private String _getServletUrl(
		StripeCommercePaymentRequest stripeCommercePaymentRequest) {

		return StringBundler.concat(
			_portal.getPortalURL(
				stripeCommercePaymentRequest.getHttpServletRequest()),
			_portal.getPathModule(), StringPool.SLASH,
			StripeCommercePaymentMethodConstants.START_PAYMENT_SERVLET_PATH);
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Portal _portal;

}