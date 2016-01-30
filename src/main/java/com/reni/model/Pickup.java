package com.reni.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class Pickup extends Basic {

	private static final long serialVersionUID = 3856330394507432661L;
	private String pickupId;
	private Date pickupDate;
	private String assignmentType;
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
	private String comments;
	private List<ItemTransaction> itemTransactions;
	
	
	
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
	public String getAssignmentType() {
		return assignmentType;
	}
	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
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
	public List<ItemTransaction> getItemTransactions() {
		return itemTransactions;
	}
	public void setItemTransactions(List<ItemTransaction> itemTransactions) {
		this.itemTransactions = itemTransactions;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
