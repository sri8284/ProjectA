package com.reni.service;

import java.util.List;
import java.util.Map;

import com.reni.model.Pickup;
import com.reni.service.exception.RENIServiceException;

public interface PickupService {

	Pickup fetchPickupDetailsById(final String pickupId) throws RENIServiceException;
	void createPickup(final Integer userId,final Pickup pickupDetails) throws RENIServiceException;
	List<Map<String, Object>> fetchPickupsReport(String pickupDateInput) throws RENIServiceException;
	List<Map<String, Object>> fetchPickupsAssignmentStatus(String pickupDate) throws RENIServiceException;
	List<Map<String, Object>> fetchPickupStatus(String pickupDate) throws RENIServiceException;
	void closePickup(Integer userId, String pickupId, Pickup pickupInput) throws RENIServiceException;
}
