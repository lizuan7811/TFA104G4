<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>雞胸肉</title>

<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/product.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/header_footer.css" />

<script src="https://kit.fontawesome.com/a3a545912b.js" crossorigin="anonymous"></script>

<!-- jquery link -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
<!-- js link -->
<script	src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_js/product.js"></script>

</head>
<body>
	<header>
		<div class="logo">
			<a href="#首頁連結"> <img
				src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/食健logo.png"
				alt="logo">
			</a>
		</div>

		<ul class="header_li">
			<li><a class="menu_title" href="#">關於我們</a></li>
			<li><a class="menu_title" href="#">食健商城</a> <!-- <div class="header_menu">
                    <a href="#" class="menu_link">商城分類1</a>
                    <a href="#" class="menu_link">商城分類2</a>                
                </div> --></li>
			<li><a class="menu_title" href="#">食健生活</a></li>
			<li><a class="menu_title" href="#">會員登入</a></li>
		</ul>


		<div class="icon">
			<div class="search_icon"><i class="fas fa-search"></i></div>
			<div class="QA_icon"><i class="far fa-question-circle"></i></div>
			<!-- 加入購物車 icon -->
			<form name="checkoutForm"action="<%=request.getContextPath()%>/CartServlet" method="POST">
            <input class="cart_icon" type="submit" name="Submit" value="&#xf07a" id="submitButton">
			<input type="hidden" name="action"value="CHECKOUT">
			</form>
			<span class="cart-counter">0</span>


		</div>
	</header>

	<main>

		<div class="inner">
			<div class="product_img">
				<img
					src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/雞胸肉.jpg">
			</div>
			<div class="box1">
				<div class="product_name">舒肥雞胸肉</div>
				<div class="product_list">
					<ul>
						<li>每包180公克大片雞胸肉。 <br>低溫舒肥，鮮嫩多汁不乾柴。 <br>退冰即可食用，夏日超開胃又方便。
							<br>微波加熱、隔水加熱、乾煎加熱皆可。<br></li>
					</ul>
				</div>

			</div>
			<div class="box2">
				<div class="product_price_text">售價</div>
				<div class="product_price">
					<i class="fas fa-dollar-sign"></i>79
				</div>
			</div>
			<form name="shoppingForm" action="<%=request.getContextPath()%>/OneProductServlet"method="POST">
				<div class="box3">
					<div class="shopBar"></div>
					<span class="minus"><i class="fas fa-minus-square"></i></span>
					<span style="margin: 10px 10px;">
					<input id="inputvalue"class="product_input" name="quantity" value="1"></span>
					<span class="plus"><i class="fas fa-plus-square"></i></span>
					<input class="buy_icon" type="submit" value="放入購物車">
				</div>
<!-- 				<input type="hidden" name="idIngre" value="107"> -->
<!-- 				<input type="hidden" name="name" value="舒肥雞胸肉"> -->
<!-- 				<input type="hidden" name="descrip" value="低溫舒肥，鮮嫩多汁不乾柴。"> -->
<!-- 				<input type="hidden" name="price" value="79"> -->
<!-- 				<input type="hidden" name="action" value="ADD_ONE"> -->
		</form>


		<!-- <div class="box3" style="display:flex; align-items: center; width: 300px; justify-content: space-between;">
                    <span class="minus_icon" ><i class="fas fa-minus-square"></i></span>
                    <div ><input class="product_input" value="1"></div>
                    <span class="plus_icon"><i class="fas fa-plus-square"></i></span>
                    <span class="buy_icon"><i class="fas fa-shopping-cart"></i> 加入購物車</span> -->



		<div class="backShop_icon" onclick="location.href='shop.jsp'">
			<span><i class="fas fa-store"></i></span> <span>返回商城</span>
		</div>


	</main>

	<footer>
		<section class="myfooter">
			<div class="contact_info">
				<img
					src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/食健logo.png"
					alt="logo">
				<div class="box_1">連絡電話:0800-000-000</div>
				<div class="box_1">聯絡地址:台北市南京復興</div>
				<div class="box_1">E-MAIL:abc@123.com</div>
			</div>
			<div id="all_rights_reserve">©2021 食健 All Rights Reserve.</div>
		</section>
	</footer>

</body>
</html>