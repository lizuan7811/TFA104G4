package com.finalorder.controller;

import org.json.JSONObject;

public interface FinalOrderService {
//	產生訂單
	public Integer buildOrderService(JSONObject orderObj);
//	刪除訂單
	public Integer deleteOrderService(Integer idfnaleOrder);
	
	public JSONObject getInitDetail();
	
	public JSONObject serviceInitOwnOrder(Integer custID) ;

}
