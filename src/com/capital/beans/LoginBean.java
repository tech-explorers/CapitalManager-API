package com.capital.beans;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="login_details")
public class LoginBean {

	@Column(name="emailid")
	private String emailId;
	@Column(name="password")
	private String password;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
