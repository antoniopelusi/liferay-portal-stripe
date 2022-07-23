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

package com.liferay.trash.kernel.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.trash.kernel.model.TrashEntry;

import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     Julio Camarero
 * @deprecated As of Judson (7.1.x)
 */
@Deprecated
public class TrashUtil {

	public static void addBaseModelBreadcrumbEntries(
			HttpServletRequest httpServletRequest,
			LiferayPortletResponse liferayPortletResponse, String className,
			long classPK, PortletURL containerModelURL)
		throws PortalException, PortletException {

		_trash.addBaseModelBreadcrumbEntries(
			httpServletRequest, liferayPortletResponse, className, classPK,
			containerModelURL);
	}

	public static void addContainerModelBreadcrumbEntries(
			HttpServletRequest httpServletRequest,
			LiferayPortletResponse liferayPortletResponse, String className,
			long classPK, PortletURL containerModelURL)
		throws PortalException, PortletException {

		_trash.addContainerModelBreadcrumbEntries(
			httpServletRequest, liferayPortletResponse, className, classPK,
			containerModelURL);
	}

	public static void addTrashSessionMessages(
		ActionRequest actionRequest, List<TrashedModel> trashedModels) {

		_trash.addTrashSessionMessages(actionRequest, trashedModels);
	}

	public static void addTrashSessionMessages(
		ActionRequest actionRequest, List<TrashedModel> trashedModels,
		String cmd) {

		_trash.addTrashSessionMessages(actionRequest, trashedModels, cmd);
	}

	public static void addTrashSessionMessages(
		ActionRequest actionRequest, TrashedModel trashedModel) {

		_trash.addTrashSessionMessages(actionRequest, trashedModel);
	}

	public static void addTrashSessionMessages(
		ActionRequest actionRequest, TrashedModel trashedModel, String cmd) {

		_trash.addTrashSessionMessages(actionRequest, trashedModel, cmd);
	}

	public static void deleteEntriesAttachments(
		long companyId, long repositoryId, Date date,
		String[] attachmentFileNames) {

		_trash.deleteEntriesAttachments(
			companyId, repositoryId, date, attachmentFileNames);
	}

	public static Group disableTrash(Group group) {
		return _trash.disableTrash(group);
	}

	public static List<TrashEntry> getEntries(Hits hits)
		throws PortalException {

		return _trash.getEntries(hits);
	}

	public static OrderByComparator<TrashEntry> getEntryOrderByComparator(
		String orderByCol, String orderByType) {

		return _trash.getEntryOrderByComparator(orderByCol, orderByType);
	}

	public static int getMaxAge(Group group) throws PortalException {
		return _trash.getMaxAge(group);
	}

	public static String getNewName(String oldName, String token) {
		return _trash.getNewName(oldName, token);
	}

	public static String getNewName(
			ThemeDisplay themeDisplay, String className, long classPK,
			String oldName)
		throws PortalException {

		return _trash.getNewName(themeDisplay, className, classPK, oldName);
	}

	public static String getOriginalTitle(String title) {
		return _trash.getOriginalTitle(title);
	}

	public static String getOriginalTitle(String title, String paramName) {
		return _trash.getOriginalTitle(title, paramName);
	}

	public static Trash getTrash() {
		return _trash;
	}

	public static String getTrashTime(String title, String separator) {
		return _trash.getTrashTime(title, separator);
	}

	public static String getTrashTitle(long trashEntryId) {
		return _trash.getTrashTitle(trashEntryId);
	}

	public static PortletURL getViewContentURL(
			HttpServletRequest httpServletRequest, long trashEntryId)
		throws PortalException {

		return _trash.getViewContentURL(httpServletRequest, trashEntryId);
	}

	public static PortletURL getViewContentURL(
			HttpServletRequest httpServletRequest, String className,
			long classPK)
		throws PortalException {

		return _trash.getViewContentURL(httpServletRequest, className, classPK);
	}

	public static PortletURL getViewURL(HttpServletRequest httpServletRequest)
		throws PortalException {

		return _trash.getViewURL(httpServletRequest);
	}

	public static boolean isInTrash(String className, long classPK)
		throws PortalException {

		return _trash.isInTrash(className, classPK);
	}

	public static boolean isTrashEnabled(Group group) {
		return _trash.isTrashEnabled(group);
	}

	public static boolean isTrashEnabled(long groupId) throws PortalException {
		return _trash.isTrashEnabled(groupId);
	}

	public static boolean isValidTrashTitle(String title) {
		return _trash.isValidTrashTitle(title);
	}

	public void setTrash(Trash trash) {
		_trash = trash;
	}

	private static Trash _trash;

}