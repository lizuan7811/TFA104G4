package com.fooddiary.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fooddiary.model.FoodDiaryService;
import com.fooddiary.model.FoodDiaryVO;


@WebServlet("/FoodDiaryServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//設定Servlet可以處理圖片上傳 Multipart請求

public class FoodDiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Start");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 新增個人文章

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String custId = req.getParameter("custID").trim();
				Integer custID = Integer.parseInt(custId);// 會員編號

				String subject = req.getParameter("subject").trim();
				if (subject == null || subject.trim().length() == 0) {
					errorMsgs.add("文章標題請勿空白");
				}

				String text = req.getParameter("text").trim();
				if (text == null) {
					errorMsgs.add("文章內容請勿空白");
				}

				// 新增圖片1
				Part photo1 = req.getPart("pic1");
				byte[] photo_video_1 = null;
				if (photo1 != null) {
					photo_video_1 = getPhoto(photo1);
				}

				// 新增圖片2
				Part photo2 = req.getPart("pic2");
				byte[] photo_video_2 = null;
				if (photo2 != null) {
					photo_video_2 = getPhoto(photo2);
				}

				// 新增圖片3
				Part photo3 = req.getPart("pic3");
				byte[] photo_video_3 = null;
				if (photo3 != null) {
					photo_video_3 = getPhoto(photo3);
				}

				// 文章是否公開 狀態設定 使用radio單選
				Boolean status = Boolean.parseBoolean(req.getParameter("status"));

				Integer diaryType = Integer.parseInt(req.getParameter("diary_type"));
				if (diaryType == 0) {
					errorMsgs.add("請選擇文章類別");
				}

				Timestamp createdTime = Timestamp.valueOf(LocalDateTime.now());

				FoodDiaryVO diaryVO = new FoodDiaryVO();
				diaryVO.setCustID(custID);
				diaryVO.setSubject(subject);
				diaryVO.setText(text);
				diaryVO.setPhoto_video_1(photo_video_1);
				diaryVO.setPhoto_video_2(photo_video_2);
				diaryVO.setPhoto_video_3(photo_video_3);
				diaryVO.setStatus(status);
				diaryVO.setDiaryType(diaryType);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("diaryVO", diaryVO); // 含有輸入格式錯誤的diaryVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/fooddiary/add_fooddiary.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 *****************************************/
				FoodDiaryService diarySvc = new FoodDiaryService();
				diaryVO = diarySvc.add_diary(custID, subject, text, photo_video_1, photo_video_2, photo_video_3, status,
						diaryType, createdTime);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/fooddiary/diaryListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
			}
			
		}
		
		// 刪除文章
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer diaryid = new Integer(req.getParameter("diaryid"));
				
				/***************************2.開始刪除資料***************************************/
				FoodDiaryService diarySvc = new FoodDiaryService();
				diarySvc.deleteDiary(diaryid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/fooddiary/diaryListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/fooddiary/diaryListOne.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 修改文章
		if ("getOne_For_Update".equals(action)) { // 來自diaryListOne.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer diaryID = new Integer(req.getParameter("diaryid"));
				
				/***************************2.開始查詢資料****************************************/
				FoodDiaryService diarySvc = new FoodDiaryService();
				FoodDiaryVO diaryVO = diarySvc.getOneDiary(diaryID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("diaryVO", diaryVO);         // 資料庫取出的diaryVO物件,存入req
				String url = "/front_end/fooddiary/diary_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/fooddiary/diaryListOne.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 修改文章
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				Integer diaryID = Integer.parseInt(req.getParameter("diaryid"));
				
				String custId = req.getParameter("custID").trim();
				Integer custID = Integer.parseInt(custId);// 會員編號

				String subject = req.getParameter("subject").trim();
				if (subject == null || subject.trim().length() == 0) {
					errorMsgs.add("文章標題請勿空白");
				}

				String text = req.getParameter("text").trim();
				if (text == null) {
					errorMsgs.add("文章內容請勿空白");
				}

				// 新增圖片1
				Part photo1 = req.getPart("pic1");
				byte[] photo_video_1 = null;
				if (photo1 != null) {
					photo_video_1 = getPhoto(photo1);
				}

				// 新增圖片2
				Part photo2 = req.getPart("pic2");
				byte[] photo_video_2 = null;
				if (photo2 != null) {
					photo_video_2 = getPhoto(photo2);
				}

				// 新增圖片3
				Part photo3 = req.getPart("pic3");
				byte[] photo_video_3 = null;
				if (photo3 != null) {
					photo_video_3 = getPhoto(photo3);
				}

				// 文章是否公開 狀態設定 使用radio單選
				Boolean status = Boolean.parseBoolean(req.getParameter("status"));

				Integer diaryType = Integer.parseInt(req.getParameter("diary_type"));
				if (diaryType == 0) {
					errorMsgs.add("請選擇文章類別");
				}

				Timestamp createdTime = Timestamp.valueOf(LocalDateTime.now());

				FoodDiaryVO diaryVO = new FoodDiaryVO();
				diaryVO.setDiaryID(diaryID);
				diaryVO.setCustID(custID);
				diaryVO.setSubject(subject);
				diaryVO.setText(text);
				diaryVO.setPhoto_video_1(photo_video_1);
				diaryVO.setPhoto_video_2(photo_video_2);
				diaryVO.setPhoto_video_3(photo_video_3);
				diaryVO.setStatus(status);
				diaryVO.setDiaryType(diaryType);	

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("diaryVO", diaryVO); // 含有輸入格式錯誤的diaryVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/fooddiary/add_fooddiary.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				FoodDiaryService diarySvc = new FoodDiaryService();
				diaryVO = diarySvc.update_diary(diaryID, custID, subject, text, photo_video_1, photo_video_2, photo_video_3, status, diaryType, createdTime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("diaryVO", diaryVO); // 資料庫update成功後,正確的的diaryVO物件,存入req
				String url = "/front_end/fooddiary/diaryListOne.jsp";
				res.sendRedirect(req.getContextPath() + url);
				return;
//				String url = "/front_end/fooddiary/diaryListOne.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/fooddiary/add_fooddiary.jsp");
				failureView.forward(req, res);
			}			
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自diaryListOne的請求
				
				/***************************2.開始查詢資料*****************************************/
				Integer diaryID = Integer.parseInt(req.getParameter("diaryid"));
				FoodDiaryService diarySvc = new FoodDiaryService();
				FoodDiaryVO diaryVO = diarySvc.getOneDiary(diaryID);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("diaryVO", diaryVO); // 資料庫取出的diaryVO物件,存入req
				String url = "/front_end/fooddiary/diary_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			
		}
	}

	public static byte[] getPhoto(Part part) throws IOException {
		byte[] buffer = new byte[1024];
		InputStream in = part.getInputStream();
		buffer = new byte[in.available()];
		in.read(buffer);
		in.close();
		return buffer;
	}

}
