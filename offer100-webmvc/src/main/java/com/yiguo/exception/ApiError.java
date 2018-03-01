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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;

/**
 * 
 *
 * api错误信息
 * 
 * <pre>
 * {
 * 	httpCode: 'int: http状态码，如果定义，http返回码，将使用此值',
 * 	code: '错误码',
 * 	i18n: '国际化编码',
 * 	msg: '错误说明',
 * 	data: 'json: 期望传递的数据',
 * 	errors: [{
 * 		//错误详情
 * 	}]
 * }
 * </pre>
 * 
 * @author <a href="mailto:1360527082@qq.com">王焕</a>
 * @since 0.1.0
 */
public class ApiError implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Map<String, Object> values = new HashMap<>();

	public Map<String, Object> getValues() {
		return values;
	}

	public Integer getHttpCode() {
		return (Integer) values.get("httpCode");
	}

	public Integer removeHttpCode() {
		return remove("httpCode");
	}

	public String removeI18n() {
		remove("i18nArgs");
		return remove("i18n");
	}

	public void setHttpCode(Integer httpCode) {
		if (httpCode == null) {
			values.remove("httpCode");
		} else {
			values.put("httpCode", httpCode);
		}
	}

	public String getCode() {
		return (String) values.get("code");
	}

	public void setCode(String code) {
		values.put("code", code);
		if (!values.containsKey("i18n") && !StringUtils.isNumeric(code)) {
			setI18n(code);
		}
	}

	public String getMsg() {
		return (String) values.get("msg");
	}

	public void setMsg(String msg) {
		values.put("msg", msg);
	}

	public String getI18n() {
		return (String) values.get("i18n");
	}

	public void setI18n(String i18n) {
		values.put("i18n", i18n);
	}

	public Object[] getI18nArgs() {
		return (Object[]) values.get("i18nArgs");
	}

	public void setI18nArgs(Object[] args) {
		values.put("i18nArgs", args);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getData() {
		return (Map<String, Object>) values.computeIfAbsent("data", key -> new HashMap<>());
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getErrors() {
		return (List<Map<String, Object>>) values.computeIfAbsent("errors", key -> new ArrayList<>());
	}

	public ApiError() {
	}

	public ApiError(String code, String msg) {
		setCode(code);
		setMsg(msg);
	}

	public ApiError(Integer httpCode, String code, String msg) {
		if (httpCode != null) {
			setHttpCode(httpCode);
		}
		setCode(code);
		setMsg(msg);
	}

	public ApiError(Exception ex) {
		String msg = ex.getMessage();
		if (StringUtils.isEmpty(msg)) {
			msg = ex.toString();
		}
		setMsg(msg);
		if (ex instanceof IllegalArgumentException || ex instanceof MissingServletRequestParameterException) {
			setHttpCode(400);
		} else if (ex instanceof IllegalStateException) {
			setHttpCode(503);
		}
	}

	/** 移除某个属性 */
	@SuppressWarnings("unchecked")
	public <T> T remove(String prop) {
		return (T) values.remove(prop);
	}
}
