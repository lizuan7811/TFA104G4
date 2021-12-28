package com.fooddiary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fooddiary.model.FoodDiaryService;


@WebServlet("/MainDiaryTypeServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//設定Servlet可以處理圖片上傳 Multipart請求
public class MainDiaryTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Start");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		String Type = req.getParameter("diaryType");
		Integer diaryType = Integer.parseInt(Type);// 日誌分類
		System.out.println(diaryType);
	    FoodDiaryService diarySvc = new FoodDiaryService();
//	    JSONObject obj = new JSONObject().put("diaryType", diarySvc.searchType(diaryType) );
	    
//	    JSONArray jArr = diarySvc.searchType(diaryType) ;
//	   System.out.println(diarySvc.searchType(diaryType));
		res.getWriter().print(new JSONObject().put("diaryType", diarySvc.searchType(diaryType)).toString());
	}

}
