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

import java.time.LocalDate;

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
	@Path("/{"+DATE+"}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response  fetchPickupReportByDate(@PathParam(DATE) final LocalDate pickupDate) throws RENIServiceException{
		return Response.ok(service.fetchPickupReportByDate(pickupDate)).build();
	}
	
	
	@GET
	@Path(STATUS+"/{"+DATE+"}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchPickupsStatus(@PathParam(DATE) final LocalDate pickupDate) throws RENIServiceException{
		return Response.ok(service.fetchPickupsStatus(pickupDate)).build();
	}
	
	@GET
	@Path(STATUS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchCompletePickupsStatusDetails() throws RENIServiceException{
		//Which should have pickupId, date, areaCode, areaName should sent.
		return Response.ok(service.fetchCompletePickupsStatusDetails()).build();
	}
	
	@GET
	@Path("/{"+PICKUP_ID_PATH+"}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchPickupDetailsById(@PathParam(PICKUP_ID_PATH) final String pickupId) throws RENIServiceException{
		return Response.ok(service.fetchPickupDetailsById(pickupId)).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPickup(@HeaderParam(USER_ID) final Integer userId, final Pickup pickupInput) throws RENIServiceException{
		
		service.createPickup(userId, pickupInput);
		
		return Response.ok().build();
	}
	
}
