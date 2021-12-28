package com.user_use.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.mysql.cj.Session;
import com.static_file.model.FinalStaticFile;

//管理者呼叫方法讀取使用者列表
public class UserListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService usv;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Session sess = (Session) request.getAttribute(FinalStaticFile.ADMIN_SESSION);
		System.out.println("session:" + sess);
		if ("getUserList".equals(request.getParameter("metChoice")) && sess != null) {
			getUsList(request, response);
		} else if ("getDiaryComms".equals(request.getParameter("metChoice"))) {
			getDiaryComms(request, response);
		} else if ("DiaryReported".equals(request.getParameter("metChoice"))) {
			getDiaryReported(request, response);
		} else if ("DiaryResponse".equals(request.getParameter("metChoice"))) {
			setDiaryResponse(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.doPost(request, response);
	}

	private void getUsList(HttpServletRequest request, HttpServletResponse response) {
		UserService usv = new UserServiceImpl();
//		Integer.parseInt(request.getParameter("usPos"))
		JSONArray jsonArr = usv.selectUser(String.valueOf(Integer.MIN_VALUE));
		if (jsonArr == null) {
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

	private void getDiaryComms(HttpServletRequest request, HttpServletResponse response) {
		try {
			usv = new UserServiceImpl();
			usv.serviceGetDiaryComms();
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(usv.serviceGetDiaryComms().toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getDiaryReported(HttpServletRequest request, HttpServletResponse response) {

		try {
			usv = new UserServiceImpl();
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(usv.serviceGetDiaryReported().toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setDiaryResponse(HttpServletRequest request, HttpServletResponse response) {
		usv = new UserServiceImpl();
		Integer executeNum = 0;
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			Integer diaryID = Integer.valueOf(request.getParameter("diaryID"));
			Integer diaryRpID = Integer.valueOf(request.getParameter("diaryReportID"));
			Boolean drCheck = Boolean.valueOf(request.getParameter("reportResult"));
			PrintWriter pw = response.getWriter();
			pw.write(usv.serviceDiaryRpCheck(diaryID,diaryRpID, drCheck));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
