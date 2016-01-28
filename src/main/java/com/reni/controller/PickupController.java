package com.reni.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.reni.service.constants.ReniServiceConstant.PICKUP_ID_PATH;
import static com.reni.service.constants.ReniServiceConstant.PICKUP_PATH;

import com.reni.model.Pickup;
import com.reni.service.PickupService;
import com.reni.service.exception.RENIServiceException;

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
		System.out.println("controller id");
		return Response.ok(service.getPickupById(pickupId)).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listOfPickups() throws RENIServiceException{
		System.out.println("controller");
		return Response.ok(service.getPickupDetails()).build();
	}
	
	@POST
	public Response createPickup(final Pickup pickupDetails) throws RENIServiceException{
		
		//TODO -validate basic input.
		service.createPickup(pickupDetails);
		return Response.ok().build();
	}
}
