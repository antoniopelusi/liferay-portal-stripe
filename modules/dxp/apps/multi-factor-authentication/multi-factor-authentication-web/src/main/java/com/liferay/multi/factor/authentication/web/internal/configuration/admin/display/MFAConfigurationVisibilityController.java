/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.multi.factor.authentication.web.internal.configuration.admin.display;

import com.liferay.configuration.admin.display.ConfigurationVisibilityController;
import com.liferay.multi.factor.authentication.web.internal.policy.MFAPolicy;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.osgi.util.StringPlus;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Marta Medio
 */
@Component(immediate = true, service = {})
public class MFAConfigurationVisibilityController
	implements ConfigurationVisibilityController {

	@Override
	public boolean isVisible(
		ExtendedObjectClassDefinition.Scope scope, Serializable scopePK) {

		if (_mfaPolicy.isMFAEnabled(CompanyThreadLocal.getCompanyId()) &&
			(ExtendedObjectClassDefinition.Scope.COMPANY == scope)) {

			return true;
		}

		return false;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTracker = ServiceTrackerFactory.open(
			bundleContext, "(mfa.visibility.configuration.pid=*)",
			new ServiceTrackerCustomizer<Object, List<String>>() {

				@Override
				public List<String> addingService(
					ServiceReference<Object> serviceReference) {

					List<String> mfaVisibilityConfigurationPids =
						StringPlus.asList(
							serviceReference.getProperty(
								"mfa.visibility.configuration.pid"));

					_mfaVisibilityConfigurationPids.addAll(
						mfaVisibilityConfigurationPids);

					_update();

					return mfaVisibilityConfigurationPids;
				}

				@Override
				public void modifiedService(
					ServiceReference<Object> serviceReference,
					List<String> mfaVisibilityConfigurationPids) {

					_mfaVisibilityConfigurationPids.removeAll(
						mfaVisibilityConfigurationPids);

					_mfaVisibilityConfigurationPids.addAll(
						StringPlus.asList(
							serviceReference.getProperty(
								"mfa.visibility.configuration.pid")));

					_update();
				}

				@Override
				public void removedService(
					ServiceReference<Object> serviceReference,
					List<String> mfaVisibilityConfigurationPids) {

					_mfaVisibilityConfigurationPids.removeAll(
						mfaVisibilityConfigurationPids);

					_update();
				}

			});

		_serviceRegistration = bundleContext.registerService(
			ConfigurationVisibilityController.class, this, _getProperties());

		_update();
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();

		_serviceRegistration = null;

		_serviceTracker.close();
	}

	private Dictionary<String, Object> _getProperties() {
		return HashMapDictionaryBuilder.<String, Object>put(
			"configuration.pid",
			new ArrayList<>(_mfaVisibilityConfigurationPids)
		).build();
	}

	private void _update() {
		if (_serviceRegistration != null) {
			_serviceRegistration.setProperties(_getProperties());
		}
	}

	@Reference
	private MFAPolicy _mfaPolicy;

	private final Set<String> _mfaVisibilityConfigurationPids =
		Collections.newSetFromMap(new ConcurrentHashMap<>());
	private volatile ServiceRegistration<ConfigurationVisibilityController>
		_serviceRegistration;
	private ServiceTracker<Object, List<String>> _serviceTracker;

}