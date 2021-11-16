package you.basicdao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import you.conn.BaseConn;


public class BaseDaoImpl implements BaseDao{

	
//	增加管理者帳號
	@Override
	public int insertData(Connection conn,PreparedStatement ps,String sql) 
	{
		int modifyRowNum=0;
		try {

			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sql);
			ps.setInt(1,0);
			ps.setString(2,"Admin");
			ps.setString(3, "Admin");
			long cl=Calendar.getInstance().getTimeInMillis();
			ps.setTimestamp(4,new Timestamp(cl));
			ps.setBoolean(5,false);
			modifyRowNum=ps.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
				try 
				{
					conn.rollback();
				} catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
		}
		finally 
		{
			try
			{
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			BaseConn.closeConnection(conn, ps);
		}
		return modifyRowNum;
	}

	
//	刪除管理者帳號
	
	@Override
	public int deleteData(Connection conn,PreparedStatement ps,String sql,String para) {
		int modifyRowNum=0;
		try {
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sql);
			if(!"".equals(para) && para!=null) 
			{
				ps.setInt(1,Integer.parseInt(para));
			}
			modifyRowNum=ps.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
				try 
				{
					conn.rollback();
				} catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
		}
		finally 
		{
			try
			{
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			BaseConn.closeConnection(conn, ps);
		}
		return modifyRowNum;
	}

//	修改指定帳號
//USER_UPDATE="UPDATE Customer SET commentReportedNum=?,diaryReportedNum=?,suspended=? WHERE idCustomer=?;";
	@Override
	public int updateData(Connection conn,PreparedStatement ps,String sql,Object...para) {
		int modifyRowNum=0;
		try {
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sql);
			
			if(sql.toLowerCase().indexOf("admin")>0)
			{
				int i=0;
				while(i<para.length) {
					ps.setInt(1,Integer.parseInt(para[i++].toString()));
					ps.setString(2,para[i++].toString());
					ps.setString(3,para[i++].toString());
					long cl=Calendar.getInstance().getTimeInMillis();
					ps.setTimestamp(4,new Timestamp(cl));
					i++;
					ps.setBoolean(5,Boolean.parseBoolean(para[i++].toString()));
					ps.setInt(6,Integer.parseInt(para[i].toString()));
				}
			}else if(sql.toLowerCase().indexOf("customer")>0)
			{
				int i=0;
				while(i<para.length)
				{
					ps.setInt(1,Integer.parseInt(para[i++].toString()));
					ps.setInt(2,Integer.parseInt(para[i++].toString()));
					ps.setBoolean(3,Boolean.parseBoolean(para[i++].toString()));
					ps.setInt(4,Integer.parseInt(para[i++].toString()));	
					i++;
//					UserInsert(目前用不到)
//					ps.setInt(1,Integer.parseInt(para[i++].toString()));
//					ps.setString(2,para[i++].toString());
//					ps.setBlob(3,para[i++].getClass().getResourceAsStream(null));
//					ps.setString(4,para[i++].toString());
//					ps.setString(5,para[i++].toString());
//					ps.setString(6,para[i++].toString());
//					ps.setString(7,para[i++].toString());
//					ps.setString(8,para[i++].toString());
//					long cl=Calendar.getInstance().getTimeInMillis();
//					ps.setTimestamp(9,new Timestamp(cl));
//					i++;
//					ps.setBoolean(10,Boolean.parseBoolean(para[i++].toString()));
//					ps.setString(11,para[i++].toString());
//					ps.setString(12,para[i++].toString());
//					ps.setInt(13,Integer.parseInt(para[i++].toString()));
//					ps.setInt(14,Integer.parseInt(para[i++].toString()));	
				}
			}
			modifyRowNum=ps.executeUpdate();
			System.out.println(modifyRowNum);
		} catch (SQLException e) 
		{
			e.printStackTrace();
				try 
				{
					conn.rollback();
				} catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
		}
		finally 
		{
			try
			{
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			BaseConn.closeConnection(conn, ps);
		}
		return modifyRowNum;
	}

	@Override
	public ResultSet selectData(Connection conn,PreparedStatement ps,String sql,String param) {
		ResultSet rs=null;
		try 
		{
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sql);
			if(!"".equals(param) && param!=null && Integer.parseInt(param)>0) 
			{
				System.out.println("執行ps.setInt(1,Integer.parseInt(param));了");
				ps.setInt(1,Integer.parseInt(param));
			}
			else {
				ps=conn.prepareStatement(sql);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) 
		{
			e.printStackTrace();
				try 
				{
					conn.rollback();
				} catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
		}
		finally 
		{
			try
			{
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			BaseConn.closeConnection(conn, ps);
		}
		return rs;
	}

}
