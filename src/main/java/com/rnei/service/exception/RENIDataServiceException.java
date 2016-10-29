package com.rnei.service.exception;

public class RENIDataServiceException extends RENIServiceException {

	private static final long serialVersionUID = -6608963914399846720L;

	public RENIDataServiceException(final String errorMessage, Exception e) {
		super(errorMessage, e);
	}
	
	public RENIDataServiceException(final String errorMessage) {
		super(errorMessage);
	}
}
