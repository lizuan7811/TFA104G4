package model.FinalOrder;

import java.io.Serializable;
import java.sql.Timestamp;

public class FinalOrderVO implements Serializable{
	private Integer idFinalOrder;
	private Integer idCustomer;
	private String recipient;
	private String recipientAddress;
	private Double orderAmount;
	private Timestamp createdTime;
	private Timestamp shipTime;
	private Timestamp arrivalTime;
	private String footnote;

	public FinalOrderVO() {
		
	}

	public Integer getIdFinalOrder() {
		return idFinalOrder;
	}

	public void setIdFinalOrder(Integer idFinalOrder) {
		this.idFinalOrder = idFinalOrder;
	}

	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getShipTime() {
		return shipTime;
	}

	public void setShipTime(Timestamp shipTime) {
		this.shipTime = shipTime;
	}

	public Timestamp getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getFootnote() {
		return footnote;
	}

	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}
	
}
