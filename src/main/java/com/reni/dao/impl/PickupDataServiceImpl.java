package com.reni.dao.impl;

import static com.reni.common.util.CommonUtil.currentTimeStamp;
import static com.reni.data.constants.RENIDataConstants.ASSIGNMENT_TYPE;
import static com.reni.data.constants.RENIDataConstants.COMMENTS;
import static com.reni.data.constants.RENIDataConstants.CREATED_BY;
import static com.reni.data.constants.RENIDataConstants.CREATED_DATE;
import static com.reni.data.constants.RENIDataConstants.ITEM_EXPECTED_VOL;
import static com.reni.data.constants.RENIDataConstants.ITEM_ID;
import static com.reni.data.constants.RENIDataConstants.ITEM_PAID_RATE;
import static com.reni.data.constants.RENIDataConstants.ORR;
import static com.reni.data.constants.RENIDataConstants.PICKUP_DATE;
import static com.reni.data.constants.RENIDataConstants.PICKUP_ID;
import static com.reni.data.constants.RENIDataConstants.VENDOR_ID;
import static com.reni.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;
import static com.reni.service.constants.RENIServiceConstant.ITEM_TRANSACTION_SAVE_FAILED;
import static com.reni.service.constants.RENIServiceConstant.PICKUP_NOT_ALLOWED;

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

	private static final String SELECT_PICKUP = "SELECT * FROM pickup";
	private static final String SELECT_PICKUP_BY_ID = "SELECT * FROM pickup where Pickup_id=:id";
	private static final String INSERT_PICKUP = "INSERT INTO PICKUP (PICKUP_ID,CREATED_DATE,CREATED_BY,PICKUP_DATE,ASSIGNMENT_TYPE,ORR,VENDOR_ID,COMMENTS)"
			+ "VALUES (:PICKUP_ID,:CREATED_DATE,:CREATED_BY,:PICKUP_DATE,:ASSIGNMENT_TYPE,:ORR,:VENDOR_ID,:COMMENTS)";
	private static final String INSERT_ITEM_TRANSACTION = "INSERT INTO ITEM_TRANSACTION (PICKUP_ID,ITEM_ID,CREATED_DATE,CREATED_BY,ITEM_PAID_RATE,ITEM_EXPECTED_VOL)"
			+ "VALUES (:PICKUP_ID,:ITEM_ID,:CREATED_DATE,:CREATED_BY,:ITEM_PAID_RATE,:ITEM_EXPECTED_VOL)";;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pickup> listOfPickups() {
		List<Pickup> pickupDetails = (List<Pickup>) namedParameterJdbcTemplate.query(SELECT_PICKUP,
				new PickupRowMapper());
		return pickupDetails;
	}

	@Override
	public void createPickup(Integer userId, Pickup pickup) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(PICKUP_ID, pickup.getPickupId());
			namedParameters.put(VENDOR_ID, pickup.getVendorId());
			namedParameters.put(ASSIGNMENT_TYPE, pickup.getAssignmentType());
			namedParameters.put(ORR, pickup.getOrr());
			namedParameters.put(PICKUP_DATE, pickup.getPickupDate());
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
			namedParameters.put(ITEM_ID, itemTransaction.getItemId());
			namedParameters.put(PICKUP_ID, pickupId);
			namedParameters.put(ITEM_PAID_RATE, itemTransaction.getItemPaidRate());
			namedParameters.put(ITEM_EXPECTED_VOL, itemTransaction.getItemExpectedVol());
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
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", pckupId);
		return (Pickup) namedParameterJdbcTemplate.queryForObject(SELECT_PICKUP_BY_ID, namedParameters,
				new PickupRowMapper());
	}

	@Override
	public void deletePickup(Pickup pickup) throws RENIDataServiceException {
		// TODO Auto-generated method stub

	}
}
