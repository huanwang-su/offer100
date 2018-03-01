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

import com.yiguo.utils.UtilJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 统一API异常处理
 * 
 * @author <a href="mailto:1360527082@qq.com">王焕</a>
 * @since 0.1.0
 */

@ControllerAdvice
public class ApiExceptionControllerAdvice {
	private @Value("${api.error.debug:false}") boolean debug;
	protected Set<String> exKeys = new HashSet<>(
			Arrays.asList(StringUtils.split("Exception,SQL,java.,.java:,com.", ",")));
	private static Logger logger = LogManager.getLogger(ApiExceptionControllerAdvice.class);
	private static Level level = logger.getLevel();

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {

		if (Level.WARN.equals(level)) {
			logger.warn("ex={}", ex);
		} else if (Level.INFO.equals(level)) {
			logger.info("params={}, ex={}", UtilJson.writeValueAsString(request.getParameterMap()), ex);
		} else if (Level.DEBUG.equals(level)) {
			logger.debug("?={}, params={}", request.getQueryString(),
					UtilJson.writeValueAsString(request.getParameterMap()), ex);
		}
		// spring翻译使用req.getmessage()方法
		RequestContext req = new RequestContext(request);

		ApiError error = new ApiError();
		error.setHttpCode(500);
		if (ex instanceof ApiException) {
			error.getValues().putAll(((ApiException) ex).getError().getValues());
		} else {
			error.setMsg(ex.getMessage());
			if (ex instanceof IllegalArgumentException || ex instanceof MissingServletRequestParameterException
					|| ex instanceof MethodArgumentTypeMismatchException
					|| ex instanceof HttpMessageNotReadableException) {
				error.setHttpCode(400);
			}
		}
		// 数据校验异常
		if (ex instanceof BindException) {
			error.setHttpCode(response.getStatus());
			BindException bException = (BindException) ex;
			StringBuilder sBuilder = new StringBuilder();
			for (ObjectError err : bException.getAllErrors()) {
				if (err instanceof FieldError) {
					String tem = ((FieldError) err).getDefaultMessage();
					if (tem.contains("java") || tem.contains("Exception") || tem.contains("com."))
						sBuilder.append(((FieldError) err).getField()).append(".error,");
					else if (tem.startsWith("{") && tem.endsWith("}"))
						sBuilder.append(req.getMessage(tem.substring(1, tem.length() - 1)) + ",");
					else
						sBuilder.append(tem + ",");
				}
			}
			String msg = sBuilder.substring(0, sBuilder.length() - 1);
			error.setMsg(msg);
			error.setHttpCode(400);
		}

		if (error.getHttpCode() != 400) { // 如果是400的，使用本身的提示信息
			String msg = error.getMsg();
			if (msg != null) {
				for (String exKey : exKeys) {
					if (msg.indexOf(exKey) != -1) {
						logger.error("BUG-FIXME: msg={}, ex={}", msg, ex);
						msg = req.getMessage("api.exception");// ":( 服务故障，请稍后重试";
						if (Objects.equals(error.getI18n(), error.getCode())) {
							error.setI18n("api.exception");
						}
						error.setMsg(msg);
						break;
					}
				}
			}
		}

		procApiError(error);

		response.setStatus(error.getHttpCode());
		return error.getValues();
	}

	protected void procApiError(ApiError error) {
	}
}
