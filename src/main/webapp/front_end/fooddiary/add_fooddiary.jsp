<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fooddiary.model.*"%>

<%FoodDiaryVO diaryVO = (FoodDiaryVO) request.getAttribute("diaryVO");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編輯我的日誌</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/edit_diary.css" />

<script defer src="https://use.fontawesome.com/releases/v5.0.0/js/all.js"> //載入font icon </script>

<script src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_js/edit_blog.js"></script>

</head>
<body>
    <div class="bottom_box">
        <div class="email_icon"><i class="fas fa-envelope"></i></div>
        <br>
        <div class="phone_icon"><i class="fas fa-phone-square"></i></i></div>
        <br>
        <div class="chat_icon"><i class="fab fa-facebook-messenger"></i></div>
    </div>
    <header>
        <div class="logo">
            <a href="#首頁連結">
                <img src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/食健logo.png" alt="logo">
            </a>
        </div>
        
        <ul class="header_li">
            <li>
                <a class="menu_title" href="#">關於我們</a>
            </li>
            <li>
                <a class="menu_title" href="#">食健商城</a>
                <!-- <div class="header_menu">
                    <a href="#" class="menu_link">商城分類1</a>
                    <a href="#" class="menu_link">商城分類2</a>                
                </div> -->
            </li>
            <li>
                <a class="menu_title" href="#">食健生活</a>
            </li>
            <li>
                <a class="menu_title" href="#">會員登入</a>
            </li>
        </ul>

        <div class="icon">
            <div class="search_icon"><i class="fas fa-search"></i></div>
            <div class="QA_icon"><i class="far fa-question-circle"></i></div>
            <div class="cart_icon"><i class="fas fa-shopping-cart"></i></div>       
        </div>
    </header>

    <main>
    
<c:if test="${not empty errorMsgs}">
	
	<ul class="error_msg_ul">
		<font style="color:red">請修正以下錯誤:</font>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
        <form action="<%=request.getContextPath()%>/FoodDiaryServlet" name="form1" enctype="multipart/form-data"  method="post">
                <div class="add_content"><br>
                    <div>
                     <span class="info">會員: </span> <input type="text" name="custID" value="1" readonly="readonly"/><br><br>
       				
       				<span class="info">請選擇文章類型:  </span><select class="select_type" name="diary_type">
                            <option value="0">請選擇文章類型</option>
                            <option value="1">健康飲食</option>
                            <option value="2">活力運動</option>
                            <option value="3">食譜分享</option>
                            <option value="4">食健生活</option>
                            <option value="5">心靈雞湯</option>
                            <option value="6">健康情報</option>
                            <option value="7">其它</option>
                        </select>      
	
                    </div><br>
                    
                    <input class="article_title" type="text" name="subject" placeholder="請輸入文章標題"value="<%=(diaryVO==null)? "" : diaryVO.getSubject()%>"/>
          
                    <textarea class="text" name="text" placeholder="請輸入文章內容..."></textarea>
                    
                        <label class="add_pic_1">
                            <div class="preview" id="preview"><img id="view_1" title="請添加圖片"/></div> 
                            <input name= "pic1" id="img" style="display:none;"  onchange="readURL(this)" targetID="view_1" type="file" accept="image/gif, image/jpeg, image/png"/>
                        </label>       
                        <label class="add_pic_2">
                            <div class="preview" id="preview"><img id="view_2" title="請添加圖片"/></div> 
                            <input name= "pic2" id="img" style="display:none;"  onchange="readURL(this)" targetID="view_2" type="file" accept="image/gif, image/jpeg, image/png" />
                        </label> 
                        <label class="add_pic_3">
                            <div class="preview" id="preview"><img id="view_3" title="請添加圖片"/></div> 
                            <input name= "pic3" id="img" style="display:none;"  onchange="readURL(this)" targetID="view_3" type="file" accept="image/gif, image/jpeg, image/png" />
                        </label> 
                                         
                       

                        <div class="all_btn">
                            <div id="radio">
                                文章顯示狀態: 
                                <c:set var="medium" value="Hindi Kannada" />
                                <label><input type="radio" name="status" value="true" checked="checked"><span class="round button">公開</span></label>
                                <label><input type="radio" name="status" value="false"><span class="round button">不公開</span></label>
                            </div>
                            
                            <input type="hidden" name="action" value="insert">
                            <input class="submit_article" type="submit" value="發佈" name="add_article" >
                        </div>
                    
                </div>

        </form>
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