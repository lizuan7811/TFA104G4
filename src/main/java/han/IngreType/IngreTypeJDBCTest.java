package han.IngreType;

import java.util.List;

public class IngreTypeJDBCTest {
	public static void main(String[] args) {
		IngreTypeDAO dao = new IngreTypeDAOImpl();
		
		// 新增
//		IngreTypeVO type1 = new IngreTypeVO();
//		type1.setIdIngreType(1);
//		type1.setTypeName("肉類");
//		dao.insert(type1);

		// 修改
//		IngreTypeVO type2 = new IngreTypeVO();
//		type2.setIdIngreType(2);
//		type2.setTypeName("222222");
//		dao.update(type2);

		// 刪除
//		dao.delete(2);

		//查詢
//		IngreTypeVO type3 = dao.findByPK(4);
//		System.out.print(type3.getIdIngreType() + ",");
//		System.out.print(type3.getTypeName() + ",");
//		System.out.println("---------------------");

		//查詢全部
		List<IngreTypeVO> list = dao.getAll();
		for (IngreTypeVO type : list) {
			System.out.print(type.getIdIngreType() + ",");
			System.out.print(type.getTypeName()+ ",");
			System.out.println();
		}
	}

}
