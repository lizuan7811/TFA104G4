package madi.model.Card;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class CardJDBCTest {

	public static void main(String[] args) {
		CardDAOImpl dao = new CardDAOImpl();

		// add
		CardVO card1 = new CardVO();
		card1.setIdCard(1);
		card1.setIdCustomer(1);
		card1.setType(true);
		card1.setNumber("1234-5678-9012-3456");
		card1.setExpiryDate(new java.sql.Date(new java.util.Date().getTime()));
		card1.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
		card1.setDefaultOption(false);
		dao.insert(card1);
		System.out.println("新增成功");
		
		// update
		CardVO card2 = new CardVO();
		card2.setIdCard(2);
		card2.setIdCustomer(2);
		card2.setType(false);
		card2.setNumber("3241-4343-6453-4634");
		card2.setExpiryDate(new java.sql.Date(new java.util.Date().getTime()));
		card2.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
		card2.setDefaultOption(true);
		dao.update(card2);
		System.out.println("修改成功");
		
		// delete
//		dao.delete(2);
//		System.out.println("刪除成功");
		
		// findByPK
		CardVO card3 = dao.findByPK(1);
		System.out.print(card3.getIdCard() + ",");
		System.out.print(card3.getIdCustomer() + ",");
		System.out.print(card3.getType() + ",");
		System.out.print(card3.getNumber() + ",");
		System.out.print(card3.getExpiryDate() + ",");
		System.out.print(card3.getCreatedTime() + ",");
		System.out.println(card3.getDefaultOption());
		System.out.println("---------------------");
		
		// GetAll
		List<CardVO> list = dao.getAll();
		for (CardVO card : list) {
			System.out.print(card.getIdCard() + ",");
			System.out.print(card.getIdCustomer() + ",");
			System.out.print(card.getType() + ",");
			System.out.print(card.getNumber() + ",");
			System.out.print(card.getExpiryDate() + ",");
			System.out.print(card.getCreatedTime() + ",");
			System.out.print(card.getDefaultOption());
			System.out.println();
		}
	}
}
