<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="refresh" content="3; url=customerLogin.jsp">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
<title>重設密碼成功</title>
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
	<main class="main_height">
		<div class="page_container_short">
			<span class="re-register_span">密碼重設成功！<br>將於3秒後跳轉至登入頁面</span>
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
		src="https://use.fontawesome.com/releases/v5.0.0/js/all.js">
		//載入font icon
	</script>
</body>

</html>