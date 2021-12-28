package com.basic_tool.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public interface BaseDao {
//	增加
	public int insertData(Connection conn,PreparedStatement ps,String sql);
//	刪除
	public int deleteData(Connection conn,PreparedStatement ps,String sql,String param);
//	修改
	public int updateData(Connection conn,PreparedStatement ps,String sql,Object... para);
//	查詢
	public ResultSet selectData(Connection conn,PreparedStatement ps,String sql,Map param);

}
