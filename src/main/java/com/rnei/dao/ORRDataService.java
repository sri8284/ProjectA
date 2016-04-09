package com.rnei.dao;

import java.util.List;

import com.rnei.model.OnRoadResource;
import com.rnei.model.OnRoadResourcePickup;
import com.rnei.model.Pickup;
import com.rnei.service.exception.RENIDataServiceException;

public interface ORRDataService {

	List<OnRoadResource> fetchORRDetails() throws RENIDataServiceException;

	List<OnRoadResource> fetchActiveORRDetails() throws RENIDataServiceException;

	void createORR(OnRoadResource onRoadResource) throws RENIDataServiceException;;

	void updateORR(OnRoadResource onRoadResource) throws RENIDataServiceException;;

	List<OnRoadResource> fetchOnHireORRDetails() throws RENIDataServiceException;;

	List<OnRoadResource> fetchActiveOnHireORRDetails() throws RENIDataServiceException;;

	void createOnHireORR(String userId,OnRoadResource onRoadResource) throws RENIDataServiceException;;

	void updateOnHireORR(OnRoadResource onRoadResource) throws RENIDataServiceException;

	boolean isDuplicateOnHireORR(String drivingLicNo) throws RENIDataServiceException;

	List<Pickup> fetchORRPickupDetails(OnRoadResourcePickup orrPickupInput);

}
