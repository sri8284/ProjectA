package com.rnei.rowmapper;


import static com.rnei.service.constants.RENIDataConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rnei.model.Expense;
import com.rnei.model.ExpenseType;
import com.rnei.model.PaymentMode;
import com.rnei.model.Pickup;

public class ORRPickupRowMapper implements RowMapper<Pickup> {

	@Override
	public Pickup mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pickup pickup = null;
		List<ItemTransaction> items = new ArrayList<ItemTransaction>();
		while(rs.next()){
			if(pickup==null){
				pickup = new Pickup();
				pickup.setPickupId(rs.getString(PICKUP_ID));
				pickup.setAssignmentType(rs.getString(ASSIGNMENT_TYPE));
				pickup.setVehicaleColseMeter(rs.getInt(VEHICLE_CLOSING_METER));
				pickup.setComplete(rs.getString(COMPLETE_FLAG));
				pickup.setCreatedBy(rs.getString(CREATED_BY));
				pickup.setCreatedDt(rs.getDate(CREATED_DATE));
				pickup.setVehicaleOpenMeter(rs.getInt(VEHICLE_OPENING_METER));
				pickup.setOrrId(rs.getString(ORR_ID));
				pickup.setPickupDate(rs.getDate(PICKUP_DATE));
				pickup.setPickupTime(rs.getTime(PICKUP_TIME));
				pickup.setPickupId(rs.getString(PICKUP_ID));
				pickup.setParitalPayment((rs.getFloat(PARITAL_PAYMENT)));
				pickup.setTotalPayment(rs.getFloat(TOTAL_PAYMENT));
				pickup.setUpdatedBy(rs.getString(UPDATED_BY));
				pickup.setUpdatedDt(rs.getDate(UPDATED_DATE));
				pickup.setVendorId(rs.getString(VENDOR_ID));
				pickup.setBalancePayment(rs.getFloat(BALANCE_PAYMENT));
			}
			
			ItemTransaction item = new ItemTransaction();
			item.setItemCode(rs.getString(ITEM_CODE));
			item.setItemExpectedVol(rs.getFloat(ITEM_EXPECTED_VOL));
			item.setItemPaidRate(rs.getFloat(ITEM_PAID_RATE));
			item.setTotalAmount(rs.getFloat(TOTAL_AMOUNT));
			item.setItemActualVol(rs.getFloat(ITEM_ACTUAL_VOL));
			items.add(item);
		}
		if(pickup!=null){
			pickup.setItemTransactions(items);
		}
		
		return pickup;
	}

}
