package com.reni.data.constants;

public interface RENIDataConstants {

	//common columns
	String CREATED_DATE="CREATED_DATE";
	String CREATED_BY="CREATED_BY";
	String UPDATED_DATE="UPDATED_DATE";
	String UPDATED_BY="UPDATED_BY";
	
	//PICKUP TABLE
	String PICKUP_ID="PICKUP_ID";
	String PICKUP_DATE="PICKUP_DATE";
	String PICKUP_TIME="PICKUP_TIME";
	String ASSIGNMENT_TYPE="ASSIGNMENT_TYPE";
	String OTHER="OTHER";
	String TOTAL_PAYMENT="TOTAL_PAYMENT";
	String BALANCE_PAYMENT="BALANCE_PAYMENT";
	String PARITAL_PAYMENT="PARITAL_PAYMENT";
	String VEHICLE_OPENING_METER="VEHICLE_OPENING_METER";
	String VEHICLE_CLOSING_METER="VEHICLE_CLOSING_METER";
	String COMPLETE_FLAG="COMPLETE_FLAG";
	String NO_OF_KILOMETERS="NO_OF_KILOMETERS";
	String WEIGH_BRIDGE="WEIGH_BRIDGE";
	String ATTACHMENT="ATTACHMENT";
	String PICKUP_OPEN_TIME="PICKUP_OPEN_TIME";
	String PICKUP_CLOSE_TIME="PICKUP_CLOSE_TIME";
	String COMMENTS="COMMENTS";
	
	//EMPLOYEE TABLE
	String EMP_ID="EMP_ID";
	String ROLE_TYPE="ROLE_TYPE";
	String EMP_LAST_NAME="EMP_LAST_NAME";
	String EMP_FIRST_NAME="EMP_FIRST_NAME";
	
	//SESSION TABLE
	String ACCESS_KEY="Access_Key";
	String START_TIME="START_TIME";
	String END_TIME="END_TIME";
	
	//USER TABLE
	String PASSWORD = "password";
	String USER_ID="userId";
	
	//VENDOR TABLE
	String VENDOR_ID ="VENDOR_ID";
	String VND_FIRST_NAME="VND_FIRST_NAME";
	String VND_LAST_NAME ="VND_LAST_NAME";
	String VND_ADDRESS ="VND_ADDRESS";
	String VND_CONTACT_NO ="VND_CONTACT_NO";
	String CITY ="CITY";
	String STATE ="STATE";
	String LANDMARK ="LANDMARK";
	String PINCODE ="PINCODE";
	
	
	//ORR TABLE
	String ORR_ID ="ORR_ID";
	String ORR_NAME ="ORR_NAME";
	String ACTIVE_STATE ="ACTIVE_STATE";
	String VEHICLE_NO ="VEHICLE_NO";
	String DRIVING_LIC_NO ="DRIVING_LIC_NO";
	String CONCAT_NO ="CONCAT_NO";
	String CURRENT_DISPACT_ID ="CURRENT_DISPACT_ID";
	String PREV_PICKUP_ID ="PREV_PICKUP_ID";
	String CURRENT_PICKUP_ID ="CURRENT_PICKUP_ID";
	

	//ITEM TABLE
	String ITEM_CODE ="ITEM_CODE";
	String ITEM_NAME ="ITEM_NAME";
	String CURRENT_RATE ="CURRENT_RATE";
	
	//AREA TABLE
	String AREA_CODE="AREA_CODE";
	String AREA_NAME="AREA_NAME";

	//PAYENT_MODE TABLE
	String PAYMENT_CODE="PAYMENT_CODE";
	String PAYMENT_NAME="PAYMENT_NAME";
	
	//ITEM_TRANSACTION TABLE
	String ITEM_EXPECTED_VOL="ITEM_EXPECTED_VOL";
	String ITEM_ACTUAL_VOL="ITEM_ACTUAL_VOL";
	String ITEM_CURRENT_RATE="ITEM_CURRENT_RATE";
	String ITEM_PAID_RATE="ITEM_PAID_RATE";
	String AMT_NEED_TO_BE_PAID="AMT_NEED_TO_BE_PAID";
	String PARTIAL_PAYMENT="PARTIAL_PAYMENT";
	String TOTAL_AMOUNT="TOTAL_AMOUNT";
	
	//EXPENSE_TYPE TABLE
	String EXP_TYPE="EXP_TYPE";
	String EXP_SUBTYPE="EXP_SUBTYPE";
	String EXP_TYPE_NAME="EXP_TYPE_NAME";
	String EXP_SUBTYPE_NAME="EXP_SUBTYPE_NAME";
	
	//SESSION_TIME
	int SESSION_TIME=1000;
	
	//EXPENSE TABLE
	
	String EXP_DATE="EXP_DATE";
	String EXP_AMOUNT="EXP_AMOUNT";
	String MERCHANT_NAME="MERCHANT_NAME";
	String BILLNO="BILLNO";
	String LOCATION="LOCATION";
	
	//NOTES
	String FROM_DATE="FROM_DATE";
	String TO_DATE="TO_DATE";
	String NOTE_DATE ="NOTE_DATE";
	String NOTE_COMMENTS = "COMMENTS";
	String NOTE_ID ="NOTE_ID";
	
}
