<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.customer.model.*"%>
<%
	CustomerService custSvc = new CustomerService();
	List<CustomerVO> list = custSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<link
	href="<%=request.getContextPath()%>/customer/css/customerMgmtStyle.css"
	rel="stylesheet">
<title>會員管理</title>
</head>
<style>
</style>

<body>
	<header>
		<img src="<%=request.getContextPath()%>/customer/images/食健logo.png"
			alt="logo">
		<div class="top_box">
			<apean class="stage1">會員管理</apean>
			<apean class="stage2">●基本資料查詢</apean>
		</div>
	</header>
	<main>

		 <div class="aside">
            <nav>
                <ul class="nav_list">
                    <li><a href="customer/customerMgmt.jsp">會員管理</a></li>

                    <li>
                        <a href="<%=request.getContextPath()%>/search_product.html">產品管理</a>
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/insert_product.html">新增商品</a></li>
                            <li><a href="<%=request.getContextPath()%>/search_product.html">搜尋、修改商品</a></li>
                            <li><a href="<%=request.getContextPath()%>/searchAll_product.html">全部商品</a></li>
                        </ul>
                    </li>

                    <li><a href="<%=request.getContextPath()%>/Search_Recipe.html">食譜管理</a>
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/Insert_Recipe.html">新增食譜</a></li>
                            <li><a href="<%=request.getContextPath()%>/Search_Recipe.html">搜尋、修改食譜</a></li>
                            <li><a href="<%=request.getContextPath()%>/searchAll_Recipe.html">全部食譜</a></li>
                        </ul>
                    </li>

                    <li><a href="finalOrder.html">訂單管理</a></li>
                    <li><a href="back_end/fooddiary/back_end_diary_manage.jsp">日誌管理</a>
                        <ul>
                            <li><a href="back_end/fooddiary/back_end_diary_manage.jsp">全部日誌</a></li>
                            <li><a href="<%=request.getContextPath()%>/diaryRepertResp.html">檢舉管理</a></li>
                            
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>


		<div class="mainbox">
			<ul>
				<li>
					<form method="post"
						action="<%=request.getContextPath()%>/customer/CustomerServlet">
						<b>請輸入會員帳號：</b> <input type="text" name="account"> <input
							type="submit" value="查詢"> <input type="hidden"
							name="account" value="${custVO.account}"> <input
							type="hidden" name="action" value="get_cust_for_display">
					</form>
				</li>
				<li>
					<table class="list" id="data_table">
						<thead class="thead">
							<tr>
								<th class="header" style='width: 70px'>編號</th>
								<th class="header" style='width: 70px'>姓名</th>
								<th class="header">帳號</th>
								<th class="header" style='width: 200px'>Email</th>
								<th class="header">手機</th>
								<th class="header">通知設定</th>
								<th class="header">啟動狀態</th>
								<th class="header">停權狀態</th>
								<th class="header">留言被檢舉成功次數</th>
								<th class="header">日誌被檢舉成功次數</th>
								<th class="header">詳細基本資料</th>
							</tr>
						</thead>
						<%@ include file="pages/page1.file"%>
						<c:forEach var="custVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tbody>
								<tr>
									<td>${custVO.idCustomer}</td>
									<td>${custVO.name}</td>
									<td>${custVO.account}</td>
									<td>${custVO.email}</td>
									<td>${custVO.phone}</td>
									<td>${(custVO.notification == true)?'開啟':'關閉'}</td>
									<td>${(custVO.activated == true)?'啟動':'未啟動'}</td>
									<td>${(custVO.suspended == true)?'停權':'正常'}</td>
									<td>${custVO.commentReportedNum}</td>
									<td>${custVO.diaryReportedNum}</td>
									<td>
										<form method="post"
											action="<%=request.getContextPath()%>/customer/CustomerServlet">
											<input type="submit" value="查詢"> <input type="hidden"
												name="account" value="${custVO.account}"> <input
												type="hidden" name="action" value="get_cust_for_display">
										</form>
								</tr>
							</tbody>
						</c:forEach>
					</table> <%@ include file="pages/page2.file"%>
				</li>
				<br>
				<button id="download_btn">下載excel檔</button>
			</ul>
		</div>

	</main>

</body>
<script src="<%=request.getContextPath()%>/customer/js/customerMgmt.js"></script>

</html>