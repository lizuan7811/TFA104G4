<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<jsp:directive.page import="java.util.Base64"/>
<jsp:directive.page trimDirectiveWhitespace=true"/>

<%
	out.clear();
	for(Cookie cookie:request.getCookies()){
		if(cookie.getName().equals("file"))
		{
			byte[] binary=Base64.getDecoder().decode(cookie.getValue().replace(" ",""));
			response.setHeader("Content-Type","image/git");
			response.setHeader("Content-Disposition","inline;filename=cookie.gif");
			response.setContentLength(binary.length);
			response.getOutputStream().write(binary);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			return;
		}
	}
%>
