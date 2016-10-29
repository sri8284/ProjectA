package com.rnei.model;


import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_DEFAULT)
public class VendorSchedule extends Basic {
  private String pickupScheduleId;
  private String areaCode;
  private String vendorId;
  private Date scheduleDate;
  private Time scheduleTime;
  //TODO - we can give the targetDate to user?
  private Date vendorTargetDate;
  private Time vendorTargetTime;
  private String status;
  private List<VendorScheduleItemRate> items;

  public String getPickupScheduleId() {
    return pickupScheduleId;
  }

  public void setPickupScheduleId(final String pickupScheduleId) {
    this.pickupScheduleId = pickupScheduleId;
  }

  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(final String areaCode) {
    this.areaCode = areaCode;
  }

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(final String vendorId) {
    this.vendorId = vendorId;
  }

  public Date getScheduleDate() {
    return scheduleDate;
  }

  public void setScheduleDate(final Date scheduleDate) {
    this.scheduleDate = scheduleDate;
  }

  public Time getScheduleTime() {
    return scheduleTime;
  }

  public void setScheduleTime(final Time scheduleTime) {
    this.scheduleTime = scheduleTime;
  }

  public Date getVendorTargetDate() {
    return vendorTargetDate;
  }

  public void setVendorTargetDate(final Date vendorTargetDate) {
    this.vendorTargetDate = vendorTargetDate;
  }

  public Time getVendorTargetTime() {
    return vendorTargetTime;
  }

  public void setVendorTargetTime(final Time vendorTargetTime) {
    this.vendorTargetTime = vendorTargetTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(final String status) {
    this.status = status;
  }

  public List<VendorScheduleItemRate> getItems() {
    return items;
  }

  public void setItems(List<VendorScheduleItemRate> items) {
    this.items = items;
  }

  }
