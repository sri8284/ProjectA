package com.reni.service.impl;

import static com.reni.common.util.CommonUtil.isNullOrEmpty;
import static com.reni.service.constants.RENIServiceConstant.BILLNO_MANDATORY;
import static com.reni.service.constants.RENIServiceConstant.DATA_FECTH_ERROR;
import static com.reni.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;
import static com.reni.service.constants.RENIServiceConstant.EXPENSE_DATE_MANDATORY;
import static com.reni.service.constants.RENIServiceConstant.EXP_TYPE_PAYMENT_CODE_MANDATORY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.ExpenseDataService;
import com.reni.model.Expense;
import com.reni.service.ExpenseService;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ExpenseServiceImpl implements ExpenseService {

	
	@Autowired
	ExpenseDataService expenseDataService;
	
	@Override
	public List<Expense> fetchExpenseDetailsByDate(String expenseDate) throws RENIServiceException {
		
		try {
			//TODO - convert string to date.
			LocalDate expDate=LocalDate.parse(expenseDate,DateTimeFormatter.ISO_LOCAL_DATE);
			
			return expenseDataService.fetchExpenseDetailsByDate(expDate);

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
		
	}

	@Override
	public void createExpense(String userId, Expense expenseInput) throws RENIServiceException {
		try {
			
			validateExpMandatoryFields(expenseInput);
			
			expenseDataService.createExpense(userId,expenseInput);

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}
		
	}

	private void validateExpMandatoryFields(Expense expenseInput) throws RENIServiceException {

		if(isNullOrEmpty(expenseInput.getBillNo())){
			throw new RENIServiceException(RENIErrorCodes.BILLNO_MANDATORY, BILLNO_MANDATORY);
		}
		
		if(expenseInput.getExpenseDate()==null){
			throw new RENIServiceException(RENIErrorCodes.EXPENSE_DATE_MANDATORY, EXPENSE_DATE_MANDATORY);

		}
		
		if(expenseInput.getExpenseType()==null || expenseInput.getPaymentMode()==null){
			throw new RENIServiceException(RENIErrorCodes.EXP_TYPE_PAYMENT_CODE_MANDATORY, EXP_TYPE_PAYMENT_CODE_MANDATORY);

		}
		
		if(isNullOrEmpty(expenseInput.getExpenseType().getExpType(),expenseInput.getExpenseType().getExpSubType(), expenseInput.getPaymentMode().getPaymentCode())){
			throw new RENIServiceException(RENIErrorCodes.EXP_TYPE_PAYMENT_CODE_MANDATORY, EXP_TYPE_PAYMENT_CODE_MANDATORY);
		}
	}


}
