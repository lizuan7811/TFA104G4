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

import han.Recipe.RecipeDAO;
import han.Recipe.RecipeDAOImpl;
import han.Recipe.RecipeVO;

@WebServlet("/Recipeservlet.html")
public class RecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		Integer idRecipe = Integer.valueOf(req.getParameter("idRecipe"));
		System.out.println("接收到前端傳來的資料：" + "idRecipe=" + idRecipe);

		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			
			RecipeDAO dao = new RecipeDAOImpl();
			RecipeVO recipe = dao.findByPK(idRecipe);
			System.out.println("查詢到資料庫資料:" + recipe.getIdRecipe() + " " + recipe.getRecipeName());
			req.setAttribute("recipe", recipe);

			JSONObject obj = new JSONObject();
			obj.put("idRecipe", recipe.getIdRecipe());
			obj.put("name", recipe.getRecipeName());
			obj.put("descrip", recipe.getDescrip());
			obj.put("text", recipe.getText());
			obj.put("msg", "success");
			
			
			resp.getWriter().println(obj.toString());

			
//			 RequestDispatcher rd = req.getRequestDispatcher("/tbmgf_v1/resipe.html"); 
//			 rd.forward(req, resp);
			
//			resp.sendRedirect("http://localhost:8081/tbmgf_v1/resipe.html");
			
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		this.doPost(req, resp);
	}

}
