<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.controller.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/front_end/GP4_html_cf/cf_js/cartPay.js"></script>
<title>Insert title here</title>
</head>
<body>

	<%
		Vector<Ingre> cart = (Vector<Ingre>) session.getAttribute("cart");
		String amount =  (String) request.getAttribute("amount");
	%>
	
		<div id="pay_info">
			<ul class="pay_method">
				<li> <span style="color: red;">*</span> 付款方式</li>
			</ul>

			<ul class="pay_radio">
				<li><input type="radio" class="rto" name="pay" id="card_info" value="1">信用卡付款</li>
				<li><input type="radio" class="rto" name="pay" value="宅配貨到付款">宅配貨到付款</li>
				<!-- <li class="pay_btn"> <button class="paybtn" style="width:150px;height:30px;">確認</button></li> -->
			</ul>

			<ul  class="pay_method">
				<li> <span style="color: red;">*</span> 收件人資訊</li>
			</ul>

			<ul class="pay_radio">
				<li>姓名: <input class="paytxt payName" type="text"> </li>
				<li>地址: <input class="paytxt payAddr" type="text"> </li>
				<li>備註: <input class="paytxt payComm" type="text"> </li>
			</ul>
			
			<button class="final_btn" style="color: rgb(241, 238, 238);">送出訂單</button>				

		</div> 

</body>
</html>