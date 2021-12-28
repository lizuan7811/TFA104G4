package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
@WebServlet("/GetPhotoServlet")
public class GetPhotoServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String metChoice=request.getParameter("metChoice");
			System.out.println(metChoice);
			PrintWriter pw=response.getWriter();
			
			getIngrePhotoInJSON gipij=new getIngrePhotoInJSON();

			JSONObject jObj=gipij.getIngrePhotos();
			pw.write(jObj.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request, response);
	}
	
	
}
