package com.reni.controller;

import static com.reni.service.constants.RENIServiceConstant.ACTIVE_PATH;
import static com.reni.service.constants.RENIServiceConstant.ONHIRE_PATH;
import static com.reni.service.constants.RENIServiceConstant.ORR_PATH;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reni.model.OnRoadResource;
import com.reni.service.ORRService;
import com.reni.service.exception.RENIServiceException;

@Path(ORR_PATH)
@Component
public class ORRController extends CoreController {

	@Autowired
	ORRService service;

	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchORRDetails() throws RENIServiceException{
		return Response.ok(service.fetchORRDetails()).build();
	}
	
	@GET
	@Path(ACTIVE_PATH)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchActiveORRDetails() throws RENIServiceException{
		return Response.ok(service.fetchActiveORRDetails()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createORR(OnRoadResource onRoadResource) throws RENIServiceException{

		validateInput(onRoadResource);
		
		service.updateORR(onRoadResource);
		
		return Response.ok().build();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateORR(OnRoadResource onRoadResource) throws RENIServiceException{

		validateInput(onRoadResource);

		service.updateORR(onRoadResource);
		
		return Response.ok().build();
	}
	
	
	@GET
	@Path(ONHIRE_PATH)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchOnHireORRDetails() throws RENIServiceException{
		return Response.ok(service.fetchOnHireORRDetails()).build();
	}
	
	@GET
	@Path(ONHIRE_PATH+ACTIVE_PATH)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchActiveOnHireORRDetails() throws RENIServiceException{
		return Response.ok(service.fetchActiveOnHireORRDetails()).build();
	}
	
	@POST
	@Path(ONHIRE_PATH)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createOnHireORR(OnRoadResource onRoadResource) throws RENIServiceException{

		validateInput(onRoadResource);
		
		service.createOnHireORR(onRoadResource);
		
		return Response.ok().build();
	}
	
	@PUT
	@Path(ONHIRE_PATH)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOnHireORR(OnRoadResource onRoadResource) throws RENIServiceException{
		
		validateInput(onRoadResource);
		
		service.updateOnHireORR(onRoadResource);
		
		return Response.ok().build();
	}
	
	
}
