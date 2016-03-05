package com.rnei.service.exception;

public class RENIValidationException extends RENIServiceException{

	private static final long serialVersionUID = -5849281486120156979L;

	public RENIValidationException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}
	
	public RENIValidationException(String errorMessage) {
		super(errorMessage);
	}
	
}
