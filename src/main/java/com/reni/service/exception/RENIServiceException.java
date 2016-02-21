package com.reni.service.exception;

public class RENIServiceException extends CoreException {

	private static final long serialVersionUID = 2802438308525002456L;

	public RENIServiceException(final Enum<?> errorCode, final String errorMessage, Exception e) {
		super(errorCode.toString(),errorMessage);
		e.printStackTrace();
	}
	
	public RENIServiceException(final Enum<?> errorCode, final String errorMessage) {
		super(errorCode.toString(),errorMessage);
		
	}
	
	public RENIServiceException(final String errorMessage) {
		super(errorMessage);
		
	}
}
