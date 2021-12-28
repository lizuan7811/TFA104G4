package com.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.customer.model.*;

public class ActivateAccountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static final String BASE_PATH = "/customer";
	private static final String REGISTER_SUCCEED_PAGE = BASE_PATH + "/customerRegisterSucceed.jsp";
	private static final String REGISTER_FAIL_PAGE = BASE_PATH + "/customerRegisterFail.jsp";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		
		String idStr = req.getParameter("id");
		int idCustomer = -1;
		try {
			idCustomer = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.println("該帳號無效，請重新申請");
		}
		
		CustomerService custSvc = new CustomerService();
		CustomerVO custVO = custSvc.getCustById(idCustomer);
		
		// 檢查驗證碼是否和註冊時一致，以決定是否啟動該帳號
		String urlCheckCode = req.getParameter("checkCode");
		String redisCheckCode = RedisUtils.getCheckCode(String.valueOf(idCustomer));
		// String url; 法二
		
		// 更新Activate的欄位值，啟動帳號
		if (urlCheckCode != null && redisCheckCode != null) {
			boolean isActivated = urlCheckCode.equals(redisCheckCode);
			if (isActivated) {
				custSvc.updateActiByAdmin(idCustomer); // 更新啟動狀態
				session.setAttribute("custVO", custVO); // 將用戶資料存進session
				req.getRequestDispatcher(REGISTER_SUCCEED_PAGE).forward(req, res);
			}
		} else if (redisCheckCode == null) {
				req.getRequestDispatcher(REGISTER_FAIL_PAGE).forward(req, res);
		}
		
	}
}
