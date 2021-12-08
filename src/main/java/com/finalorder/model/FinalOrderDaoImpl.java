package com.finalorder.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.basic_tool.controller.Util;
import com.static_file.model.FinalStaticFile;

import han.Ingre.IngreVO;
import han.Recipe.RecipeVO;
import han.RecipeIngre.RecipeIngreVO;

public class FinalOrderDaoImpl implements FinalOrderDao{
	
	private static HashMap<String,FinalOrderVO>histoOrderHashMap;
	private static HashMap<String,RecipeIngreVO>recipeIngreHashMap;
	private static HashMap<String, RecipeVO>recipeHashMap;
	private static HashMap<String, IngreVO>ingreHashMap;
	
	private FinalOrderVO finalOrderVo;
	private RecipeIngreVO recipeIngreVo;
	private RecipeVO recipeVo;
	private IngreVO ingreVo;
	
//	產生訂單前，就會將這三個HashMap的物件建構出來
	public FinalOrderDaoImpl(Connection conn,PreparedStatement ps) {
		
		this.recipeHashMap=recipeALLSelect(conn,ps);
		this.ingreHashMap=ingreAllSelect(conn,ps);
		this.recipeIngreHashMap=recipeIngreSelect(conn,ps);
	}
	
	public HashMap<String,FinalOrderVO> getHistoOrderHashMap(Connection conn,PreparedStatement ps,Integer idCustomer)
	{
		this.histoOrderHashMap=getPriveHistoOrderHashMap(conn,ps,idCustomer);
		return this.histoOrderHashMap;
	}
	
	public HashMap<String,RecipeVO> getRecipeHashMap()
	{
		return this.recipeHashMap;
	}
	
	public HashMap<String,IngreVO> getIngreHashMap()
	{
		return this.ingreHashMap;
	}
	public HashMap<String,RecipeIngreVO> getRecipeIngreHashMap()
	{
		return this.recipeIngreHashMap;
	}
	
	public int[] orderListInsert(Connection conn, PreparedStatement ps,Integer idFinalOrder,HashMap<Integer,Integer>finalOrderMap) {
		int[] tempInt=null;
		try {
			ps=conn.prepareStatement(FinalStaticFile.ORDERINGRELIST_INSERT);
			for(Integer key:finalOrderMap.keySet())
			{
				ps.setInt(1,idFinalOrder);
				ps.setInt(2, key);
				ps.setInt(3,(Integer)finalOrderMap.get(key));
				ps.setBigDecimal(4, ingreHashMap.get(String.valueOf(key)).getPrice());
				ps.addBatch();
			}
			tempInt=ps.executeBatch();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return tempInt;
	}
	
//	所有歷史訂單的MAP
	private HashMap<String, FinalOrderVO> getPriveHistoOrderHashMap(Connection conn, PreparedStatement ps,Integer idCustomer) {
		histoOrderHashMap=new HashMap<String,FinalOrderVO>();
		try {
			ps=conn.prepareStatement(FinalStaticFile.FINALUSERORDER_SELECT);
			ResultSet rs=ps.executeQuery();
			ps.setInt(1,idCustomer);
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
		recipeHashMap=new HashMap<String,RecipeVO>();
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
		ingreHashMap=new HashMap<String,IngreVO>();
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
				ingreVo.setGran(rs.getInt("gran"));
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

	private HashMap<String,RecipeIngreVO>recipeIngreSelect(Connection conn, PreparedStatement ps)
	{
		recipeIngreHashMap=new HashMap<String,RecipeIngreVO>();
		try {
			ps=conn.prepareStatement(FinalStaticFile.RECIPEINGRE_SELECT);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Integer idRI=rs.getInt("idRecipeIngre");
				recipeIngreVo=new RecipeIngreVO();
				recipeIngreVo.setIdRecipeIngre(idRI);
				
				recipeIngreVo.setIdRecipe(rs.getInt("idRecipe"));
				
				recipeIngreVo.setIdIngre(rs.getInt("idIngre"));
				recipeIngreVo.setIngreQuan(rs.getInt("ingreQuan"));
				
				recipeIngreHashMap.put(idRI.toString(),recipeIngreVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return recipeIngreHashMap;
	}
	
	
	
	@Override
	public Boolean isPay(Connection conn,PreparedStatement ps,FinalOrderVO fovo) {
		Boolean payCheck=false;
		
		
		
		
		return payCheck;
	}

	
	
	
	@Override
	public Integer finalOrderInsert(Connection conn, PreparedStatement ps,FinalOrderVO fovo,Boolean payCheck) {
//		若傳入的訂單ID與目前紀錄的定單，且確定已經付款完畢，就將建立的訂單寫進資料庫
		Integer orderInsertCount=0;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cl=Calendar.getInstance();
		String tmStr=df.format(cl.getTimeInMillis());
		Timestamp ts=Timestamp.valueOf(tmStr);
		try {
			if(payCheck) {
				ps=conn.prepareStatement(FinalStaticFile.FINALORDERSG_INSERT);
//				ps.setInt(1,(Integer)null);
				ps.setInt(1,fovo.getIdCustomer());
				ps.setString(2,fovo.getRecipient());
				ps.setString(3,fovo.getRecipientAddress());
				ps.setBigDecimal(4,fovo.getOrderAmount());
				ps.setTimestamp(5,ts);
				ps.setTimestamp(6,ts);
				ps.setTimestamp(7,null);
				ps.setString(8, fovo.getFootnote());
			}else
			{
				System.out.println("未完成付款程序!");
				System.out.println("訂單未正確產生!");
				return 0;
			}
			orderInsertCount=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderInsertCount;
	}
	public Integer getUserLatestOrderID(Connection conn,PreparedStatement ps,Integer idCustomer)
	{
		Integer userLatestOrderID=0;
		try {
			ps=conn.prepareStatement(FinalStaticFile.USERLATESTORDER_SELECT);
			ps.setInt(1, idCustomer);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				userLatestOrderID=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("userLatestOrderID:"+userLatestOrderID);
		return userLatestOrderID;
	}

	}
