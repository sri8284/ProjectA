package com.rnei.model;

import java.util.Date;

public class OnRoadResourcePickup extends Pickup {
	private static final long serialVersionUID = -1267971173909097003L;
	private Date fromDate;
	private Date toDate;
	private String orrId;
	
	public String getOrrId() {
		return orrId;
	}
	public void setOrrId(String orrId) {
		this.orrId = orrId;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
}
