package com.rnei.service.impl;

import static com.rnei.common.util.CommonUtil.isNullOrEmpty;
import static com.rnei.service.constants.RENIDataConstants.SESSION_TIME;
import static com.rnei.service.constants.RENIServiceConstant.DUPLICATE_SESSION;
import static com.rnei.service.constants.RENIServiceConstant.INVALID_REQUEST;
import static com.rnei.service.constants.RENIServiceConstant.INVALID_USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.EmployeeDataService;
import com.rnei.dao.UserDataService;
import com.rnei.model.Employee;
import com.rnei.model.Login;
import com.rnei.service.UserService;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

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
			throw new RENIValidationException(INVALID_REQUEST);
		}
		if (isNullOrEmpty(loginDetails.getiMEINumber(), loginDetails.getPassword())) {
			throw new RENIValidationException(INVALID_REQUEST);
		}
		if (!userDataService.isValidUser(loginDetails.getUserId(), loginDetails.getPassword())) {
			throw new RENIValidationException(INVALID_USER);
		}
		if (userDataService.isUserSessionExist(loginDetails.getUserId())) {
			throw new RENIValidationException(DUPLICATE_SESSION);
		}

		Employee employee = employeeDataService.getBasicEmployeeInfo(loginDetails.getUserId());
		final String sessionId = userDataService.createSession(loginDetails.getUserId());
		employee.setSessionId(sessionId);
		employee.setSessionExpire(SESSION_TIME);
		return employee;
	}

	@Override
	public void deleteSession(Integer userId) throws RENIServiceException {
		userDataService.deleteSession(userId);
	}

}
