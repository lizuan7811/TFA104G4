<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.fooddiary.model.*"%>
<%@ page import="com.diarytype.model.*"%>

<%	
	FoodDiaryVO diaryVO = (FoodDiaryVO) request.getAttribute("diaryVO");
    DiaryTypeService typeSvc = new DiaryTypeService();
	//   DiaryTypeVO typeVO = typeSvc.getOneType(all_diary.getDiaryType());
	Map map=typeSvc.getMap();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= diaryVO.getSubject() %></title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/customer/css/customerStyle.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/gorup4_diary_page.css" />
<script defer
	src="https://use.fontawesome.com/releases/v5.0.0/js/all.js"> //載入font icon </script>
<script
	src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_js/diary_page.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</head>
<body>
	<div class="bottom_box">
		<div class="email_icon">
			<i class="fas fa-envelope"></i>
		</div>
		<br>
		<div class="phone_icon">
			<i class="fas fa-phone-square"></i></i>
		</div>
		<br>
		<div class="chat_icon">
			<i class="fab fa-facebook-messenger"></i>
		</div>
	</div>
	<header>
		<div class="logo">
			<a href="#首頁連結"> <img
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
			<div class="cart_icon">
				<i class="fas fa-shopping-cart"></i>
			</div>
		</div>
	</header>

	<main>
		<p class="breadcrumb">
			<a style="color: blue;"
				href="<%=request.getContextPath()%>/front_end/GP4_html_cf/group4_diary.html">食健生活</a>
			> <span id="breadcrumb_type" style="color: blue;"> <%= map.get(diaryVO.getDiaryType()) %>
			</span> > <span id="breadcrumb_subject" style="color: blue;">文章主題:<%= diaryVO.getSubject() %></span>
		</p>
		<br>

		<div class="article">
			<div class="article_header">
				<h1 class="article_title"><%= diaryVO.getSubject() %></h1>
				<div class="article_info">
					<div class="author">
						<img class="pro_pic"
							src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/cat_toby.jpg">
						<span class="author_name" data-custId=<%=diaryVO.getCustID()%>
							data-diaryId=<%=diaryVO.getDiaryID()%>>作者: 加法真是太神奇了 (會員編號:<%= diaryVO.getCustID() %>)
						</span> <input class="add_friend" type="button" name="加好友" value="+ 加好友">
					</div>
					<br> <span>發表於 <fmt:formatDate pattern="yyyy/M/d hh:mm"
							value="<%= diaryVO.getCreatedTime() %>" /></span>
					<!-- <span class="info_item"><span class="click_times">1314</span> 次點閱</span> -->
					<span class="info_item"><span class="thumbs_up_num">0</span>
						人給讚</span>
				</div>
				<div class="like_share_report_btn">
                    <div class="like_btn">
                        <i class="far fa-thumbs-up"></i>
                        <span>給讚</span>
                    </div>
					<div id="fb-share-button">
						<svg viewBox="0 0 12 12" preserveAspectRatio="xMidYMid meet">
                            <path class="svg-icon-path"
								d="M9.1,0.1V2H8C7.6,2,7.3,2.1,7.1,2.3C7,2.4,6.9,2.7,6.9,3v1.4H9L8.8,6.5H6.9V12H4.7V6.5H2.9V4.4h1.8V2.8 c0-0.9,0.3-1.6,0.7-2.1C6,0.2,6.6,0,7.5,0C8.2,0,8.7,0,9.1,0.1z"></path>
                        </svg>
						<span class="share_btn">Share</span>
					</div>
					<div class="report">
						<input class="report_btn" type="button" name="report" value="檢舉">
					</div>
				</div>
			</div>
			<div class="container">

				<!-- <input type="radio" id="i1" name="images" checked /> <input
					type="radio" id="i2" name="images" /> <input type="radio" id="i3"
					name="images" /> -->
                    <input type="radio" id="i1" name="images" checked />
                    <input type="radio" id="i2" name="images" />
                    <input type="radio" id="i3" name="images" />



				<div class="slide_img" id="one">

					<img src="PicReader?diaryID=<%= diaryVO.getDiaryID() %>"> <label
						class="prev" for="i3"><span>&#x2039;</span></label> <label
						class="next" for="i2"><span>&#x203a;</span></label>
				</div>
				<div class="slide_img" id="two">

					<img src="PicReader2?diaryID=<%= diaryVO.getDiaryID() %>"> <label
						class="prev" for="i1"><span>&#x2039;</span></label> <label
						class="next" for="i3"><span>&#x203a;</span></label>
				</div>
				<div class="slide_img" id="three">
					<img src="PicReader3?diaryID=<%= diaryVO.getDiaryID() %>"> <label
						class="prev" for="i2"><span>&#x2039;</span></label> <label
						class="next" for="i1"><span>&#x203a;</span></label>
				</div>
			</div>
			<div class="main_article">
				<p style="white-space: pre;">
					<%= diaryVO.getText() %></p>
			</div>
		</div>
		<div id="parent">
			<h3>訪客留言版</h3>
			<input type="text" id="nametxt" placeholder="暱稱..."><br /> <br />
			<input type="text" id="text" placeholder="留言..."><br />
			<button id="btn" value="留言">留言</button>
			<div id="box">
				<em>目前尚無留言內容，請留言</em>
			</div>
			<br />
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