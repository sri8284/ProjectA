package com.rnei.dao.impl;

import static com.rnei.common.util.CommonUtil.currentTimeStamp;
import static com.rnei.service.constants.RENIDataConstants.*;
import static com.rnei.service.constants.RENIServiceConstant.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.PickupDataService;
import com.rnei.model.ItemTransaction;
import com.rnei.model.Pickup;
import com.rnei.rowmapper.PickupRowMapper;
import com.rnei.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class PickupDataServiceImpl implements PickupDataService {

	private static final String SELECT_PICKUP_STATUS_COUNT = "SELECT COMPLETE as complete, COUNT(COMPLETE) AS count FROM PICKUP WHERE PICKUP_DATE =:PICKUP_DATE GROUP BY COMPLETE";

	private static final String SELECT_PICKUP_REPORT = "SELECT COMPLETE as complete, COUNT(COMPLETE) AS count, SUM(TOTAL_PAYMENT) AS totalPayment , SUM(PARITAL_PAYMENT) AS paritalPayment , "
			+ "SUM(BALANCE_PAYMENT) AS balancePayment "
			+ "	FROM PICKUP WHERE PICKUP_DATE =:PICKUP_DATE GROUP BY COMPLETE ";

	private static final String SELECT_PICKUP_ASSIGNMENT_STATUS = "SELECT PICKUP_ID as pickupId, STATUS as pickupStatus, PICKUP_DATE AS pickupDate, PICKUP_TIME AS pickupTime,"
			+ " P.AREA_CODE AS areaCode, A.AREA_NAME as areaName FROM PICKUP P JOIN AREA A ON A.AREA_CODE = P.AREA_CODE WHERE PICKUP_DATE= :PICKUP_DATE ";

	private static final String SELECT_PICKUP_BY_ID = "SELECT * FROM PICKUP P JOIN ITEM_TRANSACTION T ON P.PICKUP_ID=T.PICKUP_ID  where P.PICKUP_ID=:PICKUP_ID";

	private static final String INSERT_PICKUP = "INSERT INTO PICKUP (PICKUP_ID,CREATED_DATE,CREATED_BY,PICKUP_DATE,PICKUP_TIME,STATUS,ORR_ID,VENDOR_ID,COMMENTS,AREA_CODE, COMPLETE)"
			+ "VALUES (:PICKUP_ID,:CREATED_DATE,:CREATED_BY,:PICKUP_DATE,:PICKUP_TIME,:STATUS,:ORR_ID,:VENDOR_ID,:COMMENTS,:AREA_CODE, :COMPLETE)";
	private static final String INSERT_ITEM_TRANSACTION = "INSERT INTO ITEM_TRANSACTION (PICKUP_ID,ITEM_CODE,CREATED_DATE,CREATED_BY,ITEM_PAID_RATE,ITEM_EXPECTED_VOL)"
			+ "VALUES (:PICKUP_ID,:ITEM_CODE,:CREATED_DATE,:CREATED_BY,:ITEM_PAID_RATE,:ITEM_EXPECTED_VOL)";

	private static final String PICKUP_CLOSE = "UPDATE PICKUP SET STATUS=:STATUS, COMPLETE=:COMPLETE, TOTAL_PAYMENT=:TOTAL_PAYMENT,PARITAL_PAYMENT=:PARITAL_PAYMENT, BALANCE_PAYMENT=:BALANCE_PAYMENT, UPDATED_DATE=:UPDATED_DATE, UPDATED_BY=:UPDATED_BY "
			+ "WHERE PICKUP_ID =:PICKUP_ID ";
	private static final String ITEM_CLOSE_TRANSACTION ="UPDATE ITEM_TRANSACTION SET ITEM_ACTUAL_VOL=:ITEM_ACTUAL_VOL, TOTAL_AMOUNT=:TOTAL_AMOUNT, UPDATED_BY=:UPDATED_BY, UPDATED_DATE=:UPDATED_DATE "
			+ "WHERE PICKUP_ID=:PICKUP_ID AND ITEM_CODE=:ITEM_CODE ";

	private static final String UPDATE_ONHIRE_ORR = "UPDATE reni.onhireorr SET AVAILABLE = :AVAILABLE, CURRENT_PICKUP_ID = :CURRENT_PICKUP_ID, PREV_PICKUP_ID=:PREV_PICKUP_ID, UPDATED_DATE = :UPDATED_DATE, UPDATED_BY = :UPDATED_BY WHERE ORR_ID = :ORR_ID";

	private static final String UPDATE_ORR = "UPDATE reni.orr SET AVAILABLE = :AVAILABLE, CURRENT_PICKUP_ID = :CURRENT_PICKUP_ID, PREV_PICKUP_ID=:PREV_PICKUP_ID, UPDATED_DATE = :UPDATED_DATE, UPDATED_BY = :UPDATED_BY WHERE ORR_ID = :ORR_ID;";
	

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void createPickup(String userId, Pickup pickup) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(PICKUP_ID, pickup.getPickupId());
			namedParameters.put(VENDOR_ID, pickup.getVendorId());
			namedParameters.put(STATUS, NEW_PICKUP);
			namedParameters.put(ORR_ID, pickup.getOrrId());
			namedParameters.put(PICKUP_DATE, pickup.getPickupDate());
			namedParameters.put(PICKUP_TIME, pickup.getPickupTime());
			namedParameters.put(COMPLETE, NON_COMPLETE);
			namedParameters.put(AREA_CODE, pickup.getAreaCode());
			namedParameters.put(CREATED_BY, userId);
			namedParameters.put(CREATED_DATE, currentTimeStamp());
			namedParameters.put(COMMENTS, pickup.getComments());
			int status = namedParameterJdbcTemplate.update(INSERT_PICKUP, namedParameters);
			if (status > 0) {
				for (ItemTransaction itemTransaction : pickup.getItemTransactions()) {
					createItemTransaction(userId, itemTransaction, pickup.getPickupId());
				}
			} else {
				throw new RENIDataServiceException(PICKUP_NOT_ALLOWED);
			}
			
			changeORRAvaliablity(userId, pickup.getPickupId(), pickup.getOrrId(), NO);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}
	}

	private void changeORRAvaliablity(String userId, String pickupId, String orrId, String avaliable) throws RENIDataServiceException {
			try {
				Map<String, Object> namedParameters = new HashMap<String, Object>();
				
				namedParameters.put(AVAILABLE, avaliable);
				namedParameters.put(ORR_ID, orrId);
				if(YES.contentEquals(avaliable)){
					namedParameters.put(PREV_PICKUP_ID, pickupId);
					namedParameters.put(CURRENT_PICKUP_ID, null);
				}else{
					namedParameters.put(CURRENT_PICKUP_ID, pickupId);
					namedParameters.put(PREV_PICKUP_ID, null);
				}
				namedParameters.put(UPDATED_BY, userId);
				namedParameters.put(UPDATED_DATE, currentTimeStamp());
				if(orrId.startsWith("C")){
				namedParameterJdbcTemplate.update(UPDATE_ONHIRE_ORR, namedParameters);
				}else{
					namedParameterJdbcTemplate.update(UPDATE_ORR, namedParameters);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
			}
	}

	private void createItemTransaction(String userId, ItemTransaction itemTransaction, String pickupId)
			throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(ITEM_CODE, itemTransaction.getItemCode());
			namedParameters.put(PICKUP_ID, pickupId);
			namedParameters.put(ITEM_PAID_RATE, itemTransaction.getItemPaidRate());
			namedParameters.put(ITEM_EXPECTED_VOL, itemTransaction.getItemExpectedVol());
			namedParameters.put(TOTAL_AMOUNT, itemTransaction.getTotalAmount());
			namedParameters.put(CREATED_BY, userId);
			namedParameters.put(CREATED_DATE, currentTimeStamp());
			int status = namedParameterJdbcTemplate.update(INSERT_ITEM_TRANSACTION, namedParameters);
			if (status <= 0) {
				throw new RENIDataServiceException(ITEM_TRANSACTION_SAVE_FAILED);
			}
		} catch (Exception e) {
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}
	}

	@Override
	public Pickup getPickup(String pckupId) throws RENIDataServiceException {
		SqlParameterSource namedParameters = new MapSqlParameterSource(PICKUP_ID, pckupId);
		return (Pickup) namedParameterJdbcTemplate.query(SELECT_PICKUP_BY_ID, namedParameters,
				new PickupRowMapper());
	}

	@Override
	public List<Map<String, Object>> fetchPickupStatus(LocalDate pickupDate) throws RENIDataServiceException {
		Map<String, Object> namedParameters = new HashMap<String, Object>();

		namedParameters.put(PICKUP_DATE, java.sql.Date.valueOf(pickupDate));

		return namedParameterJdbcTemplate.queryForList(SELECT_PICKUP_STATUS_COUNT, namedParameters);
	}

	@Override
	public List<Map<String, Object>> fetchPickupsReport(LocalDate pickupDate) throws RENIDataServiceException {
		Map<String, Object> namedParameters = new HashMap<String, Object>();

		namedParameters.put(PICKUP_DATE, java.sql.Date.valueOf(pickupDate));

		return namedParameterJdbcTemplate.queryForList(SELECT_PICKUP_REPORT, namedParameters);
	}

	@Override
	public List<Map<String, Object>> fetchPickupsAssignmentStatus(LocalDate pickupDate) throws RENIDataServiceException {
		Map<String, Object> namedParameters = new HashMap<String, Object>();

		namedParameters.put(PICKUP_DATE, java.sql.Date.valueOf(pickupDate));

		return namedParameterJdbcTemplate.queryForList(SELECT_PICKUP_ASSIGNMENT_STATUS, namedParameters);
	}

	@Override
	public void closePickup(String userId, Pickup pickupInput) throws RENIDataServiceException {
		try{
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put(PICKUP_ID, pickupInput.getPickupId());
		namedParameters.put(STATUS, pickupInput.getPickupStatus());
		namedParameters.put(COMPLETE, pickupInput.getComplete());
		namedParameters.put(PARITAL_PAYMENT, pickupInput.getParitalPayment());
		namedParameters.put(BALANCE_PAYMENT, pickupInput.getBalancePayment());
		namedParameters.put(TOTAL_PAYMENT, pickupInput.getTotalPayment());
		namedParameters.put(UPDATED_DATE, currentTimeStamp());
		namedParameters.put(UPDATED_BY, userId);

		int status = namedParameterJdbcTemplate.update(PICKUP_CLOSE, namedParameters);
		if(status > 0){
			for (ItemTransaction itemTransaction : pickupInput.getItemTransactions()) {
				Map<String, Object> itemNamedParameter = new HashMap<String, Object>();
				itemNamedParameter.put(ITEM_CODE, itemTransaction.getItemCode());
				itemNamedParameter.put(PICKUP_ID, pickupInput.getPickupId());
				itemNamedParameter.put(ITEM_ACTUAL_VOL, itemTransaction.getItemActualVol());
				itemNamedParameter.put(UPDATED_BY, userId);
				itemNamedParameter.put(UPDATED_DATE, currentTimeStamp());
				itemNamedParameter.put(TOTAL_AMOUNT, itemTransaction.getItemPaidRate()*itemTransaction.getItemActualVol());
				int itemStatus = namedParameterJdbcTemplate.update(ITEM_CLOSE_TRANSACTION, itemNamedParameter);
				
				if (itemStatus <= 0) {
					throw new RENIDataServiceException(
							ITEM_TRANSACTION_SAVE_FAILED);
				}
			}
		}
		changeORRAvaliablity(userId, pickupInput.getPickupId(), pickupInput.getOrrId(), YES);

	} catch (Exception e) {
		e.printStackTrace();
		throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
	}
		
	}


}
