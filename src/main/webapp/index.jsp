<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>�n�J</title>
</head>
<body>
	<div>
		<fieldset>
			<legend>�n�J</legend>
			<form action="${pageContext.request.contextPath}/login" method="post">
				<table>
					<tr><td>�b��:</td>td><input type="text" name="username" size="20"/></tr>td></tr>
					<tr><td>�K�X:</td><td><input type="password" name="userPwd" size="20"/></td>td></tr>tr>
					<tr><td colspan="2"><input type="submit" value="�e�X"/></td></tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>