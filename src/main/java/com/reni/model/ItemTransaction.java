package com.reni.model;

public class ItemTransaction extends Basic {

//TODO - why this pickupId since this id presented in pickup model.
	private String pickupId;
	private String itemCode;
	private Long itemTypeSeq;
	//TODO filedname should be readable.
	private Float itemExpVol;
	//TODO filedname should be readable.
	private Float itemActVol;
	private Float itemPaidRate;
	//TODO filedname should be readable.
	private Float amtNeedTobePaid;
	private Float partialPayment;
	
	public String getPickupId() {
		return pickupId;
	}
	public void setPickupId(String pickupId) {
		this.pickupId = pickupId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Long getItemTypeSeq() {
		return itemTypeSeq;
	}
	public void setItemTypeSeq(Long itemTypeSeq) {
		this.itemTypeSeq = itemTypeSeq;
	}
	public Float getItemExpVol() {
		return itemExpVol;
	}
	public void setItemExpVol(Float itemExpVol) {
		this.itemExpVol = itemExpVol;
	}
	public Float getItemActVol() {
		return itemActVol;
	}
	public void setItemActVol(Float itemActVol) {
		this.itemActVol = itemActVol;
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

