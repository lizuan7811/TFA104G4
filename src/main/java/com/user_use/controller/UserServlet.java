package com.user_use.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.pojo.model.UserVO;

@WebServlet("/usermethod.html")
public class UserServlet extends HttpServlet{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private UserService us;
	private PrintWriter pw;
	@Override
	public void init()
	{
		us=new UserServiceImpl();
//		設定定時器，每過一天，晚上凌晨執行將Redis資料存到SQL中
//		Jedis jRedis=new Jedis("localhost",3679);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String metChoice=request.getParameter("metChoice");
		System.out.println(metChoice);
		if(metChoice!=null && "clickLike".equals(metChoice))
		{
				doLikeClick(request,response);
		}else if(metChoice!=null && "commentReport".equals(metChoice))
		{
			doCommentReport(request,response);
		}else if(metChoice!=null && ("addFriend".equals(metChoice) ||"agreeFriend".equals(metChoice)))
		{
			System.out.println("UserServlet doFriendList(addFriend)");
			doaAddFriend(request,response,metChoice);
		}else if(metChoice!=null && ("friendList").equals(metChoice))
		{
			System.out.println("UserServlet doFriendList(friendList)");
			doFriendList(request,response,metChoice);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request,response);
	}

	private void doLikeClick(HttpServletRequest request,HttpServletResponse response)
	{
		us=new UserServiceImpl();
//		DiaryLikeVO拿到dlv物件
//		DiaryLikeVO diaryLikeVO=(DiaryLikeVO)request.getAttribute("diarylikeID");
//		Integer diaryLikeID=diaryLikeVO.getDiaryLikeid();
//		FoodDiaryVO物件
		System.out.println("開始執行");
		Integer curDiaryID=Integer.valueOf(request.getParameter("curDiaryID"));
//		UserVO
		Integer curCustID=Integer.valueOf(request.getParameter("curCustID"));
		Integer likeCount=0;
		System.out.println(curDiaryID+"\t"+curCustID);
			if(curDiaryID==0 || curCustID==0 || curDiaryID==null ||curCustID==null)
			{
				System.out.println("沒有得到curDiaryID or curCustID或使用者沒有呼叫此功能!");
			}else {
//				若curDiaryID為空，執行加like，否則執行刪除like。
//				若呼叫此方法的當下，diaryID以及customerID不為空值或非正常值，就將程式正常執行，呼叫點了like的方法。
				likeCount=us.addOrDelLikeService(curDiaryID,curCustID);
			}
			System.out.println(likeCount);

			try {
				pw=response.getWriter();
				System.out.println(likeCount);
				pw.write(likeCount.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	private Integer doCommentReport(HttpServletRequest request,HttpServletResponse response)
	{
//		custID、reportReason reportResult
		Integer successNum=0;
		String diaryID=request.getParameter("diaryID");
		String custID=request.getParameter("custID");
		String reportReason=request.getParameter("reportReason");
		System.out.println(reportReason);
		successNum=us.serviceCommReport(diaryID, custID, reportReason);
		return successNum;
	}

	private Integer doaAddFriend(HttpServletRequest request,HttpServletResponse response,String metChoice) 
	{
		String custID=request.getParameter("custID");
		String myFriendID=request.getParameter("myFriendID");
		us.serviceAddFriend(metChoice,Integer.parseInt(custID),Integer.parseInt(myFriendID));
		
		return 0;
	}
	
	private void doFriendList(HttpServletRequest request,HttpServletResponse response,String metChoice) 
	{
		Integer custID=0;
		try {
			pw=response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserVO user=(UserVO)request.getAttribute("UserVO");
		JSONArray ansJson=new JSONArray();
		if(user!=null)
		{
			custID=user.getIdCustomer();
		}
		if("friendList".equals(metChoice))
		{
//			metChoice=friendList
			ansJson=us.serviceAboutFriend(metChoice, 1);
		}else if("applyList".equals(metChoice))
		{
//			metChoice=applyList
			ansJson=us.serviceAboutFriend(metChoice, custID);
		}
		pw.write(ansJson.toString());
	}

//	private Integer doApplyFriList(HttpServletRequest request,HttpServletResponse response,String metChoice)
//	{
//		
//	}
	
}
