package com.reni.service.constants;

public interface RENIServiceConstant {

	
	//Maste data path
	String MASTER_PATH="/master";
	String AREAS_PATH="/areas";
	String VENDORS_PATH="/vendors";
	String ITEMS_PATH="/items";
	String EXPENSE_TYPES_PATH="/expenseTypes";
	String PAYMENT_MODES_PATH="/paymentModes";
	String EXPENSE="/expense";
	
	
	String PICKUP_PATH = "/pickup";
	String PICKUP_ID_PATH = "/pickupId";
	String REFERENCE_PATH="/reference";
	String USER_PATH="/user";
	String LOGIN_PATH="/login";
	String LOGOUT_PATH="/logout";
	
	//ORR
	String ORR_PATH="/orr";
	String ONHIRE_PATH="/onHire";
	String AVALIABLE="/avaliable";
	String REPORT ="/report";

	
	//error message
	String INVALID_REQUEST = "Invalid Request.";
	String DUPLICATE_SESSION="User is already logined";
	String INVALID_USER="Invalid User";
	String DATA_SAVE_ERROR="Unexcepted error while saving the data";
	String SERVER_ERROR="Server Error";
	String INVALID_ACCESSS="Invalid SessionId / Session Id is expired";
	String DATA_FECTH_ERROR="Unexcepted error while fetching the data";
	String DATA_REMOVE_ERROR="Unexcepted error while removing the data";
	String PICKUP_NOT_ALLOWED="Pickup creation error - Item is/are not found";
	String ITEM_TRANSACTION_SAVE_FAILED="Item transaction failed while saving the data";
	String INVALID_RIGHTS ="Transaction is not allowed for your role";
	String ONHIRE_ORR_MANADATORY="OnHire ORR : contactNo, drivingLicNo, orrName and VehicleNo is mandatory";
	String DUPLICATE_ONHIRE_ORR="OnHire ORR already existed.";
	String BILLNO_MANDATORY="Expense BillNo is mandatory.";
	String EXP_TYPE_PAYMENT_CODE_MANDATORY="ExpenseType/SubType and PaymentMode is Mandatoy.";
	String EXPENSE_DATE_MANDATORY="Expense Date is mandatory.";

}
