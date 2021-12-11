package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
@WebServlet("/error/SendError")
public class SendError extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw=response.getWriter();
			pw.write(getErrorMsg().toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		this.doPost(request, response);
	}
	
	private JSONObject getErrorMsg() {
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("錯誤訊息key","錯誤訊息value");
		jsonObj.put("錯誤訊息key1","錯誤訊息value1");
		jsonObj.put("錯誤訊息key2","錯誤訊息value2");
		jsonObj.put("錯誤訊息key3","錯誤訊息value3");
		jsonObj.put("錯誤訊息key4","錯誤訊息value4");
		return jsonObj;
	}
}
