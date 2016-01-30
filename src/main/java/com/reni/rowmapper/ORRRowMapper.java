package com.reni.rowmapper;

import static com.reni.data.constants.RENIDataConstants.ASSIGNMENT_TYPE;
import static com.reni.data.constants.RENIDataConstants.ORR_EMP_ID;
import static com.reni.data.constants.RENIDataConstants.ORR_EMP_NAME;
import static com.reni.data.constants.RENIDataConstants.ORR_EMP_TYPE;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reni.model.OnRoadResource;
public class ORRRowMapper implements RowMapper<OnRoadResource> {

	@Override
	public OnRoadResource mapRow(ResultSet rs, int rowNum) throws SQLException {
		final OnRoadResource orr = new OnRoadResource();
		orr.setEmpId(rs.getInt(ORR_EMP_ID));
		orr.setEmpName(rs.getString(ORR_EMP_NAME));
		orr.setEmployeeType(rs.getString(ORR_EMP_TYPE));
		orr.setAssigmentType(rs.getString(ASSIGNMENT_TYPE));
		return orr;
	}

}
