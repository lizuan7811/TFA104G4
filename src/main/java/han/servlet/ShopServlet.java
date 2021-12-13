package han.servlet;

import java.io.IOException;
import java.util.Base64;
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

@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		IngreDAO dao = new IngreDAOImpl();
		List<IngreVO> list = dao.TOP3();
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		int m=list.size();
		for(int i=0; i<m; i++) {
			obj.clear();
			obj.put("idIngre", list.get(i).getIdIngre());
			obj.put("name", list.get(i).getName());
			Base64.Encoder bePhoto=Base64.getEncoder();
			String photoStr=bePhoto.encodeToString(list.get(i).getPhoto());
			obj.put("photo",photoStr);
			obj.put("price", list.get(i).getPrice());
			obj.put("descript", list.get(i).getDescrip());
			obj.put("unit", list.get(i).getUnit());
			obj.put("gran", list.get(i).getGran());
//			System.out.println(list.get(i).getName());
			array.put(obj.toMap());	
		}
		System.out.println();
		resp.getWriter().print(array.toString());
	}
}
