package han.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import han.Ingre.IngreDAO;
import han.Ingre.IngreDAOImpl;
import han.Ingre.IngreVO;

@WebServlet("/insert_productServlet2")
@MultipartConfig
public class insert_productServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		Integer idIngre = Integer.valueOf(req.getParameter("idIngre"));
		Integer idIngreType = Integer.valueOf(req.getParameter("idIngreType"));
		String name = req.getParameter("name");
		String descrip = req.getParameter("descrip");
		BigDecimal purPrice = new BigDecimal(Integer.valueOf(req.getParameter("purPrice")));
		BigDecimal price =  new BigDecimal(Integer.valueOf(req.getParameter("price")));
		String unit = req.getParameter("unit");
		Integer gran = Integer.valueOf(req.getParameter("gran"));
		Integer sell = Integer.valueOf(req.getParameter("sell"));
		String launch = req.getParameter("launch");
		
		Part img = req.getPart("file");
        byte[] photo = getPhoto(img);
		
//		System.out.println("接收到前端傳來的資料：" + " " + "img=" + img + " " + "idIngreType=" + idIngreType + " " + "idIngre="
//				+ idIngre + " " + "name=" + name + " " + "descrip=" + descrip + " " + "purPrice=" + purPrice + " "
//				+ "price=" + price + " " + "unit=" + unit + " " + "gran=" + gran + " " + "sell=" + sell + " "
//				+ "launch=" + launch);

		IngreDAO dao = new IngreDAOImpl();
		IngreVO ingre = new IngreVO();
		ingre.setIdIngre(idIngre);
		ingre.setIdIngreType(idIngreType);
		ingre.setName(name);
		ingre.setPurPrice(purPrice);
		ingre.setPrice(price);
		ingre.setUnit(unit);
		ingre.setGran(gran);
		ingre.setSell(sell);
		ingre.setDescrip(descrip);
		ingre.setPhoto(photo);
		ingre.setLaunch(Boolean.valueOf(launch));
		
		dao.insert(ingre);
//		try {
//			Connection conn = Util.getConnection();
//			PreparedStatement ps = conn.prepareStatement("INSERT INTO INGRE(IDINGRE, IDINGRETYPE, NAME, PURPRICE, "
//					+ "PRICE, UNIT, GRAN, SELL, DESCRIP, PHOTO, LAUNCH) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
//			// PreparedStatement 欲執行指令
//			ps.setInt(1, idIngre);
//			ps.setInt(2, idIngreType);
//			ps.setString(3, name);
//			ps.setInt(4, purPrice);
//			ps.setInt(5, price);
//			ps.setString(6, unit);
//			ps.setInt(7, gran);
//			ps.setInt(8, sell);
//			ps.setString(9, descrip);
//			ps.setBytes(10, photo);
//			ps.setString(11, launch);
//			ps.executeUpdate();
			
			JSONObject obj = new JSONObject();
	        resp.getWriter().write(obj.toString());
	        
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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