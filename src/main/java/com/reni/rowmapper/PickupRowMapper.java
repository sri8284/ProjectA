package com.reni.rowmapper;

import static com.reni.data.constants.RENIDataConstants.*;
import static com.reni.data.constants.RENIDataConstants.VEHICLE_CLOSING_METER;
import static com.reni.data.constants.RENIDataConstants.COMPLETE_FLAG;
import static com.reni.data.constants.RENIDataConstants.CREATED_BY;
import static com.reni.data.constants.RENIDataConstants.CREATED_DATE;
import static com.reni.data.constants.RENIDataConstants.VEHICLE_OPENING_METER;
import static com.reni.data.constants.RENIDataConstants.PICKUP_DATE;
import static com.reni.data.constants.RENIDataConstants.PICKUP_ID;
import static com.reni.data.constants.RENIDataConstants.PARITAL_PAYMENT;
import static com.reni.data.constants.RENIDataConstants.TOTAL_PAYMENT;
import static com.reni.data.constants.RENIDataConstants.UPDATED_BY;
import static com.reni.data.constants.RENIDataConstants.UPDATED_DATE;
import static com.reni.data.constants.RENIDataConstants.VEHICLE_NO;
import static com.reni.data.constants.RENIDataConstants.VENDOR_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reni.model.Pickup;

public class PickupRowMapper implements RowMapper<Pickup> {

	@Override
	public Pickup mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Pickup pickup = new Pickup();

		pickup.setPickupId(rs.getString(PICKUP_ID));
		pickup.setAssignmentType(rs.getString(ASSIGNMENT_TYPE));
		pickup.setVehicaleColseMeter(rs.getInt(VEHICLE_CLOSING_METER));
		pickup.setComplete(rs.getString(COMPLETE_FLAG));
		pickup.setCreatedBy(rs.getString(CREATED_BY));
		pickup.setCreatedDt(rs.getDate(CREATED_DATE));
		pickup.setVehicaleOpenMeter(rs.getInt(VEHICLE_OPENING_METER));
		pickup.setOrrId(rs.getString(ORR_ID));
		pickup.setPickupDate(rs.getDate(PICKUP_DATE).toLocalDate());
		pickup.setPickupId(rs.getString(PICKUP_ID));
		pickup.setParitalPayment((rs.getFloat(PARITAL_PAYMENT)));
		pickup.setTotalPayment(rs.getFloat(TOTAL_PAYMENT));
		pickup.setUpdatedBy(rs.getString(UPDATED_BY));
		pickup.setUpdatedDt(rs.getDate(UPDATED_DATE));
		pickup.setVehicleNo(rs.getString(VEHICLE_NO));
		pickup.setVendorId(rs.getString(VENDOR_ID));
		pickup.setBalancePayment(rs.getFloat(BALANCE_PAYMENT));
		
		return pickup;
	}

}
