package com.user_use.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.basic_tool.controller.Util;
import com.pojo.model.AdminVO;
import com.user_use.model.UserDao;
import com.user_use.model.UserDaoImpl;

public class ServiceDaoImpl implements ServiceDao {
	private UserDao userDao;

	public ServiceDaoImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public AdminVO login(String username, String password) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		AdminVO admin = userDao.adminLogin(conn, ps, username, password);
		if (admin != null) {
			return admin;
		} else {
			return null;
		}
	}

}
