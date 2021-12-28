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

@WebServlet("/SearchAll_productServlet")
public class SearchAll_productServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		IngreDAO dao = new IngreDAOImpl();
		List<IngreVO> list = dao.getAll();
		
		
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		int m=list.size();
		for(int i=0; i<m; i++) {
			obj.clear();
			
			IngreTypeDAO daot = new IngreTypeDAOImpl();
			IngreTypeVO type = daot.findByPK(list.get(i).getIdIngreType());
			obj.put("typeName", type.getTypeName());
			
			obj.put("idIngre", list.get(i).getIdIngre());
			obj.put("idIngreType", list.get(i).getIdIngreType());
			obj.put("name", list.get(i).getName());
			obj.put("purPrice", list.get(i).getPurPrice());
			obj.put("price", list.get(i).getPrice());
			obj.put("unit", list.get(i).getUnit());
			obj.put("gran", list.get(i).getGran());
			obj.put("sell", list.get(i).getSell());
			obj.put("descrip", list.get(i).getDescrip());
			obj.put("launch", list.get(i).getLaunch());
//			System.out.println(type.getTypeName());
			array.put(obj.toMap());	
		}
	
		resp.getWriter().print(array.toString());
	}
}
