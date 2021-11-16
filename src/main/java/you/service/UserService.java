package you.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONArray;

public interface UserService {
//	增
//	刪
	public void deleteUser(Integer usPos);
//	修
	public void alterUser(Integer usPos,Object...paras);
//	查
	public JSONArray selectUser(Integer usPos);
}
