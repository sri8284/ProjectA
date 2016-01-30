package com.reni.controller;

import static com.reni.data.constants.RENIDataConstants.SESSION_ID;
import static com.reni.data.constants.RENIDataConstants.USER_ID;
import static com.reni.service.constants.RENIServiceConstant.INVALID_REQUEST;
import static com.reni.service.constants.RENIServiceConstant.PICKUP_PATH;
import static com.reni.service.constants.RENIServiceConstant.REFERENCE_PATH;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reni.service.MasterReferenceSerice;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIServiceException;
import com.reni.service.exception.RENIValidationException;

@Path(REFERENCE_PATH)
@Component
public class MasterDataController {

	@Autowired
	MasterReferenceSerice referenceService;
	
	@GET
	@Path(PICKUP_PATH)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateUser(@HeaderParam(USER_ID) final Integer userId, @HeaderParam(SESSION_ID) final String sessionId) throws RENIServiceException{
		
		if(sessionId==null||userId==null){
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST,INVALID_REQUEST);
		}
		return Response.ok(referenceService.getPickupReferenceDetails(userId,sessionId)).build();
	}
}
