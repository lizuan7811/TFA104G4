<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isErrorPage="false"%>
<jsp:directive.page import="java.security.MessageDigest"/>
<%!
	private static final String KEY=":cookie@helloweenvsfei.com";
	public final static String calcMD5(String ss)
	{
	String s=ss==null?"":ss;
	char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	try{
		byte[] strTemp=s.getBytes();
		MessageDigest mdTemp=MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md=mdTemp.digest();
		int j=md.length;
		char str[] =new char[j*2];
		int k=0;
		for(int i=0;i<j;i++)
		{
			byte byte0=md[i];
			str[k++]=hexDigits[byte0>>>4&0xf];
			str[k++]=hexDigits[byte0&0xf];
		}
		return new String(str);
		}catch(Exception e)
		{
			return null;
		}
	}
	%>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String action=request.getParameter("action");
		if("login".equals(action))
		{
			String account=request.getParameter("account");
			String password=request.getParameter("password");
			int timeout=Integer.parseInt(request.getParameter("timeout"));
			String ssid=calcMD5(account+KEY);
			Cookie accountCookie=new Cookie("account",account);
			accountCookie.setMaxAge(timeout);
			Cookie ssidCookie=new Cookie("ssid",ssid);
			ssidCookie.setMaxAge(timeout);
			response.addCookie(accountCookie);
			response.addCookie(ssidCookie);
			
			response.sendRedirect(request.getRequestURI()+"?"+System.currentTimeMillis());
			return;
		}
		else if("logout".equals(action))
		{
			Cookie accountCookie=new Cookie("account","");
			accountCookie.setMaxAge(0);
			
			Cookie ssidCookie=new Cookie("ssid","");
			ssidCookie.setMaxAge(0);
			
			response.addCookie(accountCookie);
			response.addCookie(ssidCookie);
			response.sendRedirect(request.getRequestURI()+"?"+System.currentTimeMillis());
			return ;
		}
		boolean login=false;
		String account=null;
		String ssid=null;
		if(request.getCookies()!=null)
		{
			for(Cookie cookie:request.getCookies())
			{
				if(cookie.getName().equals("account"))
				{
					account=cookie.getValue();
				}
				if(cookie.getName().equals("ssid"))
				{
					ssid=cookie.getValue();
				}
			}
			if(account!=null && ssid!=null)
			{
				login=ssid.equals(calcMD5(account+KEY));
			}
		}
	%>
	<!DOCTYPE HTML>
	<legend><%=login?"歡迎回來":"請先登入"%></legend>
	<% if(login){%>
		歡迎，${cookie.account.value}. &nbsp;&nnbsp;
		<a href="${pageContext.request.requestURI}?action=logout">註銷</a>
	<%}else{%>
		<form action="${pageContext.request.requestURI}?action=logout" method="POST">
			<table>
				<tr><td>帳號:</td>
					<td><input type="text" name="account" style="width:200px;"></td>
				</tr>
				<tr><td>密碼:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr><td>有效期:</td>
					<td><input type="radio" name="timeout" value="-1" checked>關閉瀏覽器即失效<br/>
					<input type="radio" name="timeout" vlaue="<%=30*24*60*60%>">30天內有效<br/>
					<input type-"radio" name="timeout" value="<%=Integer.MAX_VALUE%>">永久有效<br/></td></tr>
					<tr><td></td>
						<td><input type="submit" value="登入" class="button"></td>
					</tr>
			</table>
		</form>
	<%}%>