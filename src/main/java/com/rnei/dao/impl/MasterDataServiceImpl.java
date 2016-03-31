package com.rnei.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.MasterDataRepository;
import com.rnei.model.Area;
import com.rnei.model.ExpenseType;
import com.rnei.model.Item;
import com.rnei.model.PaymentMode;
import com.rnei.model.Vendor;
import com.rnei.rowmapper.AreaRowMapper;
import com.rnei.rowmapper.ExpenseTypeRowMapper;
import com.rnei.rowmapper.ItemRowMapper;
import com.rnei.rowmapper.PaymentModeRowMapper;
import com.rnei.rowmapper.VendorRowMapper;
import com.rnei.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class MasterDataServiceImpl implements MasterDataRepository {

	private static final String SELECT_AREA ="SELECT * FROM AREA" ;
	private static final String SELECT_ITEM = "SELECT * FROM ITEM";
	private static final String SELECT_EXPESNSE_TYPE = "SELECT * FROM EXPENSE_TYPE";
	private static final String SELECT_VENDOR = "SELECT * FROM VENDOR";
	private static final String SELECT_PAYMENT_MODES = "SELECT * FROM PAYMENT_MODE";
	
	private static final String CREATE_AREA ="INTERT * FROM AREA" ;
	private static final String CREATE_ITEM = "SELECT * FROM ITEM";
	private static final String CREATE_EXPESNSE_TYPE = "SELECT * FROM EXPENSE_TYPE";
	private static final String CREATE_VENDOR = "INSERT INTO VENDOR (`VENDOR_ID`, `AREA_CODE`,`VND_FIRST_NAME`,`VND_LAST_NAME`,`VND_ADDRESS`,`VND_CONTACT_NO`,`CITY`,`STATE`,`LANDMARK`,`PINCODE`,`CREATED_DATE`,`CREATED_BY`,`UPDATE_DATE`,`UPDATE_BY`)"
			+ " VALUES (:VENDOR_ID,:AREA_CODE,:VND_FIRST_NAME,:VND_LAST_NAME,:VND_ADDRESS,:VND_CONTACT_NO,:CITY,:STATE,:LANDMARK,:PINCODE,:CREATED_DATE,:CREATED_BY,:UPDATE_DATE,:UPDATE_BY) ";
	private static final String CREATE_PAYMENT_MODES = "SELECT * FROM PAYMENT_MODE";
	
	private static final String UPDATE_AREA ="SELECT * FROM AREA" ;
	private static final String UPDATE_ITEM = "SELECT * FROM ITEM";
	private static final String UPDATE_EXPESNSE_TYPE = "SELECT * FROM EXPENSE_TYPE";
	private static final String UPDATE_VENDOR = "SELECT * FROM VENDOR";
	private static final String UPDATE_PAYMENT_MODES = "SELECT * FROM PAYMENT_MODE";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public List<Area> fetchAreaDetails() throws RENIDataServiceException {
		return (List<Area>) namedParameterJdbcTemplate.query(SELECT_AREA, new AreaRowMapper()); 
	}

	@Override
	public List<Item> fetchItemDetails() throws RENIDataServiceException {
		return (List<Item>) namedParameterJdbcTemplate.query(SELECT_ITEM, new ItemRowMapper());
	}

	@Override
	public List<ExpenseType> fetchExpenseTypeDetails() throws RENIDataServiceException {
		return (List<ExpenseType>) namedParameterJdbcTemplate.query(SELECT_EXPESNSE_TYPE, new ExpenseTypeRowMapper());
	}

	@Override
	public List<Vendor> fetchVendorDetails() throws RENIDataServiceException {
		return (List<Vendor>) namedParameterJdbcTemplate.query(SELECT_VENDOR, new VendorRowMapper());
	}

	@Override
	public List<PaymentMode> fetchPaymentModes() throws RENIDataServiceException {
		return (List<PaymentMode>) namedParameterJdbcTemplate.query(SELECT_PAYMENT_MODES, new PaymentModeRowMapper());

	}

	@Override
	public void createPaymentMode(PaymentMode paymentMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArea(Area area) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateExpenseType(ExpenseType expenseType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePaymentMode(PaymentMode paymentMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createArea(Area area) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createExpenseType(ExpenseType expenseType) {
		// TODO Auto-generated method stub
		
	}

}
