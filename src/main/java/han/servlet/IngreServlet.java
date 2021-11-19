package han.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IngreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");

			Connection con1 = conn();
			PreparedStatement ps = con1.prepareStatement("SELECT * FROM INGRE"); // PreparedStatement 欲執行指令
			IngreVO ingre = ingre(ps);
			PrintWriter pw = resp.getWriter();
			pw.write(String.valueOf(ingre.getIdIngre()));
			pw.write("\t");
			pw.write(String.valueOf(ingre.getIdIngreType()));
			pw.write("\t");
			pw.write(ingre.getName());
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		this.doPost(req, resp);
	}

	public Connection conn() {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn = null;
		try {

			conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public IngreVO ingre(PreparedStatement ps) {
		ResultSet rs = null;
		try {
			rs = ps.executeQuery(); // ResultSet executeQuery 查詢方法
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		IngreVO ingre = new IngreVO();
		try {
			while (rs.next()) {

				ingre.setIdIngre(rs.getInt("IDINGRE"));
				ingre.setIdIngreType(rs.getInt("IDINGRETYPE"));
				ingre.setName(rs.getString("NAME"));
				return ingre;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingre;
	}	
	
}
