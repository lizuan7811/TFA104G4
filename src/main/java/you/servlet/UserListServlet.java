package you.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import you.service.UserService;
import you.service.UserServiceImpl;


//管理者呼叫方法讀取使用者列表
public class UserListServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		if("getUserList".equals(request.getParameter("metChoice")))
		{
			getUsList(request,response);
		}
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request, response);
	}
	
	private void getUsList(HttpServletRequest request,HttpServletResponse response)
	{
		UserService usv=new UserServiceImpl();
//		Integer.parseInt(request.getParameter("usPos"))
		JSONArray jsonArr=usv.selectUser(""+Integer.MIN_VALUE);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonArr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
