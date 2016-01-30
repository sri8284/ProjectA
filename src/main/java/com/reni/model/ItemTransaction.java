package com.reni.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ItemTransaction extends Basic {

	// TODO - why this pickupId since this id presented in pickup model.
	private String pickupId;
	private String itemId;
	// TODO filedname should be readable.
	private Float itemExpectedVol;
	// TODO filedname should be readable.
	private Float itemActualVol;
	private Float itemCurrentRate;
	private Float itemPaidRate;
	// TODO filedname should be readable.
	private Float amtNeedTobePaid;
	private Float partialPayment;

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

	public Float getAmtNeedTobePaid() {
		return amtNeedTobePaid;
	}

	public void setAmtNeedTobePaid(Float amtNeedTobePaid) {
		this.amtNeedTobePaid = amtNeedTobePaid;
	}

	public Float getPartialPayment() {
		return partialPayment;
	}

	public void setPartialPayment(Float partialPayment) {
		this.partialPayment = partialPayment;
	}

}
