<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/a3a545912b.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>

<script	src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_js/shop.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/customer/css/customerStyle.css">

<link href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/shop_cf.css"rel="stylesheet">
<link href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/header&footer.css"rel="stylesheet">

<script type="text/javascript" src="<%=request.getContextPath()%>/slick-1.8.1/slick/slick.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/slick-1.8.1/slick/slick.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/slick-1.8.1/slick/slick-theme.css" />
<title>食健商城</title>

</head>

<body>
	<header>
		<div class="logo">
			<a href=""> <img
				src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/食健logo.png"
				alt="logo">
			</a>
		</div>

		<ul class="header_li">
			<li class="menu_title aboutUs"><a>關於我們</a></li>
			<li class="menu_title shopCity"><a>食健商城</a></li>
			<li class="menu_title eatLife"><a>食健生活</a></li>
			<li class="menu_title custLogin"><a>會員登入</a></li>
		</ul>

		<div class="icon">
			<div class="search_icon">
				<i class="fas fa-search"></i>
			</div>
			<div class="QA_icon">
				<i class="far fa-question-circle"></i>
			</div>
			<!-- 加入購物車 icon -->
			<form name="checkoutForm"
				action="<%=request.getContextPath()%>/CartServlet" method="POST">
				<input class="cart_icon" type="submit" name="Submit" value="&#xf07a"
					id="submitButton"> <input type="hidden" name="action"
					value="CHECKOUT">
			</form>
		</div>
	</header>

	<main>

		<div class="shop_bg">
			<img id="shop_bg_img"
				src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/shopbackground.jpg">
		</div>

		<div class="top">
			<form name="shoppingForm" action="<%=request.getContextPath()%>/CartServlet" method="POST">
				<div class="top_item" id="top1">
					<div id="product_img1" class="top_box1"></div>
					<div class="top_box2" id="top1_box2">
						<div id="product_name1" class="top_product"></div>
						<div id="product_price1" class="top_price">
							<span id="product_unit1" class="product_unit"></span>
						</div>


					</div>

					<div class="top_box3" id="top1_box3">
						<input class="top_cart_icon pointer" type="submit" name="Submit"
							value="&#xf07a" id="submitButton">
					</div>
					<div class="top_box4">
						TOP.1
					</div>
				</div>
			</form>

			<form name="shoppingForm" action="<%=request.getContextPath()%>/CartServlet" method="POST">
				<div class="top_item" id="top2">
					<div class="top_item" id="top1">
						<div id="product_img2" class="top_box1"></div>
						<div class="top_box2" id="top2_box2">
							<div id="product_name2" class="top_product"></div>
							<div id="product_price2" class="top_price">
								<span id="product_unit2" class="product_unit"></span>
							</div>


						</div>
						<div class="top_box3" id="top2_box3">
							<input class="top_cart_icon pointer" type="submit" name="Submit"
								value="&#xf07a" id="submitButton">
						</div>
						<div class="top_box4">TOP.2</div>
					</div>
				</div>
			</form>
			<form name="shoppingForm" action="<%=request.getContextPath()%>/CartServlet" method="POST">
				<div class="top_item" id="top3">
					<div class="top_item" id="top1">
						<div id="product_img3" class="top_box1"></div>
						<div class="top_box2" id="top3_box2">
							<div id="product_name3" class="top_product"></div>
							<div id="product_price3" class="top_price">
								<span id="product_unit3" class="product_unit"></span>
							</div>
						</div>
						<div class="top_box3" id="top3_box3">
							<input class="top_cart_icon pointer" type="submit" name="Submit"
								value="&#xf07a" id="submitButton">
						</div>
						<div class="top_box4">TOP.3</div>
					</div>
				</div>
		
		</form>
		</div>
		<div class="resipe">
			<div class="hotResipe pointer"
				onclick="location.href='hotRecipe.html'">
				<div class="hotResipe_box1">熱門食譜</div>
				<div class="hotResipe_box2">
					<img id="hotResipe_img"
						src="<%=request.getContextPath()%>/img/木板.jpg">
				</div>
			</div>

			<div class="resipe_item" id="resipe1">
				<div class="resipe_box1 pointer">
					<img id="rcpImg1" data-idRecipe="" class="resipe_img" src="">
				
<%-- 					<img class="resipe_img" src="<%=request.getContextPath()%>/img/香草烤雞胸.jpg"> --%>
				</div>
				<div class="resipe_box2" id="resipe1_box2">
				<div id="rcpName1" class="resipe_name"></div>
<!-- 					<div class="resipe_name">香草烤雞胸</div> -->
					<div class="resipe_list">
					<ul id="list1">

						</ul>
<!-- 						<ul> -->
<!-- 							<li>雞胸肉1片</li> -->
<!-- 							<li>番茄2顆</li> -->
<!-- 							<li>洋蔥1顆</li> -->
<!-- 						</ul> -->
					</div>
					<div class="resipe_cart_icon pointer">
						<i class="fas fa-shopping-cart"></i>
					</div>
				</div>
				<div class="resipe_box3" id="resipe1_box3"></div>
			</div>

			<div class="resipe_item" id="resipe2">
				<div class="resipe_box1">
				<img id="rcpImg2" data-idRecipe="" class="resipe_img" src="">
<%-- 				<img class="resipe_img" src="<%=request.getContextPath()%>/img/番茄烘蛋.jpg"> --%>
				</div>
				<div class="resipe_box2" id="resipe1_box2">
				<div id="rcpName2" data-idRecipe="" class="resipe_name"></div>
<!-- 					<div class="resipe_name">>番茄烘蛋</div> -->
					<div class="resipe_list">
						<ul id="list2">

						</ul>
					</div>
					<div class="resipe_cart_icon pointer">
						<i class="fas fa-shopping-cart"></i>
					</div>
				</div>
				<div class="resipe_box3" id="resipe2_box3"></div>
			</div>

			<div class="resipe_item" id="resipe3">
				<div class="resipe_box1">
					<img id="rcpImg3" data-idRecipe="" class="resipe_img" src="">
<%-- 					<img class="resipe_img" src="<%=request.getContextPath()%>/img/紙包鮭魚.jpg"> --%>
				</div>
				<div class="resipe_box2" id="resipe1_box2">
				<div id="rcpName3" data-idRecipe="" class="resipe_name"></div>
<!-- 					<div class="resipe_name">紙包鮭魚</div> -->
					<div class="resipe_list">
						<ul id="list3"></ul>
<!-- 						<ul> -->
<!-- 							<li>鮭魚切片1片</li> -->
<!-- 							<li>大蒜1顆</li> -->
<!-- 							<li>紅洋蔥1顆</li> -->
<!-- 							<li>檸檬1顆</li> -->
<!-- 						</ul> -->
					</div>
					<div class="resipe_cart_icon pointer">
						<i class="fas fa-shopping-cart"></i>
					</div>
				</div>
				<div class="resipe_box3" id="resipe3_box3"></div>
			</div>
		</div>

		<div class="sort">
			<div class="sort_item" id="sort1">
				<div class="sort_bar">
					<div class="sort_line" id="sort1_left"></div>
					<div class="sort_name" id="sort1_name">鮮肉</div>
					<div class="sort_line" id="sort1_right"></div>
				</div>
				<div class="sort_imgs"
					data-slick='{"slidesToShow": 4, "slidesToScroll": 4}'>
					<div id="type1" class="your-class">
						<div>
							<img class="type_img" id="type1_img1" data-idIngre="102"
								src="<%=request.getContextPath()%>/img_ingre/蝦子.jpg">
							<h3>蝦子</h3>
						</div>
						<div>
							<img class="type_img" id="type1_img2" data-idIngre="105"
								src="<%=request.getContextPath()%>/img_ingre/薄鹽鯖魚片.jpeg">
							<h3>薄鹽鯖魚片</h3>
						</div>
						<div>
							<img class="type_img" id="type1_img3" data-idIngre="101"
								src="<%=request.getContextPath()%>/img_ingre/鮭魚切片.jpg">
							<h3>鮭魚切片</h3>
						</div>
						<div>
							<img class="type_img" id="type1_img4" data-idIngre="103"
								src="<%=request.getContextPath()%>/img_ingre/牛腩切塊.jpg">
							<h3>牛腩切塊</h3>
						</div>
						<div>
							<img class="type_img" id="type1_img5" data-idIngre="106"
								src="<%=request.getContextPath()%>/img_ingre/雞蛋.jpeg">
							<h3>雞蛋</h3>
						</div>
						<div>
							<img class="type_img" id="type1_img" data-idIngre="107"
								src="<%=request.getContextPath()%>/img_ingre/雞胸肉.jpg">
							<h3>雞胸肉</h3>
						</div>
					</div>
				</div>
			</div>

			<div class="sort_item" id="sort2">
				<div class="sort_bar">
					<div class="sort_line" id="sort2_left"></div>
					<div class="sort_name" id="sort2_name">時蔬</div>
					<div class="sort_line" id="sort2_right"></div>
				</div>
				<div class="sort_imgs">
					<div class="your-class">
						<div>
							<img class="type_img" data-idIngre="202" src="<%=request.getContextPath()%>/img_ingre/鮮香菇.jpg">
							<h3>鮮香菇</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="207" src="<%=request.getContextPath()%>/img_ingre/洋蔥.jpg">
							<h3>洋蔥</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="203" src="<%=request.getContextPath()%>/img_ingre/馬鈴薯.jpg">
							<h3>馬鈴薯</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="204" src="<%=request.getContextPath()%>/img_ingre/大白菜.jpg">
							<h3>大白菜</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="201" src="<%=request.getContextPath()%>/img_ingre/櫛瓜.jpg">
							<h3>櫛瓜</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="206" src="<%=request.getContextPath()%>/img_ingre/高麗菜.jpg">
							<h3>高麗菜</h3>
						</div>
					</div>

				</div>
			</div>
			<div class="sort_item" id="sort3">
				<div class="sort_bar">
					<div class="sort_line" id="sort3_left"></div>
					<div class="sort_name" id="sort3_name">水果</div>
					<div class="sort_line" id="sort3_right"></div>
				</div>
				<div class="sort_imgs">
					<div class="your-class">
						<div>
							<img class="type_img" data-idIngre="306" src="<%=request.getContextPath()%>/img_ingre/黃檸檬.jpg">
							<h3>黃檸檬</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="305" src="<%=request.getContextPath()%>/img_ingre/奇異果.jpg">
							<h3>奇異果</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="307" src="<%=request.getContextPath()%>/img_ingre/小番茄.jpg">
							<h3>小番茄</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="304" src="<%=request.getContextPath()%>/img_ingre/橘子.jpg">
							<h3>橘子</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="301" src="<%=request.getContextPath()%>/img_ingre/草莓.jpeg">
							<h3>草莓</h3>
						</div>
						<div>
							<img class="type_img" data-idIngre="303" src="<%=request.getContextPath()%>/img_ingre/牛蕃茄.jpg">
							<h3>牛番茄</h3>
						</div>
					</div>
				</div>
			</div>
	</main>
	<footer>
		<section class="myfooter">
			<div class="contact_info">
				<img src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/食健logo.png" alt="logo">
				<div class="box_1">連絡電話:0800-000-000</div>
				<div class="box_1">聯絡地址:台北市南京復興</div>
				<div class="box_1">E-MAIL:abc@123.com</div>
			</div>
			<div id="all_rights_reserve">©2021 食健 All Rights Reserve.</div>
		</section>
	</footer>
</body>

</html>