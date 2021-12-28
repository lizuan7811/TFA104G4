package com.finalorder.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import han.Ingre.IngreVO;
import han.Recipe.RecipeVO;
import han.RecipeIngre.RecipeIngreVO;

public interface FinalOrderDao {
//	搜尋所有訂單(尚未完成以及已經完成的都算)
	public HashMap<String,FinalOrderVO> getHistoOrderHashMap(Connection conn,PreparedStatement ps,Integer idCustomer);
//	public HashMap<String,FinalOrderVO> finalOrderSelect(Connection conn,PreparedStatement ps);
//	搜尋單個"食譜"的詳細資料
	public HashMap<String,RecipeVO> getRecipeHashMap();
//	public HashMap<String,RecipeVO> recipeALLSelect(Connection conn,PreparedStatement ps,Integer idRecipe);
//	搜尋所有"食材"的詳細資料，用HashMap裝起來
	public HashMap<String,IngreVO> getIngreHashMap();
//	public HashMap<String,IngreVO> ingreAllSelect(Connection conn,PreparedStatement ps);
//	搜尋所有食譜跟食材的對照表資料，一次取得存入記憶體中
	public HashMap<String,RecipeIngreVO> getRecipeIngreHashMap();
//	確認是否付款
	public Boolean isPay(Connection conn,PreparedStatement ps,FinalOrderVO fovo);
//	新增確定要購買的訂單，新增前須確定已付款
	public Integer finalOrderInsert(Connection conn,PreparedStatement ps,JSONObject fovo,Boolean isPay);
//	將消費者訂單資料寫入資料庫的該筆訂單的詳細清單中。
	public int[] orderListInsert(Connection conn, PreparedStatement ps,Integer idFinalOrder,HashMap<Integer,Integer>finalOrderMap);

	public Integer getUserLatestOrderID(Connection conn,PreparedStatement ps,Integer idCustomer);

	public JSONArray getIngreJsonArr();

	public JSONArray getFinalOrderAll();
	
	public JSONArray getOrderIngreList();
	
	public JSONArray getInitOwnOrder(Connection conn,PreparedStatement ps,Integer custID);
}
