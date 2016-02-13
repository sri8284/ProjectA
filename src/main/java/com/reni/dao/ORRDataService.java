package com.reni.dao;

import java.util.List;

import com.reni.model.OnRoadResource;
import com.reni.service.exception.RENIDataServiceException;

public interface ORRDataService {

	List<OnRoadResource> fetchORRDetails() throws RENIDataServiceException;

	List<OnRoadResource> fetchActiveORRDetails() throws RENIDataServiceException;

	void createORR(OnRoadResource onRoadResource) throws RENIDataServiceException;;

	void updateORR(OnRoadResource onRoadResource) throws RENIDataServiceException;;

	List<OnRoadResource> fetchOnHireORRDetails() throws RENIDataServiceException;;

	List<OnRoadResource> fetchActiveOnHireORRDetails() throws RENIDataServiceException;;

	void createOnHireORR(String userId,OnRoadResource onRoadResource) throws RENIDataServiceException;;

	void updateOnHireORR(OnRoadResource onRoadResource) throws RENIDataServiceException;;

}
