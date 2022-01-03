package com.user_use.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.pojo.model.AdminVO;
import com.static_file.model.FinalStaticFile;
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter pw=null;
	private JSONObject jObj=null;
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse resp)
	{
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String metChoice=req.getParameter("metChoice");
		String regexStr="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,30}$";
		jObj=new JSONObject();
		if("login".equals(metChoice))
		{
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			System.out.println("username+\" \"+password"+username+" "+password);
			System.out.println("username.equals(String.valueOf(Integer.MIN_VALUE))\t"+username.equals(String.valueOf(Integer.MIN_VALUE)));
			try {
				

				pw = resp.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(username==null || "".equals(username)||password==null || "".equals(password)) {
				if(username==null || "".equals(username)) {
					jObj.put("usResp","未輸入管理者帳號!");
				}
				else {
					jObj.put("usResp","");
				}
				
				if(password==null || "".equals(password)) {
					jObj.put("psResp","未輸入管理者密碼!");
				}
				else {
					jObj.put("psResp","");
				}
				pw.write(jObj.toString());
				return;
			}else if(username!=null && password!=null && !"".equals(username) && !"".equals(password))
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
					HttpSession sess=req.getSession();
					sess.setAttribute(FinalStaticFile.ADMIN_SESSION,admin);
					System.out.println("ADMIN_SESSION="+req.getAttribute(FinalStaticFile.ADMIN_SESSION));
//					resp.sendRedirect(req.getContextPath()+"/adminCtrlCustPage.html");
					System.out.println(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
					+ req.getContextPath());
					jObj.put("address",req.getContextPath()+"/finalOrder.html");
					pw.write(jObj.toString());
					return ;
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
