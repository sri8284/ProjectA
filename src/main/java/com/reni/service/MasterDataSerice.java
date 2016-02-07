package com.reni.service;

import java.util.List;

import com.reni.model.Area;
import com.reni.model.ExpenseType;
import com.reni.model.Item;
import com.reni.model.Vendor;
import com.reni.service.exception.RENIServiceException;

public interface MasterDataSerice {

	List<Vendor> fetchVendorDetails() throws RENIServiceException;

	List<Item> fetchItemDetails() throws RENIServiceException;

	List<Area> fetchAreaDetails() throws RENIServiceException;

	List<ExpenseType> fetchExpenseTypeDetails() throws RENIServiceException;

}
