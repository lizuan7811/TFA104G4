package com.temporder.model;
import java.io.Serializable;
public class TempOrderVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer custID;
	private Integer ingreID;
	private Integer orderQuan;
	private Integer price;
	
	public Integer getCustID() {
		return custID;
	}
	public void setCustID(Integer custID) {
		this.custID = custID;
	}
	public Integer getIngreID() {
		return ingreID;
	}
	public void setIngreID(Integer ingreID) {
		this.ingreID = ingreID;
	}
	public Integer getOrderQuan() {
		return orderQuan;
	}
	public void setOrderQuan(Integer orderQuan) {
		this.orderQuan = orderQuan;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

}
