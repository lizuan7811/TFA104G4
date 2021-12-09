package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.json.JSONObject;

import com.basic_tool.controller.Util;

public class getIngrePhotoInJSON {
	private static String sql="SELECT idIngre,photo FROM Ingre";

	public getIngrePhotoInJSON()
	{
		
	}
	public JSONObject getIngrePhotos() {
		JSONObject ingPhotoObj=new JSONObject();
		Connection conn=Util.getConnection();
		ResultSet rs=null;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
//			ps.setInt(1, 302);
			rs=ps.executeQuery();
			while(rs.next())
			{
				
				Integer ingreID=rs.getInt("idIngre");
				Base64.Encoder picCode=Base64.getEncoder();
				String picsCode=picCode.encodeToString(rs.getBytes("photo"));
//				System.out.println(picsCode);
				ingPhotoObj.put(String.valueOf(ingreID), picsCode);
//				System.out.println(ingPhotoObj);
			}
		
			
			System.out.println("End");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ingPhotoObj;
		
	}

}
