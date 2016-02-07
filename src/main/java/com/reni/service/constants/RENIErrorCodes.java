package com.reni.service.constants;

 public enum RENIErrorCodes {
	INVALID_REQUEST("1001"), DUPLICATE_SESSION("1002"), INVALID_USER("1003"), DATA_SAVE_ERROR("1004"),
	SERVER_ERROR_CODE("000"), INVALID_ACCESSS("1005"), DATA_FECTH_ERROR("1006"), DATA_REMOVE_ERROR("1007"), PICKUP_NOT_ALLOWED("1008"), ITEM_TRANSACTION_SAVE_FAILED("1009"), INVALID_RIGHTS("1010");
	 

	RENIErrorCodes(String errorCode){
		//TODO - future implementation for fetching the business error from database.
	}
}
