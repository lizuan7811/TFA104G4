package model;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class FinalOrderJDBCTest {
	
	public static void main(String[] args) {
		FinalOrderDAOImpl dao = new FinalOrderDAOImpl();

		// add
		FinalOrderVO finalOrder1 = new FinalOrderVO();
		finalOrder1.setIdFinalOrder(1);
		finalOrder1.setIdCustomer(1);
		finalOrder1.setRecipient("小王");
		finalOrder1.setRecipientAddress("屏東縣你好路");
		finalOrder1.setOrderAmount(10000.0);
		finalOrder1.setCreatedTime(new Timestamp(new Date().getTime()));
		finalOrder1.setShipTime(new Timestamp(new Date().getTime())); 
		finalOrder1.setArrivalTime(new Timestamp(new Date().getTime()));
		finalOrder1.setFootnote("沒有備註");
		dao.insert(finalOrder1);
		System.out.println("新增成功");
		
		// update
		FinalOrderVO finalOrder2 = new FinalOrderVO();
		finalOrder2.setIdFinalOrder(2);
		finalOrder2.setIdCustomer(2);
		finalOrder2.setRecipient("大名");
		finalOrder2.setRecipientAddress("說好一起走");
		finalOrder2.setOrderAmount(2345.0);
		finalOrder2.setCreatedTime(new Timestamp(new Date().getTime()));
		finalOrder2.setShipTime(new Timestamp(new Date().getTime()));
		finalOrder2.setArrivalTime(new Timestamp(new Date().getTime()));
		finalOrder2.setFootnote("沒有備註");
		dao.update(finalOrder2);
		System.out.println("修改成功");
		
		// delete
//		dao.delete(2);
//		System.out.println("刪除成功");
		
		// findByPK
		FinalOrderVO finalOrder3 = dao.findByPK(1);
		System.out.print(finalOrder3.getIdFinalOrder() + ",");
		System.out.print(finalOrder3.getIdCustomer() + ",");
		System.out.print(finalOrder3.getRecipient() + ",");
		System.out.print(finalOrder3.getRecipientAddress() + ",");
		System.out.print(finalOrder3.getOrderAmount() + ",");
		System.out.print(finalOrder3.getCreatedTime() + ",");
		System.out.print(finalOrder3.getShipTime() + ",");
		System.out.print(finalOrder3.getArrivalTime() + ",");
		System.out.println(finalOrder3.getFootnote());
		System.out.println("---------------------");
		
		// GetAll
		List<FinalOrderVO> finalOrderList = dao.getAll();
		for (FinalOrderVO finalOrder : finalOrderList) {
			System.out.print(finalOrder.getIdFinalOrder() + ",");
			System.out.print(finalOrder.getIdCustomer() + ",");
			System.out.print(finalOrder3.getRecipient() + ",");
			System.out.print(finalOrder3.getRecipientAddress() + ",");
			System.out.print(finalOrder3.getOrderAmount() + ",");
			System.out.print(finalOrder3.getCreatedTime() + ",");
			System.out.print(finalOrder3.getShipTime() + ",");
			System.out.print(finalOrder3.getArrivalTime() + ",");
			System.out.println(finalOrder3.getFootnote());
			System.out.println();
		}
	}
}
