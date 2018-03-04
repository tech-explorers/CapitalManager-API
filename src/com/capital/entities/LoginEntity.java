package com.capital.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_details")
public class LoginEntity {

	@Id
	@Column(name = "login_id")
	private String loginid;

	@Column(name = "emailid")
	private String emailId;
	@Column(name = "password")
	private String password;

	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "USERID") private UserEntity userEntity;
	 */

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

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

/*	@Override
	public String toString() {
		return "Ha hA ID : " + loginid + "\n Username : " + emailId + "\n Password : " + password;
	}*/
}
