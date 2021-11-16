package you.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;

public interface UserBO {
	public JSONArray getUsersToJSON(Connection conn,PreparedStatement ps,Integer usPos);
}
