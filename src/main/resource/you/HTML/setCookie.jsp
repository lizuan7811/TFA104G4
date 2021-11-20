<%@page language="java" contentType="text/html;charset=utf-8"%>
<jsp:directive.page import="java.net.URLEncoder"/>
<%!
	boolean isNull(String str)
	{
		return str==null||str.trim().length()==0;
	}
%>
<%
	request.setCharacterEncoding("UTF-8");
	if("POST".equals(request.getMethod())){
		String name=request.getParameter("name");
		String value=request.getParameter("value");
		String maxAge=request.getParameter("maxAge");
		String domain=request.getParameter("domain");
		String path=request.getParameter("path");
		String comment=request.getParameter("comment");
		String secure=request.getParameter("secure");
		
		if(!isNull(name))
		{
			Cookie cookie=new Cookie(URLEncoder.encode(name,"utf-8"),URLEncoder.encode(value,"utf-8"));
			
			if(!isNull(maxAge)){cookie.setMaxAge(Integer.parseInt(maxAge));}
			if(!isNull(domain)){cookie.setDomain(domain);}
			if(!isNull(path)){cookie.setPath(path);}
			if(!isNull(comment)){cookie.setComment(comment);}
			if(!isNull(secure)){cookie.setSecure("true".equalsIgnoreCase(secure));}
			response.addCookie(cookie);
		}
	}
%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Cookie</title>
		<meta http-equiv="param" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<div align="center" style="margin:10px;">
			<filedset>
				<legent>目前有效的Cookie</legent>
				<script type="text/javascript">
					document.write(documetn.cookie);
				</script>
			</filedset>
			<filedset>
				<legent>設定新的Cookie</legent>
				<form action="setCookie.jsp" method="POST">
					<table>
						<tr><td>name:</td>
							<td><input name="name" type="text" style="width:200px;">
							</td>
						</tr>
						<tr><td>maxAge</td>
							<td><input name="value" type="text" style="width:200px;">
							</td>
						</tr>
						<tr><td>domain:</td>
							<td><input name="domain" type="text" style="width:200px;">
							</td>
						</tr>
						<tr><td>path:</td>
							<td><input name="path" type="text" style="width:200px;">
							</td>
						</tr>
						<tr><td>comment:</td>
							<td><input name="comment" type="text" style="width:200px;">
							</td>
						</tr>
						<tr><td></td>
							<td><input type="submit" value="送出" class="button">
							<input type="button" value="更新" onclick="location='setCookie.jsp'">
							</td>
						</tr>
					</table>
				</form>
		</div>
	</body>
</html>

