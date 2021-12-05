package han.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import han.Ingre.IngreDAO;
import han.Ingre.IngreDAOImpl;
import han.Ingre.IngreVO;
import han.Recipe.RecipeDAO;
import han.Recipe.RecipeDAOImpl;
import han.Recipe.RecipeVO;

@WebServlet("/Search_RecipeServlet")
public class Search_RecipeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
			
			resp.getWriter().write(obj.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
