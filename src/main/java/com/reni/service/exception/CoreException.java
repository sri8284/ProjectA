package com.reni.service.exception;

import com.reni.model.ErrorResponse;

public class CoreException extends Exception{

	private static final long serialVersionUID = -970298269399110933L;

	private final ErrorResponse errorResponse = new ErrorResponse();
	
	public CoreException(){
		
	}
	
	public CoreException(final String errorCode, final String errorMessage){
		errorResponse.setErrorCode(errorCode);
		errorResponse.setErrorMessge(errorMessage);
		errorResponse.setStatus(Boolean.FALSE);
	}
	
	public ErrorResponse getErrorResponse(){
		return errorResponse;
	}
	
	
}
