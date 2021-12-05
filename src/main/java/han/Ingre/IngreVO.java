package han.Ingre;

import java.io.Serializable;
import java.math.BigDecimal;

public class IngreVO implements Serializable {
	private Integer idIngre; // 食材編號
	private Integer idIngreType; // 食材類別編號
	private String name; // 食材品名
	private BigDecimal purPrice; // 進貨價格
	private BigDecimal price; // 單價
	private String unit; // 單位
	private Integer gran; // 克
	private Integer sell; // 銷售數量
	private String descrip; // 食品描述
	private byte[] photo; // 圖片
	private Boolean launch; // 上架狀態\n0:未上架\n1:上架
	
	public IngreVO() {

	}

	public IngreVO(int idIngre, int idIngreType, String name, BigDecimal purPrice, BigDecimal price, String unit, int gran, int sell, String descrip,
			byte[] photo, boolean launch) {
		this.idIngre = idIngre;
		this.idIngreType = idIngreType;
		this.name = name;
		this.purPrice = purPrice;
		this.price = price;
		this.unit = unit;
		this.gran = gran;
		this.sell = sell;
		this.descrip = descrip;
		this.photo = photo;
		this.launch = launch;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getGran() {
		return gran;
	}

	public void setGran(int gran) {
		this.gran = gran;
	}

	

	

	public int getIdIngre() {
		return idIngre;
	}

	public void setIdIngre(int idIngre) {
		this.idIngre = idIngre;
	}

	public int getIdIngreType() {
		return idIngreType;
	}

	public void setIdIngreType(int idIngreType) {
		this.idIngreType = idIngreType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(BigDecimal purPrice) {
		this.purPrice = purPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getSell() {
		return sell;
	}

	public void setSell(int sell) {
		this.sell = sell;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public boolean getLaunch() {
		return launch;
	}

	public void setLaunch(boolean launch) {
		this.launch = launch;
	}
	
	
}
