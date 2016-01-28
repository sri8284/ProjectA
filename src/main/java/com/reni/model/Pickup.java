package com.reni.model;

import java.util.Date;


public class Pickup extends Basic {

	private String pickupId;
	private Date pickupDate;
	private String assignementType;
	// TODO understand the variable name and rename accordingly
	private Long orr;
	// TODO understand the variable name and rename accordingly
	private String others;
	private String vendorId;
	private String vehicleNo;
	private Float totalPaidAmount;
	private Float remainPayAmount;
	// TODO understand the variable name and rename accordingly
	private Integer openMeter;
	// TODO understand the variable name and rename accordingly
	private Integer colseMeter;
	private String complete;
	private ItemTransaction item;
	
	
	
	public String getPickupId() {
		return pickupId;
	}
	public void setPickupId(String pickupId) {
		this.pickupId = pickupId;
	}
	public Date getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	public String getAssignementType() {
		return assignementType;
	}
	public void setAssignementType(String assignementType) {
		this.assignementType = assignementType;
	}
	public Long getOrr() {
		return orr;
	}
	public void setOrr(Long orr) {
		this.orr = orr;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public Float getTotalPaidAmount() {
		return totalPaidAmount;
	}
	public void setTotalPaidAmount(Float totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}
	public Float getRemainPayAmount() {
		return remainPayAmount;
	}
	public void setRemainPayAmount(Float remainPayAmount) {
		this.remainPayAmount = remainPayAmount;
	}
	public Integer getOpenMeter() {
		return openMeter;
	}
	public void setOpenMeter(Integer openMeter) {
		this.openMeter = openMeter;
	}
	public Integer getColseMeter() {
		return colseMeter;
	}
	public void setColseMeter(Integer colseMeter) {
		this.colseMeter = colseMeter;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public ItemTransaction getItem() {
		return item;
	}
	public void setItem(ItemTransaction item) {
		this.item = item;
	}

	
}
