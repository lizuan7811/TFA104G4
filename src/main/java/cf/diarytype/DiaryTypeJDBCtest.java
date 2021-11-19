package cf.diarytype;

import java.util.List;

public class DiaryTypeJDBCtest {

	public static void main(String[] args) {
		DiaryTypeDAO dao = new DiaryTypeDAOimpl();
		
		// 新增
		DiaryTypeVO type_1 = new DiaryTypeVO();
		type_1.setDiaryTypeID(8);
		type_1.setDiaryTypeName("新增類別A");
		dao.insert(type_1);
		System.out.println("新增成功");
		
		// 修改
		DiaryTypeVO type_2 = new DiaryTypeVO();
		type_2.setDiaryTypeID(8);
		type_2.setDiaryTypeName("修改為類別B");
		dao.update(type_2);
		System.out.println("修改成功");
		
		// 刪除
//		dao.delete(8);
//		System.out.println("刪除成功");
		
		// 查詢
		DiaryTypeVO type_3 = dao.findByPrimaryKey(1);
		System.out.println("日誌分類ID = " + type_3.getDiaryTypeID());
		System.out.println("日誌類別名稱 = " + type_3.getDiaryTypeName());
		System.out.println("---------------------");
		
		// 查詢全部
		List<DiaryTypeVO> list = dao.getAll();
		for (DiaryTypeVO all_type : list) {
			System.out.println("日誌分類ID = " + all_type.getDiaryTypeID());
			System.out.println("日誌類別名稱 = " + all_type.getDiaryTypeName());
			System.out.println();
		}
//
	}

}
