package com.rnei.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_DEFAULT)
public class Vendor extends Basic {

  private String vendorId;
  private String vndfirstName;
  private String vndlastName;
  private String vndContactNo;
  private Address vndAddress;
  private String areaCode;
  private String vendorType;
  private String status;

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(final String vendorId) {
    this.vendorId = vendorId;
  }

  public String getVndfirstName() {
    return vndfirstName;
  }

  public void setVndfirstName(final String vndfirstName) {
    this.vndfirstName = vndfirstName;
  }

  public String getVndlastName() {
    return vndlastName;
  }

  public void setVndlastName(final String vndlastName) {
    this.vndlastName = vndlastName;
  }

  public String getVndContactNo() {
    return vndContactNo;
  }

  public void setVndContactNo(final String vndContactNo) {
    this.vndContactNo = vndContactNo;
  }

  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(final String areaCode) {
    this.areaCode = areaCode;
  }

  public Address getVndAddress() {
    return vndAddress;
  }

  public void setVndAddress(final Address vndAddress) {
    this.vndAddress = vndAddress;
  }

  public String getVendorType() {
    return vendorType;
  }

  public void setVendorType(final String vendorType) {
    this.vendorType = vendorType;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
