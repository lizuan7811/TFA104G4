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
import han.IngreType.IngreTypeDAO;
import han.IngreType.IngreTypeDAOImpl;
import han.IngreType.IngreTypeVO;
import han.Recipe.RecipeDAO;
import han.Recipe.RecipeDAOImpl;
import han.Recipe.RecipeVO;
import han.RecipeIngre.RecipeIngreDAO;
import han.RecipeIngre.RecipeIngreDAOImpl;
import han.RecipeIngre.RecipeIngreVO;

@WebServlet("/Search_RecipeListServlet")
public class Search_RecipeListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer idRecipe = Integer.valueOf(req.getParameter("idRecipe"));
		System.out.println("接收到前端傳來的資料：" + "idRecipe=" + idRecipe);

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		RecipeIngreDAO dao1 = new RecipeIngreDAOImpl();
		List<RecipeIngreVO> list = dao1.findList(idRecipe);

//		for (RecipeIngreVO rcpingre : list) {
//			System.out.print("食材編號" + rcpingre.getIdIngre() + ",");
//			System.out.print("數量" + rcpingre.getIngreQuan() + ",");
//			System.out.println();
//		}

		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		int m = list.size();
		for (int i = 0; i < m; i++) {
			obj.clear();

			IngreDAO daot = new IngreDAOImpl();
			IngreVO ingre = daot.findByPK(list.get(i).getIdIngre());
			obj.put("Name", ingre.getName());
			obj.put("Unit", ingre.getUnit());

			obj.put("idIngre", list.get(i).getIdIngre());
			obj.put("ingreQuan", list.get(i).getIngreQuan());
			
			
			System.out.print(list.get(i).getIdIngre());
			System.out.print(ingre.getName());
			System.out.print(" 數量:"+list.get(i).getIngreQuan());
			System.out.println();
			
			array.put(obj.toMap());
		}
		
		resp.getWriter().print(array.toString());
	}
}
