package you.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import you.contents.*;
import you.pojo.*;
import you.service.*;
//@WebServlet("/adminlogin")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse resp)
	{

		ServiceDao adm=new ServiceDaoImpl();
		String username=req.getParameter("main_form_userName");
		String password=req.getParameter("main_form_Password");
		System.out.println(username+" "+password);
		AdminVO admin ;
		if((admin=adm.login(username,password))!=null)
		{
			try {
				HttpSession sess=req.getSession();
				req.getSession().setAttribute(FinalStaticFile.ADMIN_SESSION,admin);
				System.out.println("ADMIN_SESSION="+req.getAttribute(FinalStaticFile.ADMIN_SESSION));
				resp.sendRedirect("adminCtrlCustPage.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				req.getRequestDispatcher("adminLogin.html").forward(req, resp);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
	{
		this.doPost(req, resp);
	}

}
