package com.customer.controller;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.customer.model.*;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//****** 須改用動態路徑req.getContextPath() ****//
	private static final String BASE_PATH = "/customer";

	private static final String LOGIN_PAGE = BASE_PATH + "/customerLogin.jsp";
	private static final String REGISTER_PAGE = BASE_PATH + "/customerRegister.jsp";
	private static final String POST_REGISTER_PAGE = BASE_PATH + "/customerPostRegister.jsp";
	private static final String BASIC_PAGE = BASE_PATH + "/customerBasic.jsp";
	private static final String UPDATE_PAGE = BASE_PATH + "/customerUpdate.jsp";
	private static final String FORGET_PAGE = BASE_PATH + "/customerForget.jsp";
	private static final String POST_FORGET_PAGE = BASE_PATH + "/customerPostForget.jsp";
	private static final String RESET_PAGE = BASE_PATH + "/customerReset.jsp";
	private static final String RESET_SUCCEED_PAGE = BASE_PATH + "/customerResetSucceed.jsp";
	private static final String CUST_MGMT_PAGE = BASE_PATH + "/customer/customerMgmt.jsp";
//	private static final String PRE_REGISTER_PAGE = BASE_PATH + "/customerPreRegister.jsp"; // 有需要再用

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");

		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(900); // 15分鐘後session失效(在web.xml預設是30分鐘)
		String action = req.getParameter("action");
		ValidateCustomer vc = new ValidateCustomer();

		/* 註冊：來自customerRegister.jsp的請求 */
		if ("register".equals(action)) {

			Map<String, String> errorMsgs = new HashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> passwordErrorMsgs = new LinkedList<String>();
			req.setAttribute("passwordErrorMsgs", passwordErrorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 姓名
				String name = req.getParameter("name").trim();
				if (vc.validateName(name) == false) {
					for (String msg : vc.nameErrorMsgs) {
						errorMsgs.put("nameErrorMsgs", msg);
					}
				}

				// 暱稱
				String nickname = req.getParameter("nickname").trim();
				if (vc.validateNickname(nickname) == false) {
					for (String msg : vc.nicknameErrorMsgs) {
						errorMsgs.put("nicknameErrorMsgs", msg);
					}
				}

				// 帳號 用會員帳號維持身份
				String account = req.getParameter("account").trim();
				if (vc.validateAccount(account) == false) {
					for (String msg : vc.accountErrorMsgs) {
						errorMsgs.put("accountErrorMsgs", msg);
					}
				}

				// 會員密碼
				String password = req.getParameter("password").trim();
//				String password2 = req.getParameter("password2").trim();
				if (vc.validatePassword(password) == false) {
					for (String msg : vc.passwordErrorMsgs) {
						passwordErrorMsgs.add(msg);
					}
				}

				// 會員信箱
				String email = req.getParameter("email");
				if (vc.validateEmail(email) == false) {
					for (String msg : vc.emailErrorMsgs) {
						errorMsgs.put("emailErrorMsgs", msg);
					}
				}

				// 會員手機
				String phone = req.getParameter("phone");
				if (vc.validatePhone(phone) == false) {
					for (String msg : vc.phoneErrorMsgs) {
						errorMsgs.put("phoneErrorMsgs", msg);
					}
				}

				// 大頭貼
				Part proficPart = req.getPart("profic");
				WriteProfic wp = new WriteProfic();
				byte[] profic = wp.writeProfic(proficPart);

				// 信箱通知
				/*** 注意檢查 ****/
				Boolean notification = Boolean.parseBoolean(req.getParameter("notification"));
				
				// 修改時間
				Timestamp createdTime = new Timestamp(new java.util.Date().getTime());
				Boolean activated = false;
				Boolean suspended = false;
				Integer externalAcc = 0;
				String externalIdToken = "dwdwd";
				Integer commentReportedNum = 0;
				Integer diaryReportedNum = 0;
				CustomerVO custVO = new CustomerVO();
				custVO.setName(name);
				custVO.setNickname(nickname);
				custVO.setAccount(account);
				custVO.setPassword(password);
				custVO.setEmail(email);
				custVO.setPhone(phone);
				custVO.setProfic(profic);
				custVO.setNotification(notification);
				custVO.setCreatedTime(createdTime);
				custVO.setActivated(activated);
				custVO.setSuspended(suspended);
				custVO.setSuspended(suspended);
				custVO.setExternalAcc(externalAcc);
				custVO.setExternalIdToken(externalIdToken);
//				custVO.setCommentReportedNum(commentReportedNum);
//				custVO.setDiaryReportedNum(diaryReportedNum);
				System.out.println(custVO);				

				if (!errorMsgs.isEmpty() || !passwordErrorMsgs.isEmpty()) {
					session.setAttribute("custVO", custVO); // 含有輸入格式錯誤的empVO物件,也存入session
					RequestDispatcher failureView = req.getRequestDispatcher("/"+REGISTER_PAGE);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 ***************************************/
				// 判斷帳號是否已經存在
				CustomerService custSvc = new CustomerService();
				custVO = custSvc.getCustByAccount(account);
				
				/****[[需增加驗證信箱不可重複]]*****/
				
				if (custVO != null) {
					errorMsgs.put("accountErrorMsgs", "該帳號已存在，請改設其他帳號");

				} else {
					custVO = custSvc.insertCustByCust(name, nickname, account, password, email, phone, notification,
							profic, createdTime, activated, suspended, externalAcc, externalIdToken);
				}

				if (!errorMsgs.isEmpty()) {
					session.setAttribute("custVO", custVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/"+REGISTER_PAGE);

					failureView.forward(req, res);
					return;
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				session.setAttribute("custVO", custVO); // 保存用戶資料到session中
				String checkCode = GenerateLinkUtils.generateCheckCode(custVO);
				RedisUtils.saveCheckCode(String.valueOf(custVO.getIdCustomer()), checkCode);
				String link = GenerateLinkUtils.generateActivateAccountLink(custVO.getIdCustomer(), checkCode);
				EmailUtils.sendAccountActivateEmail(custVO, link);
				RequestDispatcher successView = req.getRequestDispatcher(POST_REGISTER_PAGE); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("overallErrorMsgs", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/"+REGISTER_PAGE);
				failureView.forward(req, res);
			}

		}
		
		
		/** 顯示基本資料：來自customerBasic.jsp的請求 */
		if ("get_cust_for_update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("get_cust_for_update   custVO\t"+req.getSession().getAttribute("custVO"));				

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String account = req.getParameter("account");

				/*************************** 2.開始查詢資料 ****************************************/
				CustomerService custSvc = new CustomerService();
				CustomerVO custVO = custSvc.getCustByAccount(account);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("custVO", custVO);
				RequestDispatcher successView = req.getRequestDispatcher("/"+UPDATE_PAGE);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/"+BASIC_PAGE);
				failureView.forward(req, res);
			}
		}

		
		/* 更新基本資料：來自customerUpdate.jsp的請求 */
		if ("update".equals(action)) {
			CustomerVO custVO=(CustomerVO)req.getSession().getAttribute("custVO");
			System.out.println("custVO\t"+custVO);
			System.out.println("custVO\t"+custVO.getAccount());
			System.out.println("custVO getIdCustomer\t"+custVO.getIdCustomer());
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 姓名
				String name = req.getParameter("name").trim();
				if (vc.validateName(name) == false) {
					for (String msg : vc.nameErrorMsgs) {
						errorMsgs.add(msg);
					}
				}

				// 暱稱
				String nickname = req.getParameter("nickname").trim();
				if (vc.validateNickname(nickname) == false) {
					for (String msg : vc.nicknameErrorMsgs) {
						errorMsgs.add(msg);
					}
				}

				// 帳號 用會員帳號維持身份
				/**** [問題]存在session的話是不是不用在取值 ***/
				String account = req.getParameter("account").trim();
				
				// 會員密碼
				String password = req.getParameter("password").trim();
//				String password2 = req.getParameter("password2").trim();
				if (vc.validatePassword(password) == false) {
					for (String msg : vc.passwordErrorMsgs) {
						errorMsgs.add(msg);
					}
				}

				// 會員信箱
				String email = req.getParameter("email");
				if (vc.validateEmail(email) == false) {
					for (String msg : vc.emailErrorMsgs) {
						errorMsgs.add(msg);
					}
				}

				// 會員電話
				String phone = req.getParameter("phone");
				if (vc.validatePhone(phone) == false) {
					for (String msg: vc.phoneErrorMsgs) {
						errorMsgs.add(msg);
					}
				}

				// 大頭貼
				Part proficPart = req.getPart("profic");
				WriteProfic wp = new WriteProfic();
				byte[] profic = wp.writeProfic(proficPart);
				
				// 信箱通知
				/*** 注意檢查 ****/
				Boolean notification = Boolean.parseBoolean(req.getParameter("notification"));

				// 修改時間
				Timestamp createdTime = new Timestamp(new java.util.Date().getTime());

//				CustomerVO custVO = new CustomerVO();
				custVO.setName(name);
				custVO.setNickname(nickname);
				custVO.setAccount(account);
				custVO.setPassword(password);
				custVO.setEmail(email);
				custVO.setPhone(phone);
				custVO.setProfic(profic);
				custVO.setNotification(notification);
				custVO.setCreatedTime(createdTime);

				if (!errorMsgs.isEmpty()) {
					session.setAttribute("custVO", custVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/"+UPDATE_PAGE);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 ***************************************/
				CustomerService custSvc = new CustomerService();
				Integer cId=custVO.getIdCustomer();
				custVO = custSvc.updateCustByCust(name, nickname, account, password, email, phone, 
						notification, profic, createdTime);
				custVO.setIdCustomer(cId);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				session.setAttribute("custVO", custVO);
				RequestDispatcher successView = req.getRequestDispatcher("/"+BASIC_PAGE); // 新增成功後轉交customerBasic.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/"+UPDATE_PAGE);
				failureView.forward(req, res);
			}

		}
		
		
		/* 登入：來自customerLogin.jsp的請求 */
		if ("login".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs); // 在此設定則下方errorMsgs只能用add方法而不能指派給其他參數

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String account = req.getParameter("account").trim();
				String password = req.getParameter("password").trim();

				// 帳號
				if (account == null | account.length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				// 密碼
				if (password == null | password.length() == 0) {
					errorMsgs.add("請輸入密碼");
				}

				// 有錯誤訊息則傳回原本畫面
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+LOGIN_PAGE);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始查詢資料(與model對接) **********************/
				// 查詢帳號
				CustomerService custSvc = new CustomerService();
				CustomerVO custVO = custSvc.getCustByAccount(account);
System.out.println("login custVO\t"+custVO);
System.out.println("login custVO\t"+custVO.getAccount());
System.out.println("login custVO\t"+custVO.getIdCustomer());

				if (custVO == null || custVO.getActivated() == false) {
					errorMsgs.add("帳號尚未註冊成功，請先行註冊");
				}

				// 密碼比對
				if (custVO != null && !password.equals(custVO.getPassword())) {
					errorMsgs.add("密碼錯誤，請重新輸入");
				}

				// 有錯誤訊息則傳回原本畫面
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+LOGIN_PAGE);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 3.查詢完成，準備轉交(傳給View) **********************/
				// 登入成功

//				try {
//					String location = (String) session.getAttribute("location");
//					if (location != null) {
//						session.removeAttribute("location");
//						res.sendRedirect(location);
//						return;
//					}
//				} catch (Exception ignored) {}
//				
//				res.sendRedirect(LOGIN_PAGE);
				session.setAttribute("custVO", custVO);
				RequestDispatcher successView = req.getRequestDispatcher("/"+BASIC_PAGE);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/"+LOGIN_PAGE);
				failureView.forward(req, res);
			}
		}
		

		/* 忘記密碼：來自customerForget.jsp的請求 */
		if ("forget".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String email = req.getParameter("email").trim(); // customerForget

				// 信箱
				if (vc.validateEmail(email) == false) {
					for (String msg : vc.emailErrorMsgs) {
						errorMsgs.add(msg);
					}
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+FORGET_PAGE);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始查詢資料(與model對接) **********************/
				CustomerService custSvc = new CustomerService();
				CustomerVO custVO = custSvc.getCustByEmail(email);

				// 查詢信箱
				if (custVO == null || custVO.getActivated() == false) {
					errorMsgs.add("該email尚未註冊成功，請先加入會員");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+FORGET_PAGE);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 3.查詢完成，準備轉交(傳給View) **********************/
				
				session.setAttribute("custVO", custVO);
				String checkCode = GenerateLinkUtils.generateCheckCode(custVO);
				RedisUtils.saveCheckCode(custVO.getAccount(), checkCode);
				String link = GenerateLinkUtils.generateResetPasswordLink(custVO.getAccount(), checkCode);
				EmailUtils.sendPasswordResetEmail(custVO, link);
				RequestDispatcher successView = req.getRequestDispatcher("/"+POST_FORGET_PAGE);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/"+FORGET_PAGE);
				failureView.forward(req, res);

			}
		}

		
		/* 重設密碼：來自customerReset.jsp的請求 ResetPasswordServlet */
		if ("reset".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 新密碼驗證
				String newPassword1 = req.getParameter("newPassword1").trim(); // customerReset
				String newPassword2 = req.getParameter("newPassword2").trim(); // customerReset

				if (vc.validatePassword(newPassword1, newPassword2) == false) {
					for (String msg : vc.passwordErrorMsgs) {
						errorMsgs.add(msg);
					}
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+RESET_PAGE);
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始查詢資料(與model對接) **********************/
				// 密碼不得與舊密碼重複
				CustomerService CustSvc = new CustomerService();
				CustomerVO custVO = (CustomerVO) session.getAttribute("custVO");
				if (newPassword1 == custVO.getPassword() || newPassword2 == custVO.getPassword()) {
					errorMsgs.add("不得使用與舊密碼相同之密碼，請重新輸入");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+RESET_PAGE);
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成，準備轉交(傳給View) **********************/
				CustSvc.updatePwdByCust(custVO.getAccount(), newPassword1);
				session.setAttribute("custVO", custVO);
				RequestDispatcher successView2 = req.getRequestDispatcher("/"+RESET_SUCCEED_PAGE);
				successView2.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/"+RESET_PAGE);
				failureView.include(req, res);
			}
		}
		
		
		// 
		
		
		

		
		

		// 第三方登入功能

	}

	
	// 登出
	/*** 登出 畫面待切, session待設置***/
	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 銷毀session
		req.getSession().invalidate();
		
		// 跳轉登入頁面
		res.sendRedirect(LOGIN_PAGE);
	}

}
