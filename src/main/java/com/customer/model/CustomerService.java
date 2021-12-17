package com.customer.model;

import java.sql.Timestamp;
import java.util.List;

public class CustomerService {
	
	private CustomerDAO dao;
	
	public CustomerService() {
		dao = new CustomerDAOImpl();
	}
	
	// 後端管理員
	public CustomerVO insertCustByAdmin(Integer idCustomer, String name, String nickname, String account, String password, String email, 
			String phone, Boolean notification, byte[] profic, Timestamp createdTime, Boolean activated, Boolean suspended, 
			Integer externalAcc, String externalIdToken, Integer commentReportedNum, Integer diaryReportedNum) {
		
		CustomerVO custVO = new CustomerVO();
		
		custVO.setIdCustomer(idCustomer);
		custVO.setName(name);
		custVO.setNickname(nickname);
		custVO.setAccount(account);
		custVO.setPassword(password);
		custVO.setEmail(email);
		custVO.setPhone(phone);
		custVO.setNotification(notification);
		custVO.setProfic(profic);
		custVO.setCreatedTime(createdTime);
		custVO.setActivated(activated);
		custVO.setSuspended(suspended);
		custVO.setExternalAcc(externalAcc);
		custVO.setExternalIdToken(externalIdToken);
		custVO.setCommentReportedNum(commentReportedNum);
		custVO.setDiaryReportedNum(diaryReportedNum);
		dao.insertCustByAdmin(custVO);
		
		return custVO;
	}
	
	// 預留給 Struts 2 用的
	public void insertCustByAdmin(CustomerVO custVO) {
		dao.insertCustByAdmin(custVO);
	}
	
	// customerPreRegister.jsp & customerRegister.jsp
	public CustomerVO insertCustByCust(String name, String nickname, String account, String password, String email, 
			String phone, Boolean notification, byte[] profic, Timestamp createdTime, Boolean activated, Boolean suspended, 
			Integer externalAcc, String externalIdToken, Integer commentReportedNum, Integer diaryReportedNum) {
		
		CustomerVO custVO = new CustomerVO();
		
		custVO.setName(name);
		custVO.setNickname(nickname);
		custVO.setAccount(account);
		custVO.setPassword(password);
		custVO.setEmail(email);
		custVO.setPhone(phone);
		custVO.setNotification(notification);
		custVO.setProfic(profic);
		custVO.setCreatedTime(createdTime);
		custVO.setActivated(activated);
		custVO.setSuspended(suspended);
		custVO.setExternalAcc(externalAcc);
		custVO.setExternalIdToken(externalIdToken);
		custVO.setCommentReportedNum(commentReportedNum);
		custVO.setDiaryReportedNum(diaryReportedNum);
		Integer idCustomer = dao.insertCustByCust(custVO);
		custVO.setIdCustomer(idCustomer);
		return custVO;
	}
	
	// 預留給 Struts 2 用的
	public void insertCustByCust(CustomerVO custVO) {
		dao.insertCustByCust(custVO);
	}
	
	// 後端管理員
	public CustomerVO updateCustByAdmin(Integer idCustomer, String name, String nickname, String account, String password, 
			String email, String phone, Boolean notification, byte[] profic, Timestamp createdTime, Boolean activated, Boolean suspended, 
			Integer externalAcc, String externalIdToken, Integer commentReportedNum, Integer diaryReportedNum) {
		
		CustomerVO custVO = new CustomerVO();
		
		custVO.setIdCustomer(idCustomer);
		custVO.setName(name);
		custVO.setNickname(nickname);
		custVO.setAccount(account);
		custVO.setPassword(password);
		custVO.setEmail(email);
		custVO.setPhone(phone);
		custVO.setNotification(notification);
		custVO.setProfic(profic);
		custVO.setCreatedTime(createdTime);
		custVO.setActivated(activated);
		custVO.setSuspended(suspended);
		custVO.setExternalAcc(externalAcc);
		custVO.setExternalIdToken(externalIdToken);
		custVO.setCommentReportedNum(commentReportedNum);
		custVO.setDiaryReportedNum(diaryReportedNum);
		dao.updateCustByAdmin(custVO);
		
		return custVO;
	}
	
	//預留給 Struts 2 用的
	public void updateCustByAdmin(CustomerVO custVO) {
		dao.updateCustByAdmin(custVO);
	}
	
	// customerBasic.jsp
	public CustomerVO updateCustByCust(String name, String nickname, String account, String password, 
			String email, String phone, Boolean notification, byte[] profic, Timestamp createdTime) {
		
		CustomerVO custVO = new CustomerVO();

		custVO.setName(name);
		custVO.setNickname(nickname);
		custVO.setAccount(account); 
		custVO.setPassword(password);
		custVO.setEmail(email);
		custVO.setPhone(phone);
		custVO.setNotification(notification);
		custVO.setProfic(profic);
		custVO.setCreatedTime(createdTime);
		dao.updateCustByCust(custVO);
		
		return custVO;
	}
	
	// 預留給 Struts 2 用的
	public void updateCustByCust(CustomerVO custVO) {
		dao.updateCustByCust(custVO);
	}
	
	// customerRegister.jsp
	public void updateActiByAdmin(Integer idCustomer) {
		dao.updateActiByAdmin(idCustomer);
	}
	
	// customerReset.jsp
	public void updatePwdByCust(String account, String password) {
		dao.updatePwdByCust(account, password);
	}
	
	// 後端管理員
	public void deleteCustByAdmin(Integer idCustomer) {
		dao.deleteCustByAdmin(idCustomer);
	}
	
	// 後端管理員
	public CustomerVO getCustById(Integer idCustomer) {
		return dao.findCustById(idCustomer);
	}
	
	// customerLogin.jsp
	public CustomerVO getCustByAccount(String account) { 
		return dao.findCustByAccount(account);
	}
	
	// customerForget.jsp
	public CustomerVO getCustByEmail(String email) { 
		return dao.findCustByEmail(email);
	}
	
	public List<CustomerVO> getAll(){
		return dao.getAll();
	}

}
