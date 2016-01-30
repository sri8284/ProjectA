package com.reni.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.reni.data.constants.RENIDataConstants.*;
import static com.reni.service.constants.RENIServiceConstant.INVALID_REQUEST;
import static com.reni.service.constants.RENIServiceConstant.PICKUP_ID_PATH;
import static com.reni.service.constants.RENIServiceConstant.*;

import com.reni.model.Pickup;
import com.reni.service.PickupService;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIServiceException;
import com.reni.service.exception.RENIValidationException;

@Path(PICKUP_PATH)
@Component
public class PickupController {

	@Autowired
	PickupService service;
	
	@GET
	@Path("/{"+PICKUP_ID_PATH+"}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPickupById(@PathParam(PICKUP_ID_PATH) final String pickupId) throws RENIServiceException{
		return Response.ok(service.getPickupById(pickupId)).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listOfPickups() throws RENIServiceException{
		return Response.ok(service.getPickupDetails()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPickup(final Pickup pickupDetails,@HeaderParam(SESSION_ID) final String sessionId,@HeaderParam(USER_ID) final Integer userId) throws RENIServiceException{
		if(pickupDetails==null || sessionId==null || userId==null){
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST,INVALID_REQUEST);
		}
		service.createPickup(userId, pickupDetails,sessionId);
		return Response.ok().build();
	}
	
}
