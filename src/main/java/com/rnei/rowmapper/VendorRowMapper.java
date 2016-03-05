package com.rnei.rowmapper;

import static com.rnei.service.constants.RENIDataConstants.AREA_CODE;
import static com.rnei.service.constants.RENIDataConstants.CITY;
import static com.rnei.service.constants.RENIDataConstants.LANDMARK;
import static com.rnei.service.constants.RENIDataConstants.PINCODE;
import static com.rnei.service.constants.RENIDataConstants.STATE;
import static com.rnei.service.constants.RENIDataConstants.VENDOR_ID;
import static com.rnei.service.constants.RENIDataConstants.VND_ADDRESS;
import static com.rnei.service.constants.RENIDataConstants.VND_CONTACT_NO;
import static com.rnei.service.constants.RENIDataConstants.VND_FIRST_NAME;
import static com.rnei.service.constants.RENIDataConstants.VND_LAST_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rnei.model.Address;
import com.rnei.model.Vendor;

public class VendorRowMapper implements RowMapper<Vendor> {

	@Override
	public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Vendor vendor = new Vendor();
		final Address vndAddress=new Address();
		
		vendor.setVendorId(rs.getString(VENDOR_ID));
		vendor.setAreaCode(rs.getString(AREA_CODE));
		vendor.setVndfirstName(rs.getString(VND_FIRST_NAME));
		vendor.setVndlastName(rs.getString(VND_LAST_NAME));
		vendor.setVndContactNo(rs.getString(VND_CONTACT_NO));

		vndAddress.setAddress(rs.getString(VND_ADDRESS));
		vndAddress.setCity(rs.getString(CITY));
		vndAddress.setState(rs.getString(STATE));
		vndAddress.setLandmark(rs.getString(LANDMARK));
		vndAddress.setPincode(rs.getLong(PINCODE));
		
		vendor.setVndAddress(vndAddress);
		
		return vendor;
	}

}
