package com.rnei.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.logging.Logger;
import org.springframework.stereotype.Component;

@Component
@Provider
public class RENIExceptionMapper implements ExceptionMapper<RENIServiceException> {

	Logger log = Logger.getLogger(RENIExceptionMapper.class);
	
	@Override
	public Response toResponse(RENIServiceException errorResp) {
		log.info(errorResp.getMessage());
		return Response.status(Status.EXPECTATION_FAILED).entity(errorResp.getErrorResponse()).build();
	}

}
