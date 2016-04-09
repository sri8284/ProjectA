package com.rnei.controller;

import static com.rnei.service.constants.RENIDataConstants.USER_ID;
import static com.rnei.service.constants.RENIServiceConstant.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rnei.model.Area;
import com.rnei.model.ExpenseType;
import com.rnei.model.Item;
import com.rnei.model.PaymentMode;
import com.rnei.model.Vendor;
import com.rnei.service.MasterDataSerice;
import com.rnei.service.exception.RENIServiceException;

@Path(MASTER_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class MasterDataController extends CoreController{

	static final Logger logger = Logger.getLogger(MasterDataController.class); // register class with logger  

	@Autowired
	MasterDataSerice masterDataService;
	
	@GET
	@Path(AREAS_PATH)
	public Response fetchAreaDetails(@HeaderParam(USER_ID) final Integer userId) throws RENIServiceException{
		validateInput(userId);
		return Response.ok(masterDataService.fetchAreaDetails()).build();
	}
	
	
	//fetch item details
	@GET
	@Path(ITEMS_PATH)
	public Response fetchItemDetails(@HeaderParam(USER_ID) final Integer userId) throws RENIServiceException{
		
		validateInput(userId);
		return Response.ok(masterDataService.fetchItemDetails()).build();
	}
	
	
	//fetch vendor details
	@GET
	@Path(VENDORS_PATH)
	public Response fetchVendorDetails(@HeaderParam(USER_ID) final Integer userId) throws RENIServiceException{
		
		validateInput(userId);
		return Response.ok(masterDataService.fetchVendorDetails()).build();
	}
	
	
	//fetch expenseType details
	@GET
	@Path(EXPENSE_TYPES_PATH)
	public Response fetchExpenseTypeDetails(@HeaderParam(USER_ID) final Integer userId) throws RENIServiceException{
		
		validateInput(userId);
		return Response.ok(masterDataService.fetchExpenseTypeDetails()).build();
	}
	
	//fetch payment modes details
	@GET
	@Path(PAYMENT_MODES_PATH)
	public Response fetchPaymentModes(@HeaderParam(USER_ID)  final Integer userId) throws RENIServiceException{
		
		validateInput(userId);
		return Response.ok(masterDataService.fetchPaymentModes()).build();
	}
	
	// Update
	@PUT
	@Path(AREA_PATH)
	public Response updateAreaDetails(@HeaderParam(USER_ID) final String userId, final Area area) throws RENIServiceException{
		validateInput(area);
		area.setUpdatedBy(userId);
		masterDataService.updateArea(area);
		
		return Response.ok().build();
	}
	
	
	//update item details
	@PUT
	@Path(ITEM_PATH)
	public Response updateItemDetails(@HeaderParam(USER_ID) final String userId, final Item item) throws RENIServiceException{
		
		validateInput(item);
		item.setUpdatedBy(userId);
		masterDataService.updateItem(item);
		return Response.ok().build();
	}
	
	
	//update vendor details
	@PUT
	@Path(VENDOR_PATH)
	public Response updateVendorDetails(@HeaderParam(USER_ID) final String userId, final Vendor vendor) throws RENIServiceException{
		
		validateInput(vendor);
		vendor.setUpdatedBy(userId);
		masterDataService.updateVendor(vendor);
		return Response.ok().build();
	}
	
	
	//update expenseType details
	@PUT
	@Path(EXPENSE_TYPE_PATH)
	public Response updateExpenseTypeDetails(@HeaderParam(USER_ID)final String userId, final ExpenseType expenseType) throws RENIServiceException{
		
		validateInput(expenseType);
		expenseType.setUpdatedBy(userId);
		masterDataService.updateExpenseType(expenseType);
		return Response.ok().build();
	}
	
	//update payment modes details
	@PUT
	@Path(PAYMENT_MODE_PATH)
	public Response updatePaymentModes(@HeaderParam(USER_ID) final String userId, final PaymentMode paymentMode) throws RENIServiceException{
		
		validateInput(paymentMode);
		paymentMode.setUpdatedBy(userId);
		masterDataService.updatePaymentMode(paymentMode);
		return Response.ok().build();
	}
	
	//Create
	
	@POST
	@Path(AREA_PATH)
	public Response createAreaDetails(@HeaderParam(USER_ID) final String userId, final Area area) throws RENIServiceException{
		validateInput(area);
		area.setCreatedBy(userId);
		masterDataService.createArea(area);
		
		return Response.ok().build();
	}
	
	
	//create item details
	@POST
	@Path(ITEM_PATH)
	public Response createItemDetails(@HeaderParam(USER_ID) final String userId, final Item item) throws RENIServiceException{
		
		validateInput(item);
		item.setCreatedBy(userId);
		masterDataService.createItem(item);
		
		return Response.ok().build();
	}
	
	
	//create vendor details
	@POST
	@Path(VENDOR_PATH)
	public Response createVendorDetails(@HeaderParam(USER_ID) final String userId, final Vendor vendor) throws RENIServiceException{
		
		validateInput(vendor);
		vendor.setCreatedBy(userId);
		masterDataService.createVendor(vendor);
		
		return Response.ok().build();
	}
	
	
	//create expenseType details
	@POST
	@Path(EXPENSE_TYPE_PATH)
	public Response createExpenseTypeDetails(@HeaderParam(USER_ID) final String userId, final ExpenseType expenseType) throws RENIServiceException{
		
		validateInput(expenseType);
		expenseType.setCreatedBy(userId);
		masterDataService.createExpenseType(expenseType);
		
		return Response.ok().build();
	}
	
	//create payment modes details
	@POST
	@Path(PAYMENT_MODE_PATH)
	public Response createPaymentMode(@HeaderParam(USER_ID) final String userId, final PaymentMode paymentMode) throws RENIServiceException{
		
		validateInput(paymentMode);
		paymentMode.setCreatedBy(userId);
		masterDataService.createPaymentMode(paymentMode);
		return Response.ok().build();
	}
	
}
