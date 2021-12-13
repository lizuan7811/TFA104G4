package com.finalorder.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.cart.controller.Ingre;
import com.cart.controller.OrderToJSON;
import com.finalorder.model.FinalOrderVO;
import com.google.gson.Gson;
import com.pojo.model.UserVO;

public class OrderServlet extends HttpServlet {
	private FinalOrderService foService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		foService = new FinalOrderServiceimpl();
		String metChoice = request.getParameter("metChoice");
		Gson gson=new Gson();
//		Integer idFinalOrder=Integer.valueOf(request.getParameter("idFinalOrder"));
		Integer executeNum = 0;
		
		if ("buildOrder".equals(metChoice)) {
			System.out.println("buildOrders");
			String tempStr=request.getParameter("orderObj");
			JSONObject tempStrJson=new JSONObject(tempStr);
			if("".equals(tempStrJson.get("recipient"))||"".equals(tempStrJson.get("recipientAddress"))||"".equals(tempStrJson.get("footnote"))){
				try {
					response.getWriter().write((new JSONObject().put("error", "資料輸入不完全，無法產生訂單!")).toString());
				} catch (JSONException | IOException e) {
					e.printStackTrace();
				}
				return;
			}
			HttpSession session = request.getSession();
			Vector<Ingre> cart = (Vector<Ingre>) session.getAttribute("cart");
			String action = request.getParameter("action");
			
			BigDecimal total = new BigDecimal(0);
			for (int i = 0; i < cart.size(); i++) {
				Ingre order = cart.get(i);
				BigDecimal price = order.getPrice();
				int quantity = order.getQuantity();
				total = total.add(price.multiply(new BigDecimal(quantity)));
			}
			String amount = String.valueOf(total);
			request.setAttribute("amount", amount);
//			取出最後訂單
//			將訂單做設定
//			FinalOrderVO fovo=gson.fromJson(tempStr,FinalOrderVO.class);
			JSONObject fovo=new JSONObject(tempStr);
			System.out.println("fovo\t"+fovo);
//			fovo.put("idCustomer",((UserVO)request.getAttribute("user")).getIdCustomer());
			fovo.put("idCustomer",5);
			JSONObject orderObj=new JSONObject();
			orderObj.put("customer",fovo);
			executeNum=foService.buildOrderService(orderObj);
		} else if ("deleteOrder".equals(metChoice)) {
//			foService.deleteOrderService(idFinalOrder);
		} 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.doPost(request, response);
	}

}
