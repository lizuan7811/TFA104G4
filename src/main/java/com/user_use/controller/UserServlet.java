package com.user_use.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.pojo.model.UserVO;

public class UserServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private UserService us;
	private PrintWriter pw;
	private UserVO user;

	@Override
	public void init() {
		us = new UserServiceImpl();
//		設定定時器，每過一天，晚上凌晨執行將Redis資料存到SQL中
//		Jedis jRedis=new Jedis("localhost",3679);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			pw = response.getWriter();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String metChoice = request.getParameter("metChoice");
		System.out.println(metChoice);
		if ("readyLoad".equals(metChoice)) {
			user = (UserVO) request.getAttribute("custVO");
			JSONObject jObj = new JSONObject();
			jObj.put("UserVO", user);
			pw.write(jObj.toString());
		} else if (metChoice != null && "clickLike".equals(metChoice)) {
			doLikeClick(request, response);
		} else if (metChoice != null && "commentReport".equals(metChoice)) {
			doCommentReport(request, response);
		} else if (metChoice != null && "diaryReport".equals(metChoice)) {
			doDiaryReport(request, response);
		} else if (metChoice != null && ("addFriend".equals(metChoice) || "agreeFriend".equals(metChoice))) {
			System.out.println("UserServlet doFriendList(addFriend)");
			doaAddFriend(request, response, metChoice);
		} else if (metChoice != null && "friendList".equals(metChoice) || "applyList".equals(metChoice)) {
			System.out.println("UserServlet doFriendList(friendList)");
			doFriendList(request, response, metChoice);
		}else if (metChoice != null && "respResult".equals(metChoice)){
			String respValue=request.getParameter("respValue");
			System.out.println(respValue);
			doDiaryRpChkSend(request,response);
//			serviceDiaryRpCheck(Integer diaryRpID, Boolean drCheck);
		}else if(metChoice!=null && "comment".equals(metChoice)) {
			sendComment(request,response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.doPost(request, response);
	}

	private void doLikeClick(HttpServletRequest request, HttpServletResponse response) {
		us = new UserServiceImpl();
//		DiaryLikeVO拿到dlv物件
//		DiaryLikeVO diaryLikeVO=(DiaryLikeVO)request.getAttribute("diarylikeID");
//		Integer diaryLikeID=diaryLikeVO.getDiaryLikeid();
//		FoodDiaryVO物件
		System.out.println("開始執行");
		Integer curDiaryID = Integer.valueOf(request.getParameter("curDiaryID"));
		Integer curCustID = Integer.valueOf(request.getParameter("curCustID"));
		Integer likeCount = 0;
		System.out.println(curDiaryID + "\t" + curCustID);
		if (curDiaryID == 0 || curCustID == 0 || curDiaryID == null || curCustID == null) {
			System.out.println("沒有得到curDiaryID or curCustID或使用者沒有呼叫此功能!");
		} else {
//				若curDiaryID為空，執行加like，否則執行刪除like。
//				若呼叫此方法的當下，diaryID以及customerID不為空值或非正常值，就將程式正常執行，呼叫點了like的方法。
			likeCount = us.addOrDelLikeService(curDiaryID, curCustID);
		}
		System.out.println(likeCount);

		try {
			pw = response.getWriter();
			System.out.println(likeCount);
			pw.write(likeCount.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private Integer doCommentReport(HttpServletRequest request, HttpServletResponse response) {
		Integer commentID=Integer.valueOf(request.getParameter("commentID"));
		String custNickName=request.getParameter("custNickName");
		Integer repDiaryID=Integer.valueOf(request.getParameter("repDiaryID"));
		String reportReason=request .getParameter("reportReason");
		
		System.out.println(reportReason);
		us.serviceCommReport(commentID,repDiaryID, custNickName, reportReason);
		
		
		return 0;
	}
	private void sendComment(HttpServletRequest request, HttpServletResponse response) {
		Integer commentID = 0;
		String diaryID = request.getParameter("diaryID");
		String custNickName = request.getParameter("custNickName");
		String reportReason = request.getParameter("reportReason");
		commentID = us.serviceComment(diaryID, custNickName, reportReason);
		pw.write(new JSONObject().put("commentID",commentID).toString());
		return;
	}

	private Integer doDiaryReport(HttpServletRequest request, HttpServletResponse response) {
		Integer successNum = 0;
		String diaryID = request.getParameter("diaryID");
		String custID = request.getParameter("custID");
		String reportReason = request.getParameter("reportReason");
		successNum = us.serviceDiaryReport(diaryID, custID, reportReason);
		return successNum;
	}

	private Integer doaAddFriend(HttpServletRequest request, HttpServletResponse response, String metChoice) {
		user = (UserVO) request.getAttribute("custVO");

		String custID = request.getParameter("custID");
		String myFriendID = request.getParameter("myFriendID");
//		後端判斷傳入同意好友訊息的使用者ID是否與目前登入的使用者ID相同，避免前端安全性不足，再次做判斷，增加安全性。	
//		if(user.getIdCustomer()==Integer.valueOf(custID)) {
//			us.serviceAddFriend(metChoice,Integer.parseInt(custID),Integer.parseInt(myFriendID));
//		}
		us.serviceAddFriend(metChoice, Integer.parseInt(custID), Integer.parseInt(myFriendID));
		return 0;
	}

	private void doFriendList(HttpServletRequest request, HttpServletResponse response, String metChoice) {
		Integer custID = 0;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserVO user = (UserVO) request.getAttribute("UserVO");
		JSONArray ansJson = new JSONArray();
		if (user != null) {
			custID = user.getIdCustomer();
		} else {
			custID = Integer.valueOf(request.getParameter("custID"));
		}
		if ("friendList".equals(metChoice) || "applyList".equals(metChoice)) {
			ansJson = us.serviceAboutFriend(metChoice, custID);
		}
		pw.write(ansJson.toString());
	}
	
	private void doDiaryRpChkSend(HttpServletRequest request, HttpServletResponse response)
	{
		Integer diaryRpID=Integer.valueOf(request.getParameter("diaryReportID"));
		Integer diaryID=Integer.valueOf(request.getParameter("diaryID"));
		Boolean drCheck=Boolean.valueOf(request.getParameter("respValue"));
		us.serviceDiaryRpCheck(diaryID,diaryRpID,drCheck);
	}
}
