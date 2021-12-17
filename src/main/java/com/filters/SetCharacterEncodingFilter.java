package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncodingFilter implements Filter {

	protected String encoding = null;
	protected FilterConfig config = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.encoding = config.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding(encoding); // 使用Filter解決Query String之編碼問題
		chain.doFilter(request, response); // 將程式控制權交給後續過濾器
	}
	
	@Override
	public void destroy() {
		this.encoding = null;
		this.config = null;
	}

}
