package han.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basic_tool.controller.Util;
import com.mysql.cj.protocol.Resultset;

import han.RecipeIngre.RecipeIngreVO;

@WebServlet("/RecipeIngreServlet.html")
public class RecipeIngreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest res, HttpServletResponse resp) {

		Connection conn = Util.getConnection();

		try {
			resp.setContentType("text/http; charset=utf-8");
			res.setCharacterEncoding("utf-8");

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM RECIPEINGRE");
			ResultSet rs = ps.executeQuery();
			RecipeIngreVO rcpingre = new RecipeIngreVO();
			while (rs.next()) {
				rcpingre.setIdRecipeIngre(rs.getInt("idRecipeIngre"));
				rcpingre.setIdRecipe(rs.getInt("idRecipe"));
				rcpingre.setIdIngre(rs.getInt("idIngre"));
			}

			PrintWriter pw = resp.getWriter();
			pw.write(String.valueOf(rcpingre.getIdRecipeIngre()));
			pw.write(String.valueOf(rcpingre.getIdRecipe()));
			pw.write(String.valueOf(rcpingre.getIdIngre()));

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest res, HttpServletResponse resp) {
		this.doPost(res, resp);
	}
}
