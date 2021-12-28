package han.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import han.Recipe.RecipeDAO;
import han.Recipe.RecipeDAOImpl;
import han.Recipe.RecipeVO;
import han.RecipeIngre.RecipeIngreDAO;
import han.RecipeIngre.RecipeIngreDAOImpl;
import han.RecipeIngre.RecipeIngreVO;

@WebServlet("/Update_RecipeServlet")
@MultipartConfig
public class Update_RecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		
		Integer idRecipe = Integer.valueOf(req.getParameter("idRecipe"));
		String descrip = req.getParameter("descrip");
		String name = req.getParameter("name");
		String text = req.getParameter("text");

		Integer btId = Integer.valueOf(req.getParameter("btId"));
		
		Part img = req.getPart("file");
		byte[] photo = getPhoto(img);

		RecipeDAO dao = new RecipeDAOImpl();
		RecipeVO recipe = new RecipeVO();
		recipe.setIdRecipe(idRecipe);
		recipe.setDescrip(descrip);
		recipe.setRecipeName(name);
		recipe.setText(text);
		recipe.setPhoto(photo);

		dao.update(recipe);

		JSONObject obj = new JSONObject();
		resp.getWriter().write(obj.toString());
//		============== 新增食譜↑ ==============

		RecipeIngreDAO dao1 = new RecipeIngreDAOImpl();
		dao1.deleteList(idRecipe);

//		==============刪除食譜食材↑==============

		for (int i = 1; i < btId; i++) {
			String idIngreStr = req.getParameter("idIngre" + i);
			if (idIngreStr == null || Objects.equals(idIngreStr, "")) {
				continue;
			}
			Integer idIngre = Integer.valueOf(idIngreStr);
			Integer ingreQuan = Integer.valueOf(req.getParameter("number" + i));
//				System.out.println(idIngre + " " +ingreQuan);

			RecipeIngreVO RI = new RecipeIngreVO();
			RI.setIdRecipe(idRecipe);
			RI.setIdIngre(idIngre);
			RI.setIngreQuan(ingreQuan);

			dao1.insert(RI);

//			============== 更新食譜食材 ↑===============

			
		}
	}

	public static byte[] getPhoto(Part part) throws IOException {
		byte[] buffer = new byte[1024];
		InputStream in = part.getInputStream();
		buffer = new byte[in.available()];
		in.read(buffer);
		in.close();
		return buffer;
	}

}