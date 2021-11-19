package you.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import you.conn.BaseConn;
import you.contents.FinalStaticFile;

public class  DiaryLikeBOImpl implements DiaryLikeBO{

	@Override
	public void clickAddLike(Connection conn, PreparedStatement ps, Integer diaryId, Integer custId) {
		conn=BaseConn.getConnection();
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
	public void clickDeleteLike(Connection conn, PreparedStatement ps, Integer diarylikeID) {
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


}
