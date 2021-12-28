package com.customer.model;
import java.util.*;


public interface CustomerDAO {
	public void insertCustByAdmin(CustomerVO customerVO);
	public Integer insertCustByCust(CustomerVO customerVO); // customerRegister.jsp
	public void updateCustByAdmin(CustomerVO customerVO);
	public void updateCustByCust(CustomerVO customerVO); // customerBasic.jsp
	public void updateActiByAdmin(Integer idCustomer); // customerRegister.jsp
	public void updatePwdByCust(String account, String password); // customerReset.jsp
	public void deleteCustByAdmin(Integer idCustomer);
	public CustomerVO findCustById(Integer idCustomer);
	public CustomerVO findCustByAccount(String account); // customerLogin.jsp
	public CustomerVO findCustByEmail(String email); // customerForget.jsp
	public List<CustomerVO> getAll();
}
