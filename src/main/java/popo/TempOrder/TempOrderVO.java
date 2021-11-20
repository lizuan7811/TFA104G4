package popo.TempOrder;

import java.io.Serializable;
import java.sql.Date;

public class TempOrderVO implements Serializable{
	private Integer custID;
	private Integer IngrelID;
	private Integer orderQuan;
	private Integer price;


	public TempOrderVO() {
		}


	public TempOrderVO(Integer custID, Integer ingrelID, Integer orderQuan, Integer price) {
		this.custID = custID;
		this.IngrelID = ingrelID;
		this.orderQuan = orderQuan;
		this.price = price;
	}


	public Integer getCustID() {
		return custID;
	}


	public void setCustID(Integer custID) {
		this.custID = custID;
	}


	public Integer getIngrelID() {
		return IngrelID;
	}


	public void setIngrelID(Integer ingrelID) {
		this.IngrelID = ingrelID;
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