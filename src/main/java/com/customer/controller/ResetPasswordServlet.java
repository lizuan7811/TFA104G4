package com.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.customer.model.*;

public class ResetPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String BASE_PATH = "/customer";
	private static final String RESET_PAGE = BASE_PATH + "/customerReset.jsp";
	private static final String RESET_FAIL_PAGE = BASE_PATH + "/customerResetFail.jsp";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");

		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		
		String account = req.getParameter("account");
		String urlCheckCode = req.getParameter("checkCode");
		String redisCheckCode = RedisUtils.getCheckCode(account);
		CustomerService CustSvc = new CustomerService();
		CustomerVO custVO = CustSvc.getCustByAccount(account);
		session.setAttribute("custVO", custVO);
		
		if (custVO == null) {
			out.print("該帳號無效，請重新申請");
		} 
		
		if (urlCheckCode != null && redisCheckCode != null) {
			boolean isValid = urlCheckCode.equals(redisCheckCode);
			if (isValid) {
				req.getRequestDispatcher(RESET_PAGE).forward(req, res);
			}
		} else if (redisCheckCode == null) {
				req.getRequestDispatcher(RESET_FAIL_PAGE).forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
