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

	private static final String SELECT_BAISC_EMP_DETAILS = "SELECT EMP_ID, ROLE_TYPE,EMP_FIRST_NAME,EMP_LAST_NAME FROM EMPLOYEE WHERE EMP_ID=:EMP_ID AND DELETE_FLAG='N' ";
	private static final String SELECT_ORR = "SELECT count(*) FROM ORR WHERE ORR_ID=:EMP_ID";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public Employee getBasicEmployeeInfo(String userId) throws RENIDataServiceException {
   	    SqlParameterSource namedParameters = new MapSqlParameterSource(EMP_ID, userId);
   	    Employee employee = (Employee) namedParameterJdbcTemplate.queryForObject(SELECT_BAISC_EMP_DETAILS, namedParameters,  new EmployeeRowMapper());  
   	    Integer orrCount =  namedParameterJdbcTemplate.queryForObject(SELECT_ORR, namedParameters,  Integer.class);  
	    if(orrCount==0){
	    	employee.setRoleType("ADMIN");
	    }else{
	    	employee.setRoleType("ORR");
	    }
   	    
   	    return employee;

	}


}
