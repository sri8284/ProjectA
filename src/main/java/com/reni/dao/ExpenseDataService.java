package com.reni.dao;

import java.time.LocalDate;
import java.util.List;

import com.reni.model.Expense;
import com.reni.service.exception.RENIDataServiceException;

public interface ExpenseDataService {

	List<Expense> fetchExpenseDetailsByDate(LocalDate expDate) throws RENIDataServiceException;

	void createExpense(String userId, Expense expenseInput) throws RENIDataServiceException;

}
