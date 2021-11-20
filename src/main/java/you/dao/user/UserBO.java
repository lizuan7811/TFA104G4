package you.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;

public interface UserBO {
	public JSONArray getUsersToJSON(Connection conn,PreparedStatement ps,String userName);

	public Integer addClickBO(Connection conn,PreparedStatement ps,Integer idCustomer,Integer diaryID);

	public Integer addOrDelClickBO(Integer idCustomer,Integer diaryID);

	public Integer delClickBO(Connection conn,PreparedStatement ps,Integer diaryLike,Integer diaryID);
	
//	public Integer delClickBO(Integer diaryLike,Integer diaryID);


}
