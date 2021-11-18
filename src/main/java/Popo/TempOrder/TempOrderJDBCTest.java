package po;

import java.util.List;

public class TempOrderJDBCTest {
	public static void main(String[] args) {
		TempOrderDAO dao = new TempOrderDAOImpl();

//		新增
//		TempOrder tem = new TempOrder();
//		tem.setCustID(7016);
//		tem.setIngrelID(1001);
//		tem.setOrderQuan(1005);
//		tem.setPrice(1006);
//		dao.add(tem);

//		// 修改
		TempOrderVO tem2 = new TempOrderVO();
		tem2.setCustID(7016);
		tem2.setIngrelID(1006);
		tem2.setOrderQuan(1005);
		tem2.setPrice(1006);
		dao.update(tem2);

//		// 刪除
		dao.delete(7016);

//		// 查詢
		TempOrderVO tem3 = dao.findByPK(7016, 0);
		System.out.print(tem3.getCustID() + ",");
		System.out.print(tem3.getIngrelID() + ",");
		System.out.print(tem3.getOrderQuan() + ",");
		System.out.print(tem3.getPrice() + ",");
		System.out.println("---------------------");

//		// 查詢
		List<TempOrderVO> list = dao.getAll();
		for (TempOrderVO tem4 : list) {
			System.out.print(tem4.getCustID() + ",");
			System.out.print(tem4.getIngrelID() + ",");
			System.out.print(tem4.getOrderQuan() + ",");
			System.out.print(tem4.getPrice() + ",");
			System.out.println();
		}
	}
}
