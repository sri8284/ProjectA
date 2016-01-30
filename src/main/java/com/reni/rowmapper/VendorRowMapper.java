package com.reni.rowmapper;

import static com.reni.data.constants.RENIDataConstants.VENDOR_ID;
import static com.reni.data.constants.RENIDataConstants.VND_AREA_CODE;
import static com.reni.data.constants.RENIDataConstants.VND_FIRST_NAME;
import static com.reni.data.constants.RENIDataConstants.VND_LAST_NAME;
import static com.reni.data.constants.RENIDataConstants.VND_MOBILE;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reni.model.Vendor;

public class VendorRowMapper implements RowMapper<Vendor> {

	@Override
	public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Vendor vendor = new Vendor();
		vendor.setFirstName(rs.getString(VND_FIRST_NAME));
		vendor.setLastName(rs.getString(VND_LAST_NAME));
		vendor.setMobileNo(rs.getString(VND_MOBILE));
		vendor.setVendorId(rs.getString(VENDOR_ID));
		vendor.setAreaCode(rs.getString(VND_AREA_CODE));
		
		return vendor;
	}

}
