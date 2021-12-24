package com.user_use.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.json.JSONArray;

public interface UserBO {
	public JSONArray getUsersToJSON(Connection conn,PreparedStatement ps,String userName);

	public Integer addClickBO(Connection conn,PreparedStatement ps,Integer idCustomer,Integer diaryID);

	public Integer addOrDelClickBO(Integer idCustomer,Integer diaryID);

	public Integer delClickBO(Connection conn,PreparedStatement ps,Integer diaryLike,Integer diaryID);

//	public Integer delClickBO(Integer diaryLike,Integer diaryID);

	public void insertLike(Connection conn,PreparedStatement ps,Integer diaryId,Integer custId);
	
	public void updateDeleteLike(Connection conn,PreparedStatement ps,Integer diarylikeID);

	public Integer userCommReport(Connection conn, PreparedStatement ps,Integer commentID,Integer diaryID,String custNickName, String reportReason);
	public JSONArray translateToJSON(List<Object> objList);

	public Integer foodDiaryStatus(Connection conn,PreparedStatement ps,String diaryID,String custID,Boolean diaryStatus);
	
	public Integer userDiaryReport(Connection conn, PreparedStatement ps,String diaryID,String custID, String reportReason);

	public Integer insertComments(Connection conn, PreparedStatement ps,String diaryID,String custNickName,String comment);

}
