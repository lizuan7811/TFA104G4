package madi.model.Address;

import java.sql.Timestamp;
import java.util.List;

public class AddressJDBCTest {
	
	public static void main(String[] args) {
		AddressDAOImpl dao = new AddressDAOImpl();
		
		// add
		AddressVO addr1 = new AddressVO();
		addr1.setIdAddress(1);
		addr1.setIdCustomer(1);
		addr1.setAddress("台北市松山區復興南路一段");
		addr1.setTag("工作");
		addr1.setLongitude(100.23);
		addr1.setLatitude(50.23);
		addr1.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
		addr1.setDefaultOption(true);
		dao.insert(addr1);
		System.out.println("新增成功");
		
		// update
		AddressVO addr2 = new AddressVO();
		addr2.setIdAddress(2);
		addr2.setIdCustomer(2);
		addr2.setAddress("台北市信義區");
		addr2.setTag("家");
		addr2.setLongitude(120.23);
		addr2.setLatitude(71.231);
		addr2.setCreatedTime(new Timestamp(new java.util.Date().getTime() + 1));
		addr2.setDefaultOption(true);
		dao.update(addr2);
		System.out.println("修改成功");
		
		// delete
//		dao.delete(2);
//		System.out.println("刪除成功");
		
		// findByPK
		AddressVO addr3 = dao.findByPK(1);
		System.out.print(addr3.getIdAddress() + ",");
		System.out.print(addr3.getIdCustomer() + ",");
		System.out.print(addr3.getAddress() + ",");
		System.out.print(addr3.getTag() + ",");
		System.out.print(addr3.getLongitude() + ",");
		System.out.print(addr3.getLatitude() + ",");
		System.out.print(addr3.getCreatedTime() + ",");
		System.out.println(addr3.getDefaultOption());
		System.out.println("---------------------");
		
		// GetAll
		List<AddressVO> list = dao.getAll();
		for (AddressVO addr : list) {
			System.out.print(addr.getIdAddress() + ",");
			System.out.print(addr.getIdCustomer() + ",");
			System.out.print(addr.getAddress() + ",");
			System.out.print(addr.getTag() + ",");
			System.out.print(addr.getLongitude() + ",");
			System.out.print(addr.getLatitude() + ",");
			System.out.print(addr.getCreatedTime() + ",");
			System.out.print(addr.getDefaultOption());
			System.out.println();
		}
		
	}
}
