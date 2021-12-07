package com.finalorder.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.basic_tool.controller.Util;
import com.finalorder.model.FinalOrderDao;
import com.finalorder.model.FinalOrderDaoImpl;
import com.finalorder.model.FinalOrderVO;

import han.Ingre.IngreVO;
import han.Recipe.RecipeVO;
import han.RecipeIngre.RecipeIngreVO;

public class FinalOrderBOImpl implements FinalOrderBO{
	private FinalOrderDao fodi;
	private static HashMap<String, FinalOrderVO> histoOrderHashMap;// 歷史訂單含詳細資料
	private static HashMap<String, RecipeIngreVO> recipeIngreHashMap;// 食譜與食材對照表，其中包含食材數量
	private static HashMap<String, IngreVO> ingreHashMap;// 食材表，包含食材的詳細資料
	private static HashMap<String, RecipeVO> recipeHashMap;// 食譜表，包含食譜的詳細資料
	private static HashMap<Integer, Integer> finalOrderHashMap;
	
	public FinalOrderBOImpl(Connection conn,PreparedStatement ps)
	{
		fodi=new FinalOrderDaoImpl(conn,ps);
//		histoOrderHashMap=fodi.getHistoOrderHashMap(conn,ps,0);
		recipeIngreHashMap = fodi.getRecipeIngreHashMap();
		ingreHashMap=fodi.getIngreHashMap();
		recipeHashMap=fodi.getRecipeHashMap();
		finalOrderHashMap=new HashMap<Integer,Integer>();
	}
	
	
//	計算價錢
	@Override
	public Integer buildFinalOrderBO(Connection conn, PreparedStatement ps, JSONObject userBuyObj) {
		Integer recipeCount=1;
		BigDecimal tempBD=new BigDecimal(0);
		BigDecimal totalMoney=new BigDecimal(0);
		HashMap<String,Object>recipeMap=new HashMap<String,Object>();
		HashMap<String,Object>ingreMap=new HashMap<String,Object>();
//		JSONObject<String,Object>，前端將{"recipe":{{"食譜編號":食譜數量},{"食譜編號":食譜數量}},"ingre":{{"食材編號":食材數量},{"食材編號":食材數量}}}存入JSONObject物件
//		傳請求給servlet建立訂單，所以JSONObject物件就會以JSON的格式傳到後端。
		HashMap<String,Object> userMap=(HashMap<String,Object>)userBuyObj.toMap();
		//取得記錄在食譜字串的value，這個value是RecipeVO。
//		if(userMap.containsKey("recipe")) {
//			recipeMap=(HashMap<String, Object>) userMap.get("recipe");
//			for(String key:ingreMap.keySet())
//			{
//				recipeCount=(Integer)recipeMap.get(key);
//				從recipeIngreHashMap中取得idRecipe為101的idIngre及數量資料，存成map集合並回傳
				Map<Integer,Integer> tempMp=recipeIngreHashMap.entrySet().stream()
						.filter(e->((RecipeIngreVO)e.getValue()).getIdRecipe()==101)
						.collect(Collectors.toMap(e->e.getValue().getIdIngre(),e->e.getValue().getIngreQuan()));
				
//				Map<Integer,Integer> tempMp=recipeIngreHashMap.entrySet().stream()
//				.filter(e->((RecipeIngreVO)e.getValue()).getIdRecipe()==Integer.parseInt(key))
//				.collect(Collectors.toMap(e->e.getValue().getIdIngre(),e->e.getValue().getIngreQuan()));
//				將取得的食譜對應的食材ID及數量，存到MAP中
				for(Integer itg:tempMp.keySet())
				{
					if(!finalOrderHashMap.containsKey(itg))
					{
						finalOrderHashMap.put(itg,(Integer)tempMp.get(itg)*recipeCount);
						System.out.println(itg+"\t"+tempMp.get(itg));
					}
					else
					{
						finalOrderHashMap.put(itg,(finalOrderHashMap.get(itg)+tempMp.get(itg)*recipeCount));
						System.out.println(itg+"\t"+finalOrderHashMap.get(itg)+tempMp.get(itg));
					}
					
				}
//			}
//		}
		if(userMap.containsKey("ingre")) {
//		若key為recipe，就將value中的map取出，再從中間取得key跟value，把取得的數量結果加入到finalorderhashmap中
//		從使用者傳的資料中拿到key為recipe的value，再從value中(集合中取得的map物件)拿keyset，從每個keyset取得value，value存的是食譜的數量
			ingreMap=(HashMap<String,Object>)userMap.get("ingre");
			for(String key:userMap.keySet())
			{
				if(finalOrderHashMap.containsKey(Integer.parseInt(key)))
				{
					finalOrderHashMap.put(Integer.parseInt(key), (Integer)ingreMap.get(key));
				}
				else
				{
					finalOrderHashMap.put(Integer.parseInt(key), (Integer)(finalOrderHashMap.get(key)+(Integer)ingreMap.get(key)));
				}
			}
		}
//		將訂單中的品項數量取出並計算價錢
		for(Integer key:finalOrderHashMap.keySet())
		{
			tempBD=ingreHashMap.get(String.valueOf(key)).getPrice();
			totalMoney=totalMoney.add(tempBD.multiply(BigDecimal.valueOf(Double.parseDouble(String.valueOf(finalOrderHashMap.get(key))))));
		}
		System.out.println(totalMoney);
//		拿到FinalOrderVO物件，裡面有前端傳至後端的此次消費者的消費訂單紀錄
		FinalOrderVO fovo=(FinalOrderVO) userBuyObj.get("customer");
		fovo.setOrderAmount(totalMoney);
//		判斷付款
//		Boolean flag=fodi.isPay(conn,ps,fovo);
//		寫入資料庫
//		寫入資料庫(交易控制)
		Integer succNum=0;
		try {
			conn.setAutoCommit(false);
			succNum=fodi.finalOrderInsert(conn,ps,fovo,true);
//			fodi.finalOrderInsert(conn,ps,fovo,flag);
			//		取得這筆訂單的ID(FinalOrderid)，然後產生訂單明細
			Integer idFinalOrder=fodi.getUserLatestOrderID(conn, ps, fovo.getIdCustomer());
			fodi.orderListInsert(conn, ps, idFinalOrder, finalOrderHashMap);
			conn.commit();
		}
		catch(Exception e)
		{
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return succNum;
	}
}
