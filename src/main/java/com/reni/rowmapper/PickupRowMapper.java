package com.reni.rowmapper;

import static com.reni.data.constants.RENIDataConstants.CLOSING_METER;
import static com.reni.data.constants.RENIDataConstants.COMPLETE_FLAG;
import static com.reni.data.constants.RENIDataConstants.CREATED_BY;
import static com.reni.data.constants.RENIDataConstants.CREATED_DT;
import static com.reni.data.constants.RENIDataConstants.DATE_OF_PICKUP;
import static com.reni.data.constants.RENIDataConstants.OPENING_METER;
import static com.reni.data.constants.RENIDataConstants.ORR;
import static com.reni.data.constants.RENIDataConstants.OTHER;
import static com.reni.data.constants.RENIDataConstants.PICKUP_ID;
import static com.reni.data.constants.RENIDataConstants.REMAINING_TO_BE_PAID;
import static com.reni.data.constants.RENIDataConstants.TOTAL_PAID;
import static com.reni.data.constants.RENIDataConstants.TYPE_OF_ASSIGMENT;
import static com.reni.data.constants.RENIDataConstants.UPDATED_BY;
import static com.reni.data.constants.RENIDataConstants.UPDATED_DT;
import static com.reni.data.constants.RENIDataConstants.VEHICLE_NO_ASSIGNED;
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
		pickup.setAssignementType(rs.getString(TYPE_OF_ASSIGMENT));
		pickup.setColseMeter(rs.getInt(CLOSING_METER));
		pickup.setComplete(rs.getString(COMPLETE_FLAG));
		pickup.setCreatedBy(rs.getString(CREATED_BY));
		pickup.setCreatedDt(rs.getDate(CREATED_DT));
		pickup.setOpenMeter(rs.getInt(OPENING_METER));
		pickup.setOrr(rs.getLong(ORR));
		pickup.setOthers(rs.getString(OTHER));
		pickup.setPickupDate(rs.getDate(DATE_OF_PICKUP));
		pickup.setPickupId(rs.getString(PICKUP_ID));
		pickup.setRemainPayAmount(rs.getFloat(REMAINING_TO_BE_PAID));
		pickup.setTotalPaidAmount(rs.getFloat(TOTAL_PAID));
		pickup.setUpdatedBy(rs.getString(UPDATED_BY));
		pickup.setUpdatedDt(rs.getDate(UPDATED_DT));
		pickup.setVehicleNo(rs.getString(VEHICLE_NO_ASSIGNED));
		pickup.setVendorId(rs.getString(VENDOR_ID));
		
		return pickup;
	}

}
