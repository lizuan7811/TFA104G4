package you.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.diarylike.DiaryLikeVO;
import cf.fooddiary.FoodDiaryVO;
import redis.clients.jedis.Jedis;
import you.pojo.UserVO;
import you.service.UserService;
import you.service.UserServiceImpl;

@WebServlet("/usermethod.html")
public class UserServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService us;
	public void init()
	{
//		設定定時器，每過一天，晚上凌晨執行將Redis資料存到SQL中
//		Jedis jRedis=new Jedis("localhost",3679);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
//		String metChoice=request.getParameter("metChoice");
//		if(metChoice!=null && !"".equals(metChoice))
//		{
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			doClick(request,response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
//		}
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request,response);
	}
	
	private void doClick(HttpServletRequest request,HttpServletResponse response)
	{
		PrintWriter pw=null;
		us=new UserServiceImpl();
//		DiaryLikeVO拿到dlv物件
//		DiaryLikeVO dlv=(DiaryLikeVO)request.getAttribute("diarylikeID");
//		Integer diaryLikeID=dlv.getDiaryLikeid();
////		FoodDiaryVO物件
//		FoodDiaryVO diaryID=(FoodDiaryVO)request.getAttribute("");
////		UserVO
//		UserVO user=(UserVO)request.getAttribute("");
		
		Integer likeCount=0;
//		若diaryLikiId為空，執行加like，否則執行刪除like
		try {
			pw=response.getWriter();
			pw.write(likeCount.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//			if(diaryID==null && diaryLikeID==0)
//			{
//				System.out.println("diaryLikeId為空!");
//			}else {
////				likeCount=us.addOrDelLikeService(diaryID.getDiaryID(),user.getIdCustomer());				
//			}
			likeCount=us.addOrDelLikeService(1,34);				
			likeCount=us.addOrDelLikeService(2,35);				
			likeCount=us.addOrDelLikeService(3,34);	
	}
	
}
