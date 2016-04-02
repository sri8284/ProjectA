package com.rnei.dao.impl;

import static com.rnei.common.util.CommonUtil.currentTimeStamp;
import static com.rnei.service.constants.RENIDataConstants.*;
import static com.rnei.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private static final String CREATE_AREA ="INSERT INTO AREA (AREA_CODE,AREA_NAME,CREATED_BY,CREATED_DATE) VALUES (:AREA_CODE,:AREA_NAME,:CREATED_BY,:CREATED_DATE)" ;
	private static final String CREATE_ITEM = "INSERT INTO ITEM (ITEM_CODE,ITEM_NAME,CURRENT_RATE,CREATED_DATE,CREATED_BY,UPDATED_DATE,UPDTED_BY) VALUES (:ITEM_CODE,:ITEM_NAME,:CURRENT_RATE, "
			+ ":CREATED_DATE, :CREATED_BY,:UPDATED_DATE,:UPDTED_BY)";
	private static final String CREATE_EXPESNSE_TYPE = "INSERT INTO EXPENSE_TYPE (EXP_TYPE,EXP_SUBTYPE,EXP_TYPE_NAME,EXP_SUBTYPE_NAME) VALUES (:EXP_TYPE,:EXP_SUBTYPE,:EXP_TYPE_NAME,:EXP_SUBTYPE_NAME)";
	private static final String CREATE_VENDOR = "INSERT INTO VENDOR (VENDOR_ID, AREA_CODE,VND_FIRST_NAME,VND_LAST_NAME,VND_ADDRESS,VND_CONTACT_NO,CITY,STATE,LANDMARK,PINCODE,CREATED_DATE,CREATED_BY,UPDATE_DATE,UPDATE_BY)"
			+ " VALUES (:VENDOR_ID,:AREA_CODE,:VND_FIRST_NAME,:VND_LAST_NAME,:VND_ADDRESS,:VND_CONTACT_NO,:CITY,:STATE,:LANDMARK,:PINCODE,:CREATED_DATE,:CREATED_BY,:UPDATE_DATE,:UPDATE_BY) ";
	private static final String CREATE_PAYMENT_MODES = "INSERT INTO PAYMENT_MODE (PAYMENT_CODE,PAYMENT_NAME) VALUES (:PAYMENT_CODE,:PAYMENT_NAME)";
	
	private static final String UPDATE_AREA ="UPDATE AREA SET AREA_CODE = :AREA_CODE,AREA_NAME = :AREA_NAME,CREATED_BY = :CREATED_BY,CREATED_DATE = :CREATED_DATE WHERE AREA_CODE = :AREA_CODE" ;
	private static final String UPDATE_ITEM = "UPDATE ITEM SET ITEM_CODE = :ITEM_CODE, ITEM_NAME = :ITEM_NAME,CURRENT_RATE = :CURRENT_RATE,CREATED_DATE = :CREATED_DATE,CREATED_BY = :CREATED_BY, UPDATED_DATE = :UPDATED_DATE, UPDTED_BY = :UPDTED_BY WHERE ITEM_CODE = :ITEM_CODE";
	private static final String UPDATE_EXPESNSE_TYPE = "UPDATE EXPENSE_TYPE SET EXP_TYPE = :EXP_TYPE, EXP_SUBTYPE = :EXP_SUBTYPE, EXP_TYPE_NAME = :EXP_TYPE_NAME, EXP_SUBTYPE_NAME = :EXP_SUBTYPE_NAME WHERE EXP_TYPE = :EXP_TYPE AND EXP_SUBTYPE = :EXP_SUBTYPE ";
	private static final String UPDATE_VENDOR = "UPDATE VENDOR SET VENDOR_ID = :VENDOR_ID, AREA_CODE = :AREA_CODE, VND_FIRST_NAME = :VND_FIRST_NAME, VND_LAST_NAME = :VND_LAST_NAME, VND_ADDRESS = :VND_ADDRESS, VND_CONTACT_NO = :VND_CONTACT_NO, CITY = :CITY, STATE = :STATE, LANDMARK = :LANDMARK, "
			+ "PINCODE = :PINCODE, CREATED_DATE = :CREATED_DATE, CREATED_BY = :CREATED_BY, UPDATE_DATE = :UPDATE_DATE, UPDATE_BY = :UPDATE_BY WHERE VENDOR_ID = :VENDOR_ID AND AREA_CODE = :AREA_CODE ";
	private static final String UPDATE_PAYMENT_MODES = "UPDATE PAYMENT_MODE SET PAYMENT_CODE = :PAYMENT_CODE,PAYMENT_NAME = :PAYMENT_NAME WHERE PAYMENT_CODE = :PAYMENT_CODE";
	
	/*private static final String DELETE_AREA ="DELETE FROM AREA WHERE AREA_CODE =:AREA_CODE" ;
	private static final String DELETE_ITEM = "DELETE FROM ITEM WHERE ITEM_CODE =:ITEM_CODE ";
	private static final String DELETE_EXPESNSE_TYPE = "DELETE FROM EXPENSE_TYPE WHERE EXP_TYPE = :EXP_TYPE AND EXP_SUBTYPE = :EXP_SUBTYPE ";
	private static final String DELETE_VENDOR = "DELETE FROM VENDOR WHERE VENDOR_ID = :VENDOR_ID AND AREA_CODE = :AREA_CODE";
	private static final String DELETE_PAYMENT_MODES = "DELETE FROM PAYMENT_MODE WHERE PAYMENT_CODE = :PAYMENT_CODE";
	*/
	
	
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
	public void updateArea(Area area) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(AREA_CODE, area.getAreaCode());
			namedParameters.put(AREA_NAME, area.getAreaName());
			namedParameters.put(UPDATED_BY, area.getUpdatedBy());
			namedParameters.put(UPDATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(UPDATE_AREA, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void updateVendor(Vendor vendor) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(VENDOR_ID, vendor.getVendorId());
			namedParameters.put(AREA_CODE, vendor.getAreaCode());
			namedParameters.put(VND_ADDRESS, vendor.getVndAddress());
			namedParameters.put(VND_CONTACT_NO, vendor.getVndContactNo());
			namedParameters.put(VND_FIRST_NAME, vendor.getVndfirstName());
			namedParameters.put(VND_LAST_NAME, vendor.getVndlastName());
			namedParameters.put(UPDATED_BY, vendor.getUpdatedBy());
			namedParameters.put(UPDATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(UPDATE_VENDOR, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException( DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void updateItem(Item item) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(ITEM_CODE, item.getItemCode());
			namedParameters.put(ITEM_NAME, item.getItemName());
			namedParameters.put(ITEM_CURRENT_RATE, item.getCurrentRate());
			namedParameters.put(UPDATED_BY, item.getUpdatedBy());
			namedParameters.put(UPDATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(UPDATE_ITEM, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void updateExpenseType(ExpenseType expenseType) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(EXP_TYPE, expenseType.getExpType());
			namedParameters.put(EXP_SUBTYPE, expenseType.getExpSubType());
			namedParameters.put(EXP_TYPE_NAME, expenseType.getExpTypeName());
			namedParameters.put(EXP_SUBTYPE_NAME, expenseType.getExpSubTypeName());
			namedParameters.put(UPDATED_BY, expenseType.getUpdatedBy());
			namedParameters.put(UPDATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(UPDATE_EXPESNSE_TYPE, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException( DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void updatePaymentMode(PaymentMode paymentMode) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(PAYMENT_CODE, paymentMode.getPaymentCode());
			namedParameters.put(PAYMENT_NAME, paymentMode.getPaymentName());
			namedParameters.put(UPDATED_BY, paymentMode.getCreatedBy());
			namedParameters.put(UPDATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(UPDATE_PAYMENT_MODES, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void createArea(Area area) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(AREA_CODE, area.getAreaCode());
			namedParameters.put(AREA_NAME, area.getAreaName());
			namedParameters.put(CREATED_BY, area.getCreatedBy());
			namedParameters.put(CREATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(CREATE_AREA, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void createItem(Item item) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(ITEM_CODE, item.getItemCode());
			namedParameters.put(ITEM_NAME, item.getItemName());
			namedParameters.put(ITEM_CURRENT_RATE, item.getCurrentRate());
			namedParameters.put(CREATED_BY, item.getCreatedBy());
			namedParameters.put(CREATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(CREATE_ITEM, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void createVendor(Vendor vendor) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(VENDOR_ID, vendor.getVendorId());
			namedParameters.put(AREA_CODE, vendor.getAreaCode());
			namedParameters.put(VND_ADDRESS, vendor.getVndAddress());
			namedParameters.put(VND_CONTACT_NO, vendor.getVndContactNo());
			namedParameters.put(VND_FIRST_NAME, vendor.getVndfirstName());
			namedParameters.put(VND_LAST_NAME, vendor.getVndlastName());
			namedParameters.put(CREATED_BY, vendor.getCreatedBy());
			namedParameters.put(CREATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(CREATE_VENDOR, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException( DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void createExpenseType(ExpenseType expenseType) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(EXP_TYPE, expenseType.getExpType());
			namedParameters.put(EXP_SUBTYPE, expenseType.getExpSubType());
			namedParameters.put(EXP_TYPE_NAME, expenseType.getExpTypeName());
			namedParameters.put(EXP_SUBTYPE_NAME, expenseType.getExpSubTypeName());
			namedParameters.put(CREATED_BY, expenseType.getCreatedBy());
			namedParameters.put(CREATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(CREATE_EXPESNSE_TYPE, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException( DATA_SAVE_ERROR, e);
		}		
	}
	
	@Override
	public void createPaymentMode(PaymentMode paymentMode) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(PAYMENT_CODE, paymentMode.getPaymentCode());
			namedParameters.put(PAYMENT_NAME, paymentMode.getPaymentName());
			namedParameters.put(CREATED_BY, paymentMode.getCreatedBy());
			namedParameters.put(CREATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(CREATE_PAYMENT_MODES, namedParameters);
			
		} catch (Exception e) {
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public void deleteArea(Area area) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItem(Item item) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVendor(Vendor vendor) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteExpenseType(ExpenseType expenseType) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePaymentMode(PaymentMode paymentMode) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}
	

}
