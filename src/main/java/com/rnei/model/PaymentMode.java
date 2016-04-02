package com.rnei.model;

public class PaymentMode extends Basic{

	private static final long serialVersionUID = 1L;
	private String paymentCode;
	private String paymentName;
	
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	
	
}
