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

package com.liferay.portal.kernel.deploy.hot;

import com.liferay.portal.kernel.util.PortalLifecycle;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class HotDeployUtil {

	public static void fireDeployEvent(HotDeployEvent hotDeployEvent) {
		_hotDeploy.fireDeployEvent(hotDeployEvent);
	}

	public static void fireUndeployEvent(HotDeployEvent hotDeployEvent) {
		_hotDeploy.fireUndeployEvent(hotDeployEvent);
	}

	public static HotDeploy getHotDeploy() {
		return _hotDeploy;
	}

	public static boolean registerDependentPortalLifecycle(
		String servletContextName, PortalLifecycle portalLifecycle) {

		return _hotDeploy.registerDependentPortalLifecycle(
			servletContextName, portalLifecycle);
	}

	public static void registerListener(HotDeployListener hotDeployListener) {
		_hotDeploy.registerListener(hotDeployListener);
	}

	public static void reset() {
		_hotDeploy.reset();
	}

	public static void setCapturePrematureEvents(
		boolean capturePrematureEvents) {

		_hotDeploy.setCapturePrematureEvents(capturePrematureEvents);
	}

	public static void unregisterListener(HotDeployListener hotDeployListener) {
		_hotDeploy.unregisterListener(hotDeployListener);
	}

	public static void unregisterListeners() {
		_hotDeploy.unregisterListeners();
	}

	public void setHotDeploy(HotDeploy hotDeploy) {
		_hotDeploy = hotDeploy;
	}

	private static HotDeploy _hotDeploy;

}