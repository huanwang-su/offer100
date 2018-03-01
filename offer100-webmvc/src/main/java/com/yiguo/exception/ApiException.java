/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yiguo.exception;

/**
 * 
 * @author <a href="mailto:1360527082@qq.com">王焕</a>
 * @since 0.1.0
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ApiError error;

	public ApiError getError() {
		return error;
	}

	public ApiException() {
	}

	public ApiException(ApiError error) {
		super(error.getMsg());
		this.error = error;
	}

	public ApiException(Exception ex) {
		super(ex.getMessage(), ex);
		error = new ApiError(ex);
	}

	public ApiException(String msg) {
		this(new ApiError(null, msg));
	}

	public ApiException(String code, String msg) {
		this(new ApiError(code, msg));
	}

	public ApiException(int httpCode, String code, String msg) {
		this(new ApiError(httpCode, code, msg));
	}

	public String getI18n() {
		return error.getI18n();
	}

	public void setI18n(String i18n) {
		error.setI18n(i18n);
	}

	public Object[] getI18nArgs() {
		return error.getI18nArgs();
	}

	public void setI18nArgs(Object... args) {
		error.setI18nArgs(args);
	}
}
