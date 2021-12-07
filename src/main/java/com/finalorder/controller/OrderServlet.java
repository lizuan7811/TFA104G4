package com.finalorder.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class OrderServlet extends HttpServlet{
	private FinalOrderService foService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		foService=new FinalOrderServiceimpl();
		String metChoice=request.getParameter("metChoice");
		JSONObject orderObj=new JSONObject(request.getParameter("orderObj"));
		Integer idFinalOrder=Integer.valueOf(request.getParameter("idFinalOrder"));
		Integer executeNum=0;
		if("buildOrder".equals(metChoice))
		{
			executeNum=foService.buildOrderService(orderObj);
		}
		else if(("deleteOrder").equals(metChoice))
		{
			foService.deleteOrderService(idFinalOrder);
		}
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request,response);
	}
	
	
}
