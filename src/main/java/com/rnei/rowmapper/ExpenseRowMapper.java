package com.rnei.rowmapper;


import static com.rnei.service.constants.RENIDataConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rnei.model.Expense;
import com.rnei.model.ExpenseType;
import com.rnei.model.PaymentMode;

public class ExpenseRowMapper implements RowMapper<Expense> {

	@Override
	public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Expense expense = new Expense();
		final ExpenseType expType = new ExpenseType();
		final PaymentMode payMode = new PaymentMode();
		
		expense.setBillNo(rs.getString(BILLNO));
		expense.setExpenseAmount(rs.getDouble(EXP_AMOUNT));
		expense.setExpenseDate(rs.getDate(EXP_DATE));
		expense.setLocation(rs.getString(LOCATION));
		expense.setMerchantName(rs.getString(MERCHANT_NAME));
		
		expType.setExpType(rs.getString(EXP_TYPE));
		expType.setExpTypeName(rs.getString(EXP_TYPE_NAME));
		expType.setExpSubType(rs.getString(EXP_SUBTYPE));
		expType.setExpSubTypeName(rs.getString(EXP_SUBTYPE_NAME));
		expense.setExpenseType(expType);
		
		payMode.setPaymentCode(rs.getString(PAYMENT_CODE));
		payMode.setPaymentName(rs.getString(PAYMENT_NAME));
		expense.setPaymentMode(payMode);
		
		return expense;
	}

}
