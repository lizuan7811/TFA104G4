<%@page language="java" import="java.util.*" contentType="text/html;charset=utf-8" %>

<%	Locale locale=request.getLocale();
	Calendar calendar=Calendar.getInstance(locale);
	int hour=calendar.get(Calendar.HOUR_OF_DAY);
	String greeting="";
	if(hour<=6){
		greeting="Morning!";
	}else if(hour<=9)
	{
		greeting="Two Morning!";
	}
	else if(hour<=12)
	{
		greeting="NooN";
	}
	else if(hour<=18)
	{
		greeting="night";
	}
	else if(hour<=24)
	{
		greeting=(String)request.getAttribute("greeting");
	}
	else
	{
		
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>First Welcome Page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="kewords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="this is my page">
</head>
<body>
	<table>
		<tr>
			<td><%= greeting %></td>
		</tr>
	</table>
</body>
</html>