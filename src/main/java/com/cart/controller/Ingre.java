package com.cart.controller;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ingre implements Serializable {
	private Integer idIngre;
	private String name;
	private String descrip;
	private String photo;
	private BigDecimal price;
	private int quantity;
	
	public Ingre(){
		idIngre=null;
		name = "";
		descrip = "";
		photo = null;
		price = new BigDecimal(0);
		quantity = 0;		
	}
	public Double getPriceF() {
		return Double.valueOf(price.toString());
	}
	
	public Integer getIdIngre() {
		return idIngre;
	}



	public void setIdIngre(Integer idIngre) {
		this.idIngre = idIngre;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
