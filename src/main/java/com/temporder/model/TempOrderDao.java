package com.temporder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;


public interface TempOrderDao {
//	搜尋所有訂單(尚未完成以及已經完成的都算)
	public HashMap<String,Object> finalOrderSelect(Connection conn,PreparedStatement ps);
//	搜尋單個"食譜"的詳細資料
	public void recipeSGSelect(Connection conn,PreparedStatement ps,Integer idRecipe);
//	搜尋所有"食材"的詳細資料，用HashMap裝起來
	public HashMap<String,Object> ingreAllSelect(Connection conn,PreparedStatement ps);
//	確認是否付款
	public Boolean isPay();
//	新增確定要購買的訂單，新增前須確定已付款
	public Integer finalOrderInsert(Connection conn,PreparedStatement ps,HashMap<String,Object>finalOrderMap,Boolean isPay);
	
	

}
