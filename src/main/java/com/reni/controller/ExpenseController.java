package com.reni.controller;

import static com.reni.service.constants.RENIServiceConstant.*;
import static com.reni.data.constants.RENIDataConstants.USER_ID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reni.model.Expense;
import com.reni.service.ExpenseService;
import com.reni.service.exception.RENIServiceException;

@Path(EXPENSE)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ExpenseController extends CoreController {

	
	@Autowired
	ExpenseService expenseService;
	
	@GET
	@Path("/{date}")
	public Response fetchExpenseDetailsByDate(@PathParam("date") String expenseDate) throws RENIServiceException{
		
		validateInput(expenseDate);
		
		return Response.ok(expenseService.fetchExpenseDetailsByDate(expenseDate)).build();
	}
	
	@POST
	public Response createExpense(@HeaderParam(USER_ID) String userId, Expense expenseInput) throws RENIServiceException{

		validateInput(userId, expenseInput);
		
		expenseService.createExpense(userId, expenseInput);
		
		return Response.ok().build();
	}
}
