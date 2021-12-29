package com.user_use.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.customer.model.CustomerVO;

public class TransToOtherSiteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String realPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		try {
			String transToSite = request.getParameter("transToSite");
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			CustomerVO custVO = (CustomerVO) request.getSession().getAttribute("custVO");
		
			if (custVO == null) {
				System.out.println(realPath + "/customer/customerLogin.jsp");
				pw.write(realPath + "/customer/customerLogin.jsp");
				return;
			}
			JSONObject jObj = new JSONObject();
			JSONArray jArr=new JSONArray();
			jObj.put("custID", custVO.getIdCustomer());
			jObj.put("account", custVO.getAccount());
			jArr.put(jObj.toString());
			System.out.println("aboutUs".equals(transToSite));
			if ("aboutUs".equals(transToSite)) {
//				pw.write(realPath + "/front_end/GP4_html_cf/group4_home.html");
//				System.out.println(realPath + "/front_end/GP4_html_cf/group4_home.html");
				jArr.put(realPath + "/front_end/GP4_html_cf/group4_home.html");
				pw.write(jArr.toString());
//				pw.write(jObj.toString());
				// System.out.println(realPath+"/front_end/GP4_html_cf/group4_home.html");
				return;
			} else if ("shopCity".equals(transToSite)) {
//				System.out.println(realPath + "/front_end/cart/shop.jsp");
//				pw.write(realPath + "/front_end/cart/shop.jsp");
				jArr.put(realPath +  "/front_end/cart/shop.jsp");
				pw.write(jArr.toString());
				return;
			} else if ("eatLife".equals(transToSite)) {
//				System.out.println(realPath + "/front_end/GP4_html_cf/group4_diary.html");
//				pw.write(realPath + "/front_end/GP4_html_cf/group4_diary.html");
				jArr.put(realPath + "/front_end/GP4_html_cf/group4_diary.html");
				pw.write(jArr.toString());
				return;
			} else if ("custLogin".equals(transToSite)) {
//				System.out.println(realPath + "/customer/customerLogin.jsp");
//				pw.write(realPath + "/customer/customerLogin.jsp");
				jArr.put(realPath +"/customer/customerLogin.jsp");
				pw.write(jArr.toString());
				return;
			} else if ("historyOrder".equals(transToSite)) {
//				System.out.println(realPath + "/order_history.html");
//				pw.write(realPath + "/order_history.html");
				jArr.put(realPath + "/customer/order_history.html");
				pw.write(jArr.toString());
				return;
			}else if("eatDiary".equals(transToSite)){
				System.out.println(transToSite);
				jArr.put(realPath + "/front_end/GP4_html_cf/group4_diary.html");
				System.out.println(jArr.toString());
				pw.write(jArr.toString());
				
			}else if("myLife".equals(transToSite)){
				jArr.put(realPath + "/front_end/fooddiary/diaryListOne.jsp");
				System.out.println(jArr.toString());
				pw.write(jArr.toString());
				
			}else if("lookOrder".equals(transToSite)){
				jArr.put(realPath + "/customer/order_history.html");
				System.out.println(jArr.toString());
				pw.write(jArr.toString());
			}else if("toShop".equals(transToSite)){
				jArr.put(realPath +"/front_end/cart/shop.jsp");
				System.out.println(jArr.toString());
				pw.write(jArr.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.doPost(request, response);
	}
}
