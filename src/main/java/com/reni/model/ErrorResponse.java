package com.reni.model;

public class ErrorResponse {

	private String errorCode;
	private String errorMessage;
	private Boolean status;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessge() {
		return errorMessage;
	}
	public void setErrorMessge(String errorMessge) {
		this.errorMessage = errorMessge;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
