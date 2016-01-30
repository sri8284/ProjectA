package com.reni.service;

import java.util.List;

import com.reni.model.Area;
import com.reni.model.Item;
import com.reni.model.OnRoadResource;
import com.reni.model.Vendor;
import com.reni.service.exception.RENIDataServiceException;

public interface MasterDataService {

	List<Area> getAreas() throws RENIDataServiceException;
	List<Item> getItems() throws RENIDataServiceException;
	List<OnRoadResource> getORRDetails() throws RENIDataServiceException;
	List<Vendor> getVendors() throws RENIDataServiceException;

}
