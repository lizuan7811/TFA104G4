package com.comment.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.comment.model.CommentService;
import com.comment.model.CommentVO;




@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CommentServlet Start");
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String metChoice = req.getParameter("metChoice");
//		System.out.println("send= "+ metChoice);
		
		if("comment".equals(metChoice)) {
		
			Integer diaryid = Integer.valueOf(req.getParameter("diaryid"));
			String nickname = req.getParameter("nickname");
			String commenttext = req.getParameter("commenttext");
			
			CommentService comSvc=new CommentService();
			CommentVO comVO=comSvc.add_comment(diaryid, nickname, commenttext);	
			JSONObject obj = new JSONObject();
	        res.getWriter().write(obj.put("comVO",comVO).toString());
		}	
		
		if("ALL_COMMENT".equals(metChoice)) {
			Integer diaryid = Integer.valueOf(req.getParameter("diaryid"));
			CommentService comSvc = new CommentService();
		
//			System.out.println("comSvc="+comSvc);
		    JSONArray json_array = comSvc.search_comment(diaryid);
			res.getWriter().print(json_array.toString());
			
//			CommentService comSvc=new CommentService();
//			CommentVO comVO=comSvc.search_comment(diaryid);
//			System.out.println("取得留言ID="+comVO.getCommentID());
//			req.setAttribute("diaryid", diaryid);
//			
//			JSONArray json_array = new JSONArray();
//			JSONObject obj = new JSONObject();
//			obj.put("diaryid", comVO.getDiaryID());
//			obj.put("commentid", comVO.getCommentID());
//			obj.put("nickname", comVO.getNickName());
//			obj.put("createdtime", comVO.getCreatedTime());
//			obj.put("commenttext", comVO.getCommentText());
//			json_array.put(obj.toMap());
//			res.getWriter().print(json_array.toString());
//			);

		}	
		
	}
	

}
