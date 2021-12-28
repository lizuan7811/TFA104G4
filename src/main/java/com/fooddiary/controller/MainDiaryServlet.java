package com.fooddiary.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.diarytype.model.DiaryTypeService;
import com.diarytype.model.DiaryTypeVO;
import com.fooddiary.model.FoodDiaryService;
import com.fooddiary.model.FoodDiaryVO;

@WebServlet("/MainDiaryServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//設定Servlet可以處理圖片上傳 Multipart請求
public class MainDiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);

		}

		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			req.setCharacterEncoding("UTF-8");
			res.setContentType("text/html;charset=UTF-8");
			String action = req.getParameter("action");
			
			if("ALL_DIARY".equals(action)) {
				try {
					FoodDiaryService diarySvc = new FoodDiaryService();
				    JSONArray jArr = diarySvc.searchAll();
					res.getWriter().print(jArr.toString());
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
				
			if("GET_DIARY".equals(action)) {
				Integer diaryid = Integer.valueOf(req.getParameter("diaryID"));
				Base64.Encoder beCoder=Base64.getEncoder();

				try {
					FoodDiaryService diarySvc = new FoodDiaryService();
					FoodDiaryVO diaryVO = diarySvc.getOneDiary(diaryid);
					DiaryTypeService typeSvc = new DiaryTypeService();
					Map map=typeSvc.getMap();
					req.setAttribute("diaryid", diaryid);
					JSONObject obj = new JSONObject();
					obj.put("diaryid", diaryVO.getDiaryID());
					obj.put("custid", diaryVO.getCustID());
					obj.put("subject", diaryVO.getSubject());
					obj.put("text", diaryVO.getText());
					obj.put("createdtime", diaryVO.getCreatedTime());
					obj.put("diarytype", diaryVO.getDiaryType());
					obj.put("Photo_video_1", beCoder.encodeToString(diaryVO.getPhoto_video_1()));
					obj.put("Photo_video_2", beCoder.encodeToString(diaryVO.getPhoto_video_2()));
					obj.put("Photo_video_3", beCoder.encodeToString(diaryVO.getPhoto_video_3()));
					obj.put("diarytype", map.get(diaryVO.getDiaryType()));

					obj.put("msg", "success");
					
					
					res.getWriter().println(obj.toString());
					
					
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//				

			}
		}	
}
