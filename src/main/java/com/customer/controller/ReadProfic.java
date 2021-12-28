package com.customer.controller;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.basic_tool.controller.Util;

public class ReadProfic extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
 
		res.setContentType("image/gif"); // 還有其他眾多資料型態
		ServletOutputStream out = res.getOutputStream(); // 二位元輸出
		
		try {
			
			String account = req.getParameter("account");
			String getProfic = "SELECT profic FROM Customer WHERE account = " + "\"" + account + "\"";
			pstmt = con.prepareStatement(getProfic);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("profic")); // 置入緩衝區
				byte[] buf = new byte[12 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (SQLException se) {
			se.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	public void init() throws ServletException {
			
			con = Util.getConnection();
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}