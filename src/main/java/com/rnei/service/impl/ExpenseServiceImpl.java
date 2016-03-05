package com.rnei.service.impl;

import static com.rnei.common.util.CommonUtil.isNullOrEmpty;
import static com.rnei.service.constants.RENIServiceConstant.BILLNO_MANDATORY;
import static com.rnei.service.constants.RENIServiceConstant.EXPENSE_DATE_MANDATORY;
import static com.rnei.service.constants.RENIServiceConstant.EXP_TYPE_PAYMENT_CODE_MANDATORY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.ExpenseDataService;
import com.rnei.model.Expense;
import com.rnei.service.ExpenseService;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpenseServiceImpl implements ExpenseService {

	
	@Autowired
	ExpenseDataService expenseDataService;
	
	@Override
	public List<Expense> fetchExpenseDetailsByDate(String expenseDate) throws RENIServiceException {
			LocalDate expDate=LocalDate.parse(expenseDate,DateTimeFormatter.ISO_LOCAL_DATE);
			
			return expenseDataService.fetchExpenseDetailsByDate(expDate);
	}

	@Override
	public void createExpense(String userId, Expense expenseInput) throws RENIServiceException {
			validateExpMandatoryFields(expenseInput);
			
			expenseDataService.createExpense(userId,expenseInput);

	}

	private void validateExpMandatoryFields(Expense expenseInput) throws RENIServiceException {

		if(isNullOrEmpty(expenseInput.getBillNo())){
			throw new RENIValidationException(BILLNO_MANDATORY);
		}
		
		if(expenseInput.getExpenseDate()==null){
			throw new RENIValidationException(EXPENSE_DATE_MANDATORY);
		}
		
		if(expenseInput.getExpenseType()==null || expenseInput.getPaymentMode()==null){
			throw new RENIValidationException(EXP_TYPE_PAYMENT_CODE_MANDATORY);
		}
		
		if(isNullOrEmpty(expenseInput.getExpenseType().getExpType(),expenseInput.getExpenseType().getExpSubType(), expenseInput.getPaymentMode().getPaymentCode())){
			throw new RENIValidationException(EXP_TYPE_PAYMENT_CODE_MANDATORY);
		}
	}


}
