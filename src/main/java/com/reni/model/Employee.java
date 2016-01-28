package com.reni.model;

import java.io.Serializable;


public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -593908228082118135L;
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String roleType;
	private String emailId;
	private String sessionId;
	private Integer sessionExpire;
	
	public Integer getUserId() {
		return employeeId;
	}
	public void setUserId(final Integer userId) {
		this.employeeId = userId;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(final String roleType) {
		this.roleType = roleType;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getSessionExpire() {
		return sessionExpire;
	}
	public void setSessionExpire(Integer sessionExpire) {
		this.sessionExpire = sessionExpire;
	}

	
}

