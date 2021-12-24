<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員登入</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/js/transToSite.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/customer/css/customerStyle.css">
</head>
<body>
	<header>
		<div class="logo">
			<a href="#首頁連結"> <img src="images/食健logo.png" alt="logo">
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
			</div>`
			<div class="QA_icon">
				<i class="far fa-question-circle"></i>
			</div>
			<div class="cart_icon">
				<i class="fas fa-shopping-cart"></i>
			</div>
		</div>
	</header>

	<main>
	
		<div class="page_container_login">
			<h2 class="title">會員登入</h2>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red"></font>
				<ul style="list-style: none">
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red; font-size: 15px">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<form method="post" action="<%=request.getContextPath()%>/customer/CustomerServlet" class="form_content">
				<input type="text" name="account" class="input_text" placeholder="帳號" autofocus autocomplete="true" required> 
				<input type="password" name="password" class="input_text" placeholder="密碼" required> 
				<input type="hidden" name="action" value="login">
				<input class="submit_btn" type="submit"	value="登入"> 
				<a href="customerForget.jsp" class="a" id="forgot_password_a">忘記密碼</a>
			</form>
			<div>
				<div>
					<button type="button" id="google_login_btn"
						class="third_party_login_btn">
						<span class="base_iconWrapper__3k0lf"> 
						<svg width="16" height="16" viewBox="0 0 16 16" fill="none">
                                <g clip-path="url(#clip0)">
                                    <path
									d="M16.0008 8.17753C16.0008 7.51976 15.9463 7.03976 15.8285 6.54199H8.16406V9.51085H12.6629C12.5722 10.2486 12.0824 11.3598 10.994 12.1064L10.9787 12.2058L13.4021 14.0456L13.5699 14.062C15.1119 12.6664 16.0008 10.6131 16.0008 8.17753Z"
									fill="#4285F4"></path>
                                    <path
									d="M8.1636 15.9999C10.3676 15.9999 12.218 15.2887 13.5695 14.0621L10.9935 12.1065C10.3042 12.5776 9.37899 12.9065 8.1636 12.9065C6.00489 12.9065 4.17272 11.5109 3.5196 9.58203L3.42386 9.59L0.904047 11.5011L0.871094 11.5909C2.21348 14.2042 4.97084 15.9999 8.1636 15.9999Z"
									fill="#34A853"></path>
                                    <path
									d="M3.52021 9.5824C3.34788 9.08463 3.24815 8.55126 3.24815 8.00017C3.24815 7.44903 3.34788 6.91572 3.51115 6.41795L3.50658 6.31193L0.95518 4.37012L0.871703 4.40903C0.31844 5.49349 0.000976562 6.71129 0.000976562 8.00017C0.000976562 9.28906 0.31844 10.5068 0.871703 11.5913L3.52021 9.5824Z"
									fill="#FBBC05"></path>
                                    <path
									d="M8.16364 3.09331C9.6965 3.09331 10.7305 3.7422 11.3201 4.28446L13.6239 2.08C12.209 0.791114 10.3677 0 8.16364 0C4.97087 0 2.21349 1.79554 0.871094 4.40885L3.51054 6.41777C4.17274 4.48888 6.00492 3.09331 8.16364 3.09331Z"
									fill="#EB4335"></path>
                                </g>
                                <defs>
                                    <clipPath id="clip0">
                                        <rect width="16" height="16"
									fill="white"></rect>
                                    </clipPath>
                                </defs>
                            </svg>
						</span> <span class="third_party_login_span">以Google帳號登入</span>
					</button>
				</div>

				<div class="fb_login">
					<button type="button" id="fb_login_btn"
						class="third_party_login_btn">
						<span class="base_iconWrapper__3k0lf"> 
						<svg width="16" height="16" viewBox="0 0 16 16" fill="none">
                                <circle cx="8" cy="8" r="8" fill="url(#paint0_linear)"></circle>
                                <path
									d="M10.9791 10.4467L11.3345 8.18877H9.11141V6.72413C9.11141 6.10625 9.42138 5.50361 10.4171 5.50361H11.4284V3.58128C11.4284 3.58128 10.511 3.42871 9.6343 3.42871C7.80263 3.42871 6.60653 4.51039 6.60653 6.46782V8.18877H4.57129V10.4467H6.60653V15.9056C7.01512 15.9681 7.43314 16.0001 7.85897 16.0001C8.2848 16.0001 8.70282 15.9681 9.11141 15.9056V10.4467H10.9791Z"
									fill="white"></path>
                                <defs>
                                    <linearGradient id="paint0_linear" x1="8" y1="0" x2="8" y2="15.9525" gradientUnits="userSpaceOnUse">
                                        <stop stop-color="#18ACFE"></stop>
                                        <stop offset="1" stop-color="#0163E0"></stop>
                                    </linearGradient>
                                </defs>
                            </svg>
						</span> <span class="third_party_login_span">以Facebook帳號登入</span>
					</button>
				</div>

				<div class="apple_login">
					<button type="button" id="apple_login_btn" class="third_party_login_btn">
						<span class="base_iconWrapper__3k0lf"> 
						<svg width="16" height="16" viewBox="0 0 14 15" fill="none">
                                <path d="M12.8926 5.06757C12.824 5.10896 11.1909 5.98231 11.1909 7.91872C11.2679 10.1271 13.2518 10.9015 13.2858 10.9015C13.2518 10.9429 12.9863 11.9565 12.1999 13.0191C11.5758 13.9343 10.883 14.8571 9.83114 14.8571C8.83056 14.8571 8.47137 14.2471 7.31685 14.2471C6.07699 14.2471 5.72618 14.8571 4.7769 14.8571C3.725 14.8571 2.98098 13.8849 2.32285 12.9782C1.46783 11.7916 0.741095 9.92931 0.715439 8.14122C0.698149 7.19371 0.886665 6.26231 1.36521 5.4712C2.04063 4.36674 3.24646 3.61701 4.56329 3.59229C5.57224 3.5595 6.4702 4.25979 7.08595 4.25979C7.67603 4.25979 8.77924 3.59229 10.0275 3.59229C10.5662 3.59282 12.003 3.74922 12.8926 5.06757ZM7.00061 3.40311C6.82102 2.53782 7.31685 1.67253 7.77866 1.12057C8.36875 0.453067 9.30073 0 10.1044 0C10.1557 0.865287 9.83058 1.71391 9.24942 2.33198C8.72793 2.99948 7.82997 3.50199 7.00061 3.40311Z"
									fill="#0E101A">
                                </path>
                            </svg>
						</span> <span class="third_party_login_span">以Apple帳號登入</span>
					</button>
				</div>
			</div>
			<div id="register_account">
				還不是會員嗎？<br>立刻註冊一個 <a href="customerPreRegister.jsp" class="a" id="register_account_a">註冊帳號</a>
			</div>
		</div>

	</main>

	<footer>
		<section class="myfooter">
			<div class="contact_info">
				<img src="images/食健logo.png" alt="logo">
				<div class="box_1">連絡電話:0800-000-000</div>
				<div class="box_1">聯絡地址:台北市南京復興</div>
				<div class="box_1">E-MAIL:abc@123.com</div>
			</div>
			<div id="all_rights_reserve">©2021 食健 All Rights Reserve.</div>
		</section>

	</footer>

	<script defer src="https://use.fontawesome.com/releases/v5.0.0/js/all.js"></script>
</body>
</html>