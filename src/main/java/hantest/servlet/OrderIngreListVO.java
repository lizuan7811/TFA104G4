package hantest.servlet;

import java.io.Serializable;

public class OrderIngreListVO implements Serializable {
	private int idOrder; //訂單編號
	private int idIngre; //食材編號
	private int orderQuan; //購買數量
	private int price; //單價

	public OrderIngreListVO() {

	}
	
	public OrderIngreListVO(int idOrder, int idIngre, int orderQuan, int price) {
		this.idOrder = idOrder;
		this.idIngre = idIngre;
		this.orderQuan = orderQuan;
		this.price = price;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdIngre() {
		return idIngre;
	}

	public void setIdIngre(int idIngre) {
		this.idIngre = idIngre;
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
