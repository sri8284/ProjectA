package com.reni.controller;

import static com.reni.service.constants.RENIServiceConstant.INVALID_REQUEST;

import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIValidationException;

abstract class CoreController {

	public <T> void validateInput(T value) throws RENIValidationException {
		if(value==null){
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST,INVALID_REQUEST);
		}		
	}
}
