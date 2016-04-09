package com.rnei.service;

import java.util.List;

import com.rnei.model.OnRoadResource;
import com.rnei.model.OnRoadResourcePickup;
import com.rnei.model.Pickup;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

public interface ORRService {

	List<OnRoadResource> fetchORRDetails() throws RENIServiceException;

	List<OnRoadResource> fetchActiveORRDetails() throws RENIServiceException;

	void createORR(OnRoadResource onRoadResource)  throws RENIServiceException;

	void updateORR(OnRoadResource onRoadResource)  throws RENIServiceException;

	List<OnRoadResource> fetchOnHireORRDetails()  throws RENIServiceException;

	void createOnHireORR(String userId,OnRoadResource onRoadResource)  throws RENIServiceException;

	List<OnRoadResource> fetchActiveOnHireORRDetails()  throws RENIServiceException;

	void updateOnHireORR(OnRoadResource onRoadResource) throws RENIServiceException;

	Object fetchORRReportDetails(String orrId, String reqDate);

	Object fetchOnHireORRReportDetails(String orrId, String reqDate);

	List<Pickup> fetchORRPickupDetails(OnRoadResourcePickup orrPickupInput) throws RENIValidationException;

}
