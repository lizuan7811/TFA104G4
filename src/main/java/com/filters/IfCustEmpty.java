package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IfCustEmpty implements Filter{
	private FilterConfig config;
	public void init(FilterConfig config)
	{
		this.config=config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain doChain)
			throws IOException, ServletException {
		HttpServletRequest requ=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		if(requ.getAttribute("custVO")==null)
		{
			resp.sendRedirect(requ.getScheme()+requ.getServerName()+requ.getServerPort()+requ.getServletContext()+"/customer/customerLogin.jsp");
			return;
		}
		else
		{
			doChain.doFilter(request,response);
		}
	}
	
	public void destroy() {
		config=null;
	}
	

}
