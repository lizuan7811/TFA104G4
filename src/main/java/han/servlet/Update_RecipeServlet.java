package han.servlet;

import java.io.IOException;
import java.io.InputStream;

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