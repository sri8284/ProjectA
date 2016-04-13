package com.rnei.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class OnRoadResource extends Basic {
	
	private static final long serialVersionUID = 6144529142848164911L;
	
	private String orrId;
	//below variable is used for only onHireORR
	private String orrName;
	//TODO - to be change to boolean
	private String available;
	private String vehicleNo;
	private String drivingLicNo;
	private String concatNo;
	//TODO to be change to char
	private String prevDispactherId;
	private String currDispactherId;
	private String prevPickupId;
	private String currPickupId;
	private String active;
	
	public String getOrrId() {
		return orrId;
	}
	public void setOrrId(String orrId) {
		this.orrId = orrId;
	}
	public String getOrrName() {
		return orrName;
	}
	public void setOrrName(String orrName) {
		this.orrName = orrName;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getDrivingLicNo() {
		return drivingLicNo;
	}
	public void setDrivingLicNo(String drivingLicNo) {
		this.drivingLicNo = drivingLicNo;
	}
	public String getConcatNo() {
		return concatNo;
	}
	public void setConcatNo(String concatNo) {
		this.concatNo = concatNo;
	}
	public String getPrevDispactherId() {
		return prevDispactherId;
	}
	public void setPrevDispactherId(String prevDispactherId) {
		this.prevDispactherId = prevDispactherId;
	}
	public String getCurrDispactherId() {
		return currDispactherId;
	}
	public void setCurrDispactherId(String currDispactherId) {
		this.currDispactherId = currDispactherId;
	}
	public String getPrevPickupId() {
		return prevPickupId;
	}
	public void setPrevPickupId(String prevPickupId) {
		this.prevPickupId = prevPickupId;
	}
	public String getCurrPickupId() {
		return currPickupId;
	}
	public void setCurrPickupId(String currPickupId) {
		this.currPickupId = currPickupId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
		
	
	
}
