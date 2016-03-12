package com.rnei.service.constants;

public interface RENIServiceConstant {

	
	//Master Service path
	String MASTER_PATH="/master";
	
	String AREAS_PATH="/areas";
	String VENDORS_PATH="/vendors";
	String ITEMS_PATH="/items";
	String EXPENSE_TYPES_PATH="/expenseTypes";
	String PAYMENT_MODES_PATH="/paymentModes";
	
	//ORR Service path
	String ORR_PATH="/orr";
	
	String AVALIABLE="/avaliable";
	String ONHIRE_PATH="/onHire";
	String REPORT ="/report";
	
	//Pickup Service path
	String PICKUP_PATH = "/pickup";
	
	String PICKUP_ID_PATH = "pickupId";
	String ASSIGNMENT_STATUS="assignmentStatus";
	String STATUS="/status";
	String CLOSE="close";
	
	// Expense Service Path
	String EXPENSE="/expense";
	
	String REFERENCE_PATH="/reference";
	String USER_PATH="/user";
	String LOGIN_PATH="/login";
	String LOGOUT_PATH="/logout";
	String DATE="date";
	
	String NOTES_PATH="/notes";
	String CREATE ="/create";
	
	//error message
	String INVALID_REQUEST = "Invalid Request.";
	String DUPLICATE_SESSION="User is already logined";
	String INVALID_USER="Invalid User";
	String DATA_SAVE_ERROR="Unexcepted error while saving the data";
	String SERVER_ERROR="Internal Server Error";
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
	
	String NEW_PICKUP="N";
	String PENDING_FOR_SETTLEMENT="P";
	String CLOSE_PICKUP="C";
	
	String NON_COMPLETE = "N";
	String COMPLETED="C";

}
