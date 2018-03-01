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
 * API结果：
 * 
 * <pre>
 * {
 * 	data: '数据结果',
 * 	_error: {	//如果存在说明一定是发生了错误
 * 	}
 * }
 * </pre>
 * 
 * @author 王焕
 * @since 0.1.0
 */
public class ApiResult<T> {
	private T data;
	private ApiError _error;

	/** @return {@link #data} */
	public T getData() {
		return data;
	}

	/**
	 * @param data
	 *            {@link #data}
	 */
	public void setData(T data) {
		this.data = data;
	}

	public ApiResult() {
	}

	public ApiResult(T data) {
		this.data = data;
	}

	public ApiResult(ApiError error) {
		this._error = error;
	}

	public ApiError get_error() {
		return _error;
	}

	public void set_error(ApiError _error) {
		this._error = _error;
	}

}
