<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
<title>登入</title>
</head>
<body>
	<div>
		<fieldset>
			<legend>登入</legend>
			<form action="${pageContext.request.contextPath}/login" method="post">
				<table>
					<tr><td>帳號:</td>td><input type="text" name="username" size="20"/></tr>td></tr>
					<tr><td>密碼:</td><td><input type="password" name="userPwd" size="20"/></td>td></tr>tr>
					<tr><td colspan="2"><input type="submit" value="送出"/></td></tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>