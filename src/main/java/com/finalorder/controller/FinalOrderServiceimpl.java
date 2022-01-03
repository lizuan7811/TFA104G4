package com.finalorder.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import org.json.JSONObject;

import com.basic_tool.controller.Util;
import com.finalorder.model.FinalOrderDao;
import com.finalorder.model.FinalOrderDaoImpl;
import com.finalorder.model.FinalOrderVO;
public class FinalOrderServiceimpl implements FinalOrderService {
	private Connection conn;
	private PreparedStatement ps;
	private FinalOrderBO fobi;
	private FinalOrderDao fodo;

	@Override
	public Integer buildOrderService(JSONObject userBuyObj) {
//		JSONObject<String,Object>，前端將{"食譜":食譜編號}，{"食材":食材編號}存入JSONObject物件
//		傳請求給servlet建立訂單，所以JSONObject物件就會以JSON的格式傳到後端。
//		orderObj.put("食譜名稱",(JSONObject)new HashMap<食譜編號.toString,數量>());
//		orderObj.put("食材名稱",(JSONObject)<食譜編號.toString,數量>());
//		前端傳來後端的JSON物件，使用的時候可以先轉為Map型別
		conn=Util.getConnection();
		ps=null;
		fobi=new FinalOrderBOImpl(conn,ps);
		System.out.println("buildOrderService\t"+userBuyObj);
		Integer succNum=fobi.buildFinalOrderBO(conn, ps, userBuyObj);
		Util.closeConnection(conn, ps);
		return succNum;
	}
	@Override
	public Integer deleteOrderService(Integer idfnaleOrder) {
		return null;
	}
	@Override
	public JSONObject getInitDetail() {
		conn=Util.getConnection();
		ps=null;
		JSONObject jObj=new JSONObject();
		fodo=new FinalOrderDaoImpl(conn,ps);
		
		fobi=new FinalOrderBOImpl(conn,ps);
		jObj.put("finalOrderAll",fodo.getFinalOrderAll());
		jObj.put("ingreAll",fodo.getIngreJsonArr());
		jObj.put("orderIngreList",fodo.getOrderIngreList());
		return jObj;
	}
	
	@Override
	public JSONObject serviceInitOwnOrder(Integer custID) {
		conn=Util.getConnection();
		ps=null;
		JSONObject jObj=new JSONObject();
		fodo=new FinalOrderDaoImpl(conn,ps);
		jObj.put("UserOwnfinalOrdre",fodo.getInitOwnOrder(conn,ps,custID));
		jObj.put("ingreAll",fodo.getIngreJsonArr());
		jObj.put("orderIngreList",fodo.getOrderIngreList());
		System.out.println(jObj);
		return jObj;
	}
}
