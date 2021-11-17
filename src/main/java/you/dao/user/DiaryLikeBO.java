package you.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface DiaryLikeBO {
	
//	使用者點讚，呼叫點讚方法，增加用insert，刪除使用delete
	public void clickAddLike(Connection conn,PreparedStatement ps,Integer diaryId,Integer custId);
	public void clickDeleteLike(Connection conn,PreparedStatement ps,Integer diaryId,Integer custId);


}
