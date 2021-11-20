<%@page language="java" contentType="text/html;charset=utf-8" %>
<jsp:directive.page import="java.util.Base64"/>
<jsp:directive.page import="java.util.Base64.Encoder"/>
<jsp:directive.page import="java.io.InputStream"/>
<jsp:directive.page import="java.io.File"/>

<%
	File file=new File(this.getServletContext().getRealPath("Cookie.gif"));
	byte[] binary=new byte[(int)file.length()];
	InputStream ins=this.getServletContext().getResourceAsStream(file.getName());
	ins.read(binary);
	ins.close();
	String content=Base64.getEncoder().encodeToString(binary);
	Cookie cookie=new Cookie("file",content);
	response.addCookie(cookie);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cookie Encoding</title>
</head>
<body>
	Cookie獲得二進為圖片:<img src="base64_decode.jsp"/><br/>
	<textarea id="cookieArea" style="width:100%;height:200px;"></textarea>
	<script type="text/javascript">cookieArea.value=document.cookie;</script>
</body>
</html>