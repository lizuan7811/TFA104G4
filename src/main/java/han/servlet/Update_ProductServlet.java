package han.servlet;

import java.io.IOException;
import java.io.InputStream;
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

@WebServlet("/Update_ProductServlet")
@MultipartConfig
public class Update_ProductServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		Integer idIngre = Integer.valueOf(req.getParameter("idIngre"));
		Integer idIngreType = Integer.valueOf(req.getParameter("idIngreType"));
		String name = req.getParameter("name");
		String descrip = req.getParameter("descrip");
		BigDecimal purPrice = new BigDecimal(Integer.valueOf(req.getParameter("purPrice")));
		BigDecimal price = new BigDecimal(Integer.valueOf(req.getParameter("price")));
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
        IngreVO ingre2 = new IngreVO();
    	ingre2.setIdIngreType(idIngreType);
		ingre2.setName(name);
		ingre2.setPurPrice(purPrice);
		ingre2.setPrice(price);
		ingre2.setUnit(unit);
		ingre2.setGran(gran);
		ingre2.setSell(sell);
		ingre2.setDescrip(descrip);
		ingre2.setPhoto(photo);
		ingre2.setLaunch(Boolean.valueOf(launch));
		ingre2.setIdIngre(idIngre);
        
        dao.update(ingre2);
        
//		try {
//			Connection conn = Util.getConnection();
//			PreparedStatement ps = conn.prepareStatement("UPDATE INGRE SET IDINGRETYPE = ?, NAME = ?, PURPRICE = ?,"
//					+ " PRICE = ?, UNIT = ?, GRAN = ?, SELL = ?, DESCRIP = ?, PHOTO = ?, LAUNCH = ? WHERE IDINGRE = ?");
//			// PreparedStatement 欲執行指令
//			
//			ps.setInt(1, idIngreType);
//			ps.setString(2, name);
//			ps.setInt(3, purPrice);
//			ps.setInt(4, price);
//			ps.setString(5, unit);
//			ps.setInt(6, gran);
//			ps.setInt(7, sell);
//			ps.setString(8, descrip);
//			ps.setBytes(9, photo);
//			ps.setString(10, launch);
//			ps.setInt(11, idIngre);
//			
//			ps.executeUpdate();
//			
			JSONObject obj = new JSONObject();
	        resp.getWriter().write(obj.toString());
//	        
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