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

	void createPaymentMode(PaymentMode paymentMode);

	void updateArea(Area area);

	void updateVendor(Vendor vendor);

	void updateItem(Item item);

	void updateExpenseType(ExpenseType expenseType);

	void updatePaymentMode(PaymentMode paymentMode);

	void createArea(Area area);

	void createItem(Item item);

	void createVendor(Vendor vendor);

	void createExpenseType(ExpenseType expenseType);

}
