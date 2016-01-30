package com.reni.dao;

import java.util.List;

import com.reni.model.Pickup;
import com.reni.model.PickupRefernce;
import com.reni.service.exception.RENIDataServiceException;


public interface PickupDataService {

	void createPickup(Integer userId,Pickup pickup) throws RENIDataServiceException;

	List<Pickup> listOfPickups() throws RENIDataServiceException;
	
	Pickup getPickup(String pckupId)throws RENIDataServiceException;
	
	void deletePickup(Pickup pickup)throws RENIDataServiceException;

}
