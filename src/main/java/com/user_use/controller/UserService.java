package com.user_use.controller;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UserService {
//	增
//	刪
	public void deleteUser(Integer usPos);
//	修
	public void alterUser(Integer usPos,Object...paras);
//	查
	public JSONArray selectUser(String usPos);
//	按讚
	public Integer addOrDelLikeService(Integer diaryID,Integer idCustomer);
////	刪讚
//	public Integer delClickService(Integer diaryLikeID,Integer diaryID);
////	每次讀取每次更新
//	public int selClickService();
	public Integer serviceCommReport(String diaryID,String custID,String reportReason);
	
	public Integer serviceDiaryReport(String diaryID,String custID,String reportReason);
	
	public Integer serviceAddFriend(String metChoice,Integer custID,Integer myFriendID);

	public JSONArray serviceAboutFriend(String metChoice,Integer custID);
	
	public JSONArray serviceGetDiaryComms();
	
	public JSONObject serviceGetDiaryReported();

//	public JSONArray serviceAppliedFriend(String metChoice, Integer custID);
	
	
}
