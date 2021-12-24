package com.user_use.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransToOtherSiteServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		String transToSite=request.getParameter("transToSite");
		System.out.println(request.getScheme());
		System.out.println(request.getServerName());
		System.out.println(request.getServerPort());
		System.out.println(request.getServletPath());
		System.out.println(transToSite);
		String realPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw=response.getWriter();
			if("aboutUs".equals(transToSite)) {
				System.out.println(transToSite);
				return;
			}else if("shopCity".equals(transToSite)) {
				System.out.println(transToSite);
//				request.getRequestDispatcher(request.getContextPath()+"/front_end/cart/shop.jsp").forward(request, response);
//				response.sendRedirect(request.getContextPath()+"/front_end/cart/shop.jsp");
				pw.write(realPath+"/front_end/cart/shop.jsp");
				return;
			}else if("eatLife".equals(transToSite)) {
				System.out.println(transToSite);
				pw.write(realPath+"/front_end/fooddiary/diaryListOne.jsp");
				return;
			}else if("custLogin".equals(transToSite)) {
				System.out.println(transToSite);
				pw.write(realPath+"/customer/customerLogin.jsp");
				return;
			}else if("historyOrder".equals(transToSite)) {
				System.out.println(transToSite);
				pw.write(realPath+"/order_history.html");
				return;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request, response);
	}
}
