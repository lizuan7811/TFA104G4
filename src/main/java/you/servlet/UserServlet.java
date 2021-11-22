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
		us=new UserServiceImpl();
//		設定定時器，每過一天，晚上凌晨執行將Redis資料存到SQL中
//		Jedis jRedis=new Jedis("localhost",3679);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String metChoice=request.getParameter("metChoice");
		if(metChoice!=null && "clickLike".equals(metChoice))
		{
			System.out.println("若想執行的方法與clickLike相同，執行clicklike()");
				doLikeClick(request,response);
		}else if(metChoice!=null && "commentReport".equals(metChoice))
		{
			doCommentReport(request,response);
		}
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request,response);
	}
	
	private void doLikeClick(HttpServletRequest request,HttpServletResponse response)
	{
		PrintWriter pw=null;
		us=new UserServiceImpl();
//		DiaryLikeVO拿到dlv物件
//		DiaryLikeVO diaryLikeVO=(DiaryLikeVO)request.getAttribute("diarylikeID");
//		Integer diaryLikeID=diaryLikeVO.getDiaryLikeid();
//		FoodDiaryVO物件
		System.out.println("開始執行");
		Integer curDiaryID=Integer.valueOf(request.getParameter("curDiaryID"));
//		UserVO
		Integer curCustID=Integer.valueOf(request.getParameter("curCustID"));
		Integer likeCount=0;
		System.out.println(curDiaryID+"\t"+curCustID);
			if(curDiaryID==0 || curCustID==0 || curDiaryID==null ||curCustID==null)
			{
				System.out.println("沒有得到curDiaryID or curCustID或使用者沒有呼叫此功能!");
			}else {
//				若curDiaryID為空，執行加like，否則執行刪除like。
//				若呼叫此方法的當下，diaryID以及customerID不為空值或非正常值，就將程式正常執行，呼叫點了like的方法。
				likeCount=us.addOrDelLikeService(curDiaryID,curCustID);				
			}
			System.out.println(likeCount);

			try {
				pw=response.getWriter();
				System.out.println(likeCount);
				pw.write(likeCount.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	private Integer doCommentReport(HttpServletRequest request,HttpServletResponse response)
	{
//		custID、reportReason reportResult
		Integer successNum=0;
		String diaryID=request.getParameter("diaryID");
		String custID=request.getParameter("custID");
		String reportReason=request.getParameter("reportReason");
		System.out.println(reportReason);
		successNum=us.serviceCommReport(diaryID, custID, reportReason);
		return successNum;
	}
	
}
