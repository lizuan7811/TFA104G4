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
    	
		Enumeration em = req.getSession().getAttributeNames();
		while(em.hasMoreElements()){
		req.getSession().removeAttribute(em.nextElement().toString());
		}
       
        //重定向
        res.sendRedirect(req.getContextPath()+"/buyproduct.jsp");
        return;
   
         
    }

}
