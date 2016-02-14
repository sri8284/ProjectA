package com.reni.dao.impl;

import static com.reni.common.util.CommonUtil.currentTimeStamp;
import static com.reni.data.constants.RENIDataConstants.BILLNO;
import static com.reni.data.constants.RENIDataConstants.CREATED_BY;
import static com.reni.data.constants.RENIDataConstants.CREATED_DATE;
import static com.reni.data.constants.RENIDataConstants.EXP_AMOUNT;
import static com.reni.data.constants.RENIDataConstants.EXP_DATE;
import static com.reni.data.constants.RENIDataConstants.EXP_SUBTYPE;
import static com.reni.data.constants.RENIDataConstants.EXP_TYPE;
import static com.reni.data.constants.RENIDataConstants.LOCATION;
import static com.reni.data.constants.RENIDataConstants.MERCHANT_NAME;
import static com.reni.data.constants.RENIDataConstants.PAYMENT_CODE;
import static com.reni.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.ExpenseDataService;
import com.reni.model.Expense;
import com.reni.rowmapper.ExpenseRowMapper;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ExpenseDataServiceImpl implements ExpenseDataService {

	private static final String INSERT_EXPENSE = "INSERT INTO EXPENSE (BILLNO,EXP_AMOUNT,EXP_DATE,LOCATION,MERCHANT_NAME,EXP_TYPE,EXP_SUBTYPE,PAYMENT_CODE,CREATED_DATE,CREATED_BY)"
			+ "VALUES (:BILLNO,:EXP_AMOUNT,:EXP_DATE,:LOCATION,:MERCHANT_NAME,:EXP_TYPE,:EXP_SUBTYPE,:PAYMENT_CODE,:CREATED_DATE,:CREATED_BY)";;

	private static final String SELECT_EXPENSE_BY_DATE = "select e.EXP_TYPE, e.EXP_TYPE_NAME,e.EXP_SUBTYPE, e.EXP_SUBTYPE_NAME,ex.BILLNO,ex.EXP_AMOUNT, ex.EXP_DATE,ex.LOCATION,ex.MERCHANT_NAME, p.PAYMENT_CODE,p.PAYMENT_NAME "
			+ "from expense ex "
			+ "left join expense_type e on e.exp_type=ex.exp_type and e.EXP_SUBTYPE=ex.EXP_SUBTYPE "
			+ "left join payment_mode p on p.PAYMENT_CODE=ex.PAYMENT_CODE "
			+ "where EXP_DATE=:EXP_DATE ";

			
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void createExpense(String userId,Expense expenseInput) throws RENIDataServiceException {
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			
			namedParameters.put(BILLNO, expenseInput.getBillNo());
			namedParameters.put(EXP_AMOUNT, expenseInput.getExpenseAmount());
			namedParameters.put(EXP_DATE, expenseInput.getExpenseDate());
			namedParameters.put(LOCATION, expenseInput.getLocation());
			namedParameters.put(MERCHANT_NAME, expenseInput.getMerchantName());
			namedParameters.put(EXP_TYPE, expenseInput.getExpenseType().getExpType());
			namedParameters.put(EXP_SUBTYPE, expenseInput.getExpenseType().getExpSubType());
			namedParameters.put(PAYMENT_CODE, expenseInput.getPaymentMode().getPaymentCode());
			namedParameters.put(CREATED_DATE, currentTimeStamp());
			namedParameters.put(CREATED_BY, userId);
		
			namedParameterJdbcTemplate.update(INSERT_EXPENSE, namedParameters);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}
	}

	@Override
	public List<Expense> fetchExpenseDetailsByDate(LocalDate expDate) throws RENIDataServiceException {
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		
		namedParameters.put(EXP_DATE, java.sql.Date.valueOf(expDate));
		
		return (List<Expense>)namedParameterJdbcTemplate.query(SELECT_EXPENSE_BY_DATE, namedParameters,
				new ExpenseRowMapper());
		
	}

	
}
