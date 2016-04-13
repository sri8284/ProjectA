package com.rnei.dao.impl;

import static com.rnei.service.constants.RENIDataConstants.EMP_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.EmployeeDataService;
import com.rnei.model.Employee;
import com.rnei.rowmapper.EmployeeRowMapper;
import com.rnei.service.exception.RENIDataServiceException;

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
	public Employee getBasicEmployeeInfo(String userId) throws RENIDataServiceException {
   	    SqlParameterSource namedParameters = new MapSqlParameterSource(EMP_ID, userId);
   	    return (Employee) namedParameterJdbcTemplate.queryForObject(SELECT_BAISC_EMP_DETAILS, namedParameters,  new EmployeeRowMapper());  

	}


}
