package hantest.servlet;

import java.util.List;

public class OrderIngreListJDBCTest {
	public static void main(String[] args) {
		OrderIngreListDAO dao = new OrderIngreListDAOImpl();
		
		// 新增
		OrderIngreListVO orderil1 = new OrderIngreListVO();
		orderil1.setIdOrder(5);
		orderil1.setIdIngre(2);
		orderil1.setOrderQuan(4);
		orderil1.setPrice(4);
		dao.insert(orderil1);
		
		// 修改
//		OrderIngreListVO orderil2 = new OrderIngreListVO();
//		orderil2.setIdOrder(4);
//		orderil2.setIdIngre(4);
//		orderil2.setOrderQuan(5);
//		orderil2.setPrice(5);
//		dao.update(orderil2);
		
		// 刪除
//		dao.delete(5,5);
		
		//查詢
//		OrderIngreListVO orderil3 = dao.findByPK(5,4);
//		System.out.print(orderil3.getIdOrder() + ",");
//		System.out.print(orderil3.getIdIngre() + ",");
//		System.out.print(orderil3.getOrderQuan() + ",");
//		System.out.print(orderil3.getPrice() + ",");
//		System.out.println("---------------------");
		
		//查詢全部
		List<OrderIngreListVO> list = dao.getAll();
		for (OrderIngreListVO orderil : list) {
			System.out.print(orderil.getIdOrder() + ",");
			System.out.print(orderil.getIdIngre() + ",");
			System.out.print(orderil.getOrderQuan() + ",");
			System.out.print(orderil.getPrice() + ",");
			System.out.println();
		}
		
	}
}
