package com.rnei.service;

import java.util.List;

import com.rnei.model.Expense;
import com.rnei.service.exception.RENIServiceException;

public interface ExpenseService {

	List<Expense> fetchExpenseDetailsByDate(String expenseDate) throws RENIServiceException;

	void createExpense(String userId, Expense expenseInput) throws RENIServiceException;

}
