package com.reni.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ItemTransaction extends Basic {

	private static final long serialVersionUID = 6696013968199929145L;
	private String pickupId;
	private String itemId;
	private Float itemExpectedVol;
	private Float itemActualVol;
	private Float itemCurrentRate;
	private Float itemPaidRate;
	private Float totalAmount;

	public String getPickupId() {
		return pickupId;
	}

	public void setPickupId(String pickupId) {
		this.pickupId = pickupId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Float getItemExpectedVol() {
		return itemExpectedVol;
	}

	public void setItemExpectedVol(Float itemExpectedVol) {
		this.itemExpectedVol = itemExpectedVol;
	}

	public Float getItemActualVol() {
		return itemActualVol;
	}

	public void setItemActualVol(Float itemActualVol) {
		this.itemActualVol = itemActualVol;
	}

	public Float getItemCurrentRate() {
		return itemCurrentRate;
	}

	public void setItemCurrentRate(Float itemCurrentRate) {
		this.itemCurrentRate = itemCurrentRate;
	}

	public Float getItemPaidRate() {
		return itemPaidRate;
	}

	public void setItemPaidRate(Float itemPaidRate) {
		this.itemPaidRate = itemPaidRate;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

}
