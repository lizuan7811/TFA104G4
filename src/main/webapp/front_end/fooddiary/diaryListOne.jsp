<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fooddiary.model.*"%>

<%
	FoodDiaryService diarySvc = new FoodDiaryService();
	List<FoodDiaryVO> list = diarySvc.get_LIST_ONE(1);
	pageContext.setAttribute("list", list);
%>

<%	
	FoodDiaryVO diaryVOO = (FoodDiaryVO) request.getAttribute("diaryVO");
%>

 <jsp:useBean id="typeSvc" scope="page" class="com.diarytype.model.DiaryTypeService" />
<!-- Integer id = Integer.parseInt(request.getParameter("custID")); -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的食健生活</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/customer/css/customerStyle.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/font/style.css" type="text/css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/diary_listONE.css" />
<script src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_js/blog.js"></script>


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
	<div class="all">
            <aside>
            <!-- <Form action="<%=request.getContextPath()%>/FoodDiaryServlet" name="form1" enctype="multipart/form-data"  method="post" style="margin-bottom: 0px;"> -->
			<a class="add_diary" href="add_fooddiary.jsp">新增日誌</a>
			<!-- <input type="hidden" name="custID" value="1"> 
			<input type="hidden" name="action" value="insert">
			</FORM> -->		
                <input class="return_btn" type="button">
                
                <input class="search_friend" type="text" placeholder="搜尋好友">
                <button class="search_friend"><i class="fas fa-search"></i></button>
                
                <ul class="aside_list">
                    <li>
                        <input class="all_friend" type="button" value="好友列表" name="好友列表" >
                    </li>
                    <li>
                        <input class="friend_req" type="button" value="好友申請" name="好友申請" >
                    </li>
                </ul>
            </aside>
        </div>
		<div class="all">
            <div class="news_list">
                <h3>我的日誌列表</h3>

		<table>
			<tr class="table_title">
				<th style="display: none;">系統編號</th>
				<th>日誌標題</th>
				<th>編輯時間</th>
				<th>日誌分類</th>
				<th>查詢</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			
               
			<%@ include file="page1.file"%>
			<c:forEach var="diaryVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td style="display: none;">${diaryVO.diaryID}</td>
					<td>${diaryVO.subject}</td>
					<td>${diaryVO.createdTime}</td>
					
					<td><c:forEach var="typeVO" items="${typeSvc.all}">
                    <c:if test="${diaryVO.diaryType==typeVO.diaryTypeID}">
	                  ${typeSvc.getOneType(diaryVO.diaryType).diaryTypeName} 
                    </c:if></c:forEach></td>
                    <!-- <td>${diaryVO.diaryType}</td> --> 		
					<td>
						<form action="<%=request.getContextPath()%>/FoodDiaryServlet" name="form1" enctype="multipart/form-data"  method="post" style="margin-bottom: 0px;">
							<input type="submit" value="查詢"> 
							<input type="hidden" name="diaryid" value="${diaryVO.diaryID}"> 
							<input type="hidden" name="action" value="getOne_For_Display">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/FoodDiaryServlet"	style="margin-bottom: 0px;">
							<input type="submit" value="修改"> 
							<input type="hidden"	name="diaryid" value="${diaryVO.diaryID}"> 
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/FoodDiaryServlet"
							style="margin-bottom: 0px;">

							<input type="submit" value="刪除"> <input type="hidden"
								name="diaryid" value="${diaryVO.diaryID}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
		</div>
        </div>

        
        <div class="all">
            <div class="chat_box">
                <h4 class="title">聊天列表</h4>
                <div class="chatlist" style="border: rgb(184, 182, 182) 1px solid;">
                    <div class="chat_people">
                        <img class="chat_pic" src="cf_css/cat_toby.jpg" alt="cat"> </div>
                        <div class="chat_content">
                          <h5>貪吃的貓 <span class="chat_date">16:46 pm</span></h5>
                          <br>
                          <p>今天沒那麼冷了吧?</p>
                        </div>
                    </div>
                </div>
              <div class="chat_online" style="display:none">
                <form id="chatform">
                    <label>貪吃的貓:</label>
                    <textarea type="text" id="msg" size="50"></textarea>
                    <input type="text" id="name" size="10" placeholder="your name"/>
                    <button>Send</button>
                </form>
            </div>
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

	<script defer src="https://use.fontawesome.com/releases/v5.0.0/js/all.js">
		//載入font icon
	</script>
	<script type="text/javascript">
	
	</script>

</body>
</html>