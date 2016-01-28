package com.reni.service.impl;

import static com.reni.common.util.CommonUtil.isNullOrEmpty;
import static com.reni.service.constants.ReniServiceConstant.DATA_SAVE_ERROR;
import static com.reni.service.constants.ReniServiceConstant.DUPLICATE_SESSION;
import static com.reni.service.constants.ReniServiceConstant.INVALID_REQUEST;
import static com.reni.service.constants.ReniServiceConstant.INVALID_USER;
import static com.reni.data.constants.RENIDataConstants.SESSION_TIME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.EmployeeDataService;
import com.reni.dao.LoginDataService;
import com.reni.model.Employee;
import com.reni.model.Login;
import com.reni.service.LoginService;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;
import com.reni.service.exception.RENIValidationException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LoginServiceImpl implements LoginService {

	@Autowired
	EmployeeDataService employeeDataService;
	@Autowired
	LoginDataService loginDataService;

	@Override
	public Employee getBasicEmployeeInfo(Login loginDetails) throws RENIServiceException {

		if (loginDetails.getUserId() == 0) {
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST, INVALID_REQUEST);
		}
		if (isNullOrEmpty(loginDetails.getiMEINumber(), loginDetails.getPassword())) {
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST, INVALID_REQUEST);
		}
		try {
			if (!loginDataService.isValidUser(loginDetails.getUserId(), loginDetails.getPassword())) {
				throw new RENIServiceException(RENIErrorCodes.INVALID_USER, INVALID_USER);
			}
			if (loginDataService.isUserSessionExist(loginDetails.getUserId())) {
				throw new RENIServiceException(RENIErrorCodes.DUPLICATE_SESSION, DUPLICATE_SESSION);
			}

			Employee employee = employeeDataService.getBasicEmployeeInfo(loginDetails.getUserId());
			final String sessionId = loginDataService.createSession(loginDetails.getUserId());
			employee.setSessionId(sessionId);
			employee.setSessionExpire(SESSION_TIME);
			return employee;
		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}
	}

}
