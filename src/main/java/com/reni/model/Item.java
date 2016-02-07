package com.reni.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class Item extends Basic {
	
	private String itemCode;
	private String itemName;
	private Float currentRate;
	
	
	
	public Float getCurrentRate() {
		return currentRate;
	}
	public void setCurrentRate(Float currentRate) {
		this.currentRate = currentRate;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
