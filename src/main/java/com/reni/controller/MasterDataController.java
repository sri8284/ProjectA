package com.reni.controller;

import static com.reni.data.constants.RENIDataConstants.*;
import static com.reni.service.constants.RENIServiceConstant.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reni.service.MasterDataSerice;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIServiceException;
import com.reni.service.exception.RENIValidationException;

@Path(MASTER_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class MasterDataController {

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
	
	private void validateInput(Integer userId) throws RENIValidationException {
		if(userId==null){
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST,INVALID_REQUEST);
		}		
	}
	
}
