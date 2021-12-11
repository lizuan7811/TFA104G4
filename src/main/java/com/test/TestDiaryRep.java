package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.basic_tool.controller.Util;
import com.static_file.model.FinalStaticFile;

public class TestDiaryRep {
	
	public static void main(String[] args)
	{
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		System.out.println(getDiaryComms(conn, ps));
	}
	
	public static JSONArray getDiaryComms(Connection conn, PreparedStatement ps) {
		JSONArray jsonArr= new JSONArray();
		try {
			ps=conn.prepareStatement(FinalStaticFile.DIARYREPORT_SELECT);
			JSONObject jsonObj=new JSONObject();
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				jsonObj.put("diaryReportID",rs.getInt("diaryReportID"));
				jsonObj.put("diaryID",rs.getInt("diaryID"));
				jsonObj.put("custID",rs.getInt("custID"));
				jsonObj.put("createdTime",rs.getTimestamp("createdTime"));
				jsonObj.put("reportReason",rs.getString("reportReason"));
				jsonObj.put("reportResult",rs.getBoolean("reportResult"));
				jsonArr.put(jsonObj.toMap());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArr;
	}

}
