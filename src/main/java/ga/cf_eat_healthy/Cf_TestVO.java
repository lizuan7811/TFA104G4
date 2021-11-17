package ga.cf_eat_healthy;

import java.io.Serializable;

public class Cf_TestVO implements Serializable{
	
	private Integer testno;
	private String tname;
	private String phone;
	
	Cf_TestVO() {
		super();
	}
	
	private Cf_TestVO(Integer testno, String tname, String phone) {
		super();
		this.testno=testno;
		this.tname=tname;
		this.phone=phone;
	}

	public Integer getTestno() {
		return testno;
	}

	public void setTestno(Integer testno) {
		this.testno = testno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
