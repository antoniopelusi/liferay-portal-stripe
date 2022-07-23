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

package com.liferay.petra.process.local;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.process.ProcessLog;
import com.liferay.petra.string.StringBundler;

import java.util.Objects;

/**
 * @author Shuyang Zhou
 */
class LocalProcessLog implements ProcessLog {

	LocalProcessLog(
		ProcessLog.Level level, String message, Throwable throwable) {

		_level = level;
		_message = message;
		_throwable = throwable;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProcessLog)) {
			return false;
		}

		ProcessLog processLog = (ProcessLog)object;

		if ((_level == processLog.getLevel()) &&
			Objects.equals(_message, processLog.getMessage()) &&
			Objects.equals(_throwable, processLog.getThrowable())) {

			return true;
		}

		return false;
	}

	@Override
	public ProcessLog.Level getLevel() {
		return _level;
	}

	@Override
	public String getMessage() {
		return _message;
	}

	@Override
	public Throwable getThrowable() {
		return _throwable;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _level);

		hash = HashUtil.hash(hash, _message);
		hash = HashUtil.hash(hash, _throwable);

		return hash;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{level=", _level, ", message=", _message, ", throwable=",
			_throwable, "}");
	}

	private final ProcessLog.Level _level;
	private final String _message;
	private final Throwable _throwable;

}