package com.rnei.controller;

import static com.rnei.service.constants.RENIServiceConstant.INVALID_REQUEST;
import static com.rnei.service.constants.RENIServiceConstant.UNKNOW_USER_ERROR;
import static com.rnei.service.constants.RENIServiceConstant.NON_FOUND_ERROR;

import com.google.common.base.Strings;
import com.rnei.service.exception.RENIValidationException;

abstract class CoreController {

  public <T> void validateInput(final T... values) throws RENIValidationException {
    if (values == null || values.length == 0) {
      throw new RENIValidationException(INVALID_REQUEST);
    }

    for (final T value : values) {
      if (value == null) {
        throw new RENIValidationException(INVALID_REQUEST);
      }
    }
  }
  
  public void validateUser(final String userId) throws RENIValidationException{
   if(Strings.isNullOrEmpty(userId)){
     throw new RENIValidationException(UNKNOW_USER_ERROR);
   }
  }
  
  public Object validateResponse(final Object response) throws RENIValidationException{
    if(response==null){
      throw new RENIValidationException(NON_FOUND_ERROR);
    }
    
    return response;
  }
}
