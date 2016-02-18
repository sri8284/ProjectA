package com.reni.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.reni.model.Pickup;
import com.reni.service.exception.RENIServiceException;

public interface PickupService {

	Pickup fetchPickupDetailsById(final String pickupId) throws RENIServiceException;
	void createPickup(final Integer userId,final Pickup pickupDetails) throws RENIServiceException;
	Map<String,Object> fetchCompletePickupsStatusDetails();
	Map<String, Integer> fetchPickupsStatus(LocalDate pickupDate);
	Map<String,Object> fetchPickupReportByDate(LocalDate pickupDate);
}
