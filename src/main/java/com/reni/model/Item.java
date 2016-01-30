package com.reni.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class Item extends Basic {
	
	private static final long serialVersionUID = -47447587198171592L;
	private String itemId;
	private String itemDesc;
	private Float currentRate;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public Float getCurrentRate() {
		return currentRate;
	}
	public void setCurrentRate(Float currentRate) {
		this.currentRate = currentRate;
	}
	
}
