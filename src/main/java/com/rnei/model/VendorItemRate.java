package com.rnei.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_DEFAULT)
public class VendorItemRate extends Basic{
  
  private String vendorId;
  private String itemCode; 
  private Float discount;
  private Date discountStartDate;
  private Date discountEndDate;
  private Float itemActualRate; 
  private String status;
  
  public String getVendorId() {
    return vendorId;
  }
  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }
  public String getItemCode() {
    return itemCode;
  }
  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }
  public Float getDiscount() {
    return discount;
  }
  public void setDiscount(Float discount) {
    this.discount = discount;
  }
  public Date getDiscountStartDate() {
    return discountStartDate;
  }
  public void setDiscountStartDate(Date discountStartDate) {
    this.discountStartDate = discountStartDate;
  }
  public Date getDiscountEndDate() {
    return discountEndDate;
  }
  public void setDiscountEndDate(Date discountEndDate) {
    this.discountEndDate = discountEndDate;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  
  public Float getItemActualRate() {
    return itemActualRate;
  }
  public void setItemActualRate(Float itemActualRate) {
    this.itemActualRate = itemActualRate;
  }
  
  

}
