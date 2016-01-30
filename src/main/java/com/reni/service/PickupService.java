package com.reni.service;

import java.util.List;

import com.reni.model.Pickup;
import com.reni.service.exception.RENIServiceException;

public interface PickupService {

	List<Pickup> getPickupDetails() throws RENIServiceException;
	Pickup getPickupById(final String pickupId) throws RENIServiceException;
	void createPickup(final Integer userId,final Pickup pickupDetails,final String sessionId) throws RENIServiceException;
}
