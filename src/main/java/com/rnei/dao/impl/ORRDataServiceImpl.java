package com.rnei.dao.impl;

import static com.rnei.common.util.CommonUtil.currentTimeStamp;
import static com.rnei.service.constants.RENIDataConstants.*;
import static com.rnei.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.ORRDataService;
import com.rnei.model.OnRoadResource;
import com.rnei.model.OnRoadResourcePickup;
import com.rnei.model.Pickup;
import com.rnei.rowmapper.ORRRowMapper;
import com.rnei.rowmapper.PickupRowMapper;
import com.rnei.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ORRDataServiceImpl implements ORRDataService {

	private static final String SELECT_ORR_DETAILS = "SELECT CONCAT(emp.EMP_FIRST_NAME,' ',emp.EMP_LAST_NAME) as ORR_NAME, orr.* from orr orr "
			+ "join employee emp on emp.EMP_ID = orr.ORR_ID where ACTIVE_STATE='Y' ";
	private static final String SELECT_ACTIVE_ORR_DETAILS = " SELECT CONCAT(emp.EMP_FIRST_NAME,' ',emp.EMP_LAST_NAME) as ORR_NAME, orr.* from orr orr "
			+ "	join employee emp on emp.EMP_ID = orr.ORR_ID where ACTIVE_STATE='Y' and (ASSIGNMENT_TYPE !='P' or ASSIGNMENT_TYPE is null)  ";

	private static final String SELECT_ONHIRE_ORR_DETAILS = "SELECT * FROM onhireorr where ACTIVE_STATE='Y' ";
	private static final String SELECT_ACTIVE_ONHIRE_ORR_DETAILS = "SELECT * FROM onhireorr where ACTIVE_STATE='Y' and (ASSIGNMENT_TYPE !='P' or ASSIGNMENT_TYPE is null) ";

	private static final String INSERT_ONHIRE_ORR = "INSERT INTO ONHIREORR (ORR_ID,ORR_NAME,ACTIVE_STATE,VEHICLE_NO,DRIVING_LIC_NO,CONCAT_NO,ASSIGNMENT_TYPE,CREATED_DATE,CREATED_BY)"
			+ "VALUES (:ORR_ID,:ORR_NAME,:ACTIVE_STATE,:VEHICLE_NO,:DRIVING_LIC_NO,:CONCAT_NO,:ASSIGNMENT_TYPE,:CREATED_DATE,:CREATED_BY) ";
	
	private static final String CHECK_ONHIRE_ORR_NY_DRVLICNO = "SELECT COUNT(*) FROM onhireorr where DRIVING_LIC_NO=:DRIVING_LIC_NO ";

	private static final String SELECT_ORR_PICKUP_DETAILS ="SELECT * FROM PICKUP WHERE ORR_ID=:ORR_ID AND ASSIGNMENT_TYPE=:ASSIGNMENT_TYPE "
			+ " AND (PICKUP_DATE <=:FROM_DATE AND PICKUP_DATE >=TO_DATE )";
			
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
			
			String orrId = "C"+ORRSequence()+"HYD"; 
			
			namedParameters.put(ACTIVE_STATE, onRoadResource.getActiveFlag());
			namedParameters.put(ASSIGNMENT_TYPE, "F");
			namedParameters.put(CONCAT_NO, onRoadResource.getConcatNo());
			namedParameters.put(DRIVING_LIC_NO, onRoadResource.getDrivingLicNo());
			namedParameters.put(ORR_ID, orrId);
			namedParameters.put(ORR_NAME, onRoadResource.getOrrName());
			namedParameters.put(VEHICLE_NO, onRoadResource.getVehicleNo());
			namedParameters.put(CREATED_BY, userId);
			namedParameters.put(CREATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(INSERT_ONHIRE_ORR, namedParameters);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}
	
		
		
		
	}

	@Override
	public void updateOnHireORR(OnRoadResource onRoadResource) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDuplicateOnHireORR(String drivingLicNo) throws RENIDataServiceException {
		final Map<String,Object> namedParameters = new HashMap<String,Object>();
		namedParameters.put(DRIVING_LIC_NO, drivingLicNo);
		
		final int value = namedParameterJdbcTemplate.queryForObject(CHECK_ONHIRE_ORR_NY_DRVLICNO, namedParameters,
				Integer.class);
		if(value==1){
			return true;
		}
		
		return false;
	}

	@Override
	public List<Pickup> fetchORRPickupDetails(OnRoadResourcePickup orrPickupInput) {
		Map<String, Object> namedParameters = new HashMap<String, Object>();

		namedParameters.put(ORR_ID, orrPickupInput.getOrrId());
		namedParameters.put(ASSIGNMENT_TYPE, orrPickupInput.getAssignmentType());
		namedParameters.put(FROM_DATE, orrPickupInput.getFromDate());
		namedParameters.put(TO_DATE, orrPickupInput.getToDate());
		
		return (List<Pickup>) namedParameterJdbcTemplate.query(SELECT_ORR_PICKUP_DETAILS, namedParameters, new PickupRowMapper());
	}

	private Integer ORRSequence(){
		return namedParameterJdbcTemplate.query("select seq('orr')", new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.getInt(0);
		}});
	}
}
