package po;

import java.io.Serializable;
import java.sql.Date;

public class TempOrderVO implements Serializable{
	private int custID;
	private int IngrelID;
	private int orderQuan;
	private int price;


	public TempOrderVO() {
		}


	public TempOrderVO(int custID, int ingrelID, int orderQuan, int price) {
		this.custID = custID;
		this.IngrelID = ingrelID;
		this.orderQuan = orderQuan;
		this.price = price;
	}


	public int getCustID() {
		return custID;
	}


	public void setCustID(int custID) {
		this.custID = custID;
	}


	public int getIngrelID() {
		return IngrelID;
	}


	public void setIngrelID(int ingrelID) {
		this.IngrelID = ingrelID;
	}


	public int getOrderQuan() {
		return orderQuan;
	}


	public void setOrderQuan(int orderQuan) {
		this.orderQuan = orderQuan;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}