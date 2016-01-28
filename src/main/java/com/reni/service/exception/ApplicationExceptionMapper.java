package com.reni.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.logging.Logger;
import org.springframework.stereotype.Component;

import com.reni.model.ErrorResponse;
import static com.reni.service.constants.RENIErrorCodes.SERVER_ERROR_CODE;
import static com.reni.service.constants.ReniServiceConstant.SERVER_ERROR;

@Component
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<RuntimeException> {

	Logger log = Logger.getLogger(ApplicationExceptionMapper.class);
	@Override
	public Response toResponse(RuntimeException arg0) {
		log.info(arg0.getMessage());
		
		final ErrorResponse errorResponse= new ErrorResponse();
		errorResponse.setErrorCode(SERVER_ERROR_CODE.toString());
		errorResponse.setErrorMessge(SERVER_ERROR);
		errorResponse.setStatus(Boolean.FALSE);
		return Response.status(Status.EXPECTATION_FAILED).entity(errorResponse).build();
	}
}
