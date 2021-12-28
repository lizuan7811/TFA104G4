<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.controller.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>食健購物車</title>
	<link href="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_css/cart.css" rel="stylesheet" type="text/css"/>
    <script defer src="https://use.fontawesome.com/releases/v5.0.0/js/all.js"> </script>
    <!-- 載入jquery -->
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
	<!-- 載入sweet alert -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

	<!-- 載入js -->
	<script src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_js/cart.js"></script>


</head>
<body>

<%
	Vector<Ingre> cart = (Vector<Ingre>) session.getAttribute("cart");
	String amount =  (String) request.getAttribute("amount");
%>	

<%if (cart != null && (cart.size() > 0)) {%>

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
            <li class="menu_title aboutUs">
                <a>關於我們</a>
            </li>
            <li class="menu_title shopCity">
                <a>食健商城</a>
            </li>
            <li class="menu_title eatLife">
                <a>食健生活</a>
            </li>
            <li class="menu_title custLogin">
                <a>會員登入</a>
            </li>
        </ul>

        <div class="icon">
            <div class="search_icon"><i class="fas fa-search"></i></div>
            <div class="QA_icon"><i class="far fa-question-circle"></i></div>
            <div class="cart_icon"><i class="fas fa-shopping-cart"></i></div>       
        </div>
    </header>

    <main>
		<div class="next_item" >
			<div class="mainWrapper">
				<div class="statusBar">
					<span class="pBar"></span>
					<div class="node n0 done nConfirm0">
						<div class="main done m0 done nConfirm0"></div>
						<span class="text t0 done nConfirm0">購物車確認</span>
					</div>
					<div class="node n1 nConfirm1">
						<div class="main m1 nConfirm1"></div>
						<span class="text t1 nConfirm1">付款資訊</span>
					</div>
					<div class="node n2 nConfirm2">
						<div class="main m2 nConfirm2"></div>
						<span class="text t2 nConfirm2">訂單完成</span>
					</div>
				</div><br><br><br><br><br><br><br>
				
			</div>
		</div>
		
		<div class="container">	
			<h2 id="foodtype" style="background-color: chartreuse; text-align: left;">食健食材</h2>	

	<%
	 for (int index = 0; index < cart.size(); index++) {
		Ingre order = cart.get(index);
	%>

			<div id="cart">
				<article class="product">
						
					<div class="content" style="border-top: 5px solid rgb(227, 235, 226);">
						<a class="remove" href="#">
							<img src="data:image/jpg;base64,<%=order.getPhoto()%>">
        	  </form>						
							<h3>移除商品</h3>
						</a>
						<h1><%= order.getName() %></h1><br>
		
						<%= order.getDescrip() %>
							
					</div>
		
					<div class="content">
						<div class="qt_all">
							<span class="qt-minus">-</span><input type="hidden" name="idIngre" class="idIngre" value="<%=order.getIdIngre()%>">
							<span class="qt" quan="<%= order.getQuantity() %>"><%= order.getQuantity() %></span>
							<span class="qt-plus">+</span>								
							<h2 class="pp1">單價:</h2><h2 class="price"><%= order.getPrice() %></h2>
							<h2 class="pp2" style="color: white;">總價:</h2><h2 style="color: white;" class="full-price"><%=amount %></h2>
						</div>							
					</div>
					<script type="text/javascript">
					</script>
				</article>				
			</div>	
				
	<%}%>
		</div>			
	</div> 
	
		
  <jsp:include page="/front_end/cart/cart_input_info.jsp" flush="true" />
		<div id="site-footer">
				<div class="container clearfix">
	
					<!-- <div class="left">
						<a class="button b-back disabled" id="back" style="color: rgb(241, 238, 238);">上一步</a>	
					</div> -->
	
					<div class="right">
						<h1 class="total">總金額: <span><%=amount %></span>元</h1>
						
						<a class="btn" id="next" style="color: rgb(241, 238, 238);" >下一步</a>
						<input type="hidden" name="action" value="CLEAR">
						<input type="hidden" name="amount" value=amount>
			 			<input type="submit" name="clear" value="返回商城" class="re_btn" id="del_return" style="display: none; color: rgb(241, 238, 238);"></div>

<%}%>						
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

    <script defer src="https://use.fontawesome.com/releases/v5.0.0/js/all.js"> //載入font icon </script> 

</body>
</html>