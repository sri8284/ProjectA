package com.rnei.service.exception;

import static com.rnei.service.constants.RENIServiceConstant.SERVER_ERROR;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.rnei.model.ErrorResponse;

@Component
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception arg0) {
		arg0.printStackTrace();
		final ErrorResponse errorResponse= new ErrorResponse();
		
		errorResponse.setErrorMessge(SERVER_ERROR);
		errorResponse.setStatus(Boolean.FALSE);
		
		return Response.status(Status.EXPECTATION_FAILED).entity(errorResponse).build();
	}
}
