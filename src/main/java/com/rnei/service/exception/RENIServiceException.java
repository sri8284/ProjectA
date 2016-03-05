package com.rnei.service.exception;

import com.rnei.model.ErrorResponse;

public abstract class RENIServiceException extends Exception{

	private static final long serialVersionUID = 2802438308525002456L;
	private final ErrorResponse errorResponse = new ErrorResponse();

	public RENIServiceException(final String errorMessage) {
		super(errorMessage);
		
		errorResponse.setErrorMessge(errorMessage);
		errorResponse.setStatus(Boolean.FALSE);
	}
	
	public RENIServiceException(final String errorMessage, Exception cause) {
		super(errorMessage, cause);
		
		errorResponse.setErrorMessge(errorMessage);
		errorResponse.setStatus(Boolean.FALSE);
	}
	
	public ErrorResponse getErrorResponse(){
		return errorResponse;
	}
}
