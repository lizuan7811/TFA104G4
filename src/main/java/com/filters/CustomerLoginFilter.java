package com.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*; 

public class CustomerLoginFilter implements Filter {

	private FilterConfig config;
	
	public void init(FilterConfig config) {
		this.config = config;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 取得session
		HttpSession session = req.getSession();
		
		// 從session判斷此user是否登入過
		Object account = session.getAttribute("account");
		if (account == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/customer/customerLogin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}	
	}

	public void destroy() {
		config = null;
	}
	
}
