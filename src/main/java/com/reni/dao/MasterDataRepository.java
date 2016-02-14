package com.reni.dao;

import java.util.List;

import com.reni.model.Area;
import com.reni.model.ExpenseType;
import com.reni.model.Item;
import com.reni.model.PaymentMode;
import com.reni.model.Vendor;
import com.reni.service.exception.RENIDataServiceException;

public interface MasterDataRepository {

	List<Vendor> fetchVendorDetails() throws RENIDataServiceException;

	List<ExpenseType> fetchExpenseTypeDetails() throws RENIDataServiceException;

	List<Area> fetchAreaDetails()throws RENIDataServiceException;

	List<Item> fetchItemDetails()throws RENIDataServiceException;

	List<PaymentMode> fetchPaymentModes() throws RENIDataServiceException;

}
