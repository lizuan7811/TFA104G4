package com.user_use.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.basic_tool.controller.FileWorkDaoImpl;
import com.basic_tool.controller.JRedis;
import com.basic_tool.controller.Util;
import com.static_file.model.FinalStaticFile;



public class UserBOImpl implements UserBO{
	private UserDaoImpl udl;
	private Calendar cl;
	private SimpleDateFormat sdf;
	public UserBOImpl()
	{
		udl=new UserDaoImpl();
		cl=Calendar.getInstance();
		sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}

	@Override
	public JSONArray getUsersToJSON(Connection conn,PreparedStatement ps,String userName)
	{
		JSONArray usJsonArr=new JSONArray();
		System.out.println(usJsonArr);
//		if(userName==null || String.valueOf(Integer.MIN_VALUE).equals(userName)) {
//			return null;
//		}
		ResultSet rs=udl.selectUsers(conn,ps,userName);

			if(rs!=null)
			{
				usJsonArr=FileWorkDaoImpl.getUssToJsArr(rs);
			}
			else {
				Map<String,String> mp=new HashMap<>();
				mp.put("empty","This Customer List is Empty!");
				usJsonArr.put(mp);
			}
		Util.closeConnection(conn, ps);
		return usJsonArr;
	}

	@Override
	public Integer addClickBO(Connection conn, PreparedStatement ps, Integer idCustomer, Integer diaryID) {
		// TODO Auto-generated method stub
		Integer likeNum=0;
		udl.insDiaryLike(conn, ps, idCustomer, diaryID);
		likeNum=udl.selDiaryLike(conn, ps, diaryID);
		Util.closeConnection(conn, ps);
		return likeNum;
	}

	@Override
	public Integer delClickBO(Connection conn, PreparedStatement ps, Integer diaryLike,Integer diaryID) {
		// TODO Auto-generated method stub
		Integer likeNum=0;
		udl.delDiaryLike(conn, ps, diaryLike, diaryID);
		likeNum=udl.selDiaryLike(conn, ps, diaryID);
		Util.closeConnection(conn, ps);
		return likeNum;
	}

	@Override
	public Integer addOrDelClickBO(Integer idCustomer, Integer diaryID) {
		Integer likeNum=JRedis.clickLike(diaryID, idCustomer);

		return likeNum;
	}

	@Override
	public void insertLike(Connection conn, PreparedStatement ps, Integer diaryId, Integer custId) {
		conn=Util.getConnection();
		int doResult=0;
		try {
//			"INSERT INTO `DiaryLike`(diaryLikeID,diaryID,idCustomer,createdTime)values(?,?,?,?);";

			ps=conn.prepareStatement(FinalStaticFile.DIARYLIKESG_INSERT);//插入DiaryLike的資料
			ps.setInt(1, 0);
			ps.setInt(2,diaryId);
			ps.setInt(3, custId);
//			Calendar cl=Calendar.getInstance();
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ps.setTimestamp(4,Timestamp.valueOf(sdf.format(cl.getTimeInMillis())));
			doResult=ps.executeUpdate();
			if(doResult>0)
			{
				System.out.println("添加DiaryLike成功!");
			}
			else
			{
				System.out.println("添加DiaryLike不成功!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//加like或刪除like
	@Override
	public void updateDeleteLike(Connection conn, PreparedStatement ps, Integer diarylikeID) {
		// TODO Auto-generated method stub
		int doResult=0;
		try {
			ps=conn.prepareStatement(FinalStaticFile.DIARYLIKESG_DELETE);
			ps.setInt(1,diarylikeID);
			doResult=ps.executeUpdate();
			if(doResult>0)
			{
				System.out.println("添加DiaryLike成功!");
			}
			else
			{
				System.out.println("添加DiaryLike不成功!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer insertComments(Connection conn, PreparedStatement ps,String diaryID,String custNickName,String comment)
	{
		Integer commentID=0;
		try {
			ps=conn.prepareStatement(FinalStaticFile.COMMENT_INSERT);
			ps.setInt(1, Integer.valueOf(diaryID));
			ps.setString(2,custNickName);
			ps.setTimestamp(3,FileWorkDaoImpl.getTimeStamp());
			ps.setString(4, comment);
			ps.setBoolean(5, false);
			ps.executeUpdate();
			
			ps=conn.prepareStatement(FinalStaticFile.COMMENTSG_SELECT);
			ps.setInt(1, Integer.valueOf(diaryID));
			ps.setString(2,custNickName);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				commentID=rs.getInt("commentID");
				System.out.println(commentID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentID;
	}
	
//	使用者留言檢舉
	@Override
	public Integer userCommReport(Connection conn, PreparedStatement ps,Integer commentID,Integer diaryID,String custNickName, String reportReason) {
//		先查有沒有資料，有舊覆蓋，沒有就增加。
		boolean flag=false;
		Integer executeNum=0;
		ResultSet rs=null;
		try {
//	public final static String COMMENTREPORT_SELECT="SELECT * FROM CommentReport WHERE custID = ? and diaryID = ?;";
			ps=conn.prepareStatement(FinalStaticFile.COMMENTREPORT_SELECT);
			ps.setString(1,custNickName);
			ps.setInt(2,commentID);
			rs=ps.executeQuery();
			if(rs.next())
			{
//				若有，就用Alter修改資料欄位的值
//				UPDATE CommentReport SET createdTime=?,reportReason=?,reportResult=? WHERE diaryID = ? and custID = ?;
				System.out.println("commentReport找到該文章及檢舉者，執行修改使用者檢舉資料!");
				ps=conn.prepareStatement(FinalStaticFile.COMMENTREPORT_ALTER);

				ps.setTimestamp(1,new Timestamp(cl.getTimeInMillis()));

				ps.setString(2, reportReason);

				ps.setBoolean(3, false);

				ps.setInt(4,commentID);

				ps.setString(5,custNickName);
				System.out.println("檢舉修改的輸入資料:"+diaryID+"\t"+custNickName+"\t"+new Timestamp(cl.getTimeInMillis())+"\t"+reportReason+"\t"+String.valueOf(false));

			}
			else {
				System.out.println("commentReport沒找到該文章及檢舉者，執行增加使用者資料!");
				ps.close();
//				若有，就用Insert新增資料
//				INSERT INTO CommentReport(commentReportID,diaryID,custID,createdTime,reportReason,reportResult)VALUES(?,?,?,?,?,?);";
				ps=conn.prepareStatement(FinalStaticFile.COMMENTREPORT_INSERT);
				ps.setInt(1, 0);
				ps.setInt(2,commentID);
				ps.setString(3, custNickName);
				ps.setTimestamp(4,Timestamp.valueOf(sdf.format(cl.getTimeInMillis())));
				ps.setString(5, reportReason);
				ps.setBoolean(6,false);
				System.out.println("檢舉修改的新增資料:"+diaryID+"\t"+custNickName+"\t"+new Timestamp(cl.getTimeInMillis())+"\t"+reportReason+"\t"+String.valueOf(false));
			}
			executeNum=ps.executeUpdate();
//			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeConnection(conn, ps);
		}
		return executeNum;
	}
//	日誌被檢舉需先修改使用者日誌中該日誌的狀態
	public Integer foodDiaryStatus(Connection conn,PreparedStatement ps,String diaryID,String custID,Boolean diaryStatus)
	{
		Integer executeNum=0;
		try {
			ps=conn.prepareStatement(FinalStaticFile.FOODDIARY_UPDATE);
			ps.setBoolean(1, diaryStatus);
			ps.setInt(2, Integer.parseInt(diaryID));
			ps.setInt(3, Integer.parseInt(custID));
			
			executeNum=ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return executeNum;
	}
	
	
//	使用者日誌檢舉
	@Override
	public Integer userDiaryReport(Connection conn, PreparedStatement ps,String diaryID,String custID, String reportReason) {
		boolean flag=false;
		Integer executeNum=0;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(FinalStaticFile.DIARYREPORTSG_SELECT);
			ps.setInt(1, Integer.parseInt(custID));
			ps.setInt(2, Integer.parseInt(diaryID));
			rs=ps.executeQuery();
//			先查有沒有資料，有舊覆蓋，沒有就增加。
			if(rs.next())
			{
//				若有，就用update修改資料欄位的值
				System.out.println("diaryReport找到該文章及檢舉者，執行修改使用者檢舉資料!");
				ps=conn.prepareStatement(FinalStaticFile.DIARYREPORT_ALTER);
				ps.setTimestamp(1,Timestamp.valueOf(sdf.format(cl.getTimeInMillis())));
				ps.setString(2, reportReason);
				ps.setBoolean(3, true);
				ps.setInt(4, Integer.parseInt(diaryID));
				ps.setInt(5, Integer.parseInt(custID));
				System.out.println("檢舉修改的輸入資料:"+diaryID+"\t"+custID+"\t"+Timestamp.valueOf(sdf.format(cl.getTimeInMillis()))+"\t"+reportReason+"\t"+String.valueOf(false));
			}
			else {
				System.out.println("commentReport沒找到該文章及檢舉者，執行增加使用者資料!");
				ps.close();
				ps=conn.prepareStatement(FinalStaticFile.DIARYREPORT_INSERT);
				ps.setInt(1, Integer.parseInt(diaryID));
				ps.setInt(2, Integer.parseInt(custID));
				ps.setTimestamp(3,Timestamp.valueOf(sdf.format(cl.getTimeInMillis())));
				ps.setString(4, reportReason);
				ps.setBoolean(5,true);
				System.out.println("檢舉修改的新增資料:"+diaryID+"\t"+custID+"\t"+Timestamp.valueOf(sdf.format(cl.getTimeInMillis()))+"\t"+reportReason+"\t"+String.valueOf(false));
			}
			executeNum=ps.executeUpdate();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return executeNum;
	}
	
	@Override
	public JSONArray translateToJSON(List<Object> objList) {



		return null;
	}
}
