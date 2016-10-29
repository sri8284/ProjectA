package com.rnei.model;

import java.util.List;

/**
 * @author Samba Shiva
 *
 */
public class EmployeeBean extends Basic {
	private Integer id;
	private String firstName;
	private String lastName;
	private Long mobile;
	private String empType;
	private String emailId;
	private String dob;
	private String dateOfJoin;
	private String lastWorkingDt;
	private List<Address> addresses;	
	private String aadharNumber;
	private String drivingLsNumber;
	private String panCardNumber;
	private Bank bank;
	private String deleteFlag;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	public String getLastWorkingDt() {
		return lastWorkingDt;
	}
	public void setLastWorkingDt(String lastWorkingDt) {
		this.lastWorkingDt = lastWorkingDt;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getDrivingLsNumber() {
		return drivingLsNumber;
	}
	public void setDrivingLsNumber(String drivingLsNumber) {
		this.drivingLsNumber = drivingLsNumber;
	}
	public String getPanCardNumber() {
		return panCardNumber;
	}
	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
