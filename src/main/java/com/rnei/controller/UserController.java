package com.rnei.controller;

import static com.rnei.service.constants.RENIServiceConstant.INVALID_REQUEST;
import static com.rnei.service.constants.RENIServiceConstant.LOGIN_PATH;
import static com.rnei.service.constants.RENIServiceConstant.LOGOUT_PATH;
import static com.rnei.service.constants.RENIServiceConstant.USER_PATH;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rnei.model.Employee;
import com.rnei.model.Login;
import com.rnei.service.UserService;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

@Path(USER_PATH)
@Component
public class UserController {

	@Autowired
	UserService loginService;
	
	@POST
	@Path(LOGIN_PATH)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateUser(final Login loginDetails) throws RENIServiceException{
		
		if(loginDetails==null){
			throw new RENIValidationException(INVALID_REQUEST);
		}
		Employee employee = loginService.getBasicEmployeeInfo(loginDetails);
		
		return Response.ok(employee).build();
	}
	
	@DELETE
	@Path(LOGOUT_PATH)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateUser(final Integer userId) throws RENIServiceException{
		if(userId==null){
			throw new RENIValidationException(INVALID_REQUEST);
		}
		loginService.deleteSession(userId);
		return Response.ok(Status.OK).build();
	}
	
	
}
