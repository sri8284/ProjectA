package com.reni.service.exception;

public class RENIDataServiceException extends CoreException {

	private static final long serialVersionUID = -6608963914399846720L;

	public RENIDataServiceException(final Enum<?> errorCode, final String errorMessage) {
		super(errorCode.toString(),errorMessage);
	}
	
}
