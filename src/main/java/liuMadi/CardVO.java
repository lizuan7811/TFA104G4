package liuMadi;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;

public class CardVO implements Serializable {
	private Integer idCard;
	private Integer idCustomer;
	private Boolean type;
	private String number;
	private Date expiryDate;
	private Timestamp createdTime;
	private Boolean defaultOption;

	public CardVO() {

	}

	public Integer getIdCard() {
		return idCard;
	}

	public void setIdCard(Integer idCard) {
		this.idCard = idCard;
	}

	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date date) {
		this.expiryDate = date;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Boolean getDefaultOption() {
		return defaultOption;
	}

	public void setDefaultOption(Boolean defaultOption) {
		this.defaultOption = defaultOption;
	}
	
}
