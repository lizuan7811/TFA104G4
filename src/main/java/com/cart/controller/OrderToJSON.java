package com.cart.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class OrderToJSON {
	private static JSONObject jjObj;
	private static Map<Integer,Integer> orderM;
	private static Integer count=0;
	public OrderToJSON() {
	}
	
	public static JSONObject getOrderToJson(Ingre ingreVo)
	{
		count++;
		BigDecimal total=new BigDecimal(0);
		jjObj=new JSONObject();
//		for (int i = 0; i < cart.size(); i++) {
//			Ingre order = cart.get(i);
			BigDecimal price = ingreVo.getPrice();
			int quantity = ingreVo.getQuantity();
			System.out.println(ingreVo.getIdIngre());
			total = total.add(price.multiply(new BigDecimal(quantity)));
			orderM=new HashMap<Integer,Integer>();
			if(orderM.containsKey(ingreVo.getIdIngre()))
			{
				orderM.put(ingreVo.getIdIngre(), orderM.get(ingreVo.getIdIngre())+ingreVo.getQuantity());
			}
			else
			{
				orderM.put(ingreVo.getIdIngre(), ingreVo.getQuantity());
			}
			jjObj.put("ingre",orderM);
//		}
		return jjObj;
	}

}
