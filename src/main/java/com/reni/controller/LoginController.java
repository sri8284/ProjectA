package com.reni.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.reni.service.constants.ReniServiceConstant.INVALID_REQUEST;
import com.reni.model.Employee;
import com.reni.model.Login;
import com.reni.service.LoginService;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;
import com.reni.service.exception.RENIValidationException;

@Path("/login")
@Component
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateUser(final Login loginDetails) throws RENIServiceException{
		
		if(loginDetails==null){
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST,INVALID_REQUEST);
		}
		Employee employee = loginService.getBasicEmployeeInfo(loginDetails);
		
		return Response.ok(employee).build();
	}
	
}
