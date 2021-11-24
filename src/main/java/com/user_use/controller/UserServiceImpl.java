package com.user_use.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONArray;

import com.basic_tool.controller.Util;
import com.user_use.model.UserBO;
import com.user_use.model.UserBOImpl;
import com.user_use.model.UserDao;
import com.user_use.model.UserDaoImpl;



public class UserServiceImpl implements UserService{
	private UserDao usd;
	private UserBO usbo;
	public UserServiceImpl()
	{
		usd=new UserDaoImpl();
		usbo=new UserBOImpl();
	}

	@Override
	public void deleteUser(Integer usPos) {
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		usd.deleteUser(conn, ps, usPos);
	}

	@Override
	public void alterUser(Integer usPos,Object...paras) {
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		usd.alterUser(conn, ps, usPos,paras);
	}

	@Override
	public JSONArray selectUser(String username) {
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		JSONArray userJsonArr=usbo.getUsersToJSON(conn, ps, username);
		return userJsonArr;
	}
//	如果like數量增加或減少，就要執行到select count(*) from liketable更新讚數
//	回傳SELECT * FROM DiaryLike;
	@Override
	public Integer addOrDelLikeService(Integer diaryID,Integer idCustomer) {
		// TODO Auto-generated method stub
		Integer likeNum=0;
		likeNum=usbo.addOrDelClickBO(idCustomer,diaryID);
		return likeNum;
	}
//	@Override
//	public Integer delClickService(Integer diaryLikeID,Integer diaryID) {
//		// TODO Auto-generated method stub
//		Connection conn=BaseConn.getConnection();
//		PreparedStatement ps=null;
//		Integer likeNum=0;
//		likeNum=usbo.delClickBO(conn,ps,diaryLikeID,diaryID);
//		return likeNum;
//	}
//	public int selClickService()
//	{
//
//		return 0;
//	}
	@Override
	public Integer serviceCommReport(String diaryID,String custID,String reportReason)
	{
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		int successRow=0;
		usbo.userCommReport(conn,ps,diaryID,custID,reportReason);

		return successRow;
	}


}
