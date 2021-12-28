<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.customer.model.*"%>
<%
	CustomerVO custVO = (CustomerVO) session.getAttribute("custVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
<title>會員中心 | 修改基本資料</title>
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
	<main>
		<aside class="sidebar">
			<nav>
				<ul>
					<li id="member_center_li"><strong id="member_center_title">會員中心</strong></li>
					<div class="ul_div_li">
						<li><a href="#">基本資料</a></li>
						<li><a href="#">送達地址</a></li>
						<li><a href="#">信用卡設定</a></li>
						<li><a href="#">歷史訂單</a></li>
					</div>
				</ul>
			</nav>
		</aside>
		<div class="main_content page_container_basic_info" id="basic_info">
			<h2 class="title">修改基本資料</h2>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red"></font>
				<ul style="list-style: none">
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form action="<%=request.getContextPath() %>/customer/CustomerServlet" method="POST"
				enctype="multipart/form-data">
				<div>
					<label class="label_item">姓名</label><input type="text" name="name"
						class="input_text" value=<%=custVO.getName()%>>
				</div>
				<div>
					<label class="label_item">暱稱</label><input type="text"
						name="nickname" class="input_text" value=<%=custVO.getNickname()%>>
				</div>
				<div>
					<label class="label_item">帳號</label><input type="text"
						name="account" class="input_text" value=<%=custVO.getAccount()%>
						readonly>
				</div>
				<div>
					<label class="label_item">密碼</label><input type="password"
						name="password" class="input_text" value=<%=custVO.getPassword()%>>
				</div>
				<div>
					<label class="label_item">Email</label><input type="email"
						name="email" class="input_text" value=<%=custVO.getEmail()%>>
				</div>
				<div>
					<label class="label_item">手機</label><input type="tel" name="phone"
						class="input_text" value=<%=custVO.getPhone()%>>
				</div>
				<div>
					<input type="radio" name="notification" class="notification_radio"
						id="notification_true" value="true"
						${(custVO.notification == true)? 'checked':''}><label
						for="notification" class="notification_label">是</label><input
						type="radio" name="notification" class="notification_radio"
						id="notification_false" value="false"
						${(custVO.notification == false)? 'checked':''}><label
						for="notification" class="notification_label">否</label>透過Email收取優惠及訂單訊息
				</div>
				<div class="avatar-upload">
					<div class="avatar-edit">
						<input type='file' name="profic" id="imageUpload"
							class="avatar_input" accept=".png, .jpg, .jpeg" /> <label
							for="imageUpload" class="avatar_label"></label>
					</div>
					<div class="avatar-preview">
						<img id="imagePreview"
							src="ReadProfic?account=<%=custVO.getAccount()%>"
							onerror="this.src='images/default_profic.png;this.onerror=null'">
					</div>
				</div>
				<input type="hidden" name="action" value="update"> <input
					type="submit" class="basic_info_submit_btn" value="儲存">
			</form>
		</div>

	</main>

	<footer class="footer">
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
		src="https://use.fontawesome.com/releases/v5.0.0/js/all.js">
		//載入font icon
	</script>
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