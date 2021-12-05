package han.servlet;

import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.basic_tool.controller.*;
import han.Ingre.IngreVO;

@WebServlet("/ingreservlet1.html")
public class IngreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");

			Connection conn = Util.getConnection();

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM INGRE WHERE IDINGRE = ?"); // PreparedStatement
																								// 甈脣銵�誘
			ps.setInt(1, 105);
			ResultSet rs = ps.executeQuery();
			IngreVO ingre = new IngreVO();
			while (rs.next()) {
				ingre.setIdIngre(rs.getInt("idIngre"));
				ingre.setIdIngreType(rs.getInt("idIngreType"));
				ingre.setName(rs.getString("name"));
				ingre.setPurPrice(rs.getBigDecimal("purPrice"));
				ingre.setPrice(rs.getBigDecimal("price"));
				ingre.setUnit(rs.getString("unit"));
				ingre.setGran(rs.getInt("gran"));
				ingre.setSell(rs.getInt("sell"));
				ingre.setDescrip(rs.getString("descrip"));
//				ingre.setPhoto(rs.getBytes("photo"));
				ingre.setLaunch(rs.getBoolean("launch"));
			}
			
			
			
//			req.setAttribute("ingre", ingre);

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
//			obj.put("photo", ingre.getPhoto());
			obj.put("launch", ingre.getLaunch());

			resp.getWriter().write(obj.toString());

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		this.doPost(req, resp);
	}

}
