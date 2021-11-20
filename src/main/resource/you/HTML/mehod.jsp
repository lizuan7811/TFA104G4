<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head><title>JSP Scriptlets</title>
<link rel="stylesheet" type="text/css" href="css/pushupRecipe.css">
</head>
<body>
<br/>
<%
	Object[][] letters={{true,"Congradulation to register!","e_inn@155.com","helloweenvsfei@gmail.com","2007-9-8"},
			{true,"Congradulation to register!","e_inn@155.com","helloweenvsfei@gmail.com","2007-9-8"},
			{true,"Congradulation to register!","e_inn@155.com","helloweenvsfei@gmail.com","2007-9-8"},
			{true,"Congradulation to register!","e_inn@155.com","helloweenvsfei@gmail.com","2007-9-8"}};
	String[] colors={"#DDDDDD","#AAAAAA",};
%>
	<table border=0 cellspacing=1 cellpadding=2 width=288 align=center>
		<tr stype="background:url(img/EHlogo.png);">
			<td align="center" style="line-height:22px;">&nbsp;</td>
			<td align="center" style="line-height:22px;">標題;</td>
			<td align="center" style="line-height:22px;">發信人</td>
			<td align="center" style="line-height:22px;">收信人</td>
			<td align="center" style="line-height:22px;">時間</td>
		</tr>
	<%
		for(int i=0;i<letters.length;i++){
			Object[] letter=letters[i];			
	%>
	<tr style="background:<%= colors[i%2] %>">
		<td align="center">
		<%
			if( letter[0] ==Boolean.TRUE){
		%>
		<img src="img/EHlolo.png"/>
		<%
			}
			else{
				out.println("&nbsp;");
			}
		%>
		</td>
		<td><a href="#"><%= letter[1] %></a></td>
		<td><%= letter[2] %></td>
		<td><%= letter[3] %>
		<td align="center"><%= letter[4] %></td>	
	</tr>
<% 
	} 
%>
	</table>
</body>
</html>