package com.reni.dao.impl;

import static com.reni.data.constants.RENIDataConstants.EMP_ID;
import static com.reni.data.constants.RENIDataConstants.ACCESS_KEY;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.EmployeeDataService;
import com.reni.model.Employee;
import com.reni.rowmapper.EmployeeRowMapper;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class EmployeeDataServiceImpl implements EmployeeDataService {

	private static final String SELECT_BAISC_EMP_DETAILS = "SELECT EMP_ID, ROLE_TYPE,EMP_FIRST_NAME,EMP_LAST_NAME FROM EMPLOYEE WHERE EMP_ID=:EMP_ID";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public Employee getBasicEmployeeInfo(Integer userId) throws RENIDataServiceException {
   	    SqlParameterSource namedParameters = new MapSqlParameterSource(EMP_ID, userId);
   	    return (Employee) namedParameterJdbcTemplate.queryForObject(SELECT_BAISC_EMP_DETAILS, namedParameters,  new EmployeeRowMapper());  

	}


}
