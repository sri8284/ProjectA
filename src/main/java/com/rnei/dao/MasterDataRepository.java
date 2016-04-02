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

	void updateArea(Area area) throws RENIDataServiceException;

	void updateVendor(Vendor vendor) throws RENIDataServiceException;

	void updateItem(Item item) throws RENIDataServiceException;

	void updateExpenseType(ExpenseType expenseType) throws RENIDataServiceException;

	void updatePaymentMode(PaymentMode paymentMode) throws RENIDataServiceException;

	void createArea(Area area) throws RENIDataServiceException;

	void createItem(Item item) throws RENIDataServiceException;

	void createVendor(Vendor vendor) throws RENIDataServiceException;

	void createExpenseType(ExpenseType expenseType) throws RENIDataServiceException;

	void createPaymentMode(PaymentMode paymentMode) throws RENIDataServiceException;
	
	void deleteArea(Area area) throws RENIDataServiceException;

	void deleteItem(Item item) throws RENIDataServiceException;

	void deleteVendor(Vendor vendor) throws RENIDataServiceException;

	void deleteExpenseType(ExpenseType expenseType) throws RENIDataServiceException;

	void deletePaymentMode(PaymentMode paymentMode) throws RENIDataServiceException;

}
