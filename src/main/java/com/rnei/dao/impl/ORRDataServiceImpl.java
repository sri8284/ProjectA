package com.rnei.dao.impl;

import static com.rnei.common.util.CommonUtil.currentTimeStamp;
import static com.rnei.service.constants.RENIDataConstants.*;
import static com.rnei.service.constants.RENIServiceConstant.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.rnei.rowmapper.ORRPickupRowMapper;
import com.rnei.rowmapper.ORRRowMapper;
import com.rnei.rowmapper.PickupRowMapper;
import com.rnei.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ORRDataServiceImpl implements ORRDataService {

	// it will fetch all orr details - TODO employee active Flag should be added here.
	private static final String SELECT_ORR_DETAILS = "SELECT CONCAT(emp.EMP_FIRST_NAME,' ',emp.EMP_LAST_NAME) as ORR_NAME, orr.* from orr orr "
			+ "join employee emp on emp.EMP_ID = orr.ORR_ID ";
	//available orr details will fetch TODO active falg should be added here.
	private static final String SELECT_AVAILABLE_ORR_DETAILS = " SELECT CONCAT(emp.EMP_FIRST_NAME,' ',emp.EMP_LAST_NAME) as ORR_NAME, orr.* from orr orr "
			+ "	join employee emp on emp.EMP_ID = orr.ORR_ID AND (AVAILABLE !='N' or AVAILABLE is null)  ";

	private static final String SELECT_ONHIRE_ORR_DETAILS = "SELECT * FROM onhireorr where ACTIVE ='Y' ";
	private static final String SELECT_AVAILABLE_ONHIRE_ORR_DETAILS = "SELECT * FROM onhireorr where ACTIVE ='Y' and (AVAILABLE !='N' or AVAILABLE is null)  ";

	private static final String INSERT_ONHIRE_ORR = "INSERT INTO ONHIREORR (ORR_ID,ORR_NAME,VEHICLE_NO,DRIVING_LIC_NO,CONCAT_NO,AVAILABLE,CREATED_DATE,CREATED_BY, ACTIVE)"
			+ "VALUES (:ORR_ID,:ORR_NAME,:VEHICLE_NO,:DRIVING_LIC_NO,:CONCAT_NO,:AVAILABLE,:CREATED_DATE,:CREATED_BY, :ACTIVE) ";
	
	private static final String CHECK_ONHIRE_ORR_BY_DRVLICNO = "SELECT COUNT(*) FROM onhireorr where DRIVING_LIC_NO=:DRIVING_LIC_NO ";

	private static final String SELECT_ORR_PICKUP_DETAILS ="SELECT * FROM PICKUP WHERE ORR_ID=:ORR_ID AND STATUS=:STATUS "
			+ " AND (PICKUP_DATE >=:FROM_DATE AND PICKUP_DATE <=TO_DATE )";
	private static final String UPDATE_ONHIRE_ORR = "UPDATE reni.onhireorr SET ORR_NAME = :ORR_NAME, VEHICLE_NO = :VEHICLE_NO, DRIVING_LIC_NO = :DRIVING_LIC_NO, CONCAT_NO = :CONCAT_NO,   UPDATED_DATE = :UPDATED_DATE, UPDATED_BY = :UPDATED_BY WHERE ORR_ID = :ORR_ID";
			
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
		List<OnRoadResource> orrDetails = (List<OnRoadResource>) namedParameterJdbcTemplate.query(SELECT_AVAILABLE_ORR_DETAILS,
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
		List<OnRoadResource> orrDetails = (List<OnRoadResource>) namedParameterJdbcTemplate.query(SELECT_AVAILABLE_ONHIRE_ORR_DETAILS,
				new ORRRowMapper());
		
		return orrDetails;
	}

	@Override
	public String createOnHireORR(String userId, OnRoadResource onRoadResource) throws RENIDataServiceException {
		//validate manadatory fields.
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			Integer orrSeq = ORRSequence();
			if(orrSeq ==null){
				throw new RENIDataServiceException("Techincal Problem persisted, please contact support team");
			}
			
			String orrId = "C"+ORRSequence()+"HYD"; 
			
			namedParameters.put(AVAILABLE, YES);
			namedParameters.put(CONCAT_NO, onRoadResource.getConcatNo());
			namedParameters.put(DRIVING_LIC_NO, onRoadResource.getDrivingLicNo());
			namedParameters.put(ORR_ID, orrId);
			namedParameters.put(ORR_NAME, onRoadResource.getOrrName());
			namedParameters.put(VEHICLE_NO, onRoadResource.getVehicleNo());
			namedParameters.put(CREATED_BY, userId);
			namedParameters.put(CREATED_DATE, currentTimeStamp());
			namedParameters.put(ACTIVE, YES);
		
			namedParameterJdbcTemplate.update(INSERT_ONHIRE_ORR, namedParameters);
			
			return orrId;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}
	
		
		
		
	}

	@Override
	public void updateOnHireORR(String userId, OnRoadResource onRoadResource) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			
			
			namedParameters.put(AVAILABLE, onRoadResource.getAvailable());
			namedParameters.put(CONCAT_NO, onRoadResource.getConcatNo());
			namedParameters.put(DRIVING_LIC_NO, onRoadResource.getDrivingLicNo());
			namedParameters.put(ORR_ID, onRoadResource.getOrrId());
			namedParameters.put(ORR_NAME, onRoadResource.getOrrName());
			namedParameters.put(VEHICLE_NO, onRoadResource.getVehicleNo());
			namedParameters.put(UPDATED_BY, userId);
			namedParameters.put(UPDATED_DATE, currentTimeStamp());
			namedParameters.put(ACTIVE, onRoadResource.getActive());
		
			namedParameterJdbcTemplate.update(UPDATE_ONHIRE_ORR, namedParameters);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(DATA_SAVE_ERROR, e);
		}		
	}

	@Override
	public boolean isDuplicateOnHireORR(String drivingLicNo) throws RENIDataServiceException {
		final Map<String,Object> namedParameters = new HashMap<String,Object>();
		namedParameters.put(DRIVING_LIC_NO, drivingLicNo);
		
		final int value = namedParameterJdbcTemplate.queryForObject(CHECK_ONHIRE_ORR_BY_DRVLICNO, namedParameters,
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
		namedParameters.put(STATUS, orrPickupInput.getPickupStatus());
		namedParameters.put(FROM_DATE, orrPickupInput.getFromDate());
		namedParameters.put(TO_DATE, orrPickupInput.getToDate());
		
		return (List<Pickup>) namedParameterJdbcTemplate.query(SELECT_ORR_PICKUP_DETAILS, namedParameters, new ORRPickupRowMapper());
	}

	private Integer ORRSequence(){
		return namedParameterJdbcTemplate.query("select seq('orr')", new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					return rs.getInt(1);
				}
				return null;
		}});
	}
}
