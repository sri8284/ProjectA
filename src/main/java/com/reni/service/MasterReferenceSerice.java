package com.reni.service;

import com.reni.model.PickupRefernce;
import com.reni.service.exception.RENIServiceException;

public interface MasterReferenceSerice {

	PickupRefernce getPickupReferenceDetails(Integer userId,String sessionId) throws RENIServiceException;

}
