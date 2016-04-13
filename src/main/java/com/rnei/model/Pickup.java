package com.rnei.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class Pickup extends Basic {

	private static final long serialVersionUID = 3856330394507432661L;
	private String pickupId;
	private Date pickupDate;
	private Time pickupTime;
	private String vendorId;
	private String  orrId;
	private String pickupStatus;
	private Float totalPayment;
	private Float paritalPayment;
	private Float balancePayment;
	private String complete;
	private Integer vehicaleOpenMeter;
	private Integer vehicaleColseMeter;
	private LocalDateTime pickupOpenTime;
	private LocalDateTime pickupCloseTime;
	private String comments;
	private String areaCode;
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
	public Time getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(Time pickupTime) {
		this.pickupTime = pickupTime;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getOrrId() {
		return orrId;
	}
	public void setOrrId(String orrId) {
		this.orrId = orrId;
	}
	public String getPickupStatus() {
		return pickupStatus;
	}
	public void setPickupStatus(String pickupStatus) {
		this.pickupStatus = pickupStatus;
	}
	public Float getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(Float totalPayment) {
		this.totalPayment = totalPayment;
	}
	public Float getParitalPayment() {
		return paritalPayment;
	}
	public void setParitalPayment(Float paritalPayment) {
		this.paritalPayment = paritalPayment;
	}
	public Float getBalancePayment() {
		return balancePayment;
	}
	public void setBalancePayment(Float balancePayment) {
		this.balancePayment = balancePayment;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public Integer getVehicaleOpenMeter() {
		return vehicaleOpenMeter;
	}
	public void setVehicaleOpenMeter(Integer vehicaleOpenMeter) {
		this.vehicaleOpenMeter = vehicaleOpenMeter;
	}
	public Integer getVehicaleColseMeter() {
		return vehicaleColseMeter;
	}
	public void setVehicaleColseMeter(Integer vehicaleColseMeter) {
		this.vehicaleColseMeter = vehicaleColseMeter;
	}
	public LocalDateTime getPickupOpenTime() {
		return pickupOpenTime;
	}
	public void setPickupOpenTime(LocalDateTime pickupOpenTime) {
		this.pickupOpenTime = pickupOpenTime;
	}
	public LocalDateTime getPickupCloseTime() {
		return pickupCloseTime;
	}
	public void setPickupCloseTime(LocalDateTime pickupCloseTime) {
		this.pickupCloseTime = pickupCloseTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<ItemTransaction> getItemTransactions() {
		return itemTransactions;
	}
	public void setItemTransactions(List<ItemTransaction> itemTransactions) {
		this.itemTransactions = itemTransactions;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	
}
