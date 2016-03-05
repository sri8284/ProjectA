package com.rnei.service;

import java.util.List;

import com.rnei.model.Area;
import com.rnei.model.ExpenseType;
import com.rnei.model.Item;
import com.rnei.model.PaymentMode;
import com.rnei.model.Vendor;
import com.rnei.service.exception.RENIServiceException;

public interface MasterDataSerice {

	List<Vendor> fetchVendorDetails() throws RENIServiceException;

	List<Item> fetchItemDetails() throws RENIServiceException;

	List<Area> fetchAreaDetails() throws RENIServiceException;

	List<ExpenseType> fetchExpenseTypeDetails() throws RENIServiceException;

	List<PaymentMode> fetchPaymentModes() throws RENIServiceException;

}
