package com.reni.service.impl;

import static com.reni.common.util.CommonUtil.isNullOrEmpty;
import static com.reni.data.constants.RENIDataConstants.SESSION_TIME;
import static com.reni.service.constants.RENIServiceConstant.DATA_REMOVE_ERROR;
import static com.reni.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;
import static com.reni.service.constants.RENIServiceConstant.DUPLICATE_SESSION;
import static com.reni.service.constants.RENIServiceConstant.INVALID_REQUEST;
import static com.reni.service.constants.RENIServiceConstant.INVALID_USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.EmployeeDataService;
import com.reni.dao.UserDataService;
import com.reni.model.Employee;
import com.reni.model.Login;
import com.reni.service.UserService;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;
import com.reni.service.exception.RENIValidationException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	EmployeeDataService employeeDataService;
	@Autowired
	UserDataService userDataService;

	@Override
	public Employee getBasicEmployeeInfo(Login loginDetails) throws RENIServiceException {

		if (loginDetails.getUserId() == 0) {
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST, INVALID_REQUEST);
		}
		if (isNullOrEmpty(loginDetails.getiMEINumber(), loginDetails.getPassword())) {
			throw new RENIValidationException(RENIErrorCodes.INVALID_REQUEST, INVALID_REQUEST);
		}
		try {
			if (!userDataService.isValidUser(loginDetails.getUserId(), loginDetails.getPassword())) {
				throw new RENIServiceException(RENIErrorCodes.INVALID_USER, INVALID_USER);
			}
			if (userDataService.isUserSessionExist(loginDetails.getUserId())) {
				throw new RENIServiceException(RENIErrorCodes.DUPLICATE_SESSION, DUPLICATE_SESSION);
			}

			Employee employee = employeeDataService.getBasicEmployeeInfo(loginDetails.getUserId());
			final String sessionId = userDataService.createSession(loginDetails.getUserId());
			employee.setSessionId(sessionId);
			employee.setSessionExpire(SESSION_TIME);
			return employee;
		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}
	}

	@Override
	public void deleteSession(Integer userId) throws RENIServiceException {
		try {
			userDataService.deleteSession(userId);
		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_REMOVE_ERROR, DATA_REMOVE_ERROR);

		}
	}

}
