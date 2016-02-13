package com.reni.dao.impl;

import static com.reni.common.util.CommonUtil.currentTimeStamp;
import static com.reni.data.constants.RENIDataConstants.*;
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

import com.reni.dao.ORRDataService;
import com.reni.dao.PickupDataService;
import com.reni.model.ItemTransaction;
import com.reni.model.OnRoadResource;
import com.reni.model.Pickup;
import com.reni.rowmapper.ORRRowMapper;
import com.reni.rowmapper.PickupRowMapper;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ORRDataServiceImpl implements ORRDataService {

	private static final String SELECT_ORR_DETAILS = "SELECT CONCAT(emp.EMP_FIRST_NAME,' ',emp.EMP_LAST_NAME) as ORR_NAME, orr.* from orr orr "
			+ "join employee emp on emp.EMP_ID = orr.ORR_ID where ACTIVE_STATE='Y' ";
	private static final String SELECT_ACTIVE_ORR_DETAILS = " SELECT CONCAT(emp.EMP_FIRST_NAME,' ',emp.EMP_LAST_NAME) as ORR_NAME, orr.* from orr orr "
			+ "	join employee emp on emp.EMP_ID = orr.ORR_ID where ACTIVE_STATE='Y' and (ASSIGNMENT_TYPE !='P' or ASSIGNMENT_TYPE is null)  ";

	private static final String SELECT_ONHIRE_ORR_DETAILS = "SELECT * FROM onhireorr";
	private static final String SELECT_ACTIVE_ONHIRE_ORR_DETAILS = "SELECT * FROM onhireorr where ACTIVE_STATE='Y' and (ASSIGNMENT_TYPE !='P' or ASSIGNMENT_TYPE is null) ";

	private static final String INSERT_ONHIRE_ORR = "INSERT INTO ONHIREORR (ORR_ID,ORR_NAME,ACTIVE_STATE,VEHICLE_NO,DRIVING_LIC_NO,CONCAT_NO,ASSIGNMENT_TYPE,CREATED_DATE,CREATED_BY)"
			+ "VALUES (:ORR_ID,:ORR_NAME,:ACTIVE_STATE,:VEHICLE_NO,:DRIVING_LIC_NO,:CONCAT_NO,:ASSIGNMENT_TYPE,:CREATED_DATE,:CREATED_BY) ";
	private static final String INSERT_ITEM_TRANSACTION = "INSERT INTO ITEM_TRANSACTION (PICKUP_ID,ITEM_ID,CREATED_DATE,CREATED_BY,ITEM_PAID_RATE,ITEM_EXPECTED_VOL)"
			+ "VALUES (:PICKUP_ID,:ITEM_ID,:CREATED_DATE,:CREATED_BY,:ITEM_PAID_RATE,:ITEM_EXPECTED_VOL)";;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<OnRoadResource> fetchORRDetails() throws RENIDataServiceException {
		
		List<OnRoadResource> orrDetails = (List<OnRoadResource>) namedParameterJdbcTemplate.query(SELECT_ORR_DETAILS,
				new ORRRowMapper());
		
		return orrDetails;
		
	}

	@Override
	public List<OnRoadResource> fetchActiveORRDetails() throws RENIDataServiceException {
		List<OnRoadResource> orrDetails = (List<OnRoadResource>) namedParameterJdbcTemplate.query(SELECT_ACTIVE_ORR_DETAILS,
				new ORRRowMapper());
		
		return orrDetails;
	}

	@Override
	public void createORR(OnRoadResource onRoadResource) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateORR(OnRoadResource onRoadResource) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OnRoadResource> fetchOnHireORRDetails() throws RENIDataServiceException {
		List<OnRoadResource> orrDetails = (List<OnRoadResource>) namedParameterJdbcTemplate.query(SELECT_ONHIRE_ORR_DETAILS,
				new ORRRowMapper());
		
		return orrDetails;
	}

	@Override
	public List<OnRoadResource> fetchActiveOnHireORRDetails() throws RENIDataServiceException {
		List<OnRoadResource> orrDetails = (List<OnRoadResource>) namedParameterJdbcTemplate.query(SELECT_ACTIVE_ONHIRE_ORR_DETAILS,
				new ORRRowMapper());
		
		return orrDetails;
	}

	@Override
	public void createOnHireORR(String userId, OnRoadResource onRoadResource) throws RENIDataServiceException {
		//validate manadatory fields.
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			
			namedParameters.put(ACTIVE_STATE, onRoadResource.getActiveFlag());
			namedParameters.put(ASSIGNMENT_TYPE, "F");
			namedParameters.put(CONCAT_NO, onRoadResource.getConcatNo());
			namedParameters.put(DRIVING_LIC_NO, onRoadResource.getDrivingLicNo());
			namedParameters.put(ORR_ID, onRoadResource.getOrrId());
			namedParameters.put(ORR_NAME, onRoadResource.getOrrName());
			namedParameters.put(VEHICLE_NO, onRoadResource.getVehicleNo());
			namedParameters.put(CREATED_BY, userId);
			namedParameters.put(CREATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(INSERT_ONHIRE_ORR, namedParameters);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}
	
		
		
		
	}

	@Override
	public void updateOnHireORR(OnRoadResource onRoadResource) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	
}
