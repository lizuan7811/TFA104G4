package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AddressVO implements Serializable {
	private Integer idAddress;
	private Integer idCustomer;
	private String address;
	private String tag;
	private Double longitude;
	private Double latitude;
	private Timestamp createdTime;
	private Boolean defaultOption; 
	
	public AddressVO() {
		
	}
	
	public AddressVO(Integer idAddress, Integer idCustomer, 
			String address, String tag, Double longtitude, 
			Double latitude, Timestamp createdTime, Boolean defaultOption) {
		this.idAddress = idAddress;
		this.idCustomer = idCustomer;
		this.address = address;
		this.tag = tag;
		this.longitude = longtitude; 
        this.latitude = latitude;
        this.createdTime = createdTime;
        this.defaultOption = defaultOption;
	}

	public Integer getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}

	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longtitude) {
		this.longitude = longtitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
