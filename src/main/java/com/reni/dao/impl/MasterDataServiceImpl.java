package com.reni.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.MasterDataRepository;
import com.reni.model.Area;
import com.reni.model.ExpenseType;
import com.reni.model.Item;
import com.reni.model.PaymentMode;
import com.reni.model.Vendor;
import com.reni.rowmapper.AreaRowMapper;
import com.reni.rowmapper.ExpenseTypeRowMapper;
import com.reni.rowmapper.ItemRowMapper;
import com.reni.rowmapper.PaymentModeRowMapper;
import com.reni.rowmapper.VendorRowMapper;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class MasterDataServiceImpl implements MasterDataRepository {

	private static final String SELECT_AREA ="SELECT * FROM AREA" ;
	private static final String SELECT_ITEM = "SELECT * FROM ITEM";
	private static final String SELECT_EXPESNSE_TYPE = "SELECT * FROM EXPENSE_TYPE";
	private static final String SELECT_VENDOR = "SELECT * FROM VENDOR";
	private static final String SELECT_PAYMENT_MODES = "SELECT * FROM PAYMENT_MODE";
	
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

}
