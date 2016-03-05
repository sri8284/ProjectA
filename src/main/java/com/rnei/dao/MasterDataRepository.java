package com.rnei.dao;

import java.util.List;

import com.rnei.model.Area;
import com.rnei.model.ExpenseType;
import com.rnei.model.Item;
import com.rnei.model.PaymentMode;
import com.rnei.model.Vendor;
import com.rnei.service.exception.RENIDataServiceException;

public interface MasterDataRepository {

	List<Vendor> fetchVendorDetails() throws RENIDataServiceException;

	List<ExpenseType> fetchExpenseTypeDetails() throws RENIDataServiceException;

	List<Area> fetchAreaDetails()throws RENIDataServiceException;

	List<Item> fetchItemDetails()throws RENIDataServiceException;

	List<PaymentMode> fetchPaymentModes() throws RENIDataServiceException;

}
