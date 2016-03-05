package com.rnei.dao;

import java.time.LocalDate;
import java.util.List;

import com.rnei.model.Expense;
import com.rnei.service.exception.RENIDataServiceException;

public interface ExpenseDataService {

	List<Expense> fetchExpenseDetailsByDate(LocalDate expDate) throws RENIDataServiceException;

	void createExpense(String userId, Expense expenseInput) throws RENIDataServiceException;

}
