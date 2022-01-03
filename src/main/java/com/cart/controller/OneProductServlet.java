package com.cart.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;


@WebServlet("/OneProductServlet")
public class OneProductServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("Start-OneProductServlet ShoppingCart");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		JSONObject jjObj=req.getAttribute("JSONObj")==null?new JSONObject():(JSONObject)req.getAttribute("JSONObj");
		HttpSession session = req.getSession();
		Vector<Ingre> cart = (Vector<Ingre>) session.getAttribute("cart");
		String action = req.getParameter("action");
		
//用來新增單一商品至購物車		
		if (!action.equals("CHECKOUT")) {

			// 新增食材至購物車中
			if (action.equals("ADD_ONE")) {
				boolean match = false;

				// 取得新增的食材 (名稱 食材描述 價格 數量)
				Ingre theingre = getIngre(req);

				// 第一次進入畫面判斷購物車是否為空  
				if (cart == null) {
					//還沒有購物車就創建一個新的購物車 並加入食材
					cart = new Vector<Ingre>();
					cart.add(theingre);
				} else {
					for (int i = 0; i < cart.size(); i++) {
						Ingre ingre = cart.get(i);

						// 判斷食材是否相同
						if (ingre.getName().equals(theingre.getName())) {
							ingre.setQuantity(theingre.getQuantity()
									+ ingre.getQuantity());
							cart.setElementAt(ingre, i);
							match = true;
						} 
					} 

					// 假若新增的食材不一樣時
					if (!match)
						cart.add(theingre);
				}
			}				
			session.setAttribute("cart", cart);
			
			System.out.println("cart=" + cart);
			
			BigDecimal total = new BigDecimal(0);
			for (int i = 0; i < cart.size(); i++) {
				Ingre order = cart.get(i);
				BigDecimal price = order.getPrice();
				int quantity = order.getQuantity();
				total = total.add(price.multiply(new BigDecimal(quantity)));
				System.out.println(OrderToJSON.getOrderToJson(order));
				jjObj=OrderToJSON.getOrderToJson(order);
			}
			req.setAttribute("JSONObj", jjObj);
			
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/front_end/cart/buyproduct.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;
		}
		

		// 結帳，計算購物車中的食材價錢總數
		else if (action.equals("CHECKOUT")) {
			//如果購物車沒有新增任何商品 將導回原本頁面
			if (cart==null) {
				req.setAttribute("cart", cart);
				String url = "/front_end/cart/shop.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
			}
			
			BigDecimal total = new BigDecimal(0);
			for (int i = 0; i < cart.size(); i++) {
				Ingre order = cart.get(i);
				BigDecimal price = order.getPrice();
				int quantity = order.getQuantity();
				total = total.add(price.multiply(new BigDecimal(quantity)));
				System.out.println(OrderToJSON.getOrderToJson(order));
				jjObj=OrderToJSON.getOrderToJson(order);
			}
			req.setAttribute("JSONObj", jjObj);
			
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/front_end/cart/checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;
		}

	}

		  
		private Ingre getIngre(HttpServletRequest req) {

			String idIngre=req.getParameter("idIngre");
			String name = req.getParameter("name");
			String descrip = req.getParameter("descrip");
			String photo = req.getParameter("photo");
			String price = req.getParameter("price");
			String quantity = req.getParameter("quantity");

			Ingre ingre = new Ingre();

			ingre.setIdIngre(Integer.valueOf(idIngre));
			ingre.setName(name);
			ingre.setDescrip(descrip);
			ingre.setPhoto(photo);
			ingre.setPrice(BigDecimal.valueOf(Long.valueOf(price)));	
			ingre.setQuantity(Integer.valueOf(quantity));
			return ingre;
		}
		
		
         
    }



