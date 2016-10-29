package com.rnei.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_DEFAULT)
public class VendorScheduleItemRate extends Basic {
  private String pickupScheduleId;
  private String itemCode;
  private Float itemExpectedVol;
  private Float discountRate;
  private Date discountEndDate;

  public String getPickupScheduleId() {
    return pickupScheduleId;
  }

  public void setPickupScheduleId(final String pickupScheduleId) {
    this.pickupScheduleId = pickupScheduleId;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(final String itemCode) {
    this.itemCode = itemCode;
  }

  public Float getItemExpectedVol() {
    return itemExpectedVol;
  }

  public void setItemExpectedVol(final Float itemExpectedVol) {
    this.itemExpectedVol = itemExpectedVol;
  }

  public Float getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(final Float discountRate) {
    this.discountRate = discountRate;
  }

  public Date getDiscountEndDate() {
    return discountEndDate;
  }

  public void setDiscountEndDate(final Date discountEndDate) {
    this.discountEndDate = discountEndDate;
  }

}
