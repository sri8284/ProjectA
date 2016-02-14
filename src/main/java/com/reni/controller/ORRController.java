package com.reni.controller;

import static com.reni.service.constants.RENIServiceConstant.*;
import static com.reni.data.constants.RENIDataConstants.USER_ID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ORRController extends CoreController {

	@Autowired
	ORRService service;

	
	@GET
	public Response fetchORRDetails() throws RENIServiceException{
		return Response.ok(service.fetchORRDetails()).build();
	}
	
	@GET
	@Path(AVALIABLE)
	public Response fetchActiveORRDetails() throws RENIServiceException{
		return Response.ok(service.fetchActiveORRDetails()).build();
	}
	
	@POST
	public Response createORR(OnRoadResource onRoadResource) throws RENIServiceException{

		validateInput(onRoadResource);
		
		service.createORR(onRoadResource);
		
		return Response.ok().build();
	}
	
	
	@PUT
	public Response updateORR(OnRoadResource onRoadResource) throws RENIServiceException{

		validateInput(onRoadResource);

		service.updateORR(onRoadResource);
		
		return Response.ok().build();
	}
	
	
	@GET
	@Path(ONHIRE_PATH)
	public Response fetchOnHireORRDetails() throws RENIServiceException{
		return Response.ok(service.fetchOnHireORRDetails()).build();
	}
	
	@GET
	@Path(ONHIRE_PATH+AVALIABLE)
	public Response fetchActiveOnHireORRDetails() throws RENIServiceException{
		return Response.ok(service.fetchActiveOnHireORRDetails()).build();
	}
	
	@POST
	@Path(ONHIRE_PATH)
	public Response createOnHireORR(@HeaderParam(USER_ID) String userId,OnRoadResource onRoadResource) throws RENIServiceException{
		validateInput(onRoadResource);
		
		service.createOnHireORR(userId,onRoadResource);
		
		return Response.ok().build();
		
	}
	
	@PUT
	@Path(ONHIRE_PATH)
	public Response updateOnHireORR(OnRoadResource onRoadResource) throws RENIServiceException{
		
		validateInput(onRoadResource);
		
		service.updateOnHireORR(onRoadResource);
		
		return Response.ok().build();
	}
	
	@GET
	@Path(REPORT)
	public Response fetchORRReportDetails(String orrId, String reqDate) throws RENIServiceException{
		
		validateInput(orrId,reqDate);
		
		return Response.ok(service.fetchORRReportDetails(orrId,reqDate)).build();
	}
	
	@GET
	@Path(ONHIRE_PATH+REPORT)
	public Response fetchOnHireORRReportDetails(String orrId, String reqDate) throws RENIServiceException{
		
		validateInput(orrId,reqDate);
		
		return Response.ok(service.fetchOnHireORRReportDetails(orrId,reqDate)).build();
	}
	
}
