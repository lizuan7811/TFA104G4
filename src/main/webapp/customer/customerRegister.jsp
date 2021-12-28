<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.customer.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
<title>會員註冊</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/customer/css/customerStyle.css">
</head>

<body>
	<header>
		<div class="logo">
			<a href="#首頁連結"> <img src="<%=request.getContextPath()%>/customer/images/食健logo.png" alt="logo">
			</a>
		</div>

		<ul class="header_li">
			<li><a class="menu_title" href="#">關於我們</a></li>
			<li><a class="menu_title" href="#">食健商城</a></li>
			<li><a class="menu_title" href="#">食健生活</a></li>
			<li><a class="menu_title" href="#">會員登入</a></li>
		</ul>

		<div class="icon">
			<div class="search_icon">
				<i class="fas fa-search"></i>
			</div>
			<div class="QA_icon">
				<i class="far fa-question-circle"></i>
			</div>
			<div class="cart_icon">
				<i class="fas fa-shopping-cart"></i>
			</div>
		</div>
	</header>

	<main class="main_register">
		<div class="page_container_register">
			<h2 class="title">會員註冊</h2>
			<c:if test="${not empty errorMsgs.overallErrorMsgs}">
				<font style="color: red"></font>
				<ul>
					<c:forEach var="message" items="${errorMsgs.overallErrorMsgs}">
						<li style="color: red; font-size: 14px; list-style: none">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form action="<%=request.getContextPath() %>/customer/CustomerServlet" method="post"
				enctype="multipart/form-data" class="form_content">

				<input type="text" name="name" id="name" class="input_text"
					placeholder="姓名" value="${custVO.name}" autofocus autocomplete="true"
					required>
				<c:if test="${not empty errorMsgs.nameErrorMsgs}">
					<font style="color: red"></font>
					<ul style="list-style: none">
						<c:forEach var="message" items="${errorMsgs.nameErrorMsgs}">
							<li style="color: red; font-size: 14px">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<input type="text" name="nickname" id="nickname" class="input_text"
					placeholder="暱稱（選填）" value="${custVO.nickname}">

				<c:if test="${not empty errorMsgs.nicknameErrorMsgs}">
					<font style="color: red"></font>
					<ul style="list-style: none">
						<c:forEach var="message" items="${errorMsgs.nicknameErrorMsgs}">
							<li style="color: red; font-size: 14px">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<input type="text" name="account" id="account" class="input_text"
					placeholder="帳號" value="${custVO.account}" autocomplete="true" required>
				<c:if test="${not empty errorMsgs.accountErrorMsgs}">
					<font style="color: red"></font>
					<ul style="list-style: none">
						<c:forEach var="message" items="${errorMsgs.accountErrorMsgs}">
							<li style="color: red; font-size: 14px">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<input type="password" name="password" id="password"
					class="input_text" placeholder="密碼" value="${custVO.password}"
					required>
				<c:if test="${not empty passwordErrorMsgs}">
					<font style="color: red"></font>
					<ul style="list-style: none">
						<c:forEach var="message" items="${passwordErrorMsgs}">
							<li style="color: red; font-size: 14px">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<input type="email" name="email" id="email" class="input_text"
					placeholder="Email" value="${custVO.email}" autocomplete="true" required>
				<c:if test="${not empty errorMsgs.emailErrorMsgs}">
					<font style="color: red"></font>
					<ul style="list-style: none">
						<c:forEach var="message" items="${errorMsgs.emailErrorMsgs}">
							<li style="color: red; font-size: 14px">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<input type="tel" name="phone" id="phone" class="input_text"
					placeholder="手機" value="${custVO.phone}" autocomplete="true" required>
				<c:if test="${not empty errorMsgs.phoneErrorMsgs}">
					<font style="color: red"></font>
					<ul style="list-style: none">
						<c:forEach var="message" items="${errorMsgs.phoneErrorMsgs}">
							<li style="color: red; font-size: 14px">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<div>
					<input type="radio" name="notification" class="notification_radio"
						value="true" ${(custVO.notification == true)? 'checked':''}
						required checked><label for="notification"
						class="notification_label">是</label><input type="radio"
						name="notification" class="notification_radio" value="false"><label
						for="notification" class="notification_label">否</label>透過Email收取優惠及訂單訊息
				</div>
				<div class="avatar-upload">
					<div class="avatar-edit">
						<input type='file' name="profic" id="imageUpload"
							class="avatar_input" accept=".png, .jpg, .jpeg" /> <label
							for="imageUpload" class="avatar_label"></label>
					</div>
					<div class="avatar-preview">
						<img id="imagePreview" src="<%=request.getContextPath()%>/customer/images/default_profic.png">
					</div>
				</div>
				<input type="hidden" name="action" value="register"> <input
					type="submit" class="submit_btn register_btn" value="註冊會員">
			</form>
		</div>
	</main>

	<footer>
		<section class="myfooter">
			<div class="contact_info">
				<img src="<%=request.getContextPath()%>/customer/images/食健logo.png" alt="logo">
				<div class="box_1">連絡電話:0800-000-000</div>
				<div class="box_1">聯絡地址:台北市南京復興</div>
				<div class="box_1">E-MAIL:abc@123.com</div>
			</div>
			<div id="all_rights_reserve">©2021 食健 All Rights Reserve.</div>
		</section>


	</footer>

	<script defer
		src="https://use.fontawesome.com/releases/v5.0.0/js/all.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<script>
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#imagePreview').attr('src', e.target.result);
					$('#imagePreview').hide();
					$('#imagePreview').fadeIn(650);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		$("#imageUpload").change(function() {
			readURL(this);
		});
	</script>
</body>

</html>