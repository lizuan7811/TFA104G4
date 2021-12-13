package han.servlet;

import java.io.IOException;
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

import han.Ingre.IngreDAO;
import han.Ingre.IngreDAOImpl;
import han.Ingre.IngreVO;

@WebServlet("/search_productServlet")
public class search_productServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		Integer idIngre = Integer.valueOf(req.getParameter("idIngre"));
		System.out.println("接收到前端傳來的資料：" + "idIngre=" + idIngre);

		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			
			IngreDAO dao = new IngreDAOImpl();
			IngreVO ingre = dao.findByPK(idIngre);
			System.out.println("查詢到資料庫資料:" + ingre.getIdIngre() + " " + ingre.getName());
			req.setAttribute("ingre", ingre);

			JSONObject obj = new JSONObject();
			obj.put("idIngre", ingre.getIdIngre());
			obj.put("idIngreType", ingre.getIdIngreType());
			obj.put("name", ingre.getName());
			obj.put("purPrice", ingre.getPurPrice());
			obj.put("price", ingre.getPrice());
			obj.put("unit", ingre.getUnit());
			obj.put("gran", ingre.getGran());
			obj.put("sell", ingre.getSell());
			obj.put("descrip", ingre.getDescrip());
			obj.put("launch", ingre.getLaunch());
			obj.put("msg", "success");

			resp.getWriter().write(obj.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		this.doPost(req, resp);
	}

}
