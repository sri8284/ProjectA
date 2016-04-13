package com.rnei.service;

import java.util.List;
import java.util.Map;

import com.rnei.model.Pickup;
import com.rnei.service.exception.RENIServiceException;

public interface PickupService {

	Pickup fetchPickupDetailsById(final String pickupId) throws RENIServiceException;
	String createPickup(final String userId,final Pickup pickupDetails) throws RENIServiceException;
	List<Map<String, Object>> fetchPickupsReport(String pickupDateInput) throws RENIServiceException;
	List<Map<String, Object>> fetchPickupsAssignmentStatus(String pickupDate) throws RENIServiceException;
	List<Map<String, Object>> fetchPickupStatus(String pickupDate) throws RENIServiceException;
	void closePickup(String userId, String pickupId, Pickup pickupInput) throws RENIServiceException;
}
