package you.service;

import you.pojo.AdminVO;
import you.pojo.UserVO;

public interface ServiceDao {

	public AdminVO login(String username,String password);

	
}
