package com.reni.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.reni.model.Pickup;
import com.reni.model.PickupRefernce;
import com.reni.service.exception.RENIDataServiceException;


public interface PickupDataService {

	void createPickup(Integer userId,Pickup pickup) throws RENIDataServiceException;

	Pickup getPickup(String pckupId)throws RENIDataServiceException;
	
	List<Map<String, Object>> fetchPickupStatus(LocalDate pickupDate) throws RENIDataServiceException;

	List<Map<String, Object>> fetchPickupsReport(LocalDate pickupDate) throws RENIDataServiceException;

	List<Map<String, Object>> fetchPickupsAssignmentStatus(LocalDate pickupDate) throws RENIDataServiceException;

	void closePickup(Integer userId, Pickup pickupInput) throws RENIDataServiceException;

}
