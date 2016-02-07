package com.reni.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_DEFAULT)
public class Vendor extends Basic {

	private String vendorId;
	private String vndfirstName;
	private String vndlastName;
	private String vndContactNo;
	private Address vndAddress;
	private String areaCode;
	
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVndfirstName() {
		return vndfirstName;
	}
	public void setVndfirstName(String vndfirstName) {
		this.vndfirstName = vndfirstName;
	}
	public String getVndlastName() {
		return vndlastName;
	}
	public void setVndlastName(String vndlastName) {
		this.vndlastName = vndlastName;
	}
	public String getVndContactNo() {
		return vndContactNo;
	}
	public void setVndContactNo(String vndContactNo) {
		this.vndContactNo = vndContactNo;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Address getVndAddress() {
		return vndAddress;
	}
	public void setVndAddress(Address vndAddress) {
		this.vndAddress = vndAddress;
	}
	
	
}
