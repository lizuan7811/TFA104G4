package you.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ga.cf_eat_healthy.DiaryLikeVO;
import ga.cf_eat_healthy.FoodDiaryVO;
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
		us=new UserServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		String metChoice=request.getParameter("metChoice");
		if(metChoice!=null && !"".equals(metChoice))
		{
			doClick(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request,response);
	}
	
	private void doClick(HttpServletRequest request,HttpServletResponse response)
	{
//		DiaryLikeVO拿到dlv物件
		DiaryLikeVO dlv=(DiaryLikeVO)request.getAttribute("diarylikeID");
		Integer diaryLikeID=dlv.getDiaryLikeid();
//		FoodDiaryVO物件
		FoodDiaryVO diaryID=(FoodDiaryVO)request.getAttribute("");
//		UserVO
		UserVO user=(UserVO)request.getAttribute("");
		
		Integer likeCount=0;
//		若diaryLikiId為空，執行加like，否則執行刪除like
			if(diaryLikeID==null || diaryLikeID==0)
			{
				likeCount=us.addClickService(user.getIdCustomer(),diaryID.getDiaryID());
				System.out.println("diaryLikeId為空!");
			}
			else
			{
				likeCount=us.delClickService(diaryLikeID,diaryID.getDiaryID());
				System.out.println("diaryLikeId為空!");
			}
	}
	
}
