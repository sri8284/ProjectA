package com.reni.service;

import java.util.List;

import com.reni.model.Expense;
import com.reni.service.exception.RENIServiceException;

public interface ExpenseService {

	List<Expense> fetchExpenseDetailsByDate(String expenseDate) throws RENIServiceException;

	void createExpense(String userId, Expense expenseInput) throws RENIServiceException;

}
