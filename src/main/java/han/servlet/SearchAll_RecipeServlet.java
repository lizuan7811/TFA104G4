package han.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import han.Ingre.IngreDAO;
import han.Ingre.IngreDAOImpl;
import han.Ingre.IngreVO;
import han.Recipe.RecipeDAO;
import han.Recipe.RecipeDAOImpl;
import han.Recipe.RecipeVO;

@WebServlet("/SearchAll_RecipeServlet")
public class SearchAll_RecipeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		RecipeDAO dao = new RecipeDAOImpl();
		List<RecipeVO> list = dao.getAll();
		
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		int m=list.size();
		for(int i=0; i<m; i++) {
			obj.clear();
			obj.put("idRecipe", list.get(i).getIdRecipe());
			obj.put("name", list.get(i).getRecipeName());
			obj.put("descrip", list.get(i).getDescrip());
			obj.put("text", list.get(i).getText());
			
			array.put(obj.toMap());	
		}
		resp.getWriter().print(array.toString());
	}
}
