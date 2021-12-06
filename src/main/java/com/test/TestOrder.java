package com.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.basic_tool.controller.Util;
import com.finalorder.model.FinalOrderDao;
import com.finalorder.model.FinalOrderDaoImpl;
import com.finalorder.model.FinalOrderVO;

import han.Ingre.IngreVO;
import han.Recipe.RecipeVO;
import han.RecipeIngre.RecipeIngreVO;

public class TestOrder {

	private FinalOrderDao fodi;
	private static HashMap<String, FinalOrderVO> histoOrderHashMap;// 歷史訂單含詳細資料
	private static HashMap<String, RecipeIngreVO> recipeIngreHashMap;// 食譜與食材對照表，其中包含食材數量
	private static HashMap<String, IngreVO> ingreHashMap;// 食材表，包含食材的詳細資料
	private static HashMap<String, RecipeVO> recipeHashMap;// 食譜表，包含食譜的詳細資料
	private static HashMap<Integer, Integer> finalOrderHashMap;

	public static void main(String[] args) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		
		FinalOrderDaoImpl fodi = new FinalOrderDaoImpl(conn, ps);
		
		histoOrderHashMap=fodi.getHistoOrderHashMap();
		recipeIngreHashMap = fodi.getRecipeIngreHashMap();
		ingreHashMap=fodi.getIngreHashMap();
		recipeHashMap=fodi.getRecipeHashMap();
		
		finalOrderHashMap=new HashMap<Integer,Integer>();

		buildFinalOrderBO(conn, ps, new JSONObject());

	}

	public static BigDecimal buildFinalOrderBO(Connection conn, PreparedStatement ps, JSONObject userBuyObj) {
		
		BigDecimal tempBD=new BigDecimal(0);
		BigDecimal totalMoney=new BigDecimal(0);
		HashMap<String,Object>resMap=new HashMap<String,Object>();
//		JSONObject<String,Object>，前端將{"recipe":{{"食譜編號":食譜數量},{"食譜編號":食譜數量}},"ingre":{{"食材編號":食材數量},{"食材編號":食材數量}}}存入JSONObject物件
//		傳請求給servlet建立訂單，所以JSONObject物件就會以JSON的格式傳到後端。
		HashMap<String,Object> userMap=(HashMap<String,Object>)userBuyObj.toMap();
		//取得記錄在食譜字串的value，這個value是RecipeVO。
		if(userMap.containsKey("recipe")) {
			resMap=(HashMap<String, Object>) userMap.get("recipe");
			for(String key:resMap.keySet())
			{
//				從recipeIngreHashMap中取得idRecipe為101的idIngre及數量資料，存成map集合並回傳
//				Map<Integer,Integer> tempMp=recipeIngreHashMap.entrySet().stream()
//						.filter(e->((RecipeIngreVO)e.getValue()).getIdRecipe()==101)
//						.collect(Collectors.toMap(e->e.getValue().getIdIngre(),e->e.getValue().getIngreQuan()));
				
				Map<Integer,Integer> tempMp=recipeIngreHashMap.entrySet().stream()
				.filter(e->((RecipeIngreVO)e.getValue()).getIdRecipe()==Integer.parseInt(key))
				.collect(Collectors.toMap(e->e.getValue().getIdIngre(),e->e.getValue().getIngreQuan()));
//				將取得的食譜對應的食材ID及數量，存到MAP中
				for(Integer itg:tempMp.keySet())
				{
					if(!finalOrderHashMap.containsKey(itg))
					{
						finalOrderHashMap.put(itg,(Integer)tempMp.get(itg));
						System.out.println(itg+"\t"+tempMp.get(itg));
					}
					else
					{
						finalOrderHashMap.put(itg,(finalOrderHashMap.get(itg)+tempMp.get(itg)));
						System.out.println(itg+"\t"+finalOrderHashMap.get(itg)+tempMp.get(itg));
					}
					
				}
			}
		}
		if(userMap.containsKey("ingre")) {
//		若key為recipe，就將value中的map取出，再從中間取得key跟value，把取得的數量結果加入到finalorderhashmap中
//		從使用者傳的資料中拿到key為recipe的value，再從value中(集合中取得的map物件)拿keyset，從每個keyset取得value，value存的是食譜的數量
			resMap=(HashMap<String,Object>)userMap.get("ingre");
			for(String key:userMap.keySet())
			{
				if(finalOrderHashMap.containsKey(Integer.parseInt(key)))
				{
					finalOrderHashMap.put(Integer.parseInt(key), (Integer)resMap.get(key));
				}
				else
				{
					finalOrderHashMap.put(Integer.parseInt(key), (Integer)(finalOrderHashMap.get(key)+(Integer)resMap.get(key)));
				}
			}
		}
		
		for(Integer key:finalOrderHashMap.keySet())
		{
			tempBD=ingreHashMap.get(String.valueOf(key)).getPrice();
			totalMoney=totalMoney.add(tempBD.multiply(BigDecimal.valueOf(Double.parseDouble(String.valueOf(finalOrderHashMap.get(key))))));
		}
		System.out.println(totalMoney);
		return totalMoney;
	}
}
