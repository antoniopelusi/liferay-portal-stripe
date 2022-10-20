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

package com.liferay.object.admin.rest.client.dto.v1_0;

import com.liferay.object.admin.rest.client.function.UnsafeSupplier;
import com.liferay.object.admin.rest.client.serdes.v1_0.ObjectStateFlowSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class ObjectStateFlow implements Cloneable, Serializable {

	public static ObjectStateFlow toDTO(String json) {
		return ObjectStateFlowSerDes.toDTO(json);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public ObjectState[] getObjectStates() {
		return objectStates;
	}

	public void setObjectStates(ObjectState[] objectStates) {
		this.objectStates = objectStates;
	}

	public void setObjectStates(
		UnsafeSupplier<ObjectState[], Exception> objectStatesUnsafeSupplier) {

		try {
			objectStates = objectStatesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ObjectState[] objectStates;

	@Override
	public ObjectStateFlow clone() throws CloneNotSupportedException {
		return (ObjectStateFlow)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ObjectStateFlow)) {
			return false;
		}

		ObjectStateFlow objectStateFlow = (ObjectStateFlow)object;

		return Objects.equals(toString(), objectStateFlow.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ObjectStateFlowSerDes.toJSON(this);
	}

}