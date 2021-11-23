package you.dao.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import com.mysql.cj.xdevapi.JsonArray;

import basicutil.JRedis;
import basicutil.Util;
import you.contents.FinalStaticFile;
import you.filedao.FileWorkDaoImpl;


public class UserBOImpl implements UserBO{
	private UserDaoImpl udl;
	public UserBOImpl()
	{
		udl=new UserDaoImpl();
	}
	
	public JSONArray getUsersToJSON(Connection conn,PreparedStatement ps,String userName)
	{
		JSONArray usJsonArr=new JSONArray();
		System.out.println(usJsonArr);
		ResultSet rs=udl.selectUsers(conn,ps,userName);
		
		if(rs!=null)
		{
			usJsonArr=FileWorkDaoImpl.getUssToJsArr(rs);
		}else {
			Map<String,String> mp=new HashMap<String,String>();
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
			Calendar cl=Calendar.getInstance();
			ps.setTimestamp(4,new Timestamp(cl.getTimeInMillis()));
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
	
//	使用者檢舉
	@Override
	public Integer userCommReport(Connection conn, PreparedStatement ps,String diaryID,String custID, String reportReason) {
//		先查有沒有資料，有舊覆蓋，沒有就增加。
		Boolean flag=false;
		Integer executeNum=0;
		ResultSet rs=null;
		try {
//	public final static String COMMENTREPORT_SELECT="SELECT * FROM CommentReport WHERE custID = ? and diaryID = ?;";
			ps=conn.prepareStatement(FinalStaticFile.COMMENTREPORT_SELECT);
			ps.setInt(1, Integer.parseInt(custID));
			ps.setInt(2, Integer.parseInt(diaryID));
			rs=ps.executeQuery();
 			Calendar cl=Calendar.getInstance();
 			
			if(rs.next()!=false)
			{
//				若有，就用Alter修改資料欄位的值
//				UPDATE CommentReport SET createdTime=?,reportReason=?,reportResult=? WHERE diaryID = ? and custID = ?;
				System.out.println("commentReport找到該文章及檢舉者，執行修改使用者檢舉資料!");
				ps=conn.prepareStatement(FinalStaticFile.COMMENTREPORT_ALTER);
				
				ps.setTimestamp(1,new Timestamp(cl.getTimeInMillis()));

				ps.setString(2, reportReason);

				ps.setBoolean(3, false);

				ps.setInt(4, Integer.parseInt(diaryID));

				ps.setInt(5, Integer.parseInt(custID));
				System.out.println("檢舉修改的輸入資料:"+diaryID+"\t"+custID+"\t"+new Timestamp(cl.getTimeInMillis())+"\t"+reportReason+"\t"+String.valueOf(false));

			}
			else {
				System.out.println("commentReport沒找到該文章及檢舉者，執行增加使用者資料!");
				ps.close();
//				若有，就用Insert新增資料
//				INSERT INTO CommentReport(commentReportID,diaryID,custID,createdTime,reportReason,reportResult)VALUES(?,?,?,?,?,?);";
				ps=conn.prepareStatement(FinalStaticFile.COMMENTREPORT_INSERT);
				ps.setInt(1, 0);
				ps.setInt(2, Integer.parseInt(diaryID));
				ps.setInt(3, Integer.parseInt(custID));
				ps.setTimestamp(4,new Timestamp(cl.getTimeInMillis()));
				ps.setString(5, reportReason);
				ps.setBoolean(6,false);
				System.out.println("檢舉修改的新增資料:"+diaryID+"\t"+custID+"\t"+new Timestamp(cl.getTimeInMillis())+"\t"+reportReason+"\t"+String.valueOf(false));

			}
			executeNum=ps.executeUpdate();
//			conn.commit();
		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			try {
//				conn.setAutoCommit(true);
//				System.out.println("執行結束，關閉AutoCommit!");
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		Util.closeConnection(conn, ps);
		return executeNum;
	}
}
