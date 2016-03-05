package com.rnei.controller;

import static com.rnei.service.constants.RENIDataConstants.USER_ID;
import static com.rnei.service.constants.RENIServiceConstant.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rnei.model.Notes;
import com.rnei.service.NotesSerice;
import com.rnei.service.exception.RENIServiceException;

@Path(NOTES_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class NotesController extends CoreController{

	static final Logger logger = Logger.getLogger(NotesController.class); // register class with logger  

	@Autowired
	NotesSerice notesService;
	
	@POST
	public Response fetchNotes(@HeaderParam(USER_ID) final Integer userId, Notes notes) throws RENIServiceException{
		validateInput(userId,notes);
		return Response.ok(notesService.fetchNotes(notes)).build();
	}
	
	@POST
	@Path(CREATE)
	public Response createNotes(@HeaderParam(USER_ID) final Integer userId, Notes notes) throws RENIServiceException{
		validateInput(userId,notes);
		
		notesService.createNotes(userId, notes);
		
		return Response.ok().build();
	}
}
