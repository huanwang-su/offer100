package com.yiguo.mvc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 *
 * @author wanghuan
 * @date 2018-01-06
 */
public class WebContextFilter implements Filter {

	@Override
	public void destroy() {

	}

	/**
	 * 在进入时将request和response注册到WebContext中，结束时清除
	 * 
	 * @param request
	 *            要注入的request
	 * @param response
	 *            要注入的response
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			WebContext.registry((HttpServletRequest) request, (HttpServletResponse) response);
			chain.doFilter(request, response);
		} finally {
			WebContext.release();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
