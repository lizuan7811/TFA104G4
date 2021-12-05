package han.IngreType;

import java.io.Serializable;


public class IngreTypeVO implements Serializable{
	private Integer idIngreType; //類別編號
	private String typeName; //食材類別名稱
	
	public IngreTypeVO() {
	}
	
	public IngreTypeVO(int idIngreType, String typeName) {
		this.idIngreType = idIngreType;
		this.typeName = typeName;
	}

	public int getIdIngreType() {
		return idIngreType;
	}

	public void setIdIngreType(int idIngreType) {
		this.idIngreType = idIngreType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
