package com.reni.model;

public class Login {

	private Integer userId;
	private String password;
	private String iMEINumber;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(final Integer userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public String getiMEINumber() {
		return iMEINumber;
	}
	public void setiMEINumber(String iMEINumber) {
		this.iMEINumber = iMEINumber;
	}
	
	
}
