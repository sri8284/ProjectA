package com.rnei.rowmapper;

import static com.rnei.service.constants.RENIDataConstants.EMP_FIRST_NAME;
import static com.rnei.service.constants.RENIDataConstants.EMP_ID;
import static com.rnei.service.constants.RENIDataConstants.EMP_LAST_NAME;
import static com.rnei.service.constants.RENIDataConstants.ROLE_TYPE;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rnei.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Employee employee = new Employee();
		employee.setUserId(rs.getInt(EMP_ID));
		employee.setRoleType(rs.getString(ROLE_TYPE));
		employee.setFirstName(rs.getString(EMP_FIRST_NAME));
		employee.setLastName(rs.getString(EMP_LAST_NAME));
		
		return employee;
	}

}
