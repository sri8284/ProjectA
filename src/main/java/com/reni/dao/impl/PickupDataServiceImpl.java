package com.reni.dao.impl;

import static com.reni.common.util.CommonUtil.currentTimeStamp;
import static com.reni.data.constants.RENIDataConstants.*;
import static com.reni.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;
import static com.reni.service.constants.RENIServiceConstant.ITEM_TRANSACTION_SAVE_FAILED;
import static com.reni.service.constants.RENIServiceConstant.*;

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

import com.reni.dao.PickupDataService;
import com.reni.model.ItemTransaction;
import com.reni.model.Pickup;
import com.reni.rowmapper.PickupRowMapper;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class PickupDataServiceImpl implements PickupDataService {

	private static final String SELECT_PICKUP_STATUS = "SELECT COMPLETE_FLAG as completeFlag, COUNT(COMPLETE_FLAG) AS count FROM PICKUP WHERE PICKUP_DATE =:PICKUP_DATE GROUP BY COMPLETE_FLAG";

	private static final String SELECT_PICKUP_REPORT = "SELECT COMPLETE_FLAG as completeFlag, COUNT(COMPLETE_FLAG) AS count, SUM(TOTAL_PAYMENT) AS totalPayment , SUM(PARITAL_PAYMENT) AS paritalPayment , "
			+ "SUM(BALANCE_PAYMENT) AS balancePayment "
			+ "	FROM PICKUP WHERE PICKUP_DATE =:PICKUP_DATE GROUP BY COMPLETE_FLAG ";

	private static final String SELECT_PICKUP_ASSIGNMENT_STATUS = "SELECT PICKUP_ID as pickupId, ASSIGNMENT_TYPE as assignmentType, PICKUP_DATE AS pickupDate, PICKUP_TIME AS pickupTime,"
			+ " P.AREA_CODE AS areaCode, A.AREA_NAME as areaName FROM PICKUP P JOIN AREA A ON A.AREA_CODE = P.AREA_CODE WHERE PICKUP_DATE= :PICKUP_DATE ";

	private static final String SELECT_PICKUP_BY_ID = "SELECT * FROM PICKUP P JOIN ITEM_TRANSACTION T ON P.PICKUP_ID=T.PICKUP_ID  where P.PICKUP_ID=:PICKUP_ID";

	private static final String INSERT_PICKUP = "INSERT INTO PICKUP (PICKUP_ID,CREATED_DATE,CREATED_BY,PICKUP_DATE,PICKUP_TIME,ASSIGNMENT_TYPE,ORR_ID,VENDOR_ID,COMMENTS,AREA_CODE)"
			+ "VALUES (:PICKUP_ID,:CREATED_DATE,:CREATED_BY,:PICKUP_DATE,:PICKUP_TIME,:ASSIGNMENT_TYPE,:ORR_ID,:VENDOR_ID,:COMMENTS,:AREA_CODE)";
	private static final String INSERT_ITEM_TRANSACTION = "INSERT INTO ITEM_TRANSACTION (PICKUP_ID,ITEM_CODE,CREATED_DATE,CREATED_BY,ITEM_PAID_RATE,ITEM_EXPECTED_VOL)"
			+ "VALUES (:PICKUP_ID,:ITEM_CODE,:CREATED_DATE,:CREATED_BY,:ITEM_PAID_RATE,:ITEM_EXPECTED_VOL)";

	private static final String PICKUP_CLOSE = "UPDATE PICKUP SET ASSIGNMENT_TYPE=:ASSIGNMENT_TYPE, COMPLETE_FLAG=:COMPLETE_FLAG, TOTAL_PAYMENT=:TOTAL_PAYMENT,PARITAL_PAYMENT=:PARITAL_PAYMENT, BALANCE_PAYMENT=:BALANCE_PAYMENT, UPDATED_DATE=:UPDATED_DATE, UPDATED_BY=:UPDATED_BY "
			+ "WHERE PICKUP_ID =:PICKUP_ID ";
	private static final String ITEM_CLOSE_TRANSACTION ="UPDATE ITEM_TRANSACTION SET ITEM_ACTUAL_VOL=:ITEM_ACTUAL_VOL, TOTAL_AMOUNT=:TOTAL_AMOUNT, UPDATED_BY=:UPDATED_BY, UPDATED_DATE=:UPDATED_DATE "
			+ "WHERE PICKUP_ID=:PICKUP_ID AND ITEM_CODE=:ITEM_CODE ";





	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void createPickup(Integer userId, Pickup pickup) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(PICKUP_ID, pickup.getPickupId());
			namedParameters.put(VENDOR_ID, pickup.getVendorId());
			namedParameters.put(ASSIGNMENT_TYPE, NEW_PICKUP);
			namedParameters.put(ORR_ID, pickup.getOrrId());
			namedParameters.put(PICKUP_DATE, pickup.getPickupDate());
			namedParameters.put(PICKUP_TIME, pickup.getPickupTime());
			namedParameters.put(COMPLETE_FLAG, NON_COMPLETE);
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
				throw new RENIDataServiceException(RENIErrorCodes.PICKUP_NOT_ALLOWED, PICKUP_NOT_ALLOWED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}
	}

	private void createItemTransaction(Integer userId, ItemTransaction itemTransaction, String pickupId)
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
				throw new RENIDataServiceException(RENIErrorCodes.ITEM_TRANSACTION_SAVE_FAILED,
						ITEM_TRANSACTION_SAVE_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
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

		return namedParameterJdbcTemplate.queryForList(SELECT_PICKUP_STATUS, namedParameters);
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
	public void closePickup(Integer userId, Pickup pickupInput) throws RENIDataServiceException {
		try{
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put(PICKUP_ID, pickupInput.getPickupId());
		
		namedParameters.put(ASSIGNMENT_TYPE, pickupInput.getAssignmentType());
		namedParameters.put(COMPLETE_FLAG, pickupInput.getComplete());
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
					throw new RENIDataServiceException(RENIErrorCodes.ITEM_TRANSACTION_SAVE_FAILED,
							ITEM_TRANSACTION_SAVE_FAILED);
				}
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new RENIDataServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
	}
		
	}


}
