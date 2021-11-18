package you.service;

import org.json.JSONArray;

import ga.cf_eat_healthy.FoodDiaryVO;
import you.pojo.UserVO;

public interface UserService {
//	增
//	刪
	public void deleteUser(Integer usPos);
//	修
	public void alterUser(Integer usPos,Object...paras);
//	查
	public JSONArray selectUser(String usPos);
//	按讚
	public Integer addClickService(Integer idCustomer,Integer diaryID); 
//	刪讚
	public Integer delClickService(Integer diaryLikeID,Integer diaryID);
////	每次讀取每次更新
//	public int selClickService();
}
