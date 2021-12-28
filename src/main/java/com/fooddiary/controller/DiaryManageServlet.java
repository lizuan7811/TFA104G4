package com.fooddiary.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooddiary.model.FoodDiaryService;
import com.fooddiary.model.FoodDiaryVO;


@WebServlet("/DiaryManageServlet")
public class DiaryManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println("Servlet Start");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("SEARCH".equals(action)) { // 來自back_end_diary_manage的請求
			
			/***************************2.開始查詢資料*****************************************/
			Integer diaryID = Integer.parseInt(req.getParameter("diaryID"));
			FoodDiaryService diarySvc = new FoodDiaryService();
			FoodDiaryVO diaryVO = diarySvc.getOneDiary(diaryID);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("diaryVO", diaryVO); // 資料庫取出的diaryVO物件,存入req
			String url = "/front_end/fooddiary/diary_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		
		}
		
		// 刪除文章
		if ("DELLET".equals(action)) {
			
				/***************************1.接收請求參數***************************************/
				Integer diaryid = new Integer(req.getParameter("diaryID"));
				
				/***************************2.開始刪除資料***************************************/
				FoodDiaryService diarySvc = new FoodDiaryService();
				diarySvc.deleteDiary(diaryid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/fooddiary/back_end_diary_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
		
		}
	}
	
}
