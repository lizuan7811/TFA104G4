package han.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mysql.cj.protocol.Resultset;

import com.basic_tool.controller.*;
import han.Recipe.RecipeVO;

@WebServlet("/Recipeservlet.html")
public class RecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");

			Connection conn = Util.getConnection(); // 取得連線
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM RECIPE WHERE IDRECIPE = ?");// 欲執行指令
			ps.setInt(1, 103);
			ResultSet rs = ps.executeQuery();
			RecipeVO recipe = new RecipeVO();
			while (rs.next()) {
				recipe.setIdRecipe(rs.getInt("idRecipe"));
				recipe.setRecipeName(rs.getString("recipeName"));
				recipe.setDescrip(rs.getString("descrip"));
				recipe.setText(rs.getString("text"));

			}

			req.setAttribute("recipe", recipe);

			JSONObject obj = new JSONObject();
			obj.put("idRecipe", recipe.getIdRecipe());
			obj.put("recipeName", recipe.getRecipeName());
			obj.put("descrip", recipe.getDescrip());
			obj.put("text", recipe.getText());
			resp.getWriter().write(obj.toString());



		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		this.doPost(req, resp);
	}

}
