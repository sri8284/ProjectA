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

	void createPaymentMode(PaymentMode paymentMode) throws RENIServiceException;

	void updateArea(Area area) throws RENIServiceException;
	
	void updateVendor(Vendor vendor) throws RENIServiceException;

	void updateItem(Item item)throws RENIServiceException;

	void updateExpenseType(ExpenseType expenseType)throws RENIServiceException;

	void updatePaymentMode(PaymentMode paymentMode)throws RENIServiceException;

	void createArea(Area area)throws RENIServiceException;

	void createItem(Item item)throws RENIServiceException;

	void createVendor(Vendor vendor)throws RENIServiceException;

	void createExpenseType(ExpenseType expenseType)throws RENIServiceException;
}
