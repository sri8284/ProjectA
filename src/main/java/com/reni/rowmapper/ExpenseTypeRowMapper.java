package com.reni.rowmapper;

import static com.reni.data.constants.RENIDataConstants.EXP_SUBTYPE;
import static com.reni.data.constants.RENIDataConstants.EXP_SUBTYPE_NAME;
import static com.reni.data.constants.RENIDataConstants.EXP_TYPE;
import static com.reni.data.constants.RENIDataConstants.EXP_TYPE_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reni.model.ExpenseType;

public class ExpenseTypeRowMapper implements RowMapper<ExpenseType>{
	public ExpenseType mapRow(ResultSet rs, int rowNum) throws SQLException {
		final ExpenseType expenseType = new ExpenseType();
		expenseType.setExpType(rs.getString(EXP_TYPE));
		expenseType.setExpTypeName(rs.getString(EXP_TYPE_NAME));
		expenseType.setExpSubType(rs.getString(EXP_SUBTYPE));
		expenseType.setExpSubTypeName(rs.getString(EXP_SUBTYPE_NAME));
		
		return expenseType;
	}

}
