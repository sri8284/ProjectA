package com.rnei.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.rnei.model.Pickup;
import com.rnei.model.PickupRefernce;
import com.rnei.service.exception.RENIDataServiceException;


public interface PickupDataService {

	void createPickup(String userId,Pickup pickup) throws RENIDataServiceException;

	Pickup getPickup(String pckupId)throws RENIDataServiceException;
	
	List<Map<String, Object>> fetchPickupStatus(LocalDate pickupDate) throws RENIDataServiceException;

	List<Map<String, Object>> fetchPickupsReport(LocalDate pickupDate) throws RENIDataServiceException;

	List<Map<String, Object>> fetchPickupsAssignmentStatus(LocalDate pickupDate) throws RENIDataServiceException;

	void closePickup(String userId, Pickup pickupInput) throws RENIDataServiceException;
	String getORR(String avaliable) throws RENIDataServiceException;

}
