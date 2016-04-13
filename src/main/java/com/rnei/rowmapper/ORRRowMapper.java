package com.rnei.rowmapper;

import static com.rnei.service.constants.RENIDataConstants.*;

import java.awt.datatransfer.StringSelection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rnei.model.OnRoadResource;
public class ORRRowMapper implements RowMapper<OnRoadResource> {

	@Override
	public OnRoadResource mapRow(ResultSet rs, int rowNum) throws SQLException {
		final OnRoadResource orr = new OnRoadResource();

		orr.setOrrId(rs.getString(ORR_ID));
		orr.setOrrName(rs.getString(ORR_NAME));
		String avaliable = rs.getString(AVAILABLE);
		if(avaliable==null || (avaliable!=null && avaliable.length()==0)){
			avaliable="N";
		}
		orr.setAvailable(avaliable);
		orr.setVehicleNo(rs.getString(VEHICLE_NO));
		orr.setDrivingLicNo(rs.getString(DRIVING_LIC_NO));
		orr.setConcatNo(rs.getString(CONCAT_NO));
		orr.setCurrPickupId(rs.getString(CURRENT_PICKUP_ID));
		orr.setCurrDispactherId(rs.getString(CURRENT_DISPACT_ID));
		orr.setPrevPickupId(rs.getString(PREV_PICKUP_ID));
		orr.setCreatedBy(rs.getString(CREATED_BY));
		orr.setCreatedDt(rs.getDate(CREATED_DATE));
		orr.setUpdatedBy(rs.getString(UPDATED_BY));
		orr.setUpdatedDt(rs.getDate(UPDATED_DATE));
		
		return orr;
	}

}
