package com.rnei.service.constants;

public interface RENIServiceConstant {

	/* Path */
	  // Common paths
	  String ALL_PATH = "/all";
	  String ITEM_RATES_PATH = "/itemRates";
	  String ITEM_CODE_PARAM = "itemCode";
	  // Vendor paths
	  String UPLOAD_DOCUMENTS_PATH = "/uploadDocuments";
	  String PICKUP_SCHEDULE_PATH = "/pickupSchedule";
	  String PICKUP_SCHEDULED_ID_PARAM = "pickupScheduleId";

	  /* Path Parameters */
	  // Common path parameters
	  String VENDOR_ID_PATH_PARAM = "vendorId";
	  String PICKUP_ID_PATH_PARAM = "pickupId";
	  String USER_ID = "userId";

	//Master Service path
	String MASTER_PATH="/master";
	
	String AREAS_PATH="/areas";
	String VENDORS_PATH="/vendors";
	String ITEMS_PATH="/items";
	String EXPENSE_TYPES_PATH="/expenseTypes";
	String PAYMENT_MODES_PATH="/paymentModes";
	
	String AREA_PATH="/area";
	String VENDOR_PATH="/vendor";
	String ITEM_PATH="/item";
	String EXPENSE_TYPE_PATH="/expenseType";
	String PAYMENT_MODE_PATH="/paymentMode";
	
	
	//ORR Service path
	String ORR_PATH="/orr";
	
	String AVALIABLE="/avaliable";
	String ONHIRE_PATH="/onHire";
	String REPORT ="/report";
	
	//Pickup Service path
	String PICKUP_PATH = "/pickup";
	
	String PICKUP_ID_PATH = "pickupId";
	String ASSIGNMENT_STATUS="assignmentStatus";
	String STATUS_PATH="/status";
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
	String COMPLETED="Y";
	
	String YES = "Y";
	String NO = "N";

	  String VND_ITEM_RATE_VALIDATE = "The Item code, discount, discount start date and discount end date are mandatory fields";
	  String VND_ITEM_RATE_START_END_DATE_VALIDATE = "Discount start date can't be greater than Discount end date.";
	  String EXISTING_VND_ITEM_RATE_DATES_VALIDATE = "Item start date is already existed, kindly adjust or deactive the existing one.";
	  String VND_ITEM_RATE_NOT_FOUND = "Vendor Item code is not found or existing item code is inactive .";
	  String AREA_NOT_EXISTS = "Invalid Area";
	  String SCHEDULE_PAST_DATE_VALIDATE = "Schedule Date shouldn't past dated.";
	  String INVALID_ITEM_CODES = "Invalid item codes.";
	  String SCHEDULE_ITEM_EXPECTED_ERROR = "Schedule Item expected Volume shouldn't minimum of.";
	  String SCHEDULE_MAX_COUNT_ERROR = "Pickup schedule's reached max count for the same Area, kindly request to future date.";
	  String PICKUP_SCHEDULE_MANDATORY_ERROR = "The below fields are mandatroy : Area code, VendorId, ScheduleDate, ScheduleTime.";
	  String PICKUP_SCHEDULE_ITEMS_MANDATORY_ERROR = "Items information are needed for pickup schedule.";
	  String PICKUP_SCHEDULE_ID_MANDATORY_ERROR = "Pickup scheduleId is mandatroy for update the pickup schedule.";
	  int VENDOR_PICKUP_SCHEDULE_COUNT = 10;
	  int ITEM_MIN_VOLUMN = 10;
	  String UNKNOW_USER_ERROR = "Unknow / Invalid User Initiating the request";
	  String VND_MANDTORY_VALIDATE = "Vendor Mandatory information required";
	  String VND_BASIC_VALIDATE = "Vendor basic information required - First Name, Last Name, Contact No, Area Code, Vendory Type and Vendor address ";
	  String VND_ADDRESS_VALIDATE = "Vendor address information required - State, City and Pincode";
	  String VENDOR_ID_MANDATORY = "Vendor Id is mandatory.";
	  String VENDOR_NOT_EXISTS = "Invalid Vendor";
	  String NON_FOUND_ERROR = "Requested details are not found.";

}
