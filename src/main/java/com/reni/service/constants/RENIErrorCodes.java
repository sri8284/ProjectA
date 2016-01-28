package com.reni.service.constants;

 public enum RENIErrorCodes {
	INVALID_REQUEST("1001"), DUPLICATE_SESSION("1002"), INVALID_USER("1003"), DATA_SAVE_ERROR("1004"),
	SERVER_ERROR_CODE("000");
	 

	RENIErrorCodes(String errorCode){
		//TODO - future implementation for fetching the business error from database.
	}
}
