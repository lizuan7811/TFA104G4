package com.user_use.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pojo.model.AdminVO;
import com.static_file.model.FinalStaticFile;
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse resp)
	{

		String metChoi=req.getParameter("sendAccount");
		
		if("login".equals(metChoi))
		{
			String username=req.getParameter("main_form_userName");
			String password=req.getParameter("main_form_Password");
			System.out.println("username+\" \"+password"+username+" "+password);
			System.out.println("username.equals(String.valueOf(Integer.MIN_VALUE))\t"+username.equals(String.valueOf(Integer.MIN_VALUE)));
			if(username!=null && password!=null && !"".equals(username) && !"".equals(password))
			{
				adminLogin(req,resp,username,password);
			}
		}
	}

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
	{
		this.doPost(req, resp);
	}

	private void adminLogin(HttpServletRequest req,HttpServletResponse resp,String username,String password)
	{
			AdminVO admin ;
			ServiceDao adm=new ServiceDaoImpl();
			if((admin=adm.login(username,password))!=null)
			{
				try {
					HttpSession sess=req.getSession();
					sess.setAttribute(FinalStaticFile.ADMIN_SESSION,admin);
					System.out.println("ADMIN_SESSION="+req.getAttribute(FinalStaticFile.ADMIN_SESSION));
//					resp.sendRedirect(req.getContextPath()+"/adminCtrlCustPage.html");
					req.getRequestDispatcher("adminCtrlCustPage.html").forward(req, resp);
//					req.getServerName()+":"+req.getLocalPort()+req.getContextPath()+"/adminCtrlCustPage.html"
					return ;
				} catch (IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try {
					resp.sendRedirect(req.getContextPath()+"/adminLogin.html");
//					req.getRequestDispatcher("adminLogin.html").forward(req, resp);
					return ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			System.out.println("尚未輸入帳號密碼");
		}


}
