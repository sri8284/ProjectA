package com.rnei.controller;

import static com.rnei.service.constants.RENIServiceConstant.INVALID_REQUEST;

import com.rnei.service.exception.RENIValidationException;

abstract class CoreController {

	@SuppressWarnings("unchecked")
	public <T> void validateInput(T... values) throws RENIValidationException {

		if (values != null) {
			for (T value : values) {
				if (value == null)
					throw new RENIValidationException(INVALID_REQUEST);
			}
		}
	}
}
