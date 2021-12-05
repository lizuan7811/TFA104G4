package com.finalorder.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import org.json.JSONObject;

import com.finalorder.model.FinalOrderDao;
import com.finalorder.model.FinalOrderDaoImpl;
import com.finalorder.model.FinalOrderVO;

import han.Ingre.IngreVO;
import han.Recipe.RecipeVO;

public class FinalOrderBOImpl implements FinalOrderBO{
	private FinalOrderDao fodi;
	private HashMap<String,FinalOrderVO> histoOrderHashMap;
	private HashMap<String,IngreVO>ingreHashMap;
	private HashMap<String,RecipeVO>recipeHashMap;
	private HashMap<String,FinalOrderVO>finalOrderHashMap;
	public FinalOrderBOImpl(Connection conn,PreparedStatement ps)
	{
		fodi=new FinalOrderDaoImpl(conn,ps);
		this.histoOrderHashMap=fodi.getHistoOrderHashMap();
		this.ingreHashMap=fodi.getIngreOrderHashMap();
		this.recipeHashMap=fodi.getRecipeHashMap();
		finalOrderHashMap=new HashMap<String,FinalOrderVO>();
	}
	
	@Override
	public void buildFinalOrderBO(Connection conn, PreparedStatement ps, JSONObject userBuyObj) {
//		JSONObject<String,Object>，前端將{"食譜":食譜編號}，{"食材":食材編號}存入JSONObject物件
//		傳請求給servlet建立訂單，所以JSONObject物件就會以JSON的格式傳到後端。
		for(String key:userBuyObj.toMap().keySet())
		{
			if("recipe".equals(key)) {
//				若key為recipe，就將value中的map取出，再從中間取得key跟value，把取得的數量結果加入到finalorderhashmap中
			}else if("ingre".equals(key)){
				
			}
		}
//		orderObj.put("食譜名稱",(JSONObject)new HashMap<食譜編號.toString,數量>());
//		orderObj.put("食材名稱",(JSONObject)<食譜編號.toString,數量>());
//		前端傳來後端的JSON物件，使用的時候可以先轉為Map型別
	}

}
