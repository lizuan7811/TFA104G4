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

import han.Recipe.RecipeDAO;
import han.Recipe.RecipeDAOImpl;
import han.Recipe.RecipeVO;

@WebServlet("/Shop_RandomRecipeServlet")
public class Shop_RandomRecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		RecipeDAO dao = new RecipeDAOImpl();
		List<RecipeVO> list = dao.getAll();

		int m = list.size();

		int[] arr = new int[3];
		boolean flag;
		for (int i = 0; i < 3; i++) {
			do {
				flag = false;
				arr[i] = (int) (Math.random() * m);
				if (i != 0) {
					for (int x = 0; x < i; x++) {
						if (arr[i] == arr[x]) {
							flag = true;
						}
					}
				}
			} while (flag);
		}
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < 3; i++) {
//			obj.clear();
			obj.put("idRecipe", list.get(arr[i]).getIdRecipe());
			obj.put("name", list.get(arr[i]).getRecipeName());
			array.put(obj.toMap());
		}
		resp.getWriter().print(array.toString());
	}
}
