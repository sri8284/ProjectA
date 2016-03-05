package com.rnei.rowmapper;

import static com.rnei.service.constants.RENIDataConstants.PAYMENT_CODE;
import static com.rnei.service.constants.RENIDataConstants.PAYMENT_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rnei.model.PaymentMode;

public class PaymentModeRowMapper implements RowMapper<PaymentMode> {

	@Override
	public PaymentMode mapRow(ResultSet rs, int rowNum) throws SQLException {
		final PaymentMode paymentMode = new PaymentMode();
		
		paymentMode.setPaymentCode(rs.getString(PAYMENT_CODE));
		paymentMode.setPaymentName(rs.getString(PAYMENT_NAME));
		
		return paymentMode;
	}

}
