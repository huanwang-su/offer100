package com.yiguo.mvc.intercepter;

import com.yiguo.permission.shiro.ShiroSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 头部处理拦截器
 *
 * @author wanghuan
 * @date 2018-01-06
 */
public class HandleHeaderInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (StringUtils.isNotBlank(request.getHeader("Authorization"))) {
			String jwt = request.getHeader("Authorization").trim();
			if (ShiroSessionUtils.getAttribute("token") != null
					&& (!StringUtils.equals(jwt, (CharSequence) ShiroSessionUtils.getAttribute("token")))) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ShiroSessionUtils.getAttribute("token") != null) {
			response.setHeader("Authorization", ShiroSessionUtils.getAttribute("token").toString());
		}
	}

}
