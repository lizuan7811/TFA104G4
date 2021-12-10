package com.user_use.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.mysql.cj.Session;
import com.static_file.model.FinalStaticFile;



//管理者呼叫方法讀取使用者列表
public class UserListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		Session sess=(Session) request.getAttribute(FinalStaticFile.ADMIN_SESSION);
		System.out.println("session:"+sess);
		if("getUserList".equals(request.getParameter("metChoice")))
		{
			getUsList(request,response);
		}
	}
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request, response);
	}

	private void getUsList(HttpServletRequest request,HttpServletResponse response)
	{
		UserService usv=new UserServiceImpl();
//		Integer.parseInt(request.getParameter("usPos"))
		JSONArray jsonArr=usv.selectUser(String.valueOf(Integer.MIN_VALUE));
		if(jsonArr==null)
		{
			return;
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonArr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
