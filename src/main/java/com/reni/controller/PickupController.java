package com.reni.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

	// it will fetch pickups status
	@GET
	@Path(STATUS + "/{" + DATE + "}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchPickupStatus(@PathParam(DATE) final String pickupDate) throws RENIServiceException {
		return Response.ok(service.fetchPickupStatus(pickupDate)).build();
	}

	// It will fetch pickup reports by date.
	@GET
	@Path(REPORT + "/{" + DATE + "}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchPickupsReport(@PathParam(DATE) final String pickupDate) throws RENIServiceException {
		return Response.ok(service.fetchPickupsReport(pickupDate)).build();
	}

	// it will fetch pickup details.
	@GET
	@Path("/{" + PICKUP_ID_PATH + "}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchPickupDetailsById(@PathParam(PICKUP_ID_PATH) final String pickupId)
			throws RENIServiceException {
		return Response.ok(service.fetchPickupDetailsById(pickupId)).build();
	}

	// it will fetch report details of pickup id and assignment status of the
	// pickup.
	@GET
	@Path(ASSIGNMENT_STATUS + "/{" + DATE + "}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchPickupsAssignmentStatus(@PathParam(DATE) final String pickupDate) throws RENIServiceException {
		return Response.ok(service.fetchPickupsAssignmentStatus(pickupDate)).build();
	}

	// it will create the pickup
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPickup(@HeaderParam(USER_ID) final Integer userId, final Pickup pickupInput)
			throws RENIServiceException {

		service.createPickup(userId, pickupInput);

		return Response.ok().build();
	}

	// it will create the pickup
	@PUT
	@Path(CLOSE+ "/{" + PICKUP_ID_PATH + "}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response closePickup(@HeaderParam(USER_ID) final Integer userId, @PathParam(PICKUP_ID_PATH) final String pickupId, final Pickup pickupInput)
			throws RENIServiceException {

		service.closePickup(userId, pickupId, pickupInput);

		return Response.ok().build();
	}
}
