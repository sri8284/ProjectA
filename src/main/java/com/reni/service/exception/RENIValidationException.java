package com.reni.service.exception;

public class RENIValidationException extends RENIServiceException{

	private static final long serialVersionUID = -5849281486120156979L;

	public RENIValidationException(Enum errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	
	public RENIValidationException(String errorMessage) {
		super(errorMessage);
	}
	
}
