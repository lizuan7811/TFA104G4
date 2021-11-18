package diarylike;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class DiaryLikeJDBCtest {

	public static void main(String[] args) {
		DiaryLikeDAO dao = new DiaryLikeDAOimpl();
		
		// 新增
		DiaryLikeVO like_1 = new DiaryLikeVO();
		like_1.setDiaryLikeid(9);
		like_1.setDiaryid(3);
		like_1.setCustid(2);
		like_1.setCreatedtime(Timestamp.valueOf(LocalDateTime.now()));
		
		dao.insert(like_1);
		System.out.println("新增成功");
		
		// 修改
		DiaryLikeVO like_2 = new DiaryLikeVO();
		like_2.setDiaryLikeid(9);
		like_2.setDiaryid(1);
		like_2.setCustid(2);
		like_2.setCreatedtime(Timestamp.valueOf(LocalDateTime.now()));
		
		dao.update(like_2);
		System.out.println("修改成功");
		
		// 刪除
//		dao.delete(9);
//		System.out.println("刪除成功");
		
		// 查詢
		DiaryLikeVO like_3 = dao.findByPrimaryKey(1);
		System.out.println("按讚ID = " + like_3.getDiaryLikeid());
		System.out.println("日誌ID = " + like_3.getDiaryid());
		System.out.println("會員ID = " + like_3.getCustid());
		System.out.println("創建時間 = " + like_3.getCreatedtime());
		System.out.println("---------------------");
//		
//		// 查詢全部
		List<DiaryLikeVO> list = dao.getAll();
		for (DiaryLikeVO all_like : list) {
			System.out.println("按讚ID = " + all_like.getDiaryLikeid());
			System.out.println("日誌ID = " + all_like.getDiaryid());
			System.out.println("會員ID = " + all_like.getCustid());
			System.out.println("創建時間 = " + all_like.getCreatedtime());
			System.out.println();
		}

	}

}
