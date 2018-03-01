package com.yiguo.permission.token;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token过滤器
 *
 * @author wanghuan
 * @date 2018-01-09
 */

public class TokenAuthenticationFilter extends AuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		return false;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse arg1, Object arg2) {
		boolean accessAllowed = false;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String jwt = httpRequest.getHeader("Authorization");
		if (jwt == null || !jwt.startsWith("Bearer ")) {
			return accessAllowed;
		}

		String subjectName = "";//ShiroSessionUtils.getLoginAccount().getUsername();
		if (TokenUtil.parseES256Token(jwt).equals(subjectName)) {
			accessAllowed = true;
		}
		return accessAllowed;
	}

}
