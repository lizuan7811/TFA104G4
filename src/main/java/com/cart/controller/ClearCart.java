package com.cart.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ClearCart")
public class ClearCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
    	String action = req.getParameter("action");
    
    	if("".equals(req.getParameter("pay"))||"".equals(req.getParameter("payName"))||"".equals(req.getParameter("payAddr"))||"".equals(req.getParameter("payComm"))){
			req.getRequestDispatcher("/CartServlet").forward(req, res);
			return ;
		}
    	if(action.equals("CLEAR")){
    		
    		Enumeration em = req.getSession().getAttributeNames();
    		while(em.hasMoreElements()){
    		req.getSession().removeAttribute(em.nextElement().toString());
    		}

            //重定向
            res.sendRedirect(req.getContextPath()+"/front_end/cart/shop.jsp");
            return;   		
    	}else if ("SENDORDER".equals(action)){
        	try {
	            Thread thread = Thread.currentThread();

        		thread.sleep(500);//在頁面停止1.5秒後 跳轉回商城
        		System.out.println("SENDORDER");
	            Enumeration em = req.getSession().getAttributeNames();
	    		while(em.hasMoreElements()){
	    		req.getSession().removeAttribute(em.nextElement().toString());
	    		}
	            //重定向
	    		thread.sleep(1200);
	            res.sendRedirect(req.getContextPath()+"/front_end/cart/shop.jsp");
	            return; 
  
	        }catch (InterruptedException e) {
	            e.printStackTrace();
	        }

        }
   
         
    }

}
