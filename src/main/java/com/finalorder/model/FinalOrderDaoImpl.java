package com.finalorder.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.basic_tool.controller.Util;
import com.static_file.model.FinalStaticFile;

import han.Ingre.IngreVO;
import han.Recipe.RecipeVO;

public class FinalOrderDaoImpl implements FinalOrderDao{
	
	private HashMap<String,FinalOrderVO>histoOrderHashMap;
	private HashMap<String, RecipeVO>recipeHashMap;
	private HashMap<String, IngreVO>ingreHashMap;
	
	private FinalOrderVO finalOrderVo;
	private RecipeVO recipeVo;
	private IngreVO ingreVo;
	
//	產生訂單前，就會將這三個HashMap的物件建構出來
	public FinalOrderDaoImpl(Connection conn,PreparedStatement ps) {
		this.histoOrderHashMap=getHistoOrderHashMap(conn,ps);
		this.recipeHashMap=recipeALLSelect(conn,ps);
		this.ingreHashMap=ingreAllSelect(conn,ps);
	}

	public HashMap<String,FinalOrderVO> getHistoOrderHashMap()
	{
		return this.histoOrderHashMap;
	}
	
	public HashMap<String,RecipeVO> getRecipeHashMap()
	{
		return this.recipeHashMap;
	}
	
	public HashMap<String,IngreVO> getIngreOrderHashMap()
	{
		return this.ingreHashMap;
	}
//	所有歷史訂單的MAP
	private HashMap<String, FinalOrderVO> getHistoOrderHashMap(Connection conn, PreparedStatement ps) {
		histoOrderHashMap=new HashMap<String,FinalOrderVO>();
		try {
			ps=conn.prepareStatement(FinalStaticFile.FINALORDERALL_SELECT);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Integer tempID=rs.getInt("idFinalOrder");
				finalOrderVo=new FinalOrderVO(tempID,rs.getInt("idCustomer"),rs.getString("recipient"),rs.getString("recipientAddress"),
						rs.getBigDecimal("orderAmount"),rs.getTimestamp("createdTime"),rs.getTimestamp("shipTime"),
						rs.getTimestamp("arrivalTime"),rs.getString("footnote"));	
				histoOrderHashMap.put(String.valueOf(tempID), finalOrderVo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return histoOrderHashMap;
	}
//	
	private HashMap<String, RecipeVO> recipeALLSelect(Connection conn, PreparedStatement ps) {
		
		try {
			ps=conn.prepareStatement(FinalStaticFile.RECIPEALL_SELECT);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				recipeVo=new RecipeVO();
				Integer tempID=rs.getInt("idRecipe");
				recipeVo.setIdRecipe(tempID);
				recipeVo.setRecipeName(rs.getString("recipeName"));
				recipeVo.setDescrip(rs.getString("descrip"));
				recipeVo.setText(rs.getString("text"));
				recipeVo.setPhoto(rs.getBytes("photo"));
				recipeHashMap.put(String.valueOf(tempID),recipeVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeHashMap;
	}

	private HashMap<String, IngreVO> ingreAllSelect(Connection conn, PreparedStatement ps) {

		try {
			ps=conn.prepareStatement(FinalStaticFile.INGREALL_SELECT);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ingreVo=new IngreVO();
				Integer tempID=rs.getInt("idIngre");
				ingreVo.setIdIngre(tempID);
				ingreVo.setIdIngreType(rs.getInt("idIngreType"));
				ingreVo.setName(rs.getString("name"));
				ingreVo.setPurPrice(rs.getBigDecimal("purPrice"));
				ingreVo.setPrice(rs.getBigDecimal("price"));
				ingreVo.setUnit(rs.getString("unit"));
				ingreVo.setGran(rs.getInt("getgran"));
				ingreVo.setSell(rs.getInt("sell"));
				ingreVo.setDescrip(rs.getString("descrip"));
				ingreVo.setLaunch(rs.getBoolean("launch"));
				ingreVo.setPhoto(rs.getBytes("photo"));

				ingreHashMap.put(String.valueOf(tempID), ingreVo);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingreHashMap;
	}

	@Override
	public Boolean isPay(Connection conn,PreparedStatement ps,Integer idFinalOder,BigDecimal payMouney) {
		Boolean payCheck=false;
		
		
		
		
		return payCheck;
	}

	@Override
	public Integer finalOrderInsert(Connection conn, PreparedStatement ps,Integer idFinalOrder, HashMap<String, FinalOrderVO> finalOrderMap,Boolean payCheck) {
//		若傳入的訂單ID與目前紀錄的定單，且確定已經付款完畢，就將建立的訂單寫進資料庫
		Integer orderInsertCount=0;
		try {
			conn.setAutoCommit(false);
			if(finalOrderMap.containsKey(String.valueOf(idFinalOrder)) && payCheck==true) {
				ps=conn.prepareStatement(FinalStaticFile.FINALORDERSG_INSERT);
				FinalOrderVO finalOrderVO=finalOrderMap.get(String.valueOf(idFinalOrder));
				
				ps.setInt(1,idFinalOrder);
				ps.setInt(2,finalOrderVO.getIdCustomer());
				ps.setString(3,finalOrderVO.getRecipient());
				ps.setString(4,finalOrderVO.getRecipientAddress());
				ps.setBigDecimal(5,finalOrderVO.getOrderAmount());
				ps.setTimestamp(6,finalOrderVO.getCreatedTime());
				ps.setTimestamp(7,finalOrderVO.getShipTime());
				ps.setTimestamp(8,finalOrderVO.getArrivalTime());
				ps.setString(9, finalOrderVO.getFootnote());
			}else
			{
				if(payCheck==false)
				{
					System.out.println("未完成付款程序!");
				}
				else {
					System.out.println("未選擇正確的訂單!");
				}
				System.out.println("訂單未正確產生!");
				return 0;
			}
			orderInsertCount=ps.executeUpdate();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderInsertCount;
	}
}
